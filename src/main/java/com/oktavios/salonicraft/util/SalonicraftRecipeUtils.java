package com.oktavios.salonicraft.util;

import com.oktavios.salonicraft.Salonicraft;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class SalonicraftRecipeUtils {
    public static void createShapelessSpiceRecipe(RecipeExporter exporter, ItemConvertible spiceItem, int spiceAmount, ItemConvertible inputItem, ItemConvertible outputItem) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, outputItem)
                .input(spiceItem, spiceAmount)
                .input(inputItem).criterion(FabricRecipeProvider.hasItem(inputItem), FabricRecipeProvider.conditionsFromItem(inputItem))
                .criterion(FabricRecipeProvider.hasItem(outputItem), FabricRecipeProvider.conditionsFromItem(outputItem))
                .offerTo(exporter, new Identifier(Salonicraft.MOD_ID,outputItem+"_from_"+inputItem+"_and_"+spiceItem));
    }

    public static void createShapelessSpiceRecipe(RecipeExporter exporter, ItemConvertible spiceItem, int spiceAmount, ItemConvertible inputItem, ItemConvertible outputItem, String group) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, outputItem)
                .input(spiceItem, spiceAmount)
                .input(inputItem).criterion(FabricRecipeProvider.hasItem(inputItem), FabricRecipeProvider.conditionsFromItem(inputItem))
                .criterion(FabricRecipeProvider.hasItem(outputItem), FabricRecipeProvider.conditionsFromItem(outputItem))
                .group(group)
                .offerTo(exporter, new Identifier(Salonicraft.MOD_ID,outputItem+"_from_"+inputItem+"_and_"+spiceItem));
    }

    public static void createShapelessSpiceRecipe(RecipeExporter exporter, TagKey<Item> spiceTag, int spiceAmount, ItemConvertible inputItem, ItemConvertible outputItem) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, outputItem)
                .input(Ingredient.fromTag(spiceTag), spiceAmount)
                .input(inputItem).criterion(FabricRecipeProvider.hasItem(inputItem), FabricRecipeProvider.conditionsFromItem(inputItem))
                .criterion(FabricRecipeProvider.hasItem(outputItem), FabricRecipeProvider.conditionsFromItem(outputItem))
                .offerTo(exporter, new Identifier(Salonicraft.MOD_ID,outputItem+"_from_"+inputItem+"_and_"+spiceTag.id().getPath()));
    }

    public static void createShapelessSpiceRecipe(RecipeExporter exporter, TagKey<Item> spiceTag, int spiceAmount, ItemConvertible inputItem, ItemConvertible outputItem, String group) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, outputItem)
                .input(Ingredient.fromTag(spiceTag), spiceAmount)
                .input(inputItem).criterion(FabricRecipeProvider.hasItem(inputItem), FabricRecipeProvider.conditionsFromItem(inputItem))
                .criterion(FabricRecipeProvider.hasItem(outputItem), FabricRecipeProvider.conditionsFromItem(outputItem))
                .group(group)
                .offerTo(exporter, new Identifier(Salonicraft.MOD_ID,outputItem+"_from_"+inputItem+"_and_"+spiceTag.id().getPath()));
    }
}
