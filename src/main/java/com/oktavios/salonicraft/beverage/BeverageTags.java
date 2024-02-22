package com.oktavios.salonicraft.beverage;

import com.oktavios.salonicraft.Salonicraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BeverageTags {


    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Salonicraft.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> BEVERAGE_CONTAINER = createTag("beverage_containers");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Salonicraft.MOD_ID, name));
        }
    }
}