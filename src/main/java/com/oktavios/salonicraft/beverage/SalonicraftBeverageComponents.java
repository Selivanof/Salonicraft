package com.oktavios.salonicraft.beverage;

import com.oktavios.salonicraft.beverage.component.BeverageComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class SalonicraftBeverageComponents {
    public static final BeverageComponent FRAPPES =
            new BeverageComponent.Builder()
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000), 0.5f)
                    .container(BeverageEssentials.DRINKING_GLASS)
                    .health(4).build();

    public static final BeverageComponent FRAPPES_WITH_MILK =
            new BeverageComponent.Builder()
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000), 0.5f)
                    .container(BeverageEssentials.DRINKING_GLASS)
                    .health(4).build();

    public static final BeverageComponent KAKAO =
            new BeverageComponent.Builder()
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000), 0.5f)
                    .container(BeverageEssentials.DRINKING_GLASS)
                    .health(4).build();

    public static final BeverageComponent GLASS_OF_WATER =
            new BeverageComponent.Builder()
                    .container(BeverageEssentials.DRINKING_GLASS)
                    .health(1).build();
    public static final BeverageComponent GLASS_OF_MILK =
            new BeverageComponent.Builder()
                    .container(BeverageEssentials.DRINKING_GLASS)
                    .health(1).build();
}
