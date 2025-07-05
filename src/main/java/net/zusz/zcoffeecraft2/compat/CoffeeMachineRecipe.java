package net.zusz.zcoffeecraft2.compat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
//this is in compatibility, because its only needed for JEI, as the Coffee Machine Block Entiy itself uses no recipes
public class CoffeeMachineRecipe {
        private final Ingredient input1;
        private final Ingredient input2;
        private final ItemStack output;

        public CoffeeMachineRecipe(Ingredient input1, Ingredient input2, ItemStack output) {
            this.input1 = input1;
            this.input2 = input2;
            this.output = output;
        }

        public Ingredient getInput1() {
            return input1;
        }

        public Ingredient getInput2() {
            return input2;
        }

        public ItemStack getOutput() {
            return output;
        }
}
