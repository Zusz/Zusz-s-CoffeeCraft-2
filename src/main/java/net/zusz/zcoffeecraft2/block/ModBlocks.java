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
import net.zusz.zcoffeecraft2.block.custom.*;
import net.zusz.zcoffeecraft2.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ZCoffeeCraft2.MOD_ID);

    public static final DeferredBlock<Block> RAW_ARABICA_COFFEE_BEAN_SACK  = registerBlock("raw_arabica_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK  = registerBlock("light_roasted_arabica_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK  = registerBlock("medium_roasted_arabica_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK  = registerBlock("dark_roasted_arabica_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> RAW_ROBUSTA_COFFEE_BEAN_SACK  = registerBlock("raw_robusta_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK  = registerBlock("light_roasted_robusta_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK  = registerBlock("medium_roasted_robusta_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK  = registerBlock("dark_roasted_robusta_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> RAW_LIBERICA_COFFEE_BEAN_SACK  = registerBlock("raw_liberica_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK  = registerBlock("light_roasted_liberica_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK  = registerBlock("medium_roasted_liberica_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK  = registerBlock("dark_roasted_liberica_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> RAW_EXCELSA_COFFEE_BEAN_SACK  = registerBlock("raw_excelsa_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SACK  = registerBlock("light_roasted_excelsa_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SACK  = registerBlock("medium_roasted_excelsa_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_EXCELSA_COFFEE_BEAN_SACK  = registerBlock("dark_roasted_excelsa_coffee_bean_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> LIGHT_ROASTED_ARABICA_GROUND_COFFEE_SACK  = registerBlock("light_roasted_arabica_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_ARABICA_GROUND_COFFEE_SACK  = registerBlock("medium_roasted_arabica_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_ARABICA_GROUND_COFFEE_SACK  = registerBlock("dark_roasted_arabica_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> LIGHT_ROASTED_ROBUSTA_GROUND_COFFEE_SACK  = registerBlock("light_roasted_robusta_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_ROBUSTA_GROUND_COFFEE_SACK  = registerBlock("medium_roasted_robusta_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_ROBUSTA_GROUND_COFFEE_SACK  = registerBlock("dark_roasted_robusta_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> LIGHT_ROASTED_LIBERICA_GROUND_COFFEE_SACK  = registerBlock("light_roasted_liberica_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_LIBERICA_GROUND_COFFEE_SACK  = registerBlock("medium_roasted_liberica_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_LIBERICA_GROUND_COFFEE_SACK  = registerBlock("dark_roasted_liberica_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> LIGHT_ROASTED_EXCELSA_GROUND_COFFEE_SACK  = registerBlock("light_roasted_excelsa_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_EXCELSA_GROUND_COFFEE_SACK  = registerBlock("medium_roasted_excelsa_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_EXCELSA_GROUND_COFFEE_SACK  = registerBlock("dark_roasted_excelsa_ground_coffee_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));


    public static final DeferredBlock<Block> ARABICA_COFFEE_CHERRY_SACK  = registerBlock("arabica_coffee_cherry_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> ROBUSTA_COFFEE_CHERRY_SACK  = registerBlock("robusta_coffee_cherry_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIBERICA_COFFEE_CHERRY_SACK  = registerBlock("liberica_coffee_cherry_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> EXCELSA_COFFEE_CHERRY_SACK  = registerBlock("excelsa_coffee_cherry_sack",
            () -> new SackBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.WOOL)));



    public static final DeferredBlock<Block> ARABICA_COFFEE_BUSH = BLOCKS.register("arabica_coffee_bush",
            () -> new ArabicaCoffeeBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
    public static final DeferredBlock<Block> ROBUSTA_COFFEE_BUSH = BLOCKS.register("robusta_coffee_bush",
            () -> new RobustaCoffeeBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> LIBERICA_COFFEE_BUSH = BLOCKS.register("liberica_coffee_bush",
            () -> new LibericaCoffeeBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
    public static final DeferredBlock<Block> EXCELSA_COFFEE_BUSH = BLOCKS.register("excelsa_coffee_bush",
            () -> new ExcelsaCoffeeBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> COFFEE_MACHINE = registerBlock("coffee_machine",
            () -> new CoffeeMachineBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> COFFEE_GRINDER = registerBlock("coffee_grinder",
            () -> new CoffeeGrinderBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> COFFEE_CUP_BLOCK = registerBlock("coffee_cup_block",
            () -> new CoffeeCupBlock(BlockBehaviour.Properties.of()));

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
