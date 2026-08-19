package org.hendrix.betterfalldrop;

import net.fabricmc.api.ModInitializer;
import org.hendrix.betterfalldrop.core.BFDBlocks;
import org.hendrix.betterfalldrop.core.BFDCreativeModeTabs;
import org.hendrix.betterfalldrop.core.BFDFeatures;

/**
 * Better Fall Drop.
 * WIP
 */
public final class BetterFallDrop implements ModInitializer {

    /**
     * The {@link String Mod ID}
     */
    public static final String MOD_ID = "betterfalldrop";

    /**
     * Initialize the mod
     */

    @Override
    public void onInitialize() {
        BFDBlocks.register();
        BFDCreativeModeTabs.register();
        BFDFeatures.register();
    }

}