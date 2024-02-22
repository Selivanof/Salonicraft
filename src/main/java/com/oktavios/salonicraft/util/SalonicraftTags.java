package com.oktavios.salonicraft.util;

import com.oktavios.salonicraft.Salonicraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;


public class SalonicraftTags {


    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Salonicraft.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> POWDER = createTag("powders");
        public static final TagKey<Item> POWDERABLE = createTag("powderable");
        public static final TagKey<Item> BEVERAGE = createTag("beverage");
        public static final TagKey<Item> SWEETENER = createTag("sweetener");
        public static final TagKey<Item> BEVERAGE_CONTAINER = createTag("beverage_container");
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Salonicraft.MOD_ID, name));
        }
    }
}
