package net.zusz.zcoffeecraft2.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZCoffeeCraft2.MOD_ID);

    public static final Supplier<CreativeModeTab> COFFEE_ITEMS_TAB = CREATIVE_MODE_TAB.register("coffee_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CUP_OF_COFFEE.get()))
                    .title(Component.translatable("creativetab.zcoffeecraft2.coffee_items"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.ARABICA_COFFEE_CHERRY);
                        output.accept(ModItems.RAW_ARABICA_COFFEE_BEAN);
                        output.accept(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN);
                        output.accept(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN);
                        output.accept(ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN);
                        output.accept(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK);

                        output.accept(ModItems.ROBUSTA_COFFEE_CHERRY);
                        output.accept(ModItems.RAW_ROBUSTA_COFFEE_BEAN);
                        output.accept(ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN);
                        output.accept(ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN);
                        output.accept(ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN);
                        output.accept(ModBlocks.RAW_ROBUSTA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK);

                        output.accept(ModItems.LIBERICA_COFFEE_CHERRY);
                        output.accept(ModItems.RAW_LIBERICA_COFFEE_BEAN);
                        output.accept(ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN);
                        output.accept(ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN);
                        output.accept(ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN);
                        output.accept(ModBlocks.RAW_LIBERICA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK);

                        output.accept(ModItems.EXCELSA_COFFEE_CHERRY);
                        output.accept(ModItems.RAW_EXCELSA_COFFEE_BEAN);
                        output.accept(ModItems.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN);
                        output.accept(ModItems.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN);
                        output.accept(ModItems.DARK_ROASTED_EXCELSA_COFFEE_BEAN);
                        output.accept(ModBlocks.RAW_EXCELSA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SACK);
                        output.accept(ModBlocks.DARK_ROASTED_EXCELSA_COFFEE_BEAN_SACK);

                        output.accept(ModItems.LIGHT_ARABICA_GROUND_COFFEE);
                        output.accept(ModItems.MEDIUM_ARABICA_GROUND_COFFEE);
                        output.accept(ModItems.DARK_ARABICA_GROUND_COFFEE);

                        output.accept(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE);
                        output.accept(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE);
                        output.accept(ModItems.DARK_ROBUSTA_GROUND_COFFEE);

                        output.accept(ModItems.LIGHT_LIBERICA_GROUND_COFFEE);
                        output.accept(ModItems.MEDIUM_LIBERICA_GROUND_COFFEE);
                        output.accept(ModItems.DARK_LIBERICA_GROUND_COFFEE);

                        output.accept(ModItems.LIGHT_EXCELSA_GROUND_COFFEE);
                        output.accept(ModItems.MEDIUM_EXCELSA_GROUND_COFFEE);
                        output.accept(ModItems.DARK_EXCELSA_GROUND_COFFEE);

                        output.accept(ModBlocks.COFFEE_MACHINE);
                        output.accept(ModBlocks.COFFEE_GRINDER);

                        output.accept(ModItems.WHISK);

                        output.accept(ModItems.STEAMED_MILK);
                        output.accept(ModItems.MILK_FOAM);
                        output.accept(ModItems.WHIPPED_CREAM);

                        output.accept(ModItems.COFFEE_CUP);
                        output.accept(ModItems.CUP_OF_COFFEE);
                        output.accept(ModItems.HOT_CHOCOLATE);

                        output.accept(ModItems.COFFEE_GUIDE);

                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}