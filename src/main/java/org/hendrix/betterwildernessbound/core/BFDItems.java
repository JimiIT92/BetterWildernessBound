package org.hendrix.betterwildernessbound.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumables;
import org.hendrix.betterwildernessbound.BetterWildernessBound;
import org.hendrix.betterwildernessbound.item.MarshmallowItem;
import org.hendrix.betterwildernessbound.utils.IdentifierUtils;

import java.util.function.Function;

/**
 * {@link BetterWildernessBound} {@link Item Items}
 */
public final class BFDItems {

    //#region Items

    public static final Item MARSHMALLOW = registerMarshmallow(false);
    public static final Item COOKED_MARSHMALLOW = registerMarshmallow(true);
    public static final Item BROWN_BEAR_SPAWN_EGG = register(
            "brown_bear_spawn_egg",
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(BFDEntityTypes.BROWN_BEAR)
    );
    public static final Item EXPLORER_ARMOR_TRIM_SMITHING_TEMPLATE = register(
            "explorer_armor_trim_smithing_template",
            SmithingTemplateItem::createArmorTrimTemplate,
            new Item.Properties().rarity(Rarity.UNCOMMON)
    );

    //#endregion

    /**
     * Register a marshmallow
     *
     * @param cooked Whether the marshmallow is cooked or not
     * @return The registered {@link Item}
     */
    private static Item registerMarshmallow(final boolean cooked) {
        Item.Properties properties = new Item.Properties().stacksTo(16);
        if(cooked) {
            properties = properties
                    .food(new FoodProperties(4, 0.5F, true), Consumables.defaultFood().consumeSeconds(0.8F).build())
                    .usingConvertsTo(Items.STICK);
        }
        return register(
                (cooked ? "cooked_" : "") + "marshmallow",
                cooked ? Item::new : MarshmallowItem::new,
                properties
        );
    }

    /**
     * Register an {@link Item}
     *
     * @param name The item name
     * @param itemFactory The item factory
     * @param properties The {@link Item.Properties item properties}
     * @return The registered {@link Item}
     * @param <T> The item type
     */
    public static <T extends Item> T register(final String name, final Function<Item.Properties, T> itemFactory, final Item.Properties properties) {
        final ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, IdentifierUtils.modded(name));
        final T item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    /**
     * Register all {@link Item items}
     */
    public static void register() {

    }

}
