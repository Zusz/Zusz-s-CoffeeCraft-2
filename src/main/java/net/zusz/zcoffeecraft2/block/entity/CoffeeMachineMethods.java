package net.zusz.zcoffeecraft2.block.entity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.zusz.zcoffeecraft2.item.custom.coffeeitem.coffeerecipes.CoffeeRecipeRegistry;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.item.custom.coffeeitem.coffeeingredients.CoffeeIngredientRegistry;
import net.zusz.zcoffeecraft2.item.custom.coffeeitem.groundcoffee.GroundCoffee;
import net.zusz.zcoffeecraft2.item.custom.coffeeitem.groundcoffee.GroundCoffeeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoffeeMachineMethods {

    /*public static boolean isIngredientValid(Item ingredient, String inputType) {

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
            validIngredients.add(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.get());
            validIngredients.add(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.get());
            validIngredients.add(ModItems.DARK_ROBUSTA_GROUND_COFFEE.get());
        }
        return validIngredients.contains(ingredient);
    }*/

    public static List<String> addToIngredientsList(List<String> ingredients, Item item) {
        Optional<String> stringFromItem = CoffeeIngredientRegistry.getStringFromItem(item);
        if (stringFromItem.isPresent()) {
            ingredients.add(stringFromItem.get());
        }

        /*if (item == Items.SUGAR) {
            ingredients.add("sugar");
        } else if (item == Items.HONEY_BOTTLE) {
            ingredients.add("honey");
        } else if (item == Items.MILK_BUCKET) {
            ingredients.add("milk");
        } else if (item == Items.COCOA_BEANS) {
            ingredients.add("chocolate");
        } else if (item == ModItems.WHIPPED_CREAM.asItem()) {
            ingredients.add("whipped_cream");
        } else if (item == ModItems.MILK_FOAM.asItem()) {
            ingredients.add("milk_foam");
        } else if (item == ModItems.STEAMED_MILK.asItem()) {
            ingredients.add("steamed_milk");
        }*/
        return ingredients;
    }


    public static ItemStack addBeanAndRoastToOutput(ItemStack output, Item ground) {
        String bean= null;
        String roast = null;
        Optional<GroundCoffee> optGroundCoffee = GroundCoffeeRegistry.getGroundCoffee(ground);
        if (optGroundCoffee.isPresent()) {
            bean = optGroundCoffee.get().bean();
            roast = optGroundCoffee.get().roast();
            output.set(ModDataComponents.BEAN, bean);
            output.set(ModDataComponents.ROAST, roast);
            return output;
        }
        output = null;

        /*
        if (ground == ModItems.LIGHT_ARABICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "light");
            output.set(ModDataComponents.BEAN, "arabica");
        } else if (ground == ModItems.MEDIUM_ARABICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "medium");
            output.set(ModDataComponents.BEAN, "arabica");
        } else if (ground == ModItems.DARK_ARABICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "dark");
            output.set(ModDataComponents.BEAN, "arabica");
        } else if (ground == ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "light");
            output.set(ModDataComponents.BEAN, "robusta");
        } else if (ground == ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "medium");
            output.set(ModDataComponents.BEAN, "robusta");
        }  else if (ground == ModItems.DARK_ROBUSTA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "dark");
            output.set(ModDataComponents.BEAN, "robusta");
        } else if (ground == ModItems.LIGHT_LIBERICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "light");
            output.set(ModDataComponents.BEAN, "liberica");
        } else if (ground == ModItems.MEDIUM_LIBERICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "medium");
            output.set(ModDataComponents.BEAN, "liberica");
        } else if (ground == ModItems.DARK_LIBERICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "dark");
            output.set(ModDataComponents.BEAN, "liberica");
        } else {
            //System.out.println("OUtput nulled at bean");
            output = null;
        }*/
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
            } /*else {
                System.out.println("Output null at assigning ingredients");
            } */
        }

        return output;
    }

    public static boolean isValidCoffeeCombination(ItemStack output) {
        List<String> ingredients = output.get(ModDataComponents.INGREDIENTS);
        return CoffeeRecipeRegistry.isValid(ingredients);
    }

/*    public static boolean isValidCoffeeCombination(ItemStack output) {
        List<String> ingredients = output.get(ModDataComponents.INGREDIENTS);
        if ((ingredients.isEmpty()) || //Espresso
                (ingredients.size() == 1 &&ingredients.contains("sugar")) ||
                (ingredients.size() == 1 && ingredients.contains("milk_foam")) || //Macchiato
                (ingredients.size() == 2 && ingredients.contains("milk_foam") && ingredients.contains("sugar")) ||
                (ingredients.size() == 1 && ingredients.contains("whipped_cream")) || //Con Panna
                (ingredients.size() == 2 && ingredients.contains("whipped_cream") && ingredients.contains("sugar")) ||
                (ingredients.size() == 1 && ingredients.contains("steamed_milk")) || //Flat White
                (ingredients.size() == 2 && ingredients.contains("steamed_milk") && ingredients.contains("sugar")) ||
                (ingredients.size() == 2 && ingredients.contains("steamed_milk") && ingredients.contains("milk_foam")) || //Latte
                (ingredients.size() == 3 && ingredients.contains("steamed_milk") && ingredients.contains("milk_foam") && ingredients.contains("sugar")) ||
                (ingredients.size() == 2 && ingredients.contains("honey") && ingredients.contains("milk_foam")) || //Honey Raf
                (ingredients.size() == 3 && ingredients.contains("honey") && ingredients.contains("milk_foam") && ingredients.contains("sugar")) ||
                (ingredients.size() == 3 && ingredients.contains("chocolate") && ingredients.contains("steamed_milk") && ingredients.contains("whipped_cream")) || //Mocha
                (ingredients.size() == 4 && ingredients.contains("chocolate") && ingredients.contains("steamed_milk") && ingredients.contains("whipped_cream") && ingredients.contains("sugar"))
        ) {
            return true;
        }
        return false;
    }; */
}