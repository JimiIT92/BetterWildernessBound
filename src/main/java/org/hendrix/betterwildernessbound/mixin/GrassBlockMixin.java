package org.hendrix.betterwildernessbound.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.hendrix.betterwildernessbound.utils.IdentifierUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

/**
 * Mixin class for the {@link GrassBlock}
 */
@Mixin(GrassBlock.class)
public final class GrassBlockMixin {

    /**
     * Place dappled forest moss when bonemealing grass in the dappled forest biome
     *
     * @param original      The original {@link PlacedFeature}
     * @param level         The {@link ServerLevel} reference
     * @param random        The {@link RandomSource}
     * @param testPos       The {@link BlockPos}
     * @param source        The {@link BonemealSource}
     * @return              The {@link PlacedFeature} based on the biome
     */
    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;get(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;"), method = "placeBonemealEffect")
    private static Optional<Holder.Reference<PlacedFeature>> placeBonemealEffect(final Optional<Holder.Reference<PlacedFeature>> original, final ServerLevel level, final RandomSource random, final BlockPos testPos, final BonemealSource source) {
        final boolean isInDappledForest = level.getBiome(testPos).is(Biomes.DAPPLED_FOREST);
        if(isInDappledForest) {
            return level.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("dappled_forest_grass_bonemeal")));
        }
        return original;
    }

}