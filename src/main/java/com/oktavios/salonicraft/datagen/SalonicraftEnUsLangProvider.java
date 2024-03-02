package com.oktavios.salonicraft.datagen;

import com.oktavios.salonicraft.beverage.BeverageEssentials;
import com.oktavios.salonicraft.beverage.BeverageUtils;
import com.oktavios.salonicraft.beverage.SalonicraftBeverages;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.item.SalonicraftItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class SalonicraftEnUsLangProvider extends FabricLanguageProvider {
    public SalonicraftEnUsLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        BeverageUtils.addSugVarCoffeeTranslations(translationBuilder, SalonicraftBeverages.FRAPPES,"Frappes");
        BeverageUtils.addSugVarCoffeeTranslations(translationBuilder, SalonicraftBeverages.FRAPPES_WITH_MILK,"Frappes with Milk");
        BeverageUtils.addSugVarBeverageTranslations(translationBuilder, SalonicraftBeverages.KAKAO,"Kakao");
        BeverageUtils.addSugVarBeverageTranslations(translationBuilder, SalonicraftBeverages.GLASS_OF_WATER,"Glass of Water");
        BeverageUtils.addSugVarBeverageTranslations(translationBuilder, SalonicraftBeverages.GLASS_OF_MILK,"Glass of Milk");

        translationBuilder.add(SalonicraftItems.RAW_MPOUGATSA,"Raw Mpougatsa");
        translationBuilder.add(SalonicraftItems.MPOUGATSA,"Cooked Mpougatsa");
        translationBuilder.add(SalonicraftItems.MPOUGATSA_CINNAMON,"Strange Mpougatsa");
        translationBuilder.add(SalonicraftItems.MPOUGATSA_FULL,"Mpougatsa from Thessaloniki");
        translationBuilder.add(SalonicraftItems.MPOUGATSA_SUGAR,"Mpougatsa with Sugar Powder");
        translationBuilder.add(SalonicraftItems.GOLDEN_BREAKFAST,"Golden Breakfast");
        translationBuilder.add(SalonicraftItems.TSOUREKI,"Tsoureki");

        translationBuilder.add(SalonicraftItems.COOKED_SOUVLAKI,"Cooked Souvlaki");
        translationBuilder.add(SalonicraftItems.SOUVLAKI,"Souvlaki");

        translationBuilder.add(SalonicraftItems.KOULOURI,"Strange Koulouri");
        translationBuilder.add(SalonicraftItems.KOULOURI_WITH_SESAME,"Koulouri Thessalonikis");
        translationBuilder.add(BeverageEssentials.DRINKING_GLASS,"Drinking Glass");
        translationBuilder.add(SalonicraftItems.CINNAMON_STICK,"Cinnamon Stick");
        translationBuilder.add(SalonicraftItems.COFFEE_BEAN,"Coffee Bean");

        translationBuilder.add(SalonicraftItems.COCOA_POWDER,"Cocoa Powder");
        translationBuilder.add(SalonicraftItems.CINNAMON_POWDER,"Cinnamon Powder");
        translationBuilder.add(SalonicraftItems.COFFEE_POWDER,"Coffee Powder");
        translationBuilder.add(SalonicraftItems.SUGAR_POWDER,"Sugar Powder");
        translationBuilder.add(SalonicraftItems.MILK_POWDER,"Milk Powder");
        translationBuilder.add(SalonicraftItems.FLOUR,"Flour");
        translationBuilder.add(SalonicraftItems.CUSTARD,"Custard");

        translationBuilder.add(SalonicraftItems.DOUGH,"Dough");
        translationBuilder.add(SalonicraftItems.TSOUREKI_DOUGH,"Tsoureki Dough");
        translationBuilder.add(SalonicraftItems.SWEET_PHYLLO_DOUGH,"Sweet Phyllo Dough");
        translationBuilder.add(SalonicraftItems.KOULOURI_DOUGH,"Koulouri Dough");

        translationBuilder.add(SalonicraftItems.TICKET_ONE_WAY,"One-Way OASTH Ticket");
        translationBuilder.add(SalonicraftItems.TICKET_TWO_WAY,"Two-Way OASTH Ticket");

        translationBuilder.add(SalonicraftBlocks.POWDER_STATION, "Powder Station");
        translationBuilder.add(SalonicraftBlocks.BEVERAGE_BREWER, "Beverage Brewer (made in Thessaloniki)");
        translationBuilder.add(SalonicraftBlocks.FLOUR_SACK, "Flour Sack");

        translationBuilder.add(SalonicraftBlocks.ENRICHED_CINNAMON_LOG, "Enriched Cinnamon Log");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_LOG, "Cinnamon Log");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_WOOD, "Cinnamon Wood");
        translationBuilder.add(SalonicraftBlocks.STRIPPED_CINNAMON_LOG, "Stripped Cinnamon Wood");
        translationBuilder.add(SalonicraftBlocks.STRIPPED_CINNAMON_WOOD, "Stripped Cinnamon Wood");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_PLANKS, "Cinnamon Planks");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_LEAVES, "Cinnamon Leaves");

        translationBuilder.add(SalonicraftBlocks.CINNAMON_SLAB, "Cinnamon Slab");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_STAIRS, "Cinnamon Stairs");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_BUTTON, "Cinnamon Button");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_PRESSURE_PLATE, "Cinnamon Pressure Plate");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_DOOR, "Cinnamon Door");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_TRAPDOOR, "Cinnamon Trapdoor");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_FENCE, "Cinnamon Fence");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_FENCE_GATE, "Cinnamon Fence Gate");
        translationBuilder.add(SalonicraftBlocks.STANDING_CINNAMON_SIGN, "Cinnamon Sign");
        translationBuilder.add(SalonicraftBlocks.HANGING_CINNAMON_SIGN, "Cinnamon Hanging Sign");
        translationBuilder.add(SalonicraftItems.CINNAMON_BOAT, "Cinnamon Boat");
        translationBuilder.add(SalonicraftItems.CINNAMON_CHEST_BOAT, "Cinnamon Chest Boat");
        translationBuilder.add(SalonicraftBlocks.CINNAMON_SAPLING, "Cinnamon Sapling");


        translationBuilder.add("salonicraft.tooltip.fluid_amount_with_capacity","%s / %s mL");
        translationBuilder.add("salonicraft.tooltip.fluid_amount","%s mL");
        translationBuilder.add("block.minecraft.empty","Empty");
    }
}
