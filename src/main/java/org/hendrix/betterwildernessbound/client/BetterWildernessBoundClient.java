package org.hendrix.betterwildernessbound.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.hendrix.betterwildernessbound.BetterWildernessBound;
import org.hendrix.betterwildernessbound.client.renderer.entity.BrownBearRenderer;
import org.hendrix.betterwildernessbound.core.BFDEntityTypes;

/**
 * {@link BetterWildernessBound} {@link ClientModInitializer}
 */
@Environment(EnvType.CLIENT)
public final class BetterWildernessBoundClient implements ClientModInitializer {

    /**
     * Initialize the mod client stuffs
     */
    @Override
    public void onInitializeClient() {
        EntityRenderers.register(BFDEntityTypes.BROWN_BEAR, BrownBearRenderer::new);
    }
}
