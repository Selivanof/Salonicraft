package com.oktavios.salonicraft.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.oktavios.salonicraft.Salonicraft;
import com.oktavios.salonicraft.block.entity.BeverageBrewerBlockEntity;
import com.oktavios.salonicraft.block.entity.PowderStationBlockEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

public class BeverageBrewerRecipe implements Recipe<SimpleInventory> {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;
    private static final int MAX_INGREDIENTS = 3;
    private static final class RECIPE_ARRAY {
        private final static int CONTAINER_INPUT = 0;
        private final static int PRIMARY_INPUT = 1;
        private final static int SECONDARY_INPUT = 2;
    }

    public BeverageBrewerRecipe(List<Ingredient> ingredients, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient()) {
            return false;
        }
        Boolean hasContainer = recipeItems.get(RECIPE_ARRAY.CONTAINER_INPUT).test(inventory.getStack(BeverageBrewerBlockEntity.SLOTS.CONTAINER_INPUT));
        Boolean hasPrimary = recipeItems.get(RECIPE_ARRAY.PRIMARY_INPUT).test(inventory.getStack(BeverageBrewerBlockEntity.SLOTS.PRIMARY_INPUT));
        Boolean hasSecondary = recipeItems.size() <= 2 ? true : recipeItems.get(RECIPE_ARRAY.SECONDARY_INPUT).test(inventory.getStack(BeverageBrewerBlockEntity.SLOTS.SECONDARY_INPUT));
        return  hasContainer && hasPrimary && hasSecondary;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<BeverageBrewerRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "beverage";
    }

    public static class Serializer implements RecipeSerializer<BeverageBrewerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "beverage";

        public static final Codec<BeverageBrewerRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, MAX_INGREDIENTS).fieldOf("ingredients").forGetter(BeverageBrewerRecipe::getIngredients),
                ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(r -> r.output)
        ).apply(in, BeverageBrewerRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
        }

        @Override
        public Codec<BeverageBrewerRecipe> codec() {
            return CODEC;
        }

        @Override
        public BeverageBrewerRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new BeverageBrewerRecipe(inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, BeverageBrewerRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }

            buf.writeItemStack(recipe.getResult(null));
        }
    }
}