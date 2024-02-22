package com.oktavios.salonicraft.world.tree;

import com.oktavios.salonicraft.world.SalonicraftConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class SalonicraftSaplingGenerators {
    public static final SaplingGenerator CINNAMON =
            new SaplingGenerator("cinnamon", 0f, Optional.empty(),
                    Optional.empty(),
                    Optional.of(SalonicraftConfiguredFeatures.CINNAMON_KEY),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());
}
