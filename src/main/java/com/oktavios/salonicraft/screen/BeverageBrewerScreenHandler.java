package com.oktavios.salonicraft.screen;

import com.oktavios.salonicraft.beverage.BeverageTags;
import com.oktavios.salonicraft.block.entity.BeverageBrewerBlockEntity;
import com.oktavios.salonicraft.screen.slot.SingleItemSlot;
import com.oktavios.salonicraft.screen.slot.TaggedSlot;
import com.oktavios.salonicraft.util.SalonicraftTags;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BeverageBrewerScreenHandler extends ScreenHandler {

    private final Inventory INVENTORY;
    private final PropertyDelegate propertyDelegate;
    public final BeverageBrewerBlockEntity blockEntity;
    private static final int WATER_INPUT = 0;
    private static final int CONTAINER_INPUT = 1;
    private static final int PRIMARY_INPUT = 2;
    private static final int SECONDARY_INPUT = 3;
    private static final int OUTPUT = 4;

    private FluidVariant fluidStorageVariant;
    private long fluidStorageAmount;


    public BeverageBrewerScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    public BeverageBrewerScreenHandler(int syncId, PlayerInventory inventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(SalonicraftScreenHandlers.BEVERAGE_BREWER_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 2);
        this.INVENTORY = ((Inventory) blockEntity);
        INVENTORY.onOpen(inventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((BeverageBrewerBlockEntity) blockEntity);
        this.fluidStorageVariant = ((BeverageBrewerBlockEntity) blockEntity).getFluidVariant();
        this.fluidStorageAmount = ((BeverageBrewerBlockEntity) blockEntity).getFluidAmount();

        this.addSlot(new SingleItemSlot(INVENTORY, Items.WATER_BUCKET, WATER_INPUT, 37, 16));
        this.addSlot(new TaggedSlot(INVENTORY, BeverageTags.Items.BEVERAGE_CONTAINER, CONTAINER_INPUT, 140, 61));
        this.addSlot(new TaggedSlot(INVENTORY, SalonicraftTags.Items.POWDER, PRIMARY_INPUT, 111, 16));
        this.addSlot(new Slot(INVENTORY,  SECONDARY_INPUT, 140, 16));
        this.addSlot(new TaggedSlot(INVENTORY, SalonicraftTags.Items.BEVERAGE, OUTPUT, 111, 61));


        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        addProperties(arrayPropertyDelegate);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.INVENTORY.size()) {
                if (!this.insertItem(originalStack, this.INVENTORY.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.INVENTORY.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.INVENTORY.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }

    public void setFluid(FluidVariant variant, long fluidAmount) {
        fluidStorageVariant = variant;
        fluidStorageAmount = fluidAmount;
    }

    public long getFluidAmount() {
        return fluidStorageAmount;
    }

    public FluidVariant getFluidVariant () {
        return fluidStorageVariant;
    }
}
