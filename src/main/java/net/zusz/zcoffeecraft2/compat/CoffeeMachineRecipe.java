package net.zusz.zcoffeecraft2.compat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
//this is in compatibility, because its only needed for JEI, as the Coffee Machine Block Entiy itself uses no recipes
public class CoffeeMachineRecipe {
        private final Ingredient input;
        private final Ingredient container;
        private final Ingredient fluid;
        private final Ingredient ing1;
        private final Ingredient ing2;
        private final Ingredient ing3;
        private final Ingredient ing4;
        private final ItemStack output;
        private final Ingredient fluidContainerOutput;



    public CoffeeMachineRecipe(Ingredient input,
                               Ingredient container,
                               Ingredient fluid,
                               Ingredient ing1,
                               Ingredient ing2,
                               Ingredient ing3,
                               Ingredient ing4,
                               ItemStack output,
                               Ingredient fluidContainerOutput)
    {
        this.input = input;
        this.container = container;
        this.fluid = fluid;
        this.ing1 = ing1;
        this.ing2 = ing2;
        this.ing3 = ing3;
        this.ing4 = ing4;
        this.output = output;
        this.fluidContainerOutput = fluidContainerOutput;
    }

        public Ingredient getInput() {
            return input;
        }

        public Ingredient getContainer() {
            return container;
        }

        public Ingredient getFluid()
        {
            return fluid;
        }

        public Ingredient getIngredient1() {
            return ing1;
        }
        public Ingredient getIngredient2() {
            return ing2;
        }

        public Ingredient getIngredient3() {
            return ing3;
        }

        public Ingredient getIngredient4() {
            return ing4;
        }

        public ItemStack getOutput() {
            return output;
        }

        public Ingredient getFluidContainerOutput() {
        return fluidContainerOutput;
    }

}