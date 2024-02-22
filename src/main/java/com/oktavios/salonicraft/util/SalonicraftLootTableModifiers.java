package com.oktavios.salonicraft.util;

import com.oktavios.salonicraft.item.SalonicraftItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class SalonicraftLootTableModifiers {

    private static final Identifier DUNGEON_ID = new Identifier("minecraft","chests/simple_dungeon");
    private static final Identifier TESTING_ID = new Identifier("minecraft","chests/spawn_bonus_chest");
    private static final Identifier GRASS_BLOCK_ID = new Identifier("minecraft","blocks/tall_grass");


    public static void modifyLootTables () {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

            if(DUNGEON_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.2f))
                        .with(ItemEntry.builder(SalonicraftItems.TICKET_ONE_WAY).weight(90))
                        .with(ItemEntry.builder(SalonicraftItems.TICKET_TWO_WAY).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if(GRASS_BLOCK_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(SalonicraftItems.SESAME_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

        }));
    }
}
