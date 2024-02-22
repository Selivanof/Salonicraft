package com.oktavios.salonicraft.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.Slot;

public class TaggedSlot extends Slot {

    TagKey<Item> slot_tag;
    public TaggedSlot(Inventory inventory, TagKey<Item> tag, int index, int x, int y) {
        super(inventory, index, x, y);
        slot_tag = tag;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isIn(slot_tag);
    }
}
