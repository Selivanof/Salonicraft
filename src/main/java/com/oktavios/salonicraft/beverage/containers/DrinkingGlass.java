package com.oktavios.salonicraft.beverage.containers;

import com.oktavios.salonicraft.beverage.SalonicraftBeverages;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class DrinkingGlass extends ContainerBase {
    public DrinkingGlass(Settings settings) {
        super(settings, "_glass");
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        List<AreaEffectCloudEntity> list = world.getEntitiesByClass(AreaEffectCloudEntity.class, user.getBoundingBox().expand(2.0), entity -> entity != null && entity.isAlive() && entity.getOwner() instanceof EnderDragonEntity);
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = GlassBottleItem.raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        }
        if (blockHitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            if (!world.canPlayerModifyAt(user, blockPos)) {
                return TypedActionResult.pass(itemStack);
            }
            if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                world.emitGameEvent((Entity)user, GameEvent.FLUID_PICKUP, blockPos);
                return TypedActionResult.success(this.fill(itemStack, user, new ItemStack(SalonicraftBeverages.GLASS_OF_WATER.PLAIN)), world.isClient());
            }
        }
        return TypedActionResult.pass(itemStack);
    }
}
