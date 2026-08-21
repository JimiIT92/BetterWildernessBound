package org.hendrix.betterfalldrop.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.hendrix.betterfalldrop.BetterFallDrop;
import org.hendrix.betterfalldrop.utils.IdentifierUtils;

/**
 * {@link BetterFallDrop} {@link SoundEvent Sounds}
 */
public final class BFDSounds {

    //#region Sounds

    public static final SoundEvent BROWN_BEAR_AMBIENT = registerSound("entity.brown_bear.ambient");
    public static final SoundEvent BROWN_BEAR_AMBIENT_BABY = registerSound("entity.brown_bear.ambient_baby");
    public static final SoundEvent BROWN_BEAR_DEATH = registerSound("entity.brown_bear.death");
    public static final SoundEvent BROWN_BEAR_HURT = registerSound("entity.brown_bear.hurt");
    public static final SoundEvent BROWN_BEAR_STEP = registerSound("entity.brown_bear.step");
    public static final SoundEvent BROWN_BEAR_WARNING = registerSound("entity.brown_bear.warning");

    //#endregion

    /**
     * Register a {@link SoundEvent}
     *
     * @param name The sound name
     * @return The registered {@link SoundEvent}
     */
    private static SoundEvent registerSound(final String name) {
        final Identifier identifier = IdentifierUtils.modded(name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    /**
     * Register all sounds
     */
    public static void register() {

    }

}