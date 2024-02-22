package com.oktavios.salonicraft.block.custom;

import com.mojang.serialization.MapCodec;
import com.oktavios.salonicraft.item.SalonicraftItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class CoffeaBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<CoffeaBlock> CODEC = CoffeaBlock.createCodec(CoffeaBlock::new);
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = Properties.AGE_7;
    private static final VoxelShape STAGE_0_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 6.0, 10.0);
    private static final VoxelShape STAGE_1_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 8.0, 11.0);
    private static final VoxelShape STAGE_2_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 11.0, 12.0);
    private static final VoxelShape STAGE_3_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 14.0, 13.0);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public MapCodec<CoffeaBlock> getCodec() {
        return CODEC;
    }

    public CoffeaBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AGE, 0));
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(SalonicraftItems.COFFEE_BEAN);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(AGE) == 0) {
            return STAGE_0_SHAPE;
        }
        if (state.get(AGE) == 1) {
            return STAGE_1_SHAPE;
        }
        if (state.get(AGE) == 2) {
            return STAGE_2_SHAPE;
        }
        if (state.get(AGE) == 3) {
            return STAGE_3_SHAPE;
        }
        if (state.get(AGE) < MAX_AGE) {
            return LARGE_SHAPE;
        }
        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = state.get(AGE);
        if (i < MAX_AGE && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9) {
            BlockState blockState = (BlockState)state.with(AGE, i + 1);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        }
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean bl;
        int i = state.get(AGE);
        boolean bl2 = bl = i == MAX_AGE;
        if (!bl && player.getStackInHand(hand).isOf(Items.BONE_MEAL)) {
            return ActionResult.PASS;
        }
        if (i > 5) {
            int j = 1 + world.random.nextInt(2);
            net.minecraft.block.SweetBerryBushBlock.dropStack(world, pos, new ItemStack(SalonicraftItems.COFFEE_BEAN, j + (2 * (bl ? 1 : 0))));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
            BlockState blockState = (BlockState)state.with(AGE, 4);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = Math.min(MAX_AGE, state.get(AGE) + 1);
        world.setBlockState(pos, (BlockState)state.with(AGE, i), Block.NOTIFY_LISTENERS);
    }
}
