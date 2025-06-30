package net.zusz.zcoffeecraft2.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ZCoffeeCraft2.MOD_ID);

    public static final Supplier<BlockEntityType<CoffeeMachineBlockEntity>> COFFEE_MACHINE_BE =
            BLOCK_ENTITIES.register("coffee_machine_be", () -> BlockEntityType.Builder.of(
                    CoffeeMachineBlockEntity::new, ModBlocks.COFFEE_MACHINE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
