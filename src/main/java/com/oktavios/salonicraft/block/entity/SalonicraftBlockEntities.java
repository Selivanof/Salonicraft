package com.oktavios.salonicraft.block.entity;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;


import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;



import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class SalonicraftBlockEntities {
    public static final BlockEntityType<PowderStationBlockEntity> POWDER_STATION_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Salonicraft.MOD_ID, "powder_station_block_entity"),
                    FabricBlockEntityTypeBuilder.create(PowderStationBlockEntity::new,
                            SalonicraftBlocks.POWDER_STATION).build());
    public static final BlockEntityType<BeverageBrewerBlockEntity> BEVERAGE_BREWER_BLOCK_ENTITY =
                Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Salonicraft.MOD_ID, "beverage_brewer_block_entity"),
                    FabricBlockEntityTypeBuilder.create(BeverageBrewerBlockEntity::new,
                            SalonicraftBlocks.BEVERAGE_BREWER).build());

    public static void registerBlockEntities() {
    }
}
