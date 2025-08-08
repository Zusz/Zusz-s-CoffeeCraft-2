package net.zusz.zcoffeecraft2.compat;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;
import net.zusz.zcoffeecraft2.block.entity.CoffeeMachineMethods;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoffeeMachineRecipeManager {
    public static List<CoffeeMachineRecipe> getAllRecipes() {
        ItemStack waterBottleStack = new ItemStack(Items.POTION);
        waterBottleStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));
        Ingredient water = Ingredient.of(waterBottleStack, new ItemStack(Items.WATER_BUCKET));
        Ingredient water_output = Ingredient.of(Items.GLASS_BOTTLE, Items.BUCKET);
        //Ingredient allGrounds = Ingredient.of(ModItems.LIGHT_ARABICA_GROUND_COFFEE, ModItems.MEDIUM_ARABICA_GROUND_COFFEE, ModItems.DARK_ARABICA_GROUND_COFFEE);

        List<CoffeeMachineRecipe>CoffeeMachineRecipes = new ArrayList<>();
        List<ItemLike> groundCoffees = new ArrayList<>(List.of(
                ModItems.LIGHT_ARABICA_GROUND_COFFEE,
                ModItems.MEDIUM_ARABICA_GROUND_COFFEE,
                ModItems.DARK_ARABICA_GROUND_COFFEE,
                ModItems.LIGHT_ROBUSTA_GROUND_COFFEE,
                ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE,
                ModItems.DARK_ROBUSTA_GROUND_COFFEE

        ));
        List<ItemLike> containers = new ArrayList<>(List.of(
                ModItems.COFFEE_CUP
        ));
        List<Ingredient> fluidItems = new ArrayList<>(List.of(
                water
        ));
        List<List<ItemLike>> ingredientSets = new ArrayList<>(List.of(
                List.of(),
                List.of(Items.SUGAR),
                List.of(ModItems.WHIPPED_CREAM),
                List.of(ModItems.WHIPPED_CREAM, Items.SUGAR),
                List.of(ModItems.MILK_FOAM),
                List.of(ModItems.MILK_FOAM, Items.SUGAR),
                List.of(ModItems.STEAMED_MILK),
                List.of(ModItems.STEAMED_MILK, Items.SUGAR),
                List.of(ModItems.STEAMED_MILK, ModItems.MILK_FOAM),
                List.of(ModItems.STEAMED_MILK, ModItems.MILK_FOAM, Items.SUGAR)
        ));

        for(ItemLike ground : groundCoffees){
            for(ItemLike container : containers) {
                for(Ingredient fluidItem : fluidItems) {
                    for (List<ItemLike> ingredients : ingredientSets) {
                        Ingredient ingredient1 = ingredients.size() > 0 ? Ingredient.of(ingredients.get(0)) : Ingredient.EMPTY;
                        Ingredient ingredient2 = ingredients.size() > 1 ? Ingredient.of(ingredients.get(1)) : Ingredient.EMPTY;
                        Ingredient ingredient3 = ingredients.size() > 2 ? Ingredient.of(ingredients.get(2)) : Ingredient.EMPTY;
                        Ingredient ingredient4 = ingredients.size() > 3 ? Ingredient.of(ingredients.get(3)) : Ingredient.EMPTY;
                        Item ing1 = ingredients.size() > 0 ? ingredients.get(0).asItem() : null;
                        Item ing2 = ingredients.size() > 1 ? ingredients.get(1).asItem() : null;
                        Item ing3 = ingredients.size() > 2 ? ingredients.get(2).asItem() : null;
                        Item ing4 = ingredients.size() > 3 ? ingredients.get(3).asItem() : null;

                        Ingredient fluidOutput = Ingredient.EMPTY;
                        if (fluidItem == water) {
                            fluidOutput = water_output;
                        }

                        CoffeeMachineRecipes.add(
                                new CoffeeMachineRecipe(
                                        Ingredient.of(ground),
                                        Ingredient.of(container),
                                        fluidItem,
                                        ingredient1,
                                        ingredient2,
                                        ingredient3,
                                        ingredient4,
                                        getResult(ground.asItem(), container.asItem(), null, ing1, ing2, ing3, ing4),
                                        fluidOutput
                                )
                        );
                    }
                }
            }
        }


        return CoffeeMachineRecipes;

        /*List.of(

                //Espresso Arabica
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.LIGHT_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, null, null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.MEDIUM_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, null, null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.DARK_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, null, null, null, null),
                        water_output
                ),
                //Espresso Robusta
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, null, null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, null, null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.DARK_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, null, null, null, null),
                        water_output
                ),

                //Macchiato Arabica
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.MILK_FOAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.LIGHT_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.MILK_FOAM.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.MILK_FOAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.MEDIUM_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.MILK_FOAM.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.MILK_FOAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.DARK_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.MILK_FOAM.asItem(), null, null, null),
                        water_output
                ),
                //Macchiato Robusta
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.MILK_FOAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.MILK_FOAM.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.MILK_FOAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.MILK_FOAM.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.MILK_FOAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.DARK_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.MILK_FOAM.asItem(), null, null, null),
                        water_output
                ),

                //Con Panna Arabica
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.WHIPPED_CREAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.LIGHT_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.WHIPPED_CREAM.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.WHIPPED_CREAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.MEDIUM_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.WHIPPED_CREAM.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.WHIPPED_CREAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.DARK_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.WHIPPED_CREAM.asItem(), null, null, null),
                        water_output
                ),
                //Con Panna Robusta
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.WHIPPED_CREAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.WHIPPED_CREAM.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.WHIPPED_CREAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.WHIPPED_CREAM.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.WHIPPED_CREAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.DARK_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.WHIPPED_CREAM.asItem(), null, null, null),
                        water_output
                ),

                //Flat White Arabica
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.STEAMED_MILK),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.LIGHT_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.STEAMED_MILK.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.STEAMED_MILK),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.MEDIUM_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.STEAMED_MILK.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.STEAMED_MILK),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.DARK_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.STEAMED_MILK.asItem(), null, null, null),
                        water_output
                ),
                //Flat White Robusta
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.STEAMED_MILK),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.STEAMED_MILK.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.STEAMED_MILK),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.STEAMED_MILK.asItem(), null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ROBUSTA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.STEAMED_MILK),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.DARK_ROBUSTA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, ModItems.STEAMED_MILK.asItem(), null, null, null),
                        water_output
                )

        );*/

    }

    public static ItemStack getResult(Item input, Item container, Item fluidInputItem, Item ingredient1, Item ingredient2, Item ingredient3, Item ingredient4) {
        ItemStack output = setOutputItemBasedOnContainer(container);

        output = addBeanAndRoastToOutput(output, input);

        List<String> ingredients = new ArrayList<>(List.of());

        ingredients = addToIngredientsList(ingredients, ingredient1);
        ingredients = addToIngredientsList(ingredients, ingredient2);
        ingredients = addToIngredientsList(ingredients, ingredient3);
        ingredients = addToIngredientsList(ingredients, ingredient4);

        output.set(ModDataComponents.INGREDIENTS, ingredients);

        return output;
    }


    //methods from CoffeeMachineMethods, slightly modified (now null inputs don't set output to null, just don't do anything)
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
        } else if (bean == ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "light");
            output.set(ModDataComponents.BEAN, "robusta");
        } else if (bean == ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "medium");
            output.set(ModDataComponents.BEAN, "robusta");
        }  else if (bean == ModItems.DARK_ROBUSTA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "dark");
            output.set(ModDataComponents.BEAN, "robusta");
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
}
