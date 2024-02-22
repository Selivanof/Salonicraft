package com.oktavios.salonicraft.beverage.base;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.beverage.component.BeverageComponent;
import com.oktavios.salonicraft.beverage.component.MutableBeverageComponent;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;


// Creates a beverage with 4 variations based on sugar level
// All 4 variation are register to the item registry upon construction
public class SugarVariantBeverage {
    public static final String sugar_1_suffix = "_moderate";
    public static final String sugar_2_suffix = "_sweet";
    public static final String sugar_3_suffix = "_extra_sweet";
    private static final int SUGAR_LEVELS = 4;
    public final Item PLAIN;
    public final Item MODERATE;
    public final Item SWEET;
    public final Item EXTRA_SWEET;

    public SugarVariantBeverage(String name,
                                Item.Settings settings,
                                BeverageComponent plainComponent,
                                BeverageComponent moderateComponent,
                                BeverageComponent sweetComponent,
                                BeverageComponent extraSweetComponent) {
        PLAIN = registerBeverage(name, new BasicBeverage(settings.maxCount(1), plainComponent));
        MODERATE = registerBeverage(name + sugar_1_suffix, new BasicBeverage(settings.maxCount(1), moderateComponent));
        SWEET = registerBeverage(name + sugar_2_suffix, new BasicBeverage(settings.maxCount(1), sweetComponent));
        EXTRA_SWEET = registerBeverage(name + sugar_3_suffix, new BasicBeverage(settings.maxCount(1), extraSweetComponent));
    }


   public static class Builder {



       private Item.Settings settings = new FabricItemSettings();
       private MutableBeverageComponent plainComponent = new MutableBeverageComponent.Builder().build();
       private MutableBeverageComponent moderateComponent = new MutableBeverageComponent.Builder().build();
       private MutableBeverageComponent sweetComponent = new MutableBeverageComponent.Builder().build();
       private MutableBeverageComponent extraSweetComponent = new MutableBeverageComponent.Builder().build();
       private final String name;
       public Builder(String beverageName) {
            name = beverageName;
       }
       public SugarVariantBeverage.Builder settings(Item.Settings itemSettings) {
           settings = itemSettings;
           return this;
       }

       public SugarVariantBeverage.Builder BeverageComponent(BeverageComponent beverageComponent) {

           plainComponent = beverageComponent.createMutable();
           moderateComponent = (beverageComponent.createMutable()).addHealthAmount(2);
           sweetComponent = beverageComponent.createMutable();
           extraSweetComponent = beverageComponent.createMutable();
           extraSweetComponent.addStatusEffect(Pair.of(new StatusEffectInstance(StatusEffects.NAUSEA, 1000), 1.0f));

           return this;
       }

       public SugarVariantBeverage.Builder ModerateComponent(BeverageComponent beverageComponent) {
           moderateComponent = beverageComponent.createMutable();
           return this;
       }

       public SugarVariantBeverage.Builder SweetComponent(BeverageComponent beverageComponent) {
           sweetComponent = beverageComponent.createMutable();
           return this;
       }
       public SugarVariantBeverage.Builder ExtraSweetComponent(BeverageComponent beverageComponent) {
           extraSweetComponent = beverageComponent.createMutable();
           return this;
       }
       public SugarVariantBeverage build() {
           return new SugarVariantBeverage(
                   name,
                   settings,
                   plainComponent.createFinal(),
                   moderateComponent.createFinal(),
                   sweetComponent.createFinal(),
                   extraSweetComponent.createFinal());
       }
   }

    private static Item registerBeverage(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Salonicraft.MOD_ID, name), item);
    }




}
