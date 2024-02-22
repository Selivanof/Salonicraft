package com.oktavios.salonicraft.beverage.base;

import com.mojang.datafixers.util.Pair;
import com.oktavios.salonicraft.beverage.component.BeverageComponent;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;


public class BasicBeverage extends Item {

    private final List<Pair<StatusEffectInstance, Float>> statusEffects;
    private final ItemConvertible container;

    private final int healthAmount;
    private static final int MAX_USE_TIME = 64;

    public BasicBeverage(Item.Settings settings, BeverageComponent beverageComponent) {
        super(settings);
        container = beverageComponent.getContainer();
        statusEffects = beverageComponent.getStatusEffects();
        healthAmount = beverageComponent.getHealthAmount();
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity;
        PlayerEntity playerEntity2 = playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
        }

        for (Pair<StatusEffectInstance, Float> pair : statusEffects) {
            if (world.isClient || pair.getFirst() == null || !(world.random.nextFloat() < pair.getSecond().floatValue())) continue;
            user.addStatusEffect(new StatusEffectInstance(pair.getFirst()));
        }

        user.heal(healthAmount);

        if (playerEntity != null) {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!playerEntity.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }
        if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(container);
            }
            if (playerEntity != null) {
                playerEntity.getInventory().insertStack(new ItemStack(container));
            }
        }
        user.emitGameEvent(GameEvent.DRINK);
        return stack;
    }

    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }



    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    // ADD TOOLTIP INFORMATION


}
