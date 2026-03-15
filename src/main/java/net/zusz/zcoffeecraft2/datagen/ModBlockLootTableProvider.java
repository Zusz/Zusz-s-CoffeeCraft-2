package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf((ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.RAW_ROBUSTA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.RAW_LIBERICA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.RAW_EXCELSA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SACK.get()));
        dropSelf((ModBlocks.DARK_ROASTED_EXCELSA_COFFEE_BEAN_SACK.get()));
        dropSelf(ModBlocks.LIGHT_ROASTED_ARABICA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.MEDIUM_ROASTED_ARABICA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.DARK_ROASTED_ARABICA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.LIGHT_ROASTED_ROBUSTA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.MEDIUM_ROASTED_ROBUSTA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.DARK_ROASTED_ROBUSTA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.LIGHT_ROASTED_LIBERICA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.MEDIUM_ROASTED_LIBERICA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.DARK_ROASTED_LIBERICA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.LIGHT_ROASTED_EXCELSA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.MEDIUM_ROASTED_EXCELSA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.DARK_ROASTED_EXCELSA_GROUND_COFFEE_SACK.get());
        dropSelf(ModBlocks.ARABICA_COFFEE_CHERRY_SACK.get());
        dropSelf(ModBlocks.ROBUSTA_COFFEE_CHERRY_SACK.get());
        dropSelf(ModBlocks.LIBERICA_COFFEE_CHERRY_SACK.get());
        dropSelf(ModBlocks.EXCELSA_COFFEE_CHERRY_SACK.get());
        dropSelf(ModBlocks.COFFEE_GRINDER.get());

        this.dropSelf(ModBlocks.COFFEE_MACHINE.get());
        this.add(ModBlocks.COFFEE_CUP_BLOCK.get(), LootTable.lootTable());

        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.ARABICA_COFFEE_BUSH.get(), block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ARABICA_COFFEE_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.ARABICA_COFFEE_CHERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ARABICA_COFFEE_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.ARABICA_COFFEE_CHERRY))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));
        this.add(ModBlocks.ROBUSTA_COFFEE_BUSH.get(), block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ROBUSTA_COFFEE_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.ROBUSTA_COFFEE_CHERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ROBUSTA_COFFEE_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.ROBUSTA_COFFEE_CHERRY))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));
        this.add(ModBlocks.LIBERICA_COFFEE_BUSH.get(), block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LIBERICA_COFFEE_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.LIBERICA_COFFEE_CHERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LIBERICA_COFFEE_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.LIBERICA_COFFEE_CHERRY))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));
        this.add(ModBlocks.EXCELSA_COFFEE_BUSH.get(), block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.EXCELSA_COFFEE_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.EXCELSA_COFFEE_CHERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.EXCELSA_COFFEE_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.EXCELSA_COFFEE_CHERRY))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));
    }



    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(Holder::value)
                .collect(Collectors.toList());
    }
}
