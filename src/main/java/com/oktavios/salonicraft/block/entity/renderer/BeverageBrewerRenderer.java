package com.oktavios.salonicraft.block.entity.renderer;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.block.entity.BeverageBrewerBlockEntity;
import com.oktavios.salonicraft.block.entity.PowderStationBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;


public class BeverageBrewerRenderer implements BlockEntityRenderer<BeverageBrewerBlockEntity> {
    public BeverageBrewerRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(BeverageBrewerBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState blockState = MinecraftClient.getInstance().world.getBlockState(entity.getPos());
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getRenderStack();
        matrices.push();

        matrices.translate(getXFromBlockState(blockState), 0.22f, getZFromBlockState(blockState));
        matrices.scale(0.36f, 0.36f, 0.36f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(getRotationFromBlockState(blockState)));

        itemRenderer.renderItem(stack, ModelTransformationMode.GUI, getLightLevel(entity.getWorld(),
                entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

    private int getRotationFromBlockState(BlockState blockState) {
        if (blockState.isOf(SalonicraftBlocks.BEVERAGE_BREWER)) {
            switch (blockState.get(Properties.HORIZONTAL_FACING)) {
                case NORTH -> {return 0;}
                case EAST -> {return 90;}
                case SOUTH -> {return 180;}
                case WEST -> {return 270;}
                default -> {return 0;}
            }
        } else {
            return 0;
        }
    }

    private float getXFromBlockState(BlockState blockState) {
        if (blockState.isOf(SalonicraftBlocks.BEVERAGE_BREWER)) {
            switch (blockState.get(Properties.HORIZONTAL_FACING)) {
                case NORTH -> {return 0.5f;}
                case EAST -> {return 0.75f;}
                case SOUTH -> {return 0.5f;}
                case WEST -> {return 0.25f;}
                default -> {return 0;}
            }
        } else {
            return 0;
        }
    }

    private float getZFromBlockState(BlockState blockState) {
        if (blockState.isOf(SalonicraftBlocks.BEVERAGE_BREWER)) {
            switch (blockState.get(Properties.HORIZONTAL_FACING)) {
                case NORTH -> {return 0.25f;}
                case EAST -> {return 0.5f;}
                case SOUTH -> {return 0.75f;}
                case WEST -> {return 0.5f;}
                default -> {return 0;}
            }
        } else {
            return 0;
        }
    }
}
