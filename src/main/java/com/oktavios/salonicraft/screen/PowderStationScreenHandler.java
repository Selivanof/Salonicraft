package com.oktavios.salonicraft.screen;

import com.oktavios.salonicraft.block.entity.PowderStationBlockEntity;
import com.oktavios.salonicraft.screen.slot.TaggedSlot;
import com.oktavios.salonicraft.util.SalonicraftTags;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class PowderStationScreenHandler extends ScreenHandler {

    private final Inventory INVENTORY;
    private final PropertyDelegate propertyDelegate;
    public final PowderStationBlockEntity blockEntity;

    public PowderStationScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    public PowderStationScreenHandler(int syncId, PlayerInventory inventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(SalonicraftScreenHandlers.POWDER_STATION_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 2);
        this.INVENTORY = ((Inventory) blockEntity);
        INVENTORY.onOpen(inventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((PowderStationBlockEntity) blockEntity);

        this.addSlot(new TaggedSlot(INVENTORY, SalonicraftTags.Items.POWDERABLE, 0, 80, 11));
        this.addSlot(new TaggedSlot(INVENTORY, SalonicraftTags.Items.POWDER, 1, 80, 59));


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
        int progressArrowSize = 12; // This is the width in pixels of your arrow

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
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
