package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.zusz.zcoffeecraft2.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ZCoffeeCraft2.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("arabica_coffee_cherry_to_jungle_leaves",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.JUNGLE_LEAVES).build(),
                        LootItemRandomChanceCondition.randomChance(0.01f).build() }, ModItems.ARABICA_COFFEE_CHERRY.get()));
        this.add("robusta_coffee_cherry_to_jungle_leaves",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.JUNGLE_LEAVES).build(),
                        LootItemRandomChanceCondition.randomChance(0.01f).build() }, ModItems.ROBUSTA_COFFEE_CHERRY.get()));

        this.add("arabica_coffee_cherry_from_jungle_temple",
                new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build(),
                        LootItemRandomChanceCondition.randomChance(0.1f).build()
                }, ModItems.ARABICA_COFFEE_CHERRY.get()));

    }
}
