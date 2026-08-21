package org.hendrix.betterfalldrop.core;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.hendrix.betterfalldrop.BetterFallDrop;
import org.hendrix.betterfalldrop.entity.BrownBear;
import org.hendrix.betterfalldrop.utils.IdentifierUtils;

/**
 * {@link BetterFallDrop} {@link EntityType Entity Types}
 */
public final class BFDEntityTypes {

    //#region Entity Types

    public static final EntityType<BrownBear> BROWN_BEAR = register(
            "brown_bear",
            EntityType.Builder.of(BrownBear::new, MobCategory.CREATURE)
                    .immuneTo(BFDTags.BlockTags.BROWN_BEAR_IMMUNE_TO)
                    .sized(1.4F, 1.4F)
                    .clientTrackingRange(10)
    );

    //#endregion

    /**
     * Register an entity
     *
     * @param name The entity name
     * @param builder The entity builder
     * @return The registered entity
     * @param <T> The entity type
     */
    private static <T extends Entity> EntityType<T> register(final String name, final EntityType.Builder<T> builder) {
        final ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, IdentifierUtils.modded(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    /**
     * Register all entities
     */
    public static void register() {
        FabricDefaultAttributeRegistry.register(BROWN_BEAR, BrownBear.createAttributes());
    }

}
