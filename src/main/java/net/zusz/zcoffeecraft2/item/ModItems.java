package net.zusz.zcoffeecraft2.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.item.component.UseRemainder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.item.custom.SteamedMilkItem;
import net.zusz.zcoffeecraft2.item.custom.coffeeitem.CoffeeItem;
import net.zusz.zcoffeecraft2.item.custom.ModFoodProperties;
import net.zusz.zcoffeecraft2.item.custom.WhiskItem;
import net.zusz.zcoffeecraft2.item.custom.guidebook.GuideBookItem;
import net.zusz.zcoffeecraft2.item.custom.hot_chocolate.HotChocolate;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZCoffeeCraft2.MOD_ID);

    public static final DeferredItem<Item> RAW_ARABICA_COFFEE_BEAN = ITEMS.registerItem("raw_arabica_coffee_bean", //arabica
            Item::new);
    public static final DeferredItem<Item> LIGHT_ROASTED_ARABICA_COFFEE_BEAN = ITEMS.registerItem("light_roasted_arabica_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> MEDIUM_ROASTED_ARABICA_COFFEE_BEAN = ITEMS.registerItem("medium_roasted_arabica_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> DARK_ROASTED_ARABICA_COFFEE_BEAN = ITEMS.registerItem("dark_roasted_arabica_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> LIGHT_ARABICA_GROUND_COFFEE = ITEMS.registerItem("light_arabica_ground_coffee",
            Item::new);
    public static final DeferredItem<Item> MEDIUM_ARABICA_GROUND_COFFEE = ITEMS.registerItem("medium_arabica_ground_coffee",
            Item::new);
    public static final DeferredItem<Item> DARK_ARABICA_GROUND_COFFEE = ITEMS.registerItem("dark_arabica_ground_coffee",
            Item::new);

    public static final DeferredItem<Item> RAW_ROBUSTA_COFFEE_BEAN = ITEMS.registerItem("raw_robusta_coffee_bean", //robusta
            Item::new);
    public static final DeferredItem<Item> LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN = ITEMS.registerItem("light_roasted_robusta_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN = ITEMS.registerItem("medium_roasted_robusta_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> DARK_ROASTED_ROBUSTA_COFFEE_BEAN = ITEMS.registerItem("dark_roasted_robusta_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> LIGHT_ROBUSTA_GROUND_COFFEE = ITEMS.registerItem("light_robusta_ground_coffee",
            Item::new);
    public static final DeferredItem<Item> MEDIUM_ROBUSTA_GROUND_COFFEE = ITEMS.registerItem("medium_robusta_ground_coffee",
            Item::new);
    public static final DeferredItem<Item> DARK_ROBUSTA_GROUND_COFFEE = ITEMS.registerItem("dark_robusta_ground_coffee",
            Item::new);

    public static final DeferredItem<Item> RAW_LIBERICA_COFFEE_BEAN = ITEMS.registerItem("raw_liberica_coffee_bean", //robusta
            Item::new);
    public static final DeferredItem<Item> LIGHT_ROASTED_LIBERICA_COFFEE_BEAN = ITEMS.registerItem("light_roasted_liberica_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN = ITEMS.registerItem("medium_roasted_liberica_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> DARK_ROASTED_LIBERICA_COFFEE_BEAN = ITEMS.registerItem("dark_roasted_liberica_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> LIGHT_LIBERICA_GROUND_COFFEE = ITEMS.registerItem("light_liberica_ground_coffee",
            Item::new);
    public static final DeferredItem<Item> MEDIUM_LIBERICA_GROUND_COFFEE = ITEMS.registerItem("medium_liberica_ground_coffee",
            Item::new);
    public static final DeferredItem<Item> DARK_LIBERICA_GROUND_COFFEE = ITEMS.registerItem("dark_liberica_ground_coffee",
            Item::new);

    public static final DeferredItem<Item> RAW_EXCELSA_COFFEE_BEAN = ITEMS.registerItem("raw_excelsa_coffee_bean", //robusta
            Item::new);
    public static final DeferredItem<Item> LIGHT_ROASTED_EXCELSA_COFFEE_BEAN = ITEMS.registerItem("light_roasted_excelsa_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN = ITEMS.registerItem("medium_roasted_excelsa_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> DARK_ROASTED_EXCELSA_COFFEE_BEAN = ITEMS.registerItem("dark_roasted_excelsa_coffee_bean",
            Item::new);
    public static final DeferredItem<Item> LIGHT_EXCELSA_GROUND_COFFEE = ITEMS.registerItem("light_excelsa_ground_coffee",
            Item::new);
    public static final DeferredItem<Item> MEDIUM_EXCELSA_GROUND_COFFEE = ITEMS.registerItem("medium_excelsa_ground_coffee",
            Item::new);
    public static final DeferredItem<Item> DARK_EXCELSA_GROUND_COFFEE = ITEMS.registerItem("dark_excelsa_ground_coffee",
            Item::new);



    public static final DeferredItem<Item> COFFEE_CUP = ITEMS.registerItem("coffee_cup",//other items
            Item::new);
    public static final DeferredItem<Item> WHISK = ITEMS.registerItem("whisk",
            WhiskItem::new);
    public static final DeferredItem<Item> STEAMED_MILK = ITEMS.registerItem("steamed_milk",
            SteamedMilkItem::new);
    public static final DeferredItem<Item> MILK_FOAM = ITEMS.registerItem("milk_foam",
            (properties) -> new Item(properties.stacksTo(1)));
    public static final DeferredItem<Item> WHIPPED_CREAM = ITEMS.registerItem("whipped_cream",
            Item::new);

    public static final DeferredItem<Item> ARABICA_COFFEE_CHERRY = ITEMS.registerItem("arabica_coffee_cherry",//cherry items
            (properties) -> new BlockItem(ModBlocks.ARABICA_COFFEE_BUSH.get(), properties));
    public static final DeferredItem<Item> ROBUSTA_COFFEE_CHERRY = ITEMS.registerItem("robusta_coffee_cherry",
            (properties) -> new BlockItem(ModBlocks.ROBUSTA_COFFEE_BUSH.get(), properties));
    public static final DeferredItem<Item> LIBERICA_COFFEE_CHERRY = ITEMS.registerItem("liberica_coffee_cherry",
            (properties) -> new BlockItem(ModBlocks.LIBERICA_COFFEE_BUSH.get(), properties));
    public static final DeferredItem<Item> EXCELSA_COFFEE_CHERRY = ITEMS.registerItem("excelsa_coffee_cherry",
            (properties) -> new BlockItem(ModBlocks.EXCELSA_COFFEE_BUSH.get(), properties));

    public static final DeferredItem<Item> CUP_OF_COFFEE = ITEMS.registerItem("cup_of_coffee",//unique items
            (properties) -> new CoffeeItem(properties.food(ModFoodProperties.CUP_OF_COFFEE).component(DataComponents.USE_REMAINDER, new UseRemainder(new ItemStack(Items.APPLE, 3)))));
    public static final DeferredItem<Item> HOT_CHOCOLATE = ITEMS.registerItem("hot_chocolate",
            (properties) -> new HotChocolate(properties.food(ModFoodProperties.CUP_OF_COFFEE).component(DataComponents.USE_REMAINDER, new UseRemainder(new ItemStack(Items.APPLE, 3)))));

    public static final DeferredItem<Item> COFFEE_GUIDE = ITEMS.registerItem("coffee_guide_book",
            (properties) -> new GuideBookItem(properties.stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
