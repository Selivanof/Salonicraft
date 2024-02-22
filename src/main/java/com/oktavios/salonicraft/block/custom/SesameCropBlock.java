package com.oktavios.salonicraft.block.custom;

import com.oktavios.salonicraft.item.SalonicraftItems;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

// A mix of CropBlock and TallPlantBlock
// Needs to be rewritten for better readability
public class SesameCropBlock extends CropBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
    public static final int BOTTOM_BLOCK_MAX_AGE = 4;
    public static final int MAX_AGE = 5;

    private static final double[] AGE_HEIGHT = new double[]{
            2.0,
            6.0,
            10.0,
            14.0,
            16.0,
            16.0};
    public static final IntProperty AGE = IntProperty.of("age",0,5);
    public SesameCropBlock(Settings settings) {
        super(settings);
        setDefaultState(this.withAge(0).with(HALF, DoubleBlockHalf.LOWER));
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (!(direction.getAxis() != Direction.Axis.Y || doubleBlockHalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || neighborState.isOf(this) && neighborState.get(HALF) != doubleBlockHalf)) {
            return Blocks.AIR.getDefaultState();
        }
        if (doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0,0.0,0.0,16.0,AGE_HEIGHT[this.getAge(state)], 16.0);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        float f;
        int currentAge = this.getAge(state);
        if (world.getBaseLightLevel(pos, 0) >= 9 && currentAge < this.getMaxAge() && random.nextInt((int)(25.0f / (f = CropBlock.getAvailableMoisture(this, world, pos))) + 1) == 0) {
            if((currentAge == BOTTOM_BLOCK_MAX_AGE) && (world.getBlockState(pos).get(HALF) == DoubleBlockHalf.LOWER)  &&  world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
                world.setBlockState(pos.up(1), this.withAge(currentAge + 1).with(HALF,DoubleBlockHalf.UPPER), 2);
            } else if (currentAge < BOTTOM_BLOCK_MAX_AGE){
                world.setBlockState(pos, this.withAge(currentAge + 1), 2);
            }
        }
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state) + this.getGrowthAmount(world);
        int maxAge = this.getMaxAge();
        nextAge = Math.min(nextAge, maxAge);

        if(this.getAge(state) == BOTTOM_BLOCK_MAX_AGE && world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
            world.setBlockState(pos.up(1), this.withAge(nextAge).with(HALF,DoubleBlockHalf.UPPER), 2);
        } else {
            world.setBlockState(pos, this.withAge(nextAge - 1), 2);
        }
    }


    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockState = world.getBlockState(pos.down());
            return blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER && blockState.get(AGE) == BOTTOM_BLOCK_MAX_AGE;
        }
        return super.canPlaceAt(state, world, pos);
    }

    @Override
    public int getMaxAge() {

        return MAX_AGE;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return SalonicraftItems.SESAME_SEEDS;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(HALF);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getType() == EntityType.BEE) {
            return;
        }
        if (this.getAge(state) > BOTTOM_BLOCK_MAX_AGE) {
            entity.slowMovement(state, new Vec3d(0.8f, 0.75, 0.8f));
        }
    }
}
