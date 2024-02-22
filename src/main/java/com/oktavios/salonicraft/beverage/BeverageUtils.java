package com.oktavios.salonicraft.beverage;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.beverage.base.SugarVariantBeverage;
import com.oktavios.salonicraft.datagen.SalonicraftItemTagProvider;
import com.oktavios.salonicraft.item.SalonicraftItems;
import com.oktavios.salonicraft.util.SalonicraftRecipeUtils;
import com.oktavios.salonicraft.util.SalonicraftTags;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BeverageUtils {
    public static void addSugVarBeverageEntires(FabricItemGroupEntries entries, SugarVariantBeverage beverage) {

        entries.add(beverage.PLAIN);
        entries.add(beverage.MODERATE);
        entries.add(beverage.SWEET);
        entries.add(beverage.EXTRA_SWEET);
    }

    public static void addSugVarBeverageModels(ItemModelGenerator itemModelGenerator, SugarVariantBeverage beverage) {

        itemModelGenerator.register(beverage.PLAIN,  Models.GENERATED);
        itemModelGenerator.register(beverage.MODERATE,  beverage.PLAIN, Models.GENERATED);
        itemModelGenerator.register(beverage.SWEET,  beverage.PLAIN, Models.GENERATED);
        itemModelGenerator.register(beverage.EXTRA_SWEET,  beverage.PLAIN, Models.GENERATED);

    }

    public static void addSugVarBeverageTranslations(FabricLanguageProvider.TranslationBuilder translationBuilder, SugarVariantBeverage beverage, String name) {

        translationBuilder.add(beverage.PLAIN, name);
        translationBuilder.add(beverage.MODERATE, name + " (Semi-Sweet)");
        translationBuilder.add(beverage.SWEET, name + " (Sweet)");
        translationBuilder.add(beverage.EXTRA_SWEET, name + " (Very Sweet)");
    }
    public static void addSugVarCoffeeTranslations(FabricLanguageProvider.TranslationBuilder translationBuilder, SugarVariantBeverage beverage, String name) {

        translationBuilder.add(beverage.PLAIN, name);
        translationBuilder.add(beverage.MODERATE, name + " (Semi-Sweet)");
        translationBuilder.add(beverage.SWEET, name + " (Sweet)");
        translationBuilder.add(beverage.EXTRA_SWEET, name + " (Galaktoboureko)");

    }


    public static void addSugVarBeverageRecipes(RecipeExporter exporter, SugarVariantBeverage beverage) {
        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftTags.Items.SWEETENER, 1, beverage.PLAIN, beverage.MODERATE, beverage.toString() + "_moderate");
        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftTags.Items.SWEETENER, 2, beverage.PLAIN, beverage.SWEET, beverage.toString() + "_sweet");
        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftTags.Items.SWEETENER, 3, beverage.PLAIN, beverage.EXTRA_SWEET, beverage.toString() + "_extra_sweet");

        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftTags.Items.SWEETENER, 1, beverage.MODERATE, beverage.SWEET, beverage.toString() + "_sweet");
        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftTags.Items.SWEETENER, 2, beverage.MODERATE, beverage.EXTRA_SWEET, beverage.toString() + "_extra_sweet");

        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftTags.Items.SWEETENER, 1, beverage.SWEET, beverage.EXTRA_SWEET, beverage.toString() + "_extra_sweet");
    }


    public static void addSugVarBeverageToTag(SalonicraftItemTagProvider provider, TagKey<Item> tag, SugarVariantBeverage beverage){
        provider.getOrCreateTagBuilder(tag)
                .add(beverage.PLAIN)
                .add(beverage.MODERATE)
                .add(beverage.SWEET)
                .add(beverage.EXTRA_SWEET);
    }
}