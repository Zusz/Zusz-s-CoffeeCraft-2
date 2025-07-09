package net.zusz.zcoffeecraft2.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.compat.CoffeeMachineRecipe;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineScreen;

import java.util.List;
import static net.zusz.zcoffeecraft2.compat.CoffeeMachineRecipeCategory.COFFEE_MACHINE_RECIPE_RECIPE_TYPE;

@JeiPlugin
public class JEIZCoffeeCraft2Plugin implements IModPlugin {



    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
       registration.addRecipeCategories((new CoffeeMachineRecipeCategory(
               registration.getJeiHelpers().getGuiHelper())));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
            List<CoffeeMachineRecipe> coffeeMachineRecipes = CoffeeMachineRecipeManager.getAllRecipes();
            registration.addRecipes(COFFEE_MACHINE_RECIPE_RECIPE_TYPE, coffeeMachineRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CoffeeMachineScreen.class, 93, 30, 22, 20, COFFEE_MACHINE_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COFFEE_MACHINE.asItem()),
                COFFEE_MACHINE_RECIPE_RECIPE_TYPE);
    }
}

