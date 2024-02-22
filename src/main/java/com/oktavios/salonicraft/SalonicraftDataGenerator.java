package com.oktavios.salonicraft;

import com.oktavios.salonicraft.datagen.*;
import com.oktavios.salonicraft.world.SalonicraftConfiguredFeatures;
import com.oktavios.salonicraft.world.SalonicraftPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class SalonicraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(SalonicraftBlockTagProvider::new);
		pack.addProvider(SalonicraftRecipeProvider::new);
		pack.addProvider(SalonicraftModelProvider::new);
		pack.addProvider(SalonicraftItemTagProvider::new);
		pack.addProvider(SalonicraftLootTableProvider::new);
		pack.addProvider(SalonicraftEnUsLangProvider::new);
		pack.addProvider(SalonicraftWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, SalonicraftConfiguredFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, SalonicraftPlacedFeatures::boostrap);
	}
}
