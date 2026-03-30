package net.zusz.zcoffeecraft2.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.custom.*;
import net.zusz.zcoffeecraft2.item.ModItems;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ZCoffeeCraft2.MOD_ID);

    public static final DeferredBlock<Block> RAW_ARABICA_COFFEE_BEAN_SACK  = registerBlock("raw_arabica_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK  = registerBlock("light_roasted_arabica_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK  = registerBlock("medium_roasted_arabica_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK  = registerBlock("dark_roasted_arabica_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> RAW_ROBUSTA_COFFEE_BEAN_SACK  = registerBlock("raw_robusta_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK  = registerBlock("light_roasted_robusta_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK  = registerBlock("medium_roasted_robusta_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK  = registerBlock("dark_roasted_robusta_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> RAW_LIBERICA_COFFEE_BEAN_SACK  = registerBlock("raw_liberica_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK  = registerBlock("light_roasted_liberica_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK  = registerBlock("medium_roasted_liberica_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK  = registerBlock("dark_roasted_liberica_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> RAW_EXCELSA_COFFEE_BEAN_SACK  = registerBlock("raw_excelsa_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SACK  = registerBlock("light_roasted_excelsa_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SACK  = registerBlock("medium_roasted_excelsa_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_EXCELSA_COFFEE_BEAN_SACK  = registerBlock("dark_roasted_excelsa_coffee_bean_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> LIGHT_ROASTED_ARABICA_GROUND_COFFEE_SACK  = registerBlock("light_roasted_arabica_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_ARABICA_GROUND_COFFEE_SACK  = registerBlock("medium_roasted_arabica_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_ARABICA_GROUND_COFFEE_SACK  = registerBlock("dark_roasted_arabica_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> LIGHT_ROASTED_ROBUSTA_GROUND_COFFEE_SACK  = registerBlock("light_roasted_robusta_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_ROBUSTA_GROUND_COFFEE_SACK  = registerBlock("medium_roasted_robusta_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_ROBUSTA_GROUND_COFFEE_SACK  = registerBlock("dark_roasted_robusta_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> LIGHT_ROASTED_LIBERICA_GROUND_COFFEE_SACK  = registerBlock("light_roasted_liberica_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_LIBERICA_GROUND_COFFEE_SACK  = registerBlock("medium_roasted_liberica_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_LIBERICA_GROUND_COFFEE_SACK  = registerBlock("dark_roasted_liberica_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> LIGHT_ROASTED_EXCELSA_GROUND_COFFEE_SACK  = registerBlock("light_roasted_excelsa_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MEDIUM_ROASTED_EXCELSA_GROUND_COFFEE_SACK  = registerBlock("medium_roasted_excelsa_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> DARK_ROASTED_EXCELSA_GROUND_COFFEE_SACK  = registerBlock("dark_roasted_excelsa_ground_coffee_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));


    public static final DeferredBlock<Block> ARABICA_COFFEE_CHERRY_SACK  = registerBlock("arabica_coffee_cherry_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> ROBUSTA_COFFEE_CHERRY_SACK  = registerBlock("robusta_coffee_cherry_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIBERICA_COFFEE_CHERRY_SACK  = registerBlock("liberica_coffee_cherry_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> EXCELSA_COFFEE_CHERRY_SACK  = registerBlock("excelsa_coffee_cherry_sack",
            (properties) -> new SackBlock(properties
                    .strength(0.5f).sound(SoundType.WOOL)));



    public static final DeferredBlock<Block> ARABICA_COFFEE_BUSH = registerBlock("arabica_coffee_bush",
            (properties) -> new ArabicaCoffeeBushBlock(properties.mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> ROBUSTA_COFFEE_BUSH = registerBlock("robusta_coffee_bush",
            (properties) -> new RobustaCoffeeBushBlock(properties.mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> LIBERICA_COFFEE_BUSH = registerBlock("liberica_coffee_bush",
            (properties) -> new LibericaCoffeeBushBlock(properties.mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> EXCELSA_COFFEE_BUSH = registerBlock("excelsa_coffee_bush",
            (properties) -> new ExcelsaCoffeeBushBlock(properties.mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> COFFEE_MACHINE = registerBlock("coffee_machine",
            (properties) -> new CoffeeMachineBlock(properties));
    public static final DeferredBlock<Block> COFFEE_GRINDER = registerBlock("coffee_grinder",
            (properties) -> new CoffeeGrinderBlock(properties));

    public static final DeferredBlock<Block> COFFEE_CUP_BLOCK = registerBlock("coffee_cup_block",
            (properties) -> new CoffeeCupBlock(properties));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name,  (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventbus) {
        BLOCKS.register(eventbus);
    }
}
