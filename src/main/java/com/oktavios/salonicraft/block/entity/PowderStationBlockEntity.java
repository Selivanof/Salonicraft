package com.oktavios.salonicraft.block.entity;

import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.item.SalonicraftItems;
import com.oktavios.salonicraft.recipe.PowderStationRecipe;
import com.oktavios.salonicraft.screen.PowderStationScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.windows.INPUT;

import java.util.Optional;

public class PowderStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public static final class SLOTS {


        public static final int INPUT = 0;
        public static final int OUTPUT = 1;


    }




    protected final PropertyDelegate propertyDelegate;
    private int PROGRESS = 0;
    private int MAX_PROGRESS = 72;


    public PowderStationBlockEntity( BlockPos pos, BlockState state) {
        super(SalonicraftBlockEntities.POWDER_STATION_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> PowderStationBlockEntity.this.PROGRESS;
                    case 1 -> PowderStationBlockEntity.this.MAX_PROGRESS;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PowderStationBlockEntity.this.PROGRESS = value;
                    case 1 -> PowderStationBlockEntity.this.MAX_PROGRESS = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("salonicraft.powder_station.progress", PROGRESS);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        PROGRESS = nbt.getInt("salonicraft.powder_station.progress");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Powder");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PowderStationScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        if(isOutputAvailable()) {
            if(this.hasAvailableRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(craftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.PROGRESS = 0;
    }

    private void craftItem() {
        Optional<RecipeEntry<PowderStationRecipe>> recipe = getCurrentRecipe();
        if (getStack(SLOTS.INPUT).isOf(Items.MILK_BUCKET)) {
            this.setStack(SLOTS.INPUT, new ItemStack(Items.BUCKET));
        } else {
            this.removeStack(SLOTS.INPUT, 1);
        }


        this.setStack(SLOTS.OUTPUT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(SLOTS.OUTPUT).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    private boolean craftingFinished() {
        return PROGRESS >= MAX_PROGRESS;
    }

    private void increaseCraftProgress() {
        PROGRESS++;
    }

    private boolean hasAvailableRecipe() {
        Optional<RecipeEntry<PowderStationRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canOutputAmount(recipe.get().value().getResult(null))
                && canOutputItem(recipe.get().value().getResult(null).getItem());
    }
    private Optional<RecipeEntry<PowderStationRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(PowderStationRecipe.Type.INSTANCE, inv, getWorld());
    }


    private boolean canOutputItem(Item item) {
        return this.getStack(SLOTS.OUTPUT).getItem() == item || this.getStack(SLOTS.OUTPUT).isEmpty();
    }

    private boolean canOutputAmount(ItemStack result) {
        return this.getStack(SLOTS.OUTPUT).getCount() + result.getCount() <= getStack(SLOTS.OUTPUT).getMaxCount();
    }

    private boolean isOutputAvailable() {
        return this.getStack(SLOTS.OUTPUT).isEmpty() || this.getStack(SLOTS.OUTPUT).getCount() < this.getStack(SLOTS.OUTPUT).getMaxCount();
    }

    public ItemStack getRenderStack() {
        if(this.getStack(SLOTS.OUTPUT).isEmpty()) {
            return this.getStack(SLOTS.INPUT);
        } else {
            return this.getStack(SLOTS.OUTPUT);
        }
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound initialData = new NbtCompound();
        Inventories.writeNbt(initialData, inventory);
        return initialData;
    }
}
