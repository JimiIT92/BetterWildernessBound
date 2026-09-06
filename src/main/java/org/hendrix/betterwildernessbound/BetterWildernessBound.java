package org.hendrix.betterwildernessbound;

import net.fabricmc.api.ModInitializer;
import org.hendrix.betterwildernessbound.core.*;

/**
 * Hendrix's Better Wilderness Bound.
 * Improve the Dappled Forest with new moss blocks, bears, new building blocks and more!
 */
public final class BetterWildernessBound implements ModInitializer {

    /**
     * The {@link String Mod ID}
     */
    public static final String MOD_ID = "betterwildernessbound";

    /**
     * Initialize the mod
     */

    @Override
    public void onInitialize() {
        BFDSounds.register();
        BFDEntityTypes.register();
        BFDItems.register();
        BFDBlocks.register();
        BFDCreativeModeTabs.register();
        BFDFeatures.register();
        BFDEvents.register();
    }

}