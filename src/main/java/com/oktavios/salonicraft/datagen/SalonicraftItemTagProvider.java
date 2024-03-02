package com.oktavios.salonicraft.datagen;

import com.oktavios.salonicraft.beverage.BeverageEssentials;
import com.oktavios.salonicraft.beverage.BeverageTags;
import com.oktavios.salonicraft.beverage.BeverageUtils;
import com.oktavios.salonicraft.beverage.SalonicraftBeverages;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.item.SalonicraftItems;
import com.oktavios.salonicraft.util.SalonicraftTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class SalonicraftItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public SalonicraftItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(SalonicraftTags.Items.POWDER)
                .add(SalonicraftItems.CINNAMON_POWDER)
                .add(SalonicraftItems.COFFEE_POWDER)
                .add(SalonicraftItems.MILK_POWDER)
                .add(SalonicraftItems.COCOA_POWDER)
                .add(SalonicraftItems.SUGAR_POWDER)
                .add(SalonicraftItems.FLOUR);

        getOrCreateTagBuilder(SalonicraftTags.Items.POWDERABLE)
                .add(SalonicraftItems.COFFEE_BEAN)
                .add(Items.WHEAT)
                .add(Items.MILK_BUCKET)
                .add(SalonicraftItems.CINNAMON_STICK)
                .add(Items.COCOA_BEANS)
                .add(Items.SUGAR);

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(SalonicraftBlocks.CINNAMON_PLANKS.asItem());

        getOrCreateTagBuilder(SalonicraftTags.Items.SWEETENER)
                .add(SalonicraftItems.SUGAR_POWDER)
                .add(Items.SUGAR);

        getOrCreateTagBuilder(SalonicraftTags.Items.BEVERAGE_CONTAINER)
                .add(BeverageEssentials.DRINKING_GLASS);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(SalonicraftBlocks.ENRICHED_CINNAMON_LOG.asItem())
                .add(SalonicraftBlocks.CINNAMON_WOOD.asItem())
                .add(SalonicraftBlocks.STRIPPED_CINNAMON_WOOD.asItem())
                .add(SalonicraftBlocks.STRIPPED_CINNAMON_LOG.asItem());

        BeverageUtils.addSugVarBeverageToTag(this, SalonicraftTags.Items.BEVERAGE, SalonicraftBeverages.FRAPPES_WITH_MILK);
        BeverageUtils.addSugVarBeverageToTag(this, SalonicraftTags.Items.BEVERAGE, SalonicraftBeverages.FRAPPES);
        BeverageUtils.addSugVarBeverageToTag(this, SalonicraftTags.Items.BEVERAGE, SalonicraftBeverages.KAKAO);

        getOrCreateTagBuilder(BeverageTags.Items.BEVERAGE_CONTAINER)
                .add(BeverageEssentials.DRINKING_GLASS);

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(SalonicraftBlocks.CINNAMON_PLANKS.asItem());
    }

    @Override
    public FabricTagProvider<Item>.FabricTagBuilder getOrCreateTagBuilder(TagKey<Item> tag) {
        return super.getOrCreateTagBuilder(tag);
    }
}
