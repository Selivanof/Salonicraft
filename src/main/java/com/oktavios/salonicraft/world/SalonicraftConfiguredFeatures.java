package com.oktavios.salonicraft.world;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class SalonicraftConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> CINNAMON_KEY = registerKey("cinnamon");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, CINNAMON_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(SalonicraftBlocks.ENRICHED_CINNAMON_LOG),
                new StraightTrunkPlacer(5, 2, 2),

                BlockStateProvider.of(SalonicraftBlocks.CINNAMON_LEAVES),
                new PineFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(1), ConstantIntProvider.create(5)),

                new TwoLayersFeatureSize(1, 0, 2)).build());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Salonicraft.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
