package net.zusz.zcoffeecraft2.block.entity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.zusz.zcoffeecraft2.api.coffeerecipes.CoffeeRecipe;
import net.zusz.zcoffeecraft2.api.coffeerecipes.CoffeeRecipeRegistry;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.api.coffeeingredients.CoffeeIngredientRegistry;
import net.zusz.zcoffeecraft2.api.groundcoffee.GroundCoffee;
import net.zusz.zcoffeecraft2.api.groundcoffee.GroundCoffeeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoffeeMachineMethods {
    public static List<String> addToIngredientsList(List<String> ingredients, Item item) {
        Optional<String> stringFromItem = CoffeeIngredientRegistry.getStringFromItem(item);
        if (stringFromItem.isPresent()) {
            ingredients.add(stringFromItem.get());
        }
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
        return ItemStack.EMPTY;
    }

    public static boolean isValidCoffeeCombination(List<String>ingredients) {
        return CoffeeRecipeRegistry.isValid(ingredients);
    }

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

    public static ItemStack setOutputItemBasedOnContainer(Item container) {
        ItemStack output = null;
        if (container == ModItems.COFFEE_CUP.get()){
            output = new ItemStack(ModItems.CUP_OF_COFFEE.get(), 1);
        }
        return output;
    }
    public static ItemStack getOutput(Item input, Item container, Item fluidInputItem, Item ingredient1, Item ingredient2, Item ingredient3, Item ingredient4) {

        List<String> ingredients = new ArrayList<>(List.of());

        ingredients = addToIngredientsList(ingredients, ingredient1);
        ingredients = addToIngredientsList(ingredients, ingredient2);
        ingredients = addToIngredientsList(ingredients, ingredient3);
        ingredients = addToIngredientsList(ingredients, ingredient4);

        Optional<CoffeeRecipe> recipeOpt = CoffeeRecipeRegistry.getRecipe(ingredients);
        if (recipeOpt.isPresent()) {
            CoffeeRecipe recipe = recipeOpt.get();

            ItemStack output = new ItemStack(recipe.outputItem());

            output.set(ModDataComponents.INGREDIENTS, ingredients);

            if(recipe.requiresBean()) {
                output = addBeanAndRoastToOutput(output, input);
                return output;
            } else if(input == Items.AIR) {
                return output;
            }

        }
        return ItemStack.EMPTY;
        }
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
