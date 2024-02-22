package com.oktavios.salonicraft.datagen;

import com.oktavios.salonicraft.block.SalonicraftBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class SalonicraftBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public SalonicraftBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(SalonicraftBlocks.ENRICHED_CINNAMON_LOG)
                .add(SalonicraftBlocks.CINNAMON_WOOD)
                .add(SalonicraftBlocks.CINNAMON_PLANKS)
                .add(SalonicraftBlocks.STRIPPED_CINNAMON_LOG)
                .add(SalonicraftBlocks.STRIPPED_CINNAMON_WOOD)
                .add(SalonicraftBlocks.CINNAMON_BUTTON)
                .add(SalonicraftBlocks.CINNAMON_PRESSURE_PLATE)
                .add(SalonicraftBlocks.CINNAMON_FENCE)
                .add(SalonicraftBlocks.CINNAMON_FENCE_GATE)
                .add(SalonicraftBlocks.CINNAMON_DOOR)
                .add(SalonicraftBlocks.CINNAMON_TRAPDOOR)
                .add(SalonicraftBlocks.CINNAMON_SLAB)
                .add(SalonicraftBlocks.CINNAMON_STAIRS);
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric","needs_tool_level_4")));

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(SalonicraftBlocks.CINNAMON_FENCE)
                .add(SalonicraftBlocks.CINNAMON_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(SalonicraftBlocks.CINNAMON_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(SalonicraftBlocks.ENRICHED_CINNAMON_LOG)
                .add(SalonicraftBlocks.STRIPPED_CINNAMON_LOG)
                .add(SalonicraftBlocks.STRIPPED_CINNAMON_WOOD)
                .add(SalonicraftBlocks.CINNAMON_WOOD);


        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(SalonicraftBlocks.CINNAMON_PLANKS);


    }
}
