package net.zusz.zcoffeecraft2.block.entity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineMenu;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoffeeMachineMethods {

    public static boolean isIngredientValid(Item ingredient, String inputType) {

        List<Item> validIngredients = new ArrayList<>(List.of());

        if (inputType == "ingredient") {
            validIngredients.add(ModItems.WHIPPED_CREAM.asItem());
            validIngredients.add(ModItems.MILK_FOAM.asItem());
            validIngredients.add(ModItems.STEAMED_MILK.asItem());
            validIngredients.add(Items.MILK_BUCKET.asItem());
            validIngredients.add(Items.SUGAR.asItem());
            validIngredients.add(Items.HONEY_BOTTLE.asItem());
            validIngredients.add(Items.COCOA_BEANS.asItem());
        } else if (inputType == "input") {
            validIngredients.add(ModItems.LIGHT_ARABICA_GROUND_COFFEE.get());
            validIngredients.add(ModItems.MEDIUM_ARABICA_GROUND_COFFEE.get());
            validIngredients.add(ModItems.DARK_ARABICA_GROUND_COFFEE.get());
        }
        return validIngredients.contains(ingredient);
    }

    public static List<String> addToIngredientsList(List<String> ingredients, Item item) {
        if (item == Items.SUGAR) {
            ingredients.add("sugar");
        } else if (item == Items.HONEY_BOTTLE) {
            ingredients.add("honey");
        } else if (item == Items.MILK_BUCKET) {
            ingredients.add("milk");
        } else if (item == Items.COCOA_BEANS) {
            ingredients.add("cocoa");
        } else if (item == ModItems.WHIPPED_CREAM.asItem()) {
            ingredients.add("whipped_cream");
        } else if (item == ModItems.MILK_FOAM.asItem()) {
            ingredients.add("milk_foam");
        } else if (item == ModItems.STEAMED_MILK.asItem()) {
            ingredients.add("steamed_milk");
        }
        return ingredients;
    }


    public static ItemStack addBeanAndRoastToOutput(ItemStack output, Item bean) {
        if (bean == ModItems.LIGHT_ARABICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "light");
            output.set(ModDataComponents.BEAN, "arabica");
        } else if (bean == ModItems.MEDIUM_ARABICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "medium");
            output.set(ModDataComponents.BEAN, "arabica");
        } else if (bean == ModItems.DARK_ARABICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "dark");
            output.set(ModDataComponents.BEAN, "arabica");
        } else {
            output = null;
        }
        return output;
    }

    public static ItemStack setOutputItemBasedOnContainer(Item containter) {
        ItemStack output = null;
        if (containter == ModItems.COFFEE_CUP.get()){
            output = new ItemStack(ModItems.CUP_OF_COFFEE.get(), 1);
        }
        return output;
    }
    public static ItemStack getOutput(Item input, Item container, Item fluidInputItem, Item ingredient1, Item ingredient2, Item ingredient3, Item ingredient4) {

        ItemStack output = setOutputItemBasedOnContainer(container);
        if(output != null) {
            output = addBeanAndRoastToOutput(output, input);

            List<String> ingredients = new ArrayList<>(List.of());

            ingredients = addToIngredientsList(ingredients, ingredient1);
            ingredients = addToIngredientsList(ingredients, ingredient2);
            ingredients = addToIngredientsList(ingredients, ingredient3);
            ingredients = addToIngredientsList(ingredients, ingredient4);

            if(output != null) {
                output.set(ModDataComponents.INGREDIENTS, ingredients);
            }
        }

        return output;
    }

    public static boolean isValidCoffeeCombination(ItemStack output) {
        List<String> ingredients = output.get(ModDataComponents.INGREDIENTS);
        if ((ingredients.size() == 0) || (ingredients.size() == 1 && ingredients.contains("milk_foam"))) {
            return true;
        }
        return false;
    };
}