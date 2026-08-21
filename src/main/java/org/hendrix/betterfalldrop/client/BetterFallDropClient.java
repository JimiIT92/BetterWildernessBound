package org.hendrix.betterfalldrop.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.hendrix.betterfalldrop.BetterFallDrop;
import org.hendrix.betterfalldrop.client.renderer.entity.BrownBearRenderer;
import org.hendrix.betterfalldrop.core.BFDEntityTypes;

/**
 * {@link BetterFallDrop} {@link ClientModInitializer}
 */
@Environment(EnvType.CLIENT)
public final class BetterFallDropClient implements ClientModInitializer {

    /**
     * Initialize the mod client stuffs
     */
    @Override
    public void onInitializeClient() {
        EntityRenderers.register(BFDEntityTypes.BROWN_BEAR, BrownBearRenderer::new);
    }
}
