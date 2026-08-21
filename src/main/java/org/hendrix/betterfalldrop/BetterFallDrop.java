package org.hendrix.betterfalldrop;

import net.fabricmc.api.ModInitializer;
import org.hendrix.betterfalldrop.core.*;

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
        BFDSounds.register();
        BFDEntityTypes.register();
        BFDItems.register();
        BFDBlocks.register();
        BFDCreativeModeTabs.register();
        BFDFeatures.register();
        BFDEvents.register();
    }

}