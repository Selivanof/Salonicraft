package com.oktavios.salonicraft.block;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.block.custom.*;
import com.oktavios.salonicraft.world.tree.SalonicraftSaplingGenerators;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class SalonicraftBlocks {

    // ENTITY BLOCKS
    public static final Block POWDER_STATION = registerBlock("powder_station",
            new PowderStationBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block BEVERAGE_BREWER = registerBlock("beverage_brewer",
            new BeverageBrewerBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    // PLANTS
    public static final Block SESAME_CROP = Registry.register(Registries.BLOCK, new Identifier(Salonicraft.MOD_ID, "sesame_crop"),
            new SesameCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block COFFEA = Registry.register(Registries.BLOCK, new Identifier(Salonicraft.MOD_ID, "coffea"),
            new CoffeaBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH)));


    public static final Block FLOUR_SACK = registerBlock("flour_sack",
            new DirectionalFullBlock(FabricBlockSettings.copyOf(Blocks.HAY_BLOCK).sounds(BlockSoundGroup.WOOL)));

    // CINNAMON WOOD BASIC
    public static final Block ENRICHED_CINNAMON_LOG = registerBlock("enriched_cinnamon_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block CINNAMON_LOG = registerBlock("cinnamon_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block CINNAMON_WOOD = registerBlock("cinnamon_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));

    public static final Block STRIPPED_CINNAMON_LOG = registerBlock("stripped_cinnamon_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block STRIPPED_CINNAMON_WOOD = registerBlock("stripped_cinnamon_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));


    public static final Block CINNAMON_PLANKS = registerBlock("cinnamon_planks",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block CINNAMON_LEAVES = registerBlock("cinnamon_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).nonOpaque()));

    // CINNAMON WOOD EXTRAS
    public static final Identifier CINNAMON_SIGN_TEXTURE = new Identifier(Salonicraft.MOD_ID, "entity/signs/cinnamon");
    public static final Identifier CINNAMON_HANGING_SIGN_TEXTURE = new Identifier(Salonicraft.MOD_ID, "entity/signs/hanging/cinnamon");
    public static final Identifier CINNAMON_HANGING_GUI_SIGN_TEXTURE = new Identifier(Salonicraft.MOD_ID, "textures/gui/hanging_signs/cinnamon");

    public static final Block STANDING_CINNAMON_SIGN = Registry.register(Registries.BLOCK, new Identifier(Salonicraft.MOD_ID, "cinnamon_standing_sign"),
            new TerraformSignBlock(CINNAMON_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)));
    public static final Block WALL_CINNAMON_SIGN = Registry.register(Registries.BLOCK, new Identifier(Salonicraft.MOD_ID, "cinnamon_wall_sign"),
            new TerraformWallSignBlock(CINNAMON_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).dropsLike(SalonicraftBlocks.STANDING_CINNAMON_SIGN)));
    public static final Block HANGING_CINNAMON_SIGN = Registry.register(Registries.BLOCK, new Identifier(Salonicraft.MOD_ID, "cinnamon_hanging_sign"),
            new TerraformHangingSignBlock(CINNAMON_HANGING_SIGN_TEXTURE, CINNAMON_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)));
    public static final Block WALL_HANGING_CINNAMON_SIGN = Registry.register(Registries.BLOCK, new Identifier(Salonicraft.MOD_ID, "cinnamon_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(CINNAMON_HANGING_SIGN_TEXTURE, CINNAMON_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN).dropsLike(SalonicraftBlocks.HANGING_CINNAMON_SIGN)));

    public static final Block CINNAMON_STAIRS = registerBlock("cinnamon_stairs",
            new StairsBlock(CINNAMON_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(CINNAMON_PLANKS)));
    public static final Block CINNAMON_SLAB = registerBlock("cinnamon_slab",
            new SlabBlock(FabricBlockSettings.copyOf(CINNAMON_PLANKS)));

    public static final Block CINNAMON_BUTTON = registerBlock("cinnamon_button",
            new ButtonBlock(BlockSetType.ACACIA, 10, FabricBlockSettings.copyOf(CINNAMON_PLANKS)));
    public static final Block CINNAMON_PRESSURE_PLATE = registerBlock("cinnamon_pressure_plate",
            new PressurePlateBlock(BlockSetType.ACACIA, FabricBlockSettings.copyOf(CINNAMON_PLANKS)));

    public static final Block CINNAMON_FENCE = registerBlock("cinnamon_fence",
            new FenceBlock(FabricBlockSettings.copyOf(CINNAMON_PLANKS)));
    public static final Block CINNAMON_FENCE_GATE = registerBlock("cinnamon_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, FabricBlockSettings.copyOf(CINNAMON_PLANKS)));

    public static final Block CINNAMON_DOOR = registerBlock("cinnamon_door",
            new DoorBlock(BlockSetType.ACACIA, FabricBlockSettings.copyOf(CINNAMON_PLANKS).nonOpaque()));
    public static final Block CINNAMON_TRAPDOOR = registerBlock("cinnamon_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, FabricBlockSettings.copyOf(CINNAMON_PLANKS).nonOpaque()));

    public static final BlockFamily CINNAMON_FAMILY = BlockFamilies.register(SalonicraftBlocks.CINNAMON_PLANKS)
            .sign(SalonicraftBlocks.STANDING_CINNAMON_SIGN, SalonicraftBlocks.WALL_CINNAMON_SIGN)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final Block CINNAMON_SAPLING = registerBlock("cinnamon_sapling",
            new SaplingBlock(SalonicraftSaplingGenerators.CINNAMON, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));


    private static Block registerBlock (String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Salonicraft.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Salonicraft.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static void addBlocksToBuilding(FabricItemGroupEntries entries) {
        entries.add(ENRICHED_CINNAMON_LOG);
        entries.add(CINNAMON_LOG);
        entries.add(CINNAMON_WOOD);
        entries.add(STRIPPED_CINNAMON_LOG);
        entries.add(STRIPPED_CINNAMON_WOOD);
        entries.add(CINNAMON_PLANKS);
        entries.add(CINNAMON_STAIRS);
        entries.add(CINNAMON_SLAB);
        entries.add(CINNAMON_FENCE);
        entries.add(CINNAMON_FENCE_GATE);
        entries.add(CINNAMON_DOOR);
        entries.add(CINNAMON_TRAPDOOR);
        entries.add(CINNAMON_PRESSURE_PLATE);
        entries.add(CINNAMON_BUTTON);


    }
    private static void addBlocksToFunctional(FabricItemGroupEntries entries) {
        entries.add(POWDER_STATION);
        entries.add(BEVERAGE_BREWER);
        entries.add(FLOUR_SACK);
    }
    private static void addBlocksToNatural(FabricItemGroupEntries entries) {
        entries.add(CINNAMON_LEAVES);
        entries.add(CINNAMON_SAPLING);


    }
    public static void registerModBlocks () {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(SalonicraftBlocks::addBlocksToBuilding);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(SalonicraftBlocks::addBlocksToFunctional);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(SalonicraftBlocks::addBlocksToNatural);
    }
}
