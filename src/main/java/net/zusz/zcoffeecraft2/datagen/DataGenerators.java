package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ZCoffeeCraft2.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void  gatherData(GatherDataEvent event) {
        DataGenerator generator= event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, ZCoffeeCraft2.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, ZCoffeeCraft2.MOD_ID, existingFileHelper));

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), new ModDataMapProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), new ModDatapackProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(packOutput, lookupProvider));
    }


}
