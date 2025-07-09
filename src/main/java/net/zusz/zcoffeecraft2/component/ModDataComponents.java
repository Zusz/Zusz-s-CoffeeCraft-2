package net.zusz.zcoffeecraft2.component;

import java.util.List;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(ZCoffeeCraft2.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> ROAST = register("roast",
            builder -> builder.persistent(Codec.STRING));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> BEAN = register("bean",
            builder -> builder.persistent(Codec.STRING));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<String>>> INGREDIENTS = register("ingredients",
            builder -> builder.persistent(Codec.STRING.listOf()));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                          UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    };

    public static void register(IEventBus eventbus) {
        DATA_COMPONENT_TYPES.register(eventbus);
    }
}
