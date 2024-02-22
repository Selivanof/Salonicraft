package com.oktavios.salonicraft.datagen;

import com.oktavios.salonicraft.beverage.BeverageEssentials;
import com.oktavios.salonicraft.beverage.BeverageUtils;
import com.oktavios.salonicraft.beverage.SalonicraftBeverages;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.block.custom.SesameCropBlock;
import com.oktavios.salonicraft.item.SalonicraftItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;

public class SalonicraftModelProvider extends FabricModelProvider {
    public SalonicraftModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(SalonicraftBlocks.SESAME_CROP, SesameCropBlock.AGE,0,1,2,3,4,5);

        blockStateModelGenerator.registerLog(SalonicraftBlocks.ENRICHED_CINNAMON_LOG).log(SalonicraftBlocks.ENRICHED_CINNAMON_LOG).wood(SalonicraftBlocks.CINNAMON_WOOD);
        blockStateModelGenerator.registerLog(SalonicraftBlocks.STRIPPED_CINNAMON_LOG).log(SalonicraftBlocks.STRIPPED_CINNAMON_LOG).wood(SalonicraftBlocks.STRIPPED_CINNAMON_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(SalonicraftBlocks.CINNAMON_LEAVES);
        blockStateModelGenerator.registerLog(SalonicraftBlocks.CINNAMON_LOG).log(SalonicraftBlocks.CINNAMON_LOG);

        BlockStateModelGenerator.BlockTexturePool cinnamon_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(SalonicraftBlocks.CINNAMON_PLANKS);
        cinnamon_pool.family(SalonicraftBlocks.CINNAMON_FAMILY);
        cinnamon_pool.stairs(SalonicraftBlocks.CINNAMON_STAIRS);
        cinnamon_pool.slab(SalonicraftBlocks.CINNAMON_SLAB);
        cinnamon_pool.button(SalonicraftBlocks.CINNAMON_BUTTON);
        cinnamon_pool.pressurePlate(SalonicraftBlocks.CINNAMON_PRESSURE_PLATE);
        cinnamon_pool.fence(SalonicraftBlocks.CINNAMON_FENCE);
        cinnamon_pool.fenceGate(SalonicraftBlocks.CINNAMON_FENCE_GATE);

        blockStateModelGenerator.registerDoor(SalonicraftBlocks.CINNAMON_DOOR);
        blockStateModelGenerator.registerTrapdoor(SalonicraftBlocks.CINNAMON_TRAPDOOR);
        blockStateModelGenerator.registerHangingSign(SalonicraftBlocks.STRIPPED_CINNAMON_LOG, SalonicraftBlocks.HANGING_CINNAMON_SIGN, SalonicraftBlocks.WALL_HANGING_CINNAMON_SIGN);

        blockStateModelGenerator.registerTintableCross(SalonicraftBlocks.CINNAMON_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        // Powders

        itemModelGenerator.register(SalonicraftItems.CINNAMON_POWDER, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.CINNAMON_STICK, Models.HANDHELD);

        itemModelGenerator.register(SalonicraftItems.COCOA_POWDER, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.SUGAR_POWDER, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.MILK_POWDER, Models.GENERATED);

        itemModelGenerator.register(SalonicraftItems.COFFEE_POWDER, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.COFFEE_BEAN, Models.GENERATED);


        itemModelGenerator.register(SalonicraftItems.FLOUR, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.DOUGH, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.TSOUREKI_DOUGH, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.KOULOURI_DOUGH, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.SWEET_PHYLLO_DOUGH, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.RAW_MPOUGATSA, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.CUSTARD, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.COOKED_SWEET_PHYLLO, Models.GENERATED);

        // Plants

        // Food Ingredients

        // Foods

        itemModelGenerator.register(SalonicraftItems.TSOUREKI, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.MPOUGATSA, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.MPOUGATSA_FULL, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.MPOUGATSA_SUGAR, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.MPOUGATSA_CINNAMON, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.GOLDEN_BREAKFAST, Models.GENERATED);

        itemModelGenerator.register(SalonicraftItems.SOUVLAKI, Models.HANDHELD);
        itemModelGenerator.register(SalonicraftItems.COOKED_SOUVLAKI, Models.HANDHELD);

        itemModelGenerator.register(SalonicraftItems.KOULOURI, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.KOULOURI_WITH_SESAME, Models.GENERATED);

        // Misc

        itemModelGenerator.register(SalonicraftItems.TICKET_ONE_WAY, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.TICKET_TWO_WAY, Models.GENERATED);

        // Beverages

        itemModelGenerator.register(BeverageEssentials.DRINKING_GLASS, Models.GENERATED);
        BeverageUtils.addSugVarBeverageModels(itemModelGenerator, SalonicraftBeverages.KAKAO);
        BeverageUtils.addSugVarBeverageModels(itemModelGenerator, SalonicraftBeverages.FRAPES);
        BeverageUtils.addSugVarBeverageModels(itemModelGenerator, SalonicraftBeverages.FRAPES_WITH_MILK);
        BeverageUtils.addSugVarBeverageModels(itemModelGenerator, SalonicraftBeverages.GLASS_OF_WATER);
        BeverageUtils.addSugVarBeverageModels(itemModelGenerator, SalonicraftBeverages.GLASS_OF_MILK);


        itemModelGenerator.register(SalonicraftItems.CINNAMON_BOAT, Models.GENERATED);
        itemModelGenerator.register(SalonicraftItems.CINNAMON_CHEST_BOAT, Models.GENERATED);
    }

}
