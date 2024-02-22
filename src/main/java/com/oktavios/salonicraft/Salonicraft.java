/**
 *  This mod was created by Oktavios
 *
 * */
package com.oktavios.salonicraft;

import com.oktavios.salonicraft.beverage.BeverageEssentials;
import com.oktavios.salonicraft.beverage.SalonicraftBeverages;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.block.entity.SalonicraftBlockEntities;
import com.oktavios.salonicraft.entity.SalonicraftBoats;
import com.oktavios.salonicraft.item.SalonicraftItems;
import com.oktavios.salonicraft.networking.SalonicraftPackages;
import com.oktavios.salonicraft.recipe.SalonicraftRecipes;
import com.oktavios.salonicraft.screen.SalonicraftScreenHandlers;
import com.oktavios.salonicraft.util.SalonicraftLootTableModifiers;
import com.oktavios.salonicraft.world.gen.SalonicraftWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Salonicraft implements ModInitializer {
	public static final String MOD_ID = "salonicraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {


		BeverageEssentials.registerBeverageItems();

		SalonicraftItems.registerModItems();
		SalonicraftBlocks.registerModBlocks();
		SalonicraftLootTableModifiers.modifyLootTables();

		SalonicraftBeverages.registerBeverageItems();

		SalonicraftBlockEntities.registerBlockEntities();
		SalonicraftScreenHandlers.registerScreenHandlers();

		SalonicraftPackages.registerC2SPackets();
		SalonicraftRecipes.registerRecipes();

		StrippableBlockRegistry.register(SalonicraftBlocks.ENRICHED_CINNAMON_LOG, SalonicraftBlocks.STRIPPED_CINNAMON_LOG);
		StrippableBlockRegistry.register(SalonicraftBlocks.CINNAMON_WOOD, SalonicraftBlocks.STRIPPED_CINNAMON_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(SalonicraftBlocks.ENRICHED_CINNAMON_LOG,5 ,5);
		FlammableBlockRegistry.getDefaultInstance().add(SalonicraftBlocks.CINNAMON_WOOD,5 ,5);
		FlammableBlockRegistry.getDefaultInstance().add(SalonicraftBlocks.STRIPPED_CINNAMON_WOOD,5 ,5);
		FlammableBlockRegistry.getDefaultInstance().add(SalonicraftBlocks.STRIPPED_CINNAMON_LOG,5 ,5);
		FlammableBlockRegistry.getDefaultInstance().add(SalonicraftBlocks.CINNAMON_PLANKS,5 ,20);
		FlammableBlockRegistry.getDefaultInstance().add(SalonicraftBlocks.CINNAMON_LEAVES,30 ,60);

		SalonicraftBoats.registerBoats();

		SalonicraftWorldGeneration.generateTrees();

		LOGGER.info("Salonicraft finished setup successfully!");
		LOGGER.info("Welcome to Salonica!");
	}
}