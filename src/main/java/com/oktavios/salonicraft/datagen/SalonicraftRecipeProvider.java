package com.oktavios.salonicraft.datagen;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.beverage.BeverageUtils;
import com.oktavios.salonicraft.beverage.SalonicraftBeverages;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.item.SalonicraftItems;
import com.oktavios.salonicraft.util.SalonicraftRecipeUtils;
import com.oktavios.salonicraft.util.SalonicraftTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;

public class SalonicraftRecipeProvider extends FabricRecipeProvider {
    public SalonicraftRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerFullCookingRecipe(exporter, List.of(SalonicraftItems.DOUGH), Items.BREAD, 0.35f, 100, 50, "vanilla_extended");


        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, SalonicraftItems.KOULOURI_WITH_SESAME)
                .input(SalonicraftItems.SESAME_SEEDS).criterion(FabricRecipeProvider.hasItem(SalonicraftItems.SESAME_SEEDS), FabricRecipeProvider.conditionsFromItem(SalonicraftItems.SESAME_SEEDS))
                .input(SalonicraftItems.KOULOURI).criterion(FabricRecipeProvider.hasItem(SalonicraftItems.KOULOURI), FabricRecipeProvider.conditionsFromItem(SalonicraftItems.KOULOURI))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.KOULOURI_WITH_SESAME), FabricRecipeProvider.conditionsFromItem(SalonicraftItems.KOULOURI_WITH_SESAME))
                .offerTo(exporter);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.DECORATIONS, SalonicraftItems.FLOUR, RecipeCategory.FOOD, SalonicraftBlocks.FLOUR_SACK);
        BeverageUtils.addSugVarBeverageRecipes(exporter, SalonicraftBeverages.KAKAO);
        BeverageUtils.addSugVarBeverageRecipes(exporter, SalonicraftBeverages.FRAPPES);
        BeverageUtils.addSugVarBeverageRecipes(exporter, SalonicraftBeverages.FRAPPES_WITH_MILK);
        BeverageUtils.addSugVarBeverageRecipes(exporter, SalonicraftBeverages.GLASS_OF_WATER);
        BeverageUtils.addSugVarBeverageRecipes(exporter, SalonicraftBeverages.GLASS_OF_MILK);

        createDoorRecipe(SalonicraftBlocks.CINNAMON_DOOR, Ingredient.ofItems(SalonicraftBlocks.CINNAMON_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_DOOR), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_DOOR))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_PLANKS), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_PLANKS)).offerTo(exporter);
        createTrapdoorRecipe(SalonicraftBlocks.CINNAMON_TRAPDOOR, Ingredient.ofItems(SalonicraftBlocks.CINNAMON_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_TRAPDOOR), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_TRAPDOOR))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_PLANKS), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_PLANKS)).offerTo(exporter);
        createFenceRecipe(SalonicraftBlocks.CINNAMON_FENCE, Ingredient.ofItems(SalonicraftBlocks.CINNAMON_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_FENCE), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_FENCE))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_PLANKS), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_PLANKS)).offerTo(exporter);
        createFenceGateRecipe(SalonicraftBlocks.CINNAMON_FENCE_GATE, Ingredient.ofItems(SalonicraftBlocks.CINNAMON_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_FENCE_GATE), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_FENCE_GATE))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_PLANKS), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_PLANKS)).offerTo(exporter);
        createStairsRecipe(SalonicraftBlocks.CINNAMON_STAIRS, Ingredient.ofItems(SalonicraftBlocks.CINNAMON_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_STAIRS), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_STAIRS))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_PLANKS), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_PLANKS)).offerTo(exporter);
        createSlabRecipe(RecipeCategory.FOOD,SalonicraftBlocks.CINNAMON_SLAB, Ingredient.ofItems(SalonicraftBlocks.CINNAMON_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_SLAB), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_SLAB))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_PLANKS), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_PLANKS)).offerTo(exporter);
        createSignRecipe(SalonicraftBlocks.STANDING_CINNAMON_SIGN, Ingredient.ofItems(SalonicraftBlocks.CINNAMON_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.STANDING_CINNAMON_SIGN), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.STANDING_CINNAMON_SIGN))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_PLANKS), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_PLANKS)).offerTo(exporter);
        createPressurePlateRecipe(RecipeCategory.FOOD, SalonicraftBlocks.CINNAMON_PRESSURE_PLATE, Ingredient.ofItems(SalonicraftBlocks.CINNAMON_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_PRESSURE_PLATE), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_PRESSURE_PLATE))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftBlocks.CINNAMON_PLANKS), FabricRecipeProvider.conditionsFromItem(SalonicraftBlocks.CINNAMON_PLANKS)).offerTo(exporter);
        offerHangingSignRecipe(exporter, SalonicraftBlocks.HANGING_CINNAMON_SIGN, SalonicraftBlocks.CINNAMON_PLANKS);

        offerShapelessRecipe(exporter,SalonicraftBlocks.CINNAMON_BUTTON, SalonicraftBlocks.CINNAMON_PLANKS, "cinnamon_button",1);
        offerShapelessRecipe(exporter,SalonicraftBlocks.CINNAMON_PLANKS, SalonicraftBlocks.CINNAMON_LOG, "cinnamon_planks",4);
        offerShapelessRecipe(exporter,SalonicraftBlocks.CINNAMON_PLANKS, SalonicraftBlocks.ENRICHED_CINNAMON_LOG, "cinnamon_planks",4);
        offerShapelessRecipe(exporter,SalonicraftBlocks.CINNAMON_PLANKS, SalonicraftBlocks.STRIPPED_CINNAMON_LOG, "cinnamon_planks",4);
        offerShapelessRecipe(exporter,SalonicraftBlocks.CINNAMON_PLANKS, SalonicraftBlocks.CINNAMON_WOOD, "cinnamon_planks",4);
        offerShapelessRecipe(exporter,SalonicraftBlocks.CINNAMON_PLANKS, SalonicraftBlocks.STRIPPED_CINNAMON_WOOD, "cinnamon_planks",4);

        offerFullCookingRecipe(exporter, List.of(SalonicraftItems.RAW_MPOUGATSA), SalonicraftItems.MPOUGATSA, 0.5f, 250, 125, "mpougatsa");
        offerFullCookingRecipe(exporter, List.of(SalonicraftItems.KOULOURI_DOUGH), SalonicraftItems.KOULOURI, 0.3f, 120, 60, "koulouri");
        offerFullCookingRecipe(exporter, List.of(SalonicraftItems.SWEET_PHYLLO_DOUGH), SalonicraftItems.COOKED_SWEET_PHYLLO, 0.1f, 80, 40, "sweet_phyllo");
        offerFullCookingRecipe(exporter, List.of(SalonicraftItems.TSOUREKI_DOUGH), SalonicraftItems.TSOUREKI, 0.5f, 300, 150, "tsoureki");
        offerFullCookingRecipe(exporter, List.of(SalonicraftItems.SOUVLAKI), SalonicraftItems.COOKED_SOUVLAKI, 0.5f, 200, 100, "souvlaki");

        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftItems.FLOUR, 1, Items.WATER_BUCKET, SalonicraftItems.DOUGH, "dough");

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SalonicraftItems.TSOUREKI_DOUGH).pattern("# #").pattern(" # ").pattern("# #")
                .input('#', SalonicraftItems.DOUGH)
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.TSOUREKI_DOUGH),
                        FabricRecipeProvider.conditionsFromItem(SalonicraftItems.TSOUREKI_DOUGH))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.DOUGH),
                        FabricRecipeProvider.conditionsFromItem(SalonicraftItems.DOUGH)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SalonicraftItems.KOULOURI_DOUGH).pattern(" # ").pattern("# #").pattern(" # ")
                .input('#', SalonicraftItems.DOUGH)
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.KOULOURI_DOUGH),
                        FabricRecipeProvider.conditionsFromItem(SalonicraftItems.KOULOURI_DOUGH))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.DOUGH),
                        FabricRecipeProvider.conditionsFromItem(SalonicraftItems.DOUGH)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SalonicraftItems.SWEET_PHYLLO_DOUGH).pattern("###").pattern("#S#").pattern("###")
                .input('#', SalonicraftItems.DOUGH)
                .input('S', SalonicraftTags.Items.SWEETENER)
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.SWEET_PHYLLO_DOUGH),
                        FabricRecipeProvider.conditionsFromItem(SalonicraftItems.SWEET_PHYLLO_DOUGH))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.DOUGH),
                        FabricRecipeProvider.conditionsFromItem(SalonicraftItems.DOUGH)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SalonicraftItems.RAW_MPOUGATSA).pattern("###").pattern("CCC").pattern("###")
                .input('#', SalonicraftItems.SWEET_PHYLLO_DOUGH)
                .input('C', SalonicraftItems.CUSTARD)
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.CUSTARD),
                        FabricRecipeProvider.conditionsFromItem(SalonicraftItems.CUSTARD))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.SWEET_PHYLLO_DOUGH),
                        FabricRecipeProvider.conditionsFromItem(SalonicraftItems.SWEET_PHYLLO_DOUGH)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SalonicraftItems.SOUVLAKI).pattern("M").pattern("M").pattern("S")
                .input('M', Items.BEEF)
                .input('S', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SalonicraftBlocks.BEVERAGE_BREWER).pattern("###").pattern("#B#").pattern("###")
                .input('#', Items.IRON_INGOT)
                .input('B', Items.BUCKET)
                .criterion(FabricRecipeProvider.hasItem(Items.BUCKET),
                        FabricRecipeProvider.conditionsFromItem(Items.BUCKET)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SalonicraftBlocks.POWDER_STATION).pattern("WWW").pattern("SBS").pattern("SSS")
                .input('W', ItemTags.PLANKS)
                .input('B', Items.BOWL)
                .input('S', Blocks.COBBLESTONE.asItem())
                .criterion(FabricRecipeProvider.hasItem(Items.BOWL),
                        FabricRecipeProvider.conditionsFromItem(Items.BOWL)).offerTo(exporter);


        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftBeverages.KAKAO.MODERATE, 1, SalonicraftItems.MPOUGATSA_FULL, SalonicraftItems.GOLDEN_BREAKFAST, "golden_breakfast");
        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftBeverages.KAKAO.SWEET, 1, SalonicraftItems.MPOUGATSA_FULL, SalonicraftItems.GOLDEN_BREAKFAST, "golden_breakfast");
        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftBeverages.KAKAO.PLAIN, 1, SalonicraftItems.MPOUGATSA_FULL, SalonicraftItems.GOLDEN_BREAKFAST, "golden_breakfast");

        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftItems.SESAME_SEEDS, 1, SalonicraftItems.KOULOURI, SalonicraftItems.KOULOURI_WITH_SESAME);

        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftItems.SUGAR_POWDER, 1, SalonicraftItems.MPOUGATSA, SalonicraftItems.MPOUGATSA_SUGAR);
        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftItems.CINNAMON_POWDER, 1, SalonicraftItems.MPOUGATSA, SalonicraftItems.MPOUGATSA_CINNAMON);

        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftItems.SUGAR_POWDER, 1, SalonicraftItems.MPOUGATSA_CINNAMON, SalonicraftItems.MPOUGATSA_FULL, "mpougatsa_full");
        SalonicraftRecipeUtils.createShapelessSpiceRecipe(exporter, SalonicraftItems.CINNAMON_POWDER, 1, SalonicraftItems.MPOUGATSA_SUGAR, SalonicraftItems.MPOUGATSA_FULL, "mpougatsa_full");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, SalonicraftItems.MPOUGATSA_FULL)
                .input( SalonicraftItems.SUGAR_POWDER)
                .input( SalonicraftItems.CINNAMON_POWDER)
                .input( SalonicraftItems.MPOUGATSA).criterion(FabricRecipeProvider.hasItem( SalonicraftItems.MPOUGATSA), FabricRecipeProvider.conditionsFromItem( SalonicraftItems.MPOUGATSA))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.MPOUGATSA_FULL), FabricRecipeProvider.conditionsFromItem(SalonicraftItems.MPOUGATSA_FULL))
                .group("mpougatsa_full")
                .offerTo(exporter, new Identifier(Salonicraft.MOD_ID,"mpougatsa_full_from_mpougatsa_cinnamon_and_sugar"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, SalonicraftItems.CUSTARD)
                .input(SalonicraftTags.Items.SWEETENER)
                .input(Items.MILK_BUCKET)
                .input(SalonicraftItems.FLOUR).criterion(FabricRecipeProvider.hasItem( SalonicraftItems.FLOUR), FabricRecipeProvider.conditionsFromItem( SalonicraftItems.FLOUR))
                .criterion(FabricRecipeProvider.hasItem(SalonicraftItems.CUSTARD), FabricRecipeProvider.conditionsFromItem(SalonicraftItems.CUSTARD))
                .group("custard")
                .offerTo(exporter, new Identifier(Salonicraft.MOD_ID,"custard"));

    }
    public static void offerFullCookingRecipe(RecipeExporter exporter, List<ItemConvertible> inputs, ItemConvertible output, float experience, int cookingTime, int smokingTime, String group) {
        offerSmelting(exporter, inputs, RecipeCategory.FOOD, output, experience, cookingTime, group);
        offerSmoking(exporter, inputs, RecipeCategory.FOOD, output, experience, smokingTime, group);
    }
    public static void offerSmoking(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group) {
        RecipeProvider.offerMultipleOptions(exporter, RecipeSerializer.SMOKING, SmokingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_smoking");
    }
}


