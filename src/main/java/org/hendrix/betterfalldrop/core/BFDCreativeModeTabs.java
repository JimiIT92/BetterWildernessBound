package org.hendrix.betterfalldrop.core;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;
import org.hendrix.betterfalldrop.BetterFallDrop;
import org.hendrix.betterfalldrop.utils.IdentifierUtils;

import java.util.Arrays;

/**
 * {@link BetterFallDrop} {@link CreativeModeTab Creative Mode Tabs}
 */
public final class BFDCreativeModeTabs {

    //#region Creative Mode Tabs

    public static final CreativeModeTab BETTER_FALL_DROP = register(
            BetterFallDrop.MOD_ID,
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(BFDBlocks.GLAZED_TERRACOTTA_STAIRS.black()))
                    .title(Component.translatable("creativeTab." + BetterFallDrop.MOD_ID + "." + BetterFallDrop.MOD_ID))
                    .displayItems((params, output) -> {
                        addContent(
                                output,
                                BFDBlocks.TERRACOTTA_STAIRS,
                                BFDBlocks.TERRACOTTA_SLAB,
                                BFDBlocks.TERRACOTTA_WALL
                        );
                        addContent(
                                output,
                                BFDBlocks.DYED_TERRACOTTA_STAIRS
                        );
                        addContent(
                                output,
                                BFDBlocks.DYED_TERRACOTTA_SLABS
                        );
                        addContent(
                                output,
                                BFDBlocks.DYED_TERRACOTTA_WALLS
                        );
                        addContent(
                                output,
                                BFDBlocks.CONCRETE_STAIRS
                        );
                        addContent(
                                output,
                                BFDBlocks.CONCRETE_SLABS
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
                                BFDBlocks.GLASS_SLAB,
                                BFDBlocks.GLASS_WALL,
                                BFDBlocks.TINTED_GLASS_STAIRS,
                                BFDBlocks.TINTED_GLASS_SLAB,
                                BFDBlocks.TINTED_GLASS_WALL
                        );
                        addContent(
                                output,
                                BFDBlocks.STAINED_GLASS_STAIRS
                        );
                        addContent(
                                output,
                                BFDBlocks.STAINED_GLASS_SLABS
                        );
                        addContent(
                                output,
                                BFDBlocks.STAINED_GLASS_WALLS
                        );
                        addContent(
                                output,
                                BFDBlocks.CALCITE_STAIRS,
                                BFDBlocks.CALCITE_SLAB,
                                BFDBlocks.CALCITE_WALL,
                                BFDBlocks.POLISHED_CALCITE,
                                BFDBlocks.POLISHED_CALCITE_SLAB,
                                BFDBlocks.POLISHED_CALCITE_STAIRS,
                                BFDBlocks.POLISHED_CALCITE_WALL,
                                BFDBlocks.CHISELED_CALCITE,
                                BFDBlocks.CALCITE_BRICKS,
                                BFDBlocks.CALCITE_BRICK_SLAB,
                                BFDBlocks.CALCITE_BRICK_STAIRS,
                                BFDBlocks.CALCITE_BRICK_WALL
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
        addContent(output, content.asList().toArray(new Block[0]));
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