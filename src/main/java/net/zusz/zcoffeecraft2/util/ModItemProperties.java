package net.zusz.zcoffeecraft2.util;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.item.ModItems;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.CUP_OF_COFFEE.get(), ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID,"roast"),
                (stack, level, entity, seed) -> {
                    String roast = stack.get(ModDataComponents.ROAST);
                    if ("light".equals(roast)) {; return 0.0f;};
                    if ("medium".equals(roast)) return 1.0f;
                    if ("dark".equals(roast)) return 2.0f;

                    return -1.0f;
                }
        );
    }
}
