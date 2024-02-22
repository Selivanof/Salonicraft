package com.oktavios.salonicraft.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.Slot;

public class SingleItemSlot extends Slot {

    Item ITEM;
    public SingleItemSlot(Inventory inventory, Item item, int index, int x, int y) {
        super(inventory, index, x, y);
        ITEM = item;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isOf(ITEM);
    }
}
