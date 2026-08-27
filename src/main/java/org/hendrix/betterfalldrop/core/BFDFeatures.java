package org.hendrix.betterfalldrop.core;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.hendrix.betterfalldrop.BetterFallDrop;
import org.hendrix.betterfalldrop.utils.IdentifierUtils;

import java.util.function.Predicate;

/**
 * {@link BetterFallDrop} {@link Feature Features}
 */
public final class BFDFeatures {

    /**
     * Dappled Forest biome selector
     */
    private static final Predicate<BiomeSelectionContext> DAPPLED_FOREST_BIOME_SELECTOR = BiomeSelectors.includeByKey(Biomes.DAPPLED_FOREST);

    /**
     * Register all {@link Feature Features}
     */
    public static void register() {
        BiomeModifications.addFeature(
                DAPPLED_FOREST_BIOME_SELECTOR,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("patch_orange_shrub"))
        );
        BiomeModifications.addFeature(
                DAPPLED_FOREST_BIOME_SELECTOR,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("patch_yellow_shrub"))
        );
        BiomeModifications.addFeature(
                DAPPLED_FOREST_BIOME_SELECTOR,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("patch_pumpkin_dappled_forest"))
        );
        BiomeModifications.addSpawn(
                DAPPLED_FOREST_BIOME_SELECTOR,
                MobCategory.CREATURE,
                BFDEntityTypes.BROWN_BEAR,
                15,
                1,
                2
        );
        BiomeModifications.addSpawn(
                DAPPLED_FOREST_BIOME_SELECTOR,
                MobCategory.CREATURE,
                EntityTypes.WOLF,
                5,
                1,
                4
        );
    }

}