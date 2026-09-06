package org.hendrix.betterwildernessbound.core;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;
import org.hendrix.betterwildernessbound.BetterWildernessBound;
import org.hendrix.betterwildernessbound.utils.IdentifierUtils;

import java.util.Arrays;
import java.util.List;

/**
 * {@link BetterWildernessBound} {@link CreativeModeTab Creative Mode Tabs}
 */
public final class BFDCreativeModeTabs {

    /**
     * Order of {@link DyeColor} for which colored blocks/items should appear in the Creative Mode Inventory
     */
    private static final List<DyeColor> CREATIVE_TAB_COLORS = List.of(
            DyeColor.WHITE,
            DyeColor.LIGHT_GRAY,
            DyeColor.GRAY,
            DyeColor.BLACK,
            DyeColor.BROWN,
            DyeColor.RED,
            DyeColor.ORANGE,
            DyeColor.YELLOW,
            DyeColor.LIME,
            DyeColor.GREEN,
            DyeColor.CYAN,
            DyeColor.LIGHT_BLUE,
            DyeColor.BLUE,
            DyeColor.PURPLE,
            DyeColor.MAGENTA,
            DyeColor.PINK
    );

    //#region Creative Mode Tabs

    public static final CreativeModeTab BETTER_FALL_DROP = register(
            BetterWildernessBound.MOD_ID,
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(BFDBlocks.ORANGE_SHRUB))
                    .title(Component.translatable("creativeTab." + BetterWildernessBound.MOD_ID + "." + BetterWildernessBound.MOD_ID))
                    .displayItems((params, output) -> {
                        addContent(
                                output,
                                BFDBlocks.TERRACOTTA_STAIRS
                        );
                        addContent(
                                output,
                                BFDBlocks.DYED_TERRACOTTA_STAIRS
                        );
                        addContent(
                                output,
                                BFDBlocks.TERRACOTTA_SLAB
                        );
                        addContent(
                                output,
                                BFDBlocks.DYED_TERRACOTTA_SLABS
                        );
                        addContent(
                                output,
                                BFDBlocks.TERRACOTTA_WALL
                        );
                        addContent(
                                output,
                                BFDBlocks.DYED_TERRACOTTA_WALLS
                        );
                        addContent(
                                output,
                                BFDBlocks.CONCRETE_WALLS
                        );
                        addContent(
                                output,
                                BFDBlocks.GLAZED_TERRACOTTA_STAIRS
                        );
                        addContent(
                                output,
                                BFDBlocks.GLAZED_TERRACOTTA_SLABS
                        );
                        addContent(
                                output,
                                BFDBlocks.GLAZED_TERRACOTTA_WALLS
                        );
                        addContent(
                                output,
                                BFDBlocks.GLASS_STAIRS,
                                BFDBlocks.TINTED_GLASS_STAIRS
                        );
                        addContent(
                                output,
                                BFDBlocks.STAINED_GLASS_STAIRS
                        );
                        addContent(
                                output,
                                BFDBlocks.GLASS_SLAB,
                                BFDBlocks.TINTED_GLASS_SLAB
                        );
                        addContent(
                                output,
                                BFDBlocks.STAINED_GLASS_SLABS
                        );
                        addContent(
                                output,
                                BFDBlocks.GLASS_WALL,
                                BFDBlocks.TINTED_GLASS_WALL
                        );
                        addContent(
                                output,
                                BFDBlocks.STAINED_GLASS_WALLS
                        );
                        addContent(
                                output,
                                BFDBlocks.RED_MOSS_BLOCK,
                                BFDBlocks.RED_MOSS_CARPET,
                                BFDBlocks.ORANGE_MOSS_BLOCK,
                                BFDBlocks.ORANGE_MOSS_CARPET,
                                BFDBlocks.YELLOW_MOSS_BLOCK,
                                BFDBlocks.YELLOW_MOSS_CARPET,
                                BFDBlocks.CALCITE_STAIRS,
                                BFDBlocks.CALCITE_SLAB,
                                BFDBlocks.CALCITE_WALL,
                                BFDBlocks.CHISELED_CALCITE,
                                BFDBlocks.POLISHED_CALCITE,
                                BFDBlocks.POLISHED_CALCITE_STAIRS,
                                BFDBlocks.POLISHED_CALCITE_SLAB,
                                BFDBlocks.POLISHED_CALCITE_WALL,
                                BFDBlocks.CALCITE_BRICKS,
                                BFDBlocks.CALCITE_BRICK_STAIRS,
                                BFDBlocks.CALCITE_BRICK_SLAB,
                                BFDBlocks.CALCITE_BRICK_WALL,
                                BFDBlocks.ORANGE_SHRUB,
                                BFDBlocks.YELLOW_SHRUB,
                                BFDItems.MARSHMALLOW,
                                BFDItems.COOKED_MARSHMALLOW,
                                BFDItems.EXPLORER_ARMOR_TRIM_SMITHING_TEMPLATE,
                                BFDItems.BROWN_BEAR_SPAWN_EGG
                        );
                    })
                    .build()
    );

    //#endregion

    /**
     * Add some content to a creative mode tab
     *
     * @param output The {@link CreativeModeTab.Output}
     * @param content The {@link ColorCollection<Block> content to add}
     */
    private static void addContent(final CreativeModeTab.Output output, final ColorCollection<Block> content) {
        CREATIVE_TAB_COLORS.forEach((color) -> addContent(output, content.pick(color)));
    }

    /**
     * Add some content to a creative mode tab
     *
     * @param output The {@link CreativeModeTab.Output}
     * @param content The {@link ItemLike content to add}
     */
    private static void addContent(final CreativeModeTab.Output output, final ItemLike... content) {
        Arrays.stream(content).forEach(output::accept);
    }

    /**
     * Register a {@link CreativeModeTab}
     *
     * @param name The creative mode tab name
     * @param creativeModeTab The {@link CreativeModeTab to register}
     * @return The registered {@link CreativeModeTab}
     */
    private static CreativeModeTab register(final String name, final CreativeModeTab creativeModeTab) {
        final ResourceKey<CreativeModeTab> resourceKey = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), IdentifierUtils.modded(name));
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceKey, creativeModeTab);
    }

    /**
     * Register all creative mode tabs
     */
    public static void register() {

    }
}