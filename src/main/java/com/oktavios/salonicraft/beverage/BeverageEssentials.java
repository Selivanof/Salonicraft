package com.oktavios.salonicraft.beverage;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.beverage.containers.DrinkingGlass;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BeverageEssentials {

    public static final Item DRINKING_GLASS = registerItem("drinking_glass", new DrinkingGlass(new FabricItemSettings()));

    private static void addItemsToFoodAndDrinks(FabricItemGroupEntries entries) {
        entries.add(DRINKING_GLASS);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Salonicraft.MOD_ID, name), item);
    }
    public static void registerBeverageItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(BeverageEssentials::addItemsToFoodAndDrinks);
    }

}
