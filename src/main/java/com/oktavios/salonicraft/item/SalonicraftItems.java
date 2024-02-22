package com.oktavios.salonicraft.item;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.beverage.containers.DrinkingGlass;
import com.oktavios.salonicraft.entity.SalonicraftBoats;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SalonicraftItems {

    // POWDERS
    public static final Item COFFEE_POWDER = registerItem("coffee_powder", new Item(new FabricItemSettings()));
    public static final Item CINNAMON_POWDER = registerItem("cinnamon_powder", new Item(new FabricItemSettings()));
    public static final Item COCOA_POWDER = registerItem("cocoa_powder", new Item(new FabricItemSettings()));
    public static final Item SUGAR_POWDER = registerItem("sugar_powder", new Item(new FabricItemSettings()));
    public static final Item MILK_POWDER = registerItem("milk_powder", new Item(new FabricItemSettings()));
    public static final Item FLOUR = registerItem("flour", new Item(new FabricItemSettings()));


    // BASIC INGREDIENTS
    public static final Item CINNAMON_STICK = registerItem("cinnamon_stick", new Item(new FabricItemSettings()));
    public static final Item COFFEE_BEAN = registerItem("coffee_bean",
            new AliasedBlockItem(SalonicraftBlocks.COFFEA, new FabricItemSettings()));
    public static final Item SESAME_SEEDS = registerItem("sesame_seeds", new AliasedBlockItem(SalonicraftBlocks.SESAME_CROP, new FabricItemSettings()));


    // ADVANCED INGREDIENTS
    public static final Item DOUGH = registerItem("dough", new Item(new FabricItemSettings()));
    public static final Item KOULOURI_DOUGH = registerItem("koulouri_dough", new Item(new FabricItemSettings()));
    public static final Item TSOUREKI_DOUGH = registerItem("tsoureki_dough", new Item(new FabricItemSettings()));
    public static final Item SWEET_PHYLLO_DOUGH = registerItem("sweet_phyllo_dough", new Item(new FabricItemSettings()));
    public static final Item RAW_MPOUGATSA = registerItem("raw_mpougatsa", new Item(new FabricItemSettings()));
    public static final Item CUSTARD = registerItem("custard", new Item(new FabricItemSettings()));


    // FOODS
    public static final Item COOKED_SWEET_PHYLLO = registerItem("cooked_sweet_phyllo", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.COOKED_SWEET_PHYLLO)));
    public static final Item MPOUGATSA = registerItem("mpougatsa", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.MPOUGATSA)));
    public static final Item MPOUGATSA_CINNAMON = registerItem("mpougatsa_cinnamon", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.MPOUGATSA_CINNAMON)));
    public static final Item MPOUGATSA_SUGAR = registerItem("mpougatsa_sugar", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.MPOUGATSA_SUGAR)));
    public static final Item MPOUGATSA_FULL = registerItem("mpougatsa_full", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.MPOUGATSA_FULL)));
    public static final Item GOLDEN_BREAKFAST = registerItem("golden_breakfast", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.GOLDEN_BREAKFAST)));
    public static final Item KOULOURI = registerItem("koulouri", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.KOULOURI)));
    public static final Item TSOUREKI = registerItem("tsoureki", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.TSOUREKI)));
    public static final Item KOULOURI_WITH_SESAME = registerItem("koulouri_with_sesame", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.KOULOURI_SESAME)));
    public static final Item SOUVLAKI = registerItem("souvlaki", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.SOUVLAKI)));
    public static final Item COOKED_SOUVLAKI = registerItem("cooked_souvlaki", new Item(new FabricItemSettings().food(SalonicraftFoodComponents.COOKED_SOUVLAKI)));

    // MISC
    public static final Item TICKET_TWO_WAY = registerItem("ticket_two_way", new Item(new FabricItemSettings()));
    public static final Item TICKET_ONE_WAY = registerItem("ticket_one_way", new Item(new FabricItemSettings()));


    public static final Item CINNAMON_SIGN = registerItem("cinnamon_sign",
            new SignItem(new FabricItemSettings().maxCount(16), SalonicraftBlocks.STANDING_CINNAMON_SIGN, SalonicraftBlocks.WALL_CINNAMON_SIGN));
    public static final Item HANGING_CINNAMON_SIGN = registerItem("cinnamon_hanging_sign",
            new HangingSignItem(SalonicraftBlocks.HANGING_CINNAMON_SIGN, SalonicraftBlocks.WALL_HANGING_CINNAMON_SIGN, new FabricItemSettings().maxCount(16)));

    public static final Item CINNAMON_BOAT = TerraformBoatItemHelper.registerBoatItem(SalonicraftBoats.CINNAMON_BOAT_ID, SalonicraftBoats.CINNAMON_BOAT_KEY, false);
    public static final Item CINNAMON_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(SalonicraftBoats.CINNAMON_CHEST_BOAT_ID, SalonicraftBoats.CINNAMON_BOAT_KEY, true);





    private static void addItemsToFoodAndDrinks(FabricItemGroupEntries entries) {
        entries.add(COOKED_SWEET_PHYLLO);
        entries.add(MPOUGATSA);
        entries.add(MPOUGATSA_CINNAMON);
        entries.add(MPOUGATSA_FULL);
        entries.add(MPOUGATSA_SUGAR);
        entries.add(GOLDEN_BREAKFAST);
        entries.add(KOULOURI);
        entries.add(KOULOURI_WITH_SESAME);
        entries.add(SOUVLAKI);
        entries.add(COOKED_SOUVLAKI);

    }

    private static void addItemsToIngredients(FabricItemGroupEntries entries) {
        entries.add(FLOUR);
        entries.add(DOUGH);
        entries.add(KOULOURI_DOUGH);
        entries.add(SWEET_PHYLLO_DOUGH);
        entries.add(TSOUREKI_DOUGH);
        entries.add(RAW_MPOUGATSA);
        entries.add(CUSTARD);
        entries.add(CINNAMON_STICK);
        entries.add(CINNAMON_POWDER);
        entries.add(COFFEE_BEAN);
        entries.add(COFFEE_POWDER);
        entries.add(COCOA_POWDER);
        entries.add(SUGAR_POWDER);
        entries.add(MILK_POWDER);

    }
    private static void addItemsToTools(FabricItemGroupEntries entries) {
        entries.add(CINNAMON_BOAT);
        entries.add(CINNAMON_CHEST_BOAT);
        entries.add(TICKET_ONE_WAY);
        entries.add(TICKET_TWO_WAY);
    }
    private static void addItemsToFunctional(FabricItemGroupEntries entries) {
        entries.add(CINNAMON_SIGN);
        entries.add(HANGING_CINNAMON_SIGN);
    }
    private static void addItemsToNatural(FabricItemGroupEntries entries) {
        entries.add(SESAME_SEEDS);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Salonicraft.MOD_ID, name), item);
    }
    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(SalonicraftItems::addItemsToFoodAndDrinks);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(SalonicraftItems::addItemsToIngredients);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(SalonicraftItems::addItemsToTools);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(SalonicraftItems::addItemsToFunctional);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(SalonicraftItems::addItemsToNatural);
    }
}
