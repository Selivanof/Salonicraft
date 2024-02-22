package com.oktavios.salonicraft.networking.packet;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.block.SalonicraftBlocks;
import com.oktavios.salonicraft.block.custom.BeverageBrewerBlock;
import com.oktavios.salonicraft.block.entity.BeverageBrewerBlockEntity;
import com.oktavios.salonicraft.screen.BeverageBrewerScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class FluidSyncS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        FluidVariant variant = FluidVariant.fromPacket(buf);
        long fluidAmount = buf.readLong();
        BlockPos position = buf.readBlockPos();

        if(client.world.getBlockEntity(position) instanceof BeverageBrewerBlockEntity blockEntity) {
            blockEntity.setFluidLevel(variant, fluidAmount);

            if(client.player.currentScreenHandler instanceof BeverageBrewerScreenHandler screenHandler &&
                    screenHandler.blockEntity.getPos().equals(position)) {
                blockEntity.setFluidLevel(variant, fluidAmount);
                screenHandler.setFluid(variant, fluidAmount);
            }
        }
    }
}