package net.zusz.zcoffeecraft2.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.custom.ArabicaCoffeeBushBlock;
import net.zusz.zcoffeecraft2.block.custom.CoffeeMachineBlock;
import net.zusz.zcoffeecraft2.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ZCoffeeCraft2.MOD_ID);

    public static final DeferredBlock<Block> RAW_ARABICA_COFFEE_BEAN_SACK  = registerBlock("raw_arabica_coffee_bean_sack",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK  = registerBlock("light_roasted_arabica_coffee_bean_sack",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK  = registerBlock("medium_roasted_arabica_coffee_bean_sack",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK  = registerBlock("dark_roasted_arabica_coffee_bean_sack",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));


    public static final DeferredBlock<Block> ARABICA_COFFEE_BUSH = BLOCKS.register("arabica_coffee_bush",
            () -> new ArabicaCoffeeBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> COFFEE_MACHINE = registerBlock("coffee_machine",
            () -> new CoffeeMachineBlock(BlockBehaviour.Properties.of()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name,  () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventbus) {
        BLOCKS.register(eventbus);
    }
}
