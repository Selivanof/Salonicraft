package com.oktavios.salonicraft.recipe;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.oktavios.salonicraft.Salonicraft;
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

public class PowderStationRecipe implements Recipe<SimpleInventory> {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;
    private static final int MAX_INGREDIENTS = 1;
    private static final class RECIPE_ARRAY {
        private final static int POWDERABLE = 0;
    }

    public PowderStationRecipe(List<Ingredient> ingredients, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient()) {
            return false;
        }

        return recipeItems.get(RECIPE_ARRAY.POWDERABLE).test(inventory.getStack(PowderStationBlockEntity.SLOTS.INPUT));
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

    public static class Type implements RecipeType<PowderStationRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "powder";
    }

    public static class Serializer implements RecipeSerializer<PowderStationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "powder";

        public static final Codec<PowderStationRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, MAX_INGREDIENTS).fieldOf("ingredients").forGetter(PowderStationRecipe::getIngredients),
                ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(r -> r.output)
        ).apply(in, PowderStationRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
        }

        @Override
        public Codec<PowderStationRecipe> codec() {
            return CODEC;
        }

        @Override
        public PowderStationRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new PowderStationRecipe(inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, PowderStationRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }

            buf.writeItemStack(recipe.getResult(null));
        }
    }
}