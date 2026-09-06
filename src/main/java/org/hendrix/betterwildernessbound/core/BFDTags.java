package org.hendrix.betterwildernessbound.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.hendrix.betterwildernessbound.BetterWildernessBound;
import org.hendrix.betterwildernessbound.utils.IdentifierUtils;

/**
 * {@link BetterWildernessBound} {@link TagKey Tags}
 */
public final class BFDTags {

    public static class BlockTags {

        //#region Tags

        public static final TagKey<Block> BROWN_BEAR_IMMUNE_TO = register("brown_bear_immune_to");

        //#endregion

        /**
         * Register a block {@link TagKey}
         *
         * @param name The tag name
         * @return The block {@link TagKey}
         */
        private static TagKey<Block> register(final String name) {
            return TagKey.create(Registries.BLOCK, IdentifierUtils.modded(name));
        }

    }

}