package org.hendrix.betterfalldrop.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.hendrix.betterfalldrop.core.BFDBlocks;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Mixin class for {@link TreeGrower}
 */
@Mixin(TreeGrower.class)
public final class TreeGrowerMixin {

    /**
     * Grow a specific poplar tree if the sapling is placed on a specific moss block
     *
     * @param original The {@link ResourceKey<Feature> original tree to grow}
     * @param level The {@link ServerLevel} instance
     * @param generator The {@link ChunkGenerator} instance
     * @param pos The current {@link BlockPos}
     * @param state The current {@link BlockState}
     * @param random The {@link RandomSource} instance
     * @return The {@link ResourceKey<Feature> tree to grow}
     */
    @ModifyExpressionValue(method = "growTree", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/grower/TreeGrower;getConfiguredFeature(Lnet/minecraft/util/RandomSource;Z)Lnet/minecraft/resources/ResourceKey;"))
    private ResourceKey<Feature> getConfiguredFeature(final @Nullable ResourceKey<Feature> original, final ServerLevel level, final ChunkGenerator generator, final BlockPos pos, final BlockState state, final RandomSource random) {
        if(state.is(Blocks.POPLAR_SAPLING)) {
            final BlockState below = level.getBlockState(pos.below());
            if(below.is(BFDBlocks.RED_MOSS_BLOCK)) {
                return TreeFeatures.RED_POPLAR;
            }
            if(below.is(BFDBlocks.ORANGE_MOSS_BLOCK)) {
                return TreeFeatures.ORANGE_POPLAR;
            }
            if(below.is(BFDBlocks.YELLOW_MOSS_BLOCK)) {
                return TreeFeatures.YELLOW_POPLAR;
            }
        }
        return original;
    }

}