package com.oktavios.salonicraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.block.entity.BeverageBrewerBlockEntity;
import com.oktavios.salonicraft.screen.renderer.FluidRenderer;
import com.oktavios.salonicraft.util.SalonicraftMouseUtil;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.Optional;

public class BeverageBrewerScreen extends HandledScreen<BeverageBrewerScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(Salonicraft.MOD_ID, "textures/gui/beverage_brewer_gui.png");

    private FluidRenderer fluidRenderer;

    public BeverageBrewerScreen(BeverageBrewerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        titleY = 5;
        this.playerInventoryTitleX = 8;
        this.playerInventoryTitleY = this.backgroundHeight - 89;
        setFluidStorageRenderer();
    }

    private void setFluidStorageRenderer() {
        fluidRenderer = new FluidRenderer(BeverageBrewerBlockEntity.FLUID_CAPACITY, true, 15,61);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressIndicator(context, x, y);

        fluidRenderer.drawFluid(context, handler.getFluidVariant(), handler.getFluidAmount(),x+80,y+16,16,61, BeverageBrewerBlockEntity.FLUID_CAPACITY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        renderFluidTooltip(context, mouseX, mouseY, x, y, handler.getFluidVariant(), handler.getFluidAmount(), 80, 16, fluidRenderer);
    }
    private void renderFluidTooltip(DrawContext context, int mouseX, int mouseY, int x, int y,
                                    FluidVariant fluidVariant, long fluidAmount, int offsetX, int offsetY, FluidRenderer renderer) {
        if(isMouseAboveArea(mouseX, mouseY, x, y, offsetX, offsetY, renderer)) {
            context.drawTooltip(MinecraftClient.getInstance().textRenderer,  renderer.getTooltip(new Pair<FluidVariant, Long>(fluidVariant, fluidAmount), TooltipContext.Default.BASIC),
                    Optional.empty(), mouseX - x, mouseY - y);
        }
    }



    private void renderProgressIndicator(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 113, y + 35, 176, 0, 12, handler.getScaledProgress());
        }
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, FluidRenderer renderer) {
        return SalonicraftMouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return SalonicraftMouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }

}
