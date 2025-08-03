package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, ZCoffeeCraft2.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_ARABICA_COFFEE_BEAN.asItem());
        basicItem(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.asItem());
        basicItem(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.asItem());
        basicItem(ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.asItem());
        basicItem(ModItems.LIGHT_ARABICA_GROUND_COFFEE.asItem());
        basicItem(ModItems.MEDIUM_ARABICA_GROUND_COFFEE.asItem());
        basicItem(ModItems.DARK_ARABICA_GROUND_COFFEE.asItem());
        basicItem(ModItems.ARABICA_COFFEE_CHERRY.asItem());
        basicItem(ModItems.COFFEE_CUP.asItem());
        basicItem(ModItems.WHISK.asItem());
        basicItem(ModItems.STEAMED_MILK.asItem());
        basicItem(ModItems.MILK_FOAM.asItem());
    }

}
