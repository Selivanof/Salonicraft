package com.oktavios.salonicraft.entity;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.item.SalonicraftItems;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class SalonicraftBoats {
    public static final Identifier CINNAMON_BOAT_ID = new Identifier(Salonicraft.MOD_ID, "cinnamon_boat");
    public static final Identifier CINNAMON_CHEST_BOAT_ID = new Identifier(Salonicraft.MOD_ID, "cinnamon_chest_boat");

    public static final RegistryKey<TerraformBoatType> CINNAMON_BOAT_KEY = TerraformBoatTypeRegistry.createKey(CINNAMON_BOAT_ID);

    public static void registerBoats() {
        TerraformBoatType cinnamonBoat = new TerraformBoatType.Builder()
                .item(SalonicraftItems.CINNAMON_BOAT)
                .chestItem(SalonicraftItems.CINNAMON_CHEST_BOAT)
                .planks(SalonicraftBlocks.CINNAMON_PLANKS.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, CINNAMON_BOAT_KEY, cinnamonBoat);
    }
}
