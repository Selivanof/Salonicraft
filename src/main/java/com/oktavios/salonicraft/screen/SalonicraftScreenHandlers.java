package com.oktavios.salonicraft.screen;

import com.oktavios.salonicraft.Salonicraft;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class SalonicraftScreenHandlers {
    public static final ScreenHandlerType<PowderStationScreenHandler> POWDER_STATION_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Salonicraft.MOD_ID, "powder_station_screen_handler"),
                    new ExtendedScreenHandlerType<>(PowderStationScreenHandler::new));
    public static final ScreenHandlerType<BeverageBrewerScreenHandler> BEVERAGE_BREWER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Salonicraft.MOD_ID, "beverage_brewer_screen_handler"),
                    new ExtendedScreenHandlerType<>(BeverageBrewerScreenHandler::new));
    public static void registerScreenHandlers() {
    }
}
