package com.oktavios.salonicraft;

import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.block.entity.SalonicraftBlockEntities;
import com.oktavios.salonicraft.block.entity.renderer.BeverageBrewerRenderer;
import com.oktavios.salonicraft.block.entity.renderer.PowderStationRenderer;
import com.oktavios.salonicraft.entity.SalonicraftBoats;
import com.oktavios.salonicraft.networking.SalonicraftPackages;
import com.oktavios.salonicraft.screen.BeverageBrewerScreen;
import com.oktavios.salonicraft.screen.SalonicraftScreenHandlers;
import com.oktavios.salonicraft.screen.PowderStationScreen;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.SpriteIdentifier;

public class SalonicraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(SalonicraftBlocks.CINNAMON_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SalonicraftBlocks.CINNAMON_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SalonicraftBlocks.COFFEA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SalonicraftBlocks.SESAME_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SalonicraftBlocks.CINNAMON_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SalonicraftBlocks.CINNAMON_SAPLING, RenderLayer.getCutout());

        HandledScreens.register(SalonicraftScreenHandlers.POWDER_STATION_SCREEN_HANDLER, PowderStationScreen::new);
        HandledScreens.register(SalonicraftScreenHandlers.BEVERAGE_BREWER_SCREEN_HANDLER, BeverageBrewerScreen::new);

        SalonicraftPackages.registerS2CPackets();

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, SalonicraftBlocks.CINNAMON_SIGN_TEXTURE));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, SalonicraftBlocks.CINNAMON_HANGING_SIGN_TEXTURE));

        BlockEntityRendererFactories.register(SalonicraftBlockEntities.POWDER_STATION_BLOCK_ENTITY, PowderStationRenderer::new);
        BlockEntityRendererFactories.register(SalonicraftBlockEntities.BEVERAGE_BREWER_BLOCK_ENTITY, BeverageBrewerRenderer::new);

        TerraformBoatClientHelper.registerModelLayers(SalonicraftBoats.CINNAMON_BOAT_ID, false);
    }
}
