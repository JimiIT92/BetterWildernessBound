package org.hendrix.betterwildernessbound.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PolarBearRenderer;
import net.minecraft.client.renderer.entity.state.PolarBearRenderState;
import net.minecraft.resources.Identifier;
import org.hendrix.betterwildernessbound.entity.BrownBear;
import org.hendrix.betterwildernessbound.utils.IdentifierUtils;
import org.jspecify.annotations.NonNull;

/**
 * Renderer class for a {@link BrownBear}
 */
@Environment(EnvType.CLIENT)
public final class BrownBearRenderer extends PolarBearRenderer {

    /**
     * The brown bear texture {@link Identifier}
     */
    private static final Identifier BEAR_LOCATION = IdentifierUtils.modded("textures/entity/bear/brownbear.png");
    /**
     * The baby brown bear texture {@link Identifier}
     */
    private static final Identifier BABY_BEAR_LOCATION = IdentifierUtils.modded("textures/entity/bear/brownbear_baby.png");

    /**
     * Constructor. Set the renderer context
     *
     * @param context The {@link EntityRendererProvider.Context} reference
     */
    public BrownBearRenderer(final EntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * Get the texture {@link Identifier}
     *
     * @param state The {@link PolarBearRenderState} reference
     * @return The texture {@link Identifier}
     */
    @Override
    public @NonNull Identifier getTextureLocation(final @NonNull PolarBearRenderState state) {
        return state.isBaby ? BABY_BEAR_LOCATION : BEAR_LOCATION;
    }

}
