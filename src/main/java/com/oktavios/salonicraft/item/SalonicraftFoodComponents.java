package com.oktavios.salonicraft.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class SalonicraftFoodComponents {
    public static final FoodComponent MPOUGATSA = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
    public static final FoodComponent MPOUGATSA_CINNAMON = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
    public static final FoodComponent MPOUGATSA_SUGAR = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
    public static final FoodComponent MPOUGATSA_FULL = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 100), 0.9f).build();
    public static final FoodComponent GOLDEN_BREAKFAST = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 100), 0.9f).build();
    public static final FoodComponent KOULOURI = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
    public static final FoodComponent KOULOURI_SESAME = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
    public static final FoodComponent SOUVLAKI = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
    public static final FoodComponent COOKED_SOUVLAKI = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
    public static final FoodComponent COOKED_SWEET_PHYLLO = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
    public static final FoodComponent TSOUREKI = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
}
