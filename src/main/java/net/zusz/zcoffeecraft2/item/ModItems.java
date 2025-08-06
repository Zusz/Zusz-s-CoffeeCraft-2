package net.zusz.zcoffeecraft2.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.item.custom.CoffeeItem;
import net.zusz.zcoffeecraft2.item.custom.ModFoodProperties;
import net.zusz.zcoffeecraft2.item.custom.WhiskItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZCoffeeCraft2.MOD_ID);

    public static final DeferredItem<Item> RAW_ARABICA_COFFEE_BEAN = ITEMS.register("raw_arabica_coffee_bean",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_ROASTED_ARABICA_COFFEE_BEAN = ITEMS.register("light_roasted_arabica_coffee_bean",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MEDIUM_ROASTED_ARABICA_COFFEE_BEAN = ITEMS.register("medium_roasted_arabica_coffee_bean",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DARK_ROASTED_ARABICA_COFFEE_BEAN = ITEMS.register("dark_roasted_arabica_coffee_bean",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_ARABICA_GROUND_COFFEE = ITEMS.register("light_arabica_ground_coffee",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MEDIUM_ARABICA_GROUND_COFFEE = ITEMS.register("medium_arabica_ground_coffee",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DARK_ARABICA_GROUND_COFFEE = ITEMS.register("dark_arabica_ground_coffee",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COFFEE_CUP = ITEMS.register("coffee_cup",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WHISK = ITEMS.register("whisk",
            () -> new WhiskItem(new Item.Properties()));
    public static final DeferredItem<Item> STEAMED_MILK = ITEMS.register("steamed_milk",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> MILK_FOAM = ITEMS.register("milk_foam",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> WHIPPED_CREAM = ITEMS.register("whipped_cream",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ARABICA_COFFEE_CHERRY = ITEMS.register("arabica_coffee_cherry",
            () -> new ItemNameBlockItem(ModBlocks.ARABICA_COFFEE_BUSH.get(), new Item.Properties()));
    public static final DeferredItem<Item> ROBUSTA_COFFEE_CHERRY = ITEMS.register("robusta_coffee_cherry",
            () -> new ItemNameBlockItem(ModBlocks.ROBUSTA_COFFEE_BUSH.get(), new Item.Properties()));

    public static final DeferredItem<Item> CUP_OF_COFFEE = ITEMS.register("cup_of_coffee",
            () -> new CoffeeItem(new Item.Properties().food(ModFoodProperties.CUP_OF_COFFEE)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
