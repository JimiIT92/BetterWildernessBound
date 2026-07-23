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
                    .icon(() -> new ItemStack(BFDBlocks.CONCRETE_STAIRS.red()))
                    .title(Component.translatable("creativeTab." + BetterFallDrop.MOD_ID + "." + BetterFallDrop.MOD_ID))
                    .displayItems((params, output) -> {
                        addContent(
                                output,
                                BFDBlocks.CONCRETE_STAIRS);
                        addContent(
                                output,
                                BFDBlocks.CONCRETE_SLABS);
                        addContent(
                                output,
                                BFDBlocks.CONCRETE_WALLS);
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