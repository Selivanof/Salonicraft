package com.oktavios.salonicraft.screen.renderer;

import com.google.common.base.Preconditions;
import com.mojang.blaze3d.systems.RenderSystem;
import com.oktavios.salonicraft.util.FluidUtils;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.Registries;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Pair;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

// Original File: https://github.com/mezz/JustEnoughItems by mezz (Forge Version)
public class FluidRenderer implements IIngredientRenderer<Pair<FluidVariant, Long>> {
    private static final NumberFormat nf = NumberFormat.getIntegerInstance();
    public final long capacityMb;
    private final TooltipMode tooltipMode;
    private final int width;
    private final int height;

    enum TooltipMode {
        SHOW_AMOUNT,
        SHOW_AMOUNT_AND_CAPACITY,
        ITEM_LIST
    }

    public FluidRenderer() {
        this(FluidUtils.convertToMilibuckets(FluidConstants.BUCKET), TooltipMode.SHOW_AMOUNT_AND_CAPACITY, 16, 16);
    }

    public FluidRenderer(long capacityMb, boolean showCapacity, int width, int height) {
        this(capacityMb, showCapacity ? TooltipMode.SHOW_AMOUNT_AND_CAPACITY : TooltipMode.SHOW_AMOUNT, width, height);
    }

    @Deprecated
    public FluidRenderer(int capacityMb, boolean showCapacity, int width, int height) {
        this(capacityMb, showCapacity ? TooltipMode.SHOW_AMOUNT_AND_CAPACITY : TooltipMode.SHOW_AMOUNT, width, height);
    }

    private FluidRenderer(long capacityMb, TooltipMode tooltipMode, int width, int height) {
        Preconditions.checkArgument(capacityMb > 0, "capacity must be > 0");
        Preconditions.checkArgument(width > 0, "width must be > 0");
        Preconditions.checkArgument(height > 0, "height must be > 0");
        this.capacityMb = capacityMb;
        this.tooltipMode = tooltipMode;
        this.width = width;
        this.height = height;
    }

    public void drawFluid(DrawContext context, FluidVariant fluidVariant, long fluidAmount, int x, int y, int width, int height, long maxCapacity) {
        if (fluidVariant.getFluid() == Fluids.EMPTY) {
            return;
        }
        RenderSystem.setShaderTexture(0, PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
        y += height;
        final Sprite sprite = FluidVariantRendering.getSprite(fluidVariant);
        int color = FluidVariantRendering.getColor(fluidVariant);

        final int drawHeight = (int) (fluidAmount / (maxCapacity * 1F) * height);
        final int iconHeight = sprite.getY();
        int offsetHeight = drawHeight;

        RenderSystem.setShaderColor((color >> 16 & 255) / 255.0F, (float) (color >> 8 & 255) / 255.0F, (float) (color & 255) / 255.0F, 1F);

        int iteration = 0;
        while (offsetHeight != 0) {
            final int curHeight = Math.min(offsetHeight, iconHeight);
            context.drawSprite(x, y - offsetHeight, 0, width, curHeight, sprite);
            offsetHeight -= curHeight;
            iteration++;
            if (iteration > 50) {
                break;
            }
        }
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);

        RenderSystem.setShaderTexture(0, FluidRenderHandlerRegistry.INSTANCE.get(fluidVariant.getFluid())
                .getFluidSprites(MinecraftClient.getInstance().world, null, fluidVariant.getFluid().getDefaultState())[0].getAtlasId());
    }

    @Override
    public List<Text> getTooltip(Pair<FluidVariant, Long> ingredient, TooltipContext tooltipFlag) {
        List<Text> tooltip = new ArrayList<>();
        FluidVariant fluidType = ingredient.getLeft();
        if (fluidType == null) {
            return tooltip;
        }

        MutableText displayName = Text.translatable("block." + Registries.FLUID.getId(ingredient.getLeft().getFluid()).toTranslationKey());
        tooltip.add(displayName);

        if (tooltipMode == TooltipMode.SHOW_AMOUNT_AND_CAPACITY) {
            MutableText amountString = Text.translatable("salonicraft.tooltip.fluid_amount_with_capacity", nf.format(ingredient.getRight()), nf.format(capacityMb));
            tooltip.add(amountString.fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        } else if (tooltipMode == TooltipMode.SHOW_AMOUNT) {
            MutableText amountString = Text.translatable("salonicraft.tooltip_fluid_amount", nf.format(ingredient.getRight()));
            tooltip.add(amountString.fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }

        return tooltip;
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
