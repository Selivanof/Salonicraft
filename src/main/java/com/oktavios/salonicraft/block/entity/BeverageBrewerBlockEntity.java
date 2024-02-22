package com.oktavios.salonicraft.block.entity;

import com.oktavios.salonicraft.block.custom.BeverageBrewerBlock;
import com.oktavios.salonicraft.networking.SalonicraftPackages;
import com.oktavios.salonicraft.recipe.BeverageBrewerRecipe;
import com.oktavios.salonicraft.screen.BeverageBrewerScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

// Relevant wiki pages
// https://fabricmc.net/wiki/tutorial:transfer-api_storage
// https://fabricmc.net/wiki/tutorial:transfer-api_fluid-containing-items
// https://fabricmc.net/wiki/tutorial:transfer-api_fluid_implementation
//
public class BeverageBrewerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> INVENTORY = DefaultedList.ofSize(5, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int PROGRESS = 0;
    private int MAX_PROGRESS = 90;

    public static final int FLUID_CAPACITY = 16000; // Measured in Buckets
    private final int GLASS_CAPACITY = 250; // Measured in mB
    private final int BUCKET_CAPACITY = 1000; // Measured in mB

    // Slots
    public static final class SLOTS {
        public static final int WATER_INPUT = 0;
        public static final int CONTAINER_INPUT = 1;
        public static final int PRIMARY_INPUT = 2;
        public static final int SECONDARY_INPUT = 3;
        public static final int OUTPUT = 4;
    }

    private Optional<RecipeEntry<BeverageBrewerRecipe>> current_recipe;


    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<FluidVariant>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return FLUID_CAPACITY;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            if(!world.isClient()) {
                sendFluidPacket();
            }
        }
    };


    private void sendFluidPacket() {
        PacketByteBuf data = PacketByteBufs.create();
        fluidStorage.variant.toPacket(data);
        data.writeLong(fluidStorage.amount);
        data.writeBlockPos(getPos());
        world.setBlockState(getPos(),world.getBlockState(getPos()).with(BeverageBrewerBlock.CAPACITY,getCapacityPropertyValue(fluidStorage.amount)));

        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {;
            ServerPlayNetworking.send(player, SalonicraftPackages.FLUID_SYNC, data);
        }
    }


    public BeverageBrewerBlockEntity(BlockPos pos, BlockState state) {
        super(SalonicraftBlockEntities.BEVERAGE_BREWER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> BeverageBrewerBlockEntity.this.PROGRESS;
                    case 1 -> BeverageBrewerBlockEntity.this.MAX_PROGRESS;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0 -> BeverageBrewerBlockEntity.this.PROGRESS = value;
                    case 1 -> BeverageBrewerBlockEntity.this.MAX_PROGRESS = value;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    public void setFluidLevel (FluidVariant fluidVariant, long fluidLevel) {
        this.fluidStorage.variant = fluidVariant;
        this.fluidStorage.amount = fluidLevel;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.INVENTORY;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Brewer");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new BeverageBrewerScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, INVENTORY);
        nbt.putInt("salonicraft.beverage_brewer.progress", PROGRESS);
        nbt.put("salonicraft.beverage_brewer.fluidVariant", fluidStorage.variant.toNbt());
        nbt.putLong("salonicraft.beverage_brewer.fluidAmount", fluidStorage.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, INVENTORY);
        super.readNbt(nbt);
        PROGRESS = nbt.getInt("salonicraft.beverage_brewer.progress");
        fluidStorage.variant = FluidVariant.fromNbt((NbtCompound) nbt.get("salonicraft.beverage_brewer.fluidVariant"));
        fluidStorage.amount = nbt.getLong("salonicraft.beverage_brewer.fluidAmount");
        nbt.putLong("salonicraft.beverage_brewer.fluidAmount", fluidStorage.amount);
    }

    private void resetProgress() {
        this.PROGRESS = 0;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }
        if(isOutputAvailable()) {
            if(this.hasAvailableRecipe() &&  this.hasEnoughFluid()) {
                if (getCurrentRecipe().isPresent() && !getCurrentRecipe().equals(current_recipe)) {
                    resetProgress();
                    current_recipe = getCurrentRecipe();
                }
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(craftingFinished()) {
                    this.craftItem();
                    this.removeFluid(GLASS_CAPACITY);
                    this.resetProgress();
                    markDirty();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }

        if (hasFluidBucketInInput()) {
            addFluid(BUCKET_CAPACITY);
        }
    }

    private void removeFluid(int mB) {
        try(Transaction transaction = Transaction.openOuter()) {
            fluidStorage.extract(FluidVariant.of(Fluids.WATER), mB, transaction);
            transaction.commit();
        }
    }

    private void addFluid(int mB) {
        try(Transaction transaction = Transaction.openOuter()) {
            fluidStorage.insert(FluidVariant.of(Fluids.WATER), mB, transaction);
            transaction.commit();
            setStack(SLOTS.WATER_INPUT, new ItemStack(Items.BUCKET));
        }
    }

    private boolean hasFluidBucketInInput() {
        return getStack(SLOTS.WATER_INPUT).getItem() == Items.WATER_BUCKET;
    }

    private boolean hasEnoughFluid() {
        return fluidStorage.amount >= GLASS_CAPACITY;
    }

    private boolean craftingFinished() {
        return PROGRESS >= MAX_PROGRESS;
    }

    private void increaseCraftProgress() {
        PROGRESS++;
    }

    private void craftItem() {
        Optional<RecipeEntry<BeverageBrewerRecipe>> recipe = getCurrentRecipe();
        this.removeStack(SLOTS.PRIMARY_INPUT, 1);
        if (recipe.get().value().getIngredients().size() > 2) {
            this.removeStack(SLOTS.SECONDARY_INPUT, 1);
        }

        this.setStack(SLOTS.OUTPUT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(SLOTS.OUTPUT).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound initialData = new NbtCompound();
        initialData.putLong("salonicraft.beverage_brewer.fluidAmount", fluidStorage.amount);
        initialData.put("salonicraft.beverage_brewer.fluidVariant", fluidStorage.variant.toNbt());
        Inventories.writeNbt(initialData, INVENTORY);
        return initialData;
    }

    private boolean hasAvailableRecipe() {
        Optional<RecipeEntry<BeverageBrewerRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canOutputAmount(recipe.get().value().getResult(null))
                && canOutputItem(recipe.get().value().getResult(null).getItem());
        
    }

    private Optional<RecipeEntry<BeverageBrewerRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(BeverageBrewerRecipe.Type.INSTANCE, inv, getWorld());
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

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    public FluidVariant getFluidVariant() {
        return fluidStorage.variant;
    }
    public long getFluidAmount() {
        return fluidStorage.amount;
    }

    private int getCapacityPropertyValue(long fluidAmount) {
        if (fluidAmount == 0) {
            return 0;
        } else {
            double percentage = (double) fluidAmount / FLUID_CAPACITY ;
            if (percentage <= 0.5) {
                return 1;
            } else if (percentage < 1) {
                return 2;
            } else {
                return 3;
            }
        }
    }

    public ItemStack getRenderStack() {
        if(this.getStack(SLOTS.OUTPUT).isEmpty()) {
            return this.getStack(SLOTS.CONTAINER_INPUT);
        } else {
            return this.getStack(SLOTS.OUTPUT);
        }
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }
}
