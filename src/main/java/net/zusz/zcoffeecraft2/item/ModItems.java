package net.zusz.zcoffeecraft2.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.ModBlocks;

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
    public static final DeferredItem<Item> ARABICA_COFFEE_CHERRY = ITEMS.register("arabica_coffee_cherry",
            () -> new ItemNameBlockItem(ModBlocks.ARABICA_COFFEE_BUSH.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
