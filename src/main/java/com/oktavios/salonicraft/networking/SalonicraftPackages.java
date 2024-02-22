package com.oktavios.salonicraft.networking;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.networking.packet.FluidSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class SalonicraftPackages {
    public static final Identifier FLUID_SYNC = new Identifier(Salonicraft.MOD_ID, "fluid_sync");

    public static void registerC2SPackets() {
    }

    public static void registerS2CPackets() {

        ClientPlayNetworking.registerGlobalReceiver(FLUID_SYNC, FluidSyncS2CPacket::receive);
    }
}
