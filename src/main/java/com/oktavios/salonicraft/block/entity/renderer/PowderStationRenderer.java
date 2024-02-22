package com.oktavios.salonicraft.block.entity.renderer;

import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.block.custom.PowderStationBlock;
import com.oktavios.salonicraft.block.entity.PowderStationBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;



public class PowderStationRenderer implements BlockEntityRenderer<PowderStationBlockEntity> {
    public PowderStationRenderer(BlockEntityRendererFactory.Context context) {

    }



    @Override
    public void render(PowderStationBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getRenderStack();
        matrices.push();
        matrices.translate(0.5f, 0.96f, 0.5f);
        matrices.scale(0.4f, 0.4f, 0.4f);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(getRotationFromBlockState(MinecraftClient.getInstance().world.getBlockState(entity.getPos()))));

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
        if (blockState.isOf(SalonicraftBlocks.POWDER_STATION)) {
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
}
