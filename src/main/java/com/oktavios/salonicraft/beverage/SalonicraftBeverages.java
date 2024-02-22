package com.oktavios.salonicraft.beverage;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.beverage.base.SugarVariantBeverage;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SalonicraftBeverages {


    public static final SugarVariantBeverage FRAPES = new SugarVariantBeverage.Builder("frapes")
            .BeverageComponent(SalonicraftBeverageComponents.FRAPES).build();

    public static final SugarVariantBeverage FRAPES_WITH_MILK = new SugarVariantBeverage.Builder("frapes_with_milk")
            .BeverageComponent(SalonicraftBeverageComponents.FRAPES_WITH_MILK).build();

    public static final SugarVariantBeverage KAKAO = new SugarVariantBeverage.Builder("kakao")
            .BeverageComponent(SalonicraftBeverageComponents.KAKAO).build();

    public static final SugarVariantBeverage GLASS_OF_WATER = new SugarVariantBeverage.Builder("glass_of_water")
            .BeverageComponent(SalonicraftBeverageComponents.GLASS_OF_WATER).build();

    public static final SugarVariantBeverage GLASS_OF_MILK = new SugarVariantBeverage.Builder("glass_of_milk")
            .BeverageComponent(SalonicraftBeverageComponents.GLASS_OF_MILK).build();
    private static void addItemsToFoodAndDrinks(FabricItemGroupEntries entries) {

        BeverageUtils.addSugVarBeverageEntires(entries, FRAPES);
        BeverageUtils.addSugVarBeverageEntires(entries, FRAPES_WITH_MILK);
        BeverageUtils.addSugVarBeverageEntires(entries, KAKAO);
        BeverageUtils.addSugVarBeverageEntires(entries, GLASS_OF_WATER);
        BeverageUtils.addSugVarBeverageEntires(entries, GLASS_OF_MILK);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Salonicraft.MOD_ID, name), item);
    }
    public static void registerBeverageItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(SalonicraftBeverages::addItemsToFoodAndDrinks);
    }

}
