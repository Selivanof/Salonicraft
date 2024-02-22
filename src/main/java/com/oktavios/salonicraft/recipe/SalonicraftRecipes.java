package com.oktavios.salonicraft.recipe;

import com.oktavios.salonicraft.Salonicraft;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SalonicraftRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Salonicraft.MOD_ID, PowderStationRecipe.Serializer.ID),
                PowderStationRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Salonicraft.MOD_ID, PowderStationRecipe.Type.ID),
                PowderStationRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Salonicraft.MOD_ID, BeverageBrewerRecipe.Serializer.ID),
                BeverageBrewerRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Salonicraft.MOD_ID, BeverageBrewerRecipe.Type.ID),
                BeverageBrewerRecipe.Type.INSTANCE);
    }
}
