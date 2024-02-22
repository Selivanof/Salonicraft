package com.oktavios.salonicraft.beverage.component;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.oktavios.salonicraft.beverage.BeverageEssentials;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemConvertible;

import java.util.List;

public class MutableBeverageComponent {

    private final List<Pair<StatusEffectInstance, Float>> statusEffects;
    private final ItemConvertible container;

    private int healthRestored;

    MutableBeverageComponent(ItemConvertible beverageContainer, int healthAmount, List<Pair<StatusEffectInstance, Float>> beverageEffects) {
        this.container = beverageContainer;
        this.healthRestored = healthAmount;
        this.statusEffects = beverageEffects;
    }

    public List<Pair<StatusEffectInstance, Float>> getStatusEffects() {
        return this.statusEffects;
    }
    public ItemConvertible getContainer() { return this.container; }
    public int getHealthAmount() { return this.healthRestored; }

    public MutableBeverageComponent addStatusEffect(Pair<StatusEffectInstance, Float> status) {
        statusEffects.add(status);
        return this;
    }
    public MutableBeverageComponent addHealthAmount( int amount) {
        this.healthRestored += amount;
        return this;
    }
    public MutableBeverageComponent removeHealthAmount(int amount) {

        this.healthRestored = Math.max(0, (this.healthRestored - amount));
        return this;
    }
    public BeverageComponent createFinal() {return new BeverageComponent(container, healthRestored, Lists.newArrayList(statusEffects));}


    public static class Builder {

        private final List<Pair<StatusEffectInstance, Float>> statusEffects = Lists.newArrayList();
        private ItemConvertible container = BeverageEssentials.DRINKING_GLASS;
        private int healthRestored = 0;


        public MutableBeverageComponent.Builder statusEffect(StatusEffectInstance effect, float chance) {
            this.statusEffects.add(Pair.of(effect, Float.valueOf(chance)));
            return this;
        }

        public MutableBeverageComponent.Builder container(ItemConvertible beverageContainer) {
            this.container = beverageContainer;
            return this;
        }

        public MutableBeverageComponent.Builder health(int amount) {
            this.healthRestored = amount;
            return this;
        }

        public MutableBeverageComponent build() {
            return new MutableBeverageComponent(this.container, this.healthRestored, Lists.newArrayList(this.statusEffects));
        }
    }
}