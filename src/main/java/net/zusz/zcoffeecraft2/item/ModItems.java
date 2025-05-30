package net.zusz.zcoffeecraft2.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZCoffeeCraft2.MOD_ID);

    public static final DeferredItem<Item> RAW_ARABICA_COFFEE_BEAN = ITEMS.register("raw_arabica_coffee_bean",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
