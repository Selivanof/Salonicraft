package com.oktavios.salonicraft.datagen;

import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.block.custom.CoffeaBlock;
import com.oktavios.salonicraft.block.custom.SesameCropBlock;
import com.oktavios.salonicraft.item.SalonicraftItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

public class SalonicraftLootTableProvider extends FabricBlockLootTableProvider {


    public SalonicraftLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(SalonicraftBlocks.FLOUR_SACK, drops(SalonicraftItems.FLOUR, ConstantLootNumberProvider.create(9.0f)));

        BlockStatePropertyLootCondition.Builder coffeaBuilder = BlockStatePropertyLootCondition.builder(SalonicraftBlocks.COFFEA)
                .properties(StatePredicate.Builder.create().exactMatch(CoffeaBlock.AGE, 7));
        addDrop(SalonicraftBlocks.COFFEA, this.applyExplosionDecay(SalonicraftBlocks.COFFEA,
                LootTable.builder()
                    .pool(LootPool.builder().with(ItemEntry.builder(SalonicraftItems.COFFEE_BEAN)))
                    .pool(LootPool.builder().conditionally(coffeaBuilder).with((LootPoolEntry.Builder<?>)((Object)ItemEntry.builder(SalonicraftItems.COFFEE_BEAN)
                .apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 1)))))));

        BlockStatePropertyLootCondition.Builder SesameBuilder = BlockStatePropertyLootCondition.builder(SalonicraftBlocks.SESAME_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(SesameCropBlock.AGE, 5));
        addDrop(SalonicraftBlocks.SESAME_CROP, this.applyExplosionDecay(SalonicraftBlocks.SESAME_CROP,
                LootTable.builder()
                        .pool(LootPool.builder().with(ItemEntry.builder(SalonicraftItems.SESAME_SEEDS)))
                        .pool(LootPool.builder().conditionally(SesameBuilder).with((LootPoolEntry.Builder<?>)((Object)ItemEntry.builder(SalonicraftItems.SESAME_SEEDS)
                                .apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 2)))))));

        addDrop(SalonicraftBlocks.CINNAMON_WOOD);
        addDrop(SalonicraftBlocks.CINNAMON_PLANKS);
        addDrop(SalonicraftBlocks.STRIPPED_CINNAMON_LOG);
        addDrop(SalonicraftBlocks.STRIPPED_CINNAMON_WOOD);
        addDrop(SalonicraftBlocks.CINNAMON_LOG);
        addDrop(SalonicraftBlocks.ENRICHED_CINNAMON_LOG, this.applyExplosionDecay(SalonicraftBlocks.ENRICHED_CINNAMON_LOG,
                LootTable.builder()
                        .pool(LootPool.builder().conditionally(WITH_SILK_TOUCH).rolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(SalonicraftBlocks.ENRICHED_CINNAMON_LOG)))
                        .pool(LootPool.builder().conditionally(WITHOUT_SILK_TOUCH).with(ItemEntry.builder(SalonicraftBlocks.CINNAMON_LOG.asItem())))
                        .pool(LootPool.builder().conditionally(WITHOUT_SILK_TOUCH).with(ItemEntry.builder(SalonicraftItems.CINNAMON_STICK)).conditionally(RandomChanceLootCondition.builder(0.5f)))));

        addDrop(SalonicraftBlocks.STANDING_CINNAMON_SIGN);
        addDrop(SalonicraftBlocks.HANGING_CINNAMON_SIGN);

        addDrop(SalonicraftBlocks.CINNAMON_FENCE);
        addDrop(SalonicraftBlocks.CINNAMON_FENCE_GATE);
        addDrop(SalonicraftBlocks.CINNAMON_BUTTON);
        addDrop(SalonicraftBlocks.CINNAMON_PRESSURE_PLATE);
        addDrop(SalonicraftBlocks.CINNAMON_STAIRS);
        addDrop(SalonicraftBlocks.CINNAMON_TRAPDOOR);
        addDrop(SalonicraftBlocks.CINNAMON_DOOR, doorDrops(SalonicraftBlocks.CINNAMON_DOOR));
        addDrop(SalonicraftBlocks.CINNAMON_SLAB, slabDrops(SalonicraftBlocks.CINNAMON_SLAB));

        addDrop(SalonicraftBlocks.CINNAMON_LEAVES, leavesDrops(SalonicraftBlocks.CINNAMON_LEAVES, SalonicraftBlocks.CINNAMON_SAPLING, 0.005f));
    }
}
