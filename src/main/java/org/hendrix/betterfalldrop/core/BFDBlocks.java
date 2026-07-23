package org.hendrix.betterfalldrop.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.hendrix.betterfalldrop.BetterFallDrop;
import org.hendrix.betterfalldrop.utils.IdentifierUtils;

import java.util.function.Function;

/**
 * {@link BetterFallDrop} {@link Block Blocks}
 */
public final class BFDBlocks {

    //#region Blocks

    public static final ColorCollection<Block> CONCRETE_STAIRS = registerStairs("concrete", Blocks.CONCRETE);
    public static final ColorCollection<Block> CONCRETE_SLABS = registerSlabs("concrete", Blocks.CONCRETE);
    public static final ColorCollection<Block> CONCRETE_WALLS = registerWalls("concrete", Blocks.CONCRETE);

    public static final Block TERRACOTTA_STAIRS = registerStair(Blocks.TERRACOTTA);
    public static final Block TERRACOTTA_SLAB = registerSlab(Blocks.TERRACOTTA);
    public static final Block TERRACOTTA_WALL = registerWall(Blocks.TERRACOTTA);

    public static final ColorCollection<Block> DYED_TERRACOTTA_STAIRS = registerStairs("terracotta", Blocks.DYED_TERRACOTTA);
    public static final ColorCollection<Block> DYED_TERRACOTTA_SLABS = registerSlabs("terracotta", Blocks.DYED_TERRACOTTA);
    public static final ColorCollection<Block> DYED_TERRACOTTA_WALLS = registerWalls("terracotta", Blocks.DYED_TERRACOTTA);

    public static final ColorCollection<Block> GLAZED_TERRACOTTA_STAIRS = registerStairs("glazed_terracotta", Blocks.GLAZED_TERRACOTTA);
    public static final ColorCollection<Block> GLAZED_TERRACOTTA_SLABS = registerSlabs("glazed_terracotta", Blocks.GLAZED_TERRACOTTA);
    public static final ColorCollection<Block> GLAZED_TERRACOTTA_WALLS = registerWalls("glazed_terracotta", Blocks.GLAZED_TERRACOTTA);

    public static final Block GLASS_STAIRS = registerStair(Blocks.GLASS);
    public static final Block GLASS_SLAB = registerSlab(Blocks.GLASS);
    public static final Block GLASS_WALL = registerWall(Blocks.GLASS);

    public static final Block TINTED_GLASS_STAIRS = registerStair(Blocks.TINTED_GLASS);
    public static final Block TINTED_GLASS_SLAB = registerSlab(Blocks.TINTED_GLASS);
    public static final Block TINTED_GLASS_WALL = registerWall(Blocks.TINTED_GLASS);

    public static final ColorCollection<Block> STAINED_GLASS_STAIRS = registerStairs("stained_glass", Blocks.STAINED_GLASS);
    public static final ColorCollection<Block> STAINED_GLASS_SLABS = registerSlabs("stained_glass", Blocks.STAINED_GLASS);
    public static final ColorCollection<Block> STAINED_GLASS_WALLS = registerWalls("stained_glass", Blocks.STAINED_GLASS);

    //#endregion

    /**
     * Register a stair
     *
     * @param sourceBlock The source {@link Block}
     * @return The registered {@link Block}
     */
    private static Block registerStair(final Block sourceBlock) {
        return register(
                sourceBlock.properties().blockId().identifier().getPath() + "_stairs",
                (properties) -> new StairBlock(sourceBlock.defaultBlockState(), properties),
                BlockBehaviour.Properties.ofFullCopy(sourceBlock)
        );
    }

    /**
     * Register some stairs
     *
     * @param materialName The block material name
     * @param sourceBlocks The source {@link ColorCollection<Block> Blocks}
     * @return The registered {@link ColorCollection<Block> Blocks}
     */
    private static ColorCollection<Block> registerStairs(final String materialName, final ColorCollection<Block> sourceBlocks) {
        return ColorCollection.zipMap(
                ColorCollection.VALUES,
                ColorCollection.prefixWithColor(ColorCollection.create(materialName + "_stairs")).map(BlockItemId::create),
                (color, id) -> register(id.block().identifier().getPath(), (properties) -> new StairBlock(sourceBlocks.pick(color).defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(sourceBlocks.pick(color))));
    }

    /**
     * Register a slab
     *
     * @param sourceBlock The source {@link Block}
     * @return The registered {@link Block}
     */
    private static Block registerSlab(final Block sourceBlock) {
        return register(
                sourceBlock.properties().blockId().identifier().getPath() + "_slab",
               SlabBlock::new,
                BlockBehaviour.Properties.ofFullCopy(sourceBlock)
        );
    }

    /**
     * Register some slabs
     *
     * @param materialName The block material name
     * @param sourceBlocks The source {@link ColorCollection<Block> Blocks}
     * @return The registered {@link ColorCollection<Block> Blocks}
     */
    private static ColorCollection<Block> registerSlabs(final String materialName, final ColorCollection<Block> sourceBlocks) {
        return ColorCollection.zipMap(
                ColorCollection.VALUES,
                ColorCollection.prefixWithColor(ColorCollection.create(materialName + "_slab")).map(BlockItemId::create),
                (color, id) -> register(id.block().identifier().getPath(), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(sourceBlocks.pick(color))));
    }

    /**
     * Register a wall
     *
     * @param sourceBlock The source {@link Block}
     * @return The registered {@link Block}
     */
    private static Block registerWall(final Block sourceBlock) {
        return register(
                sourceBlock.properties().blockId().identifier().getPath() + "_wall",
                WallBlock::new,
                BlockBehaviour.Properties.ofFullCopy(sourceBlock)
        );
    }

    /**
     * Register some walls
     *
     * @param materialName The block material name
     * @param sourceBlocks The source {@link ColorCollection<Block> Blocks}
     * @return The registered {@link ColorCollection<Block> Blocks}
     */
    private static ColorCollection<Block> registerWalls(final String materialName, final ColorCollection<Block> sourceBlocks) {
        return ColorCollection.zipMap(
                ColorCollection.VALUES,
                ColorCollection.prefixWithColor(ColorCollection.create(materialName + "_wall")).map(BlockItemId::create),
                (color, id) -> register(id.block().identifier().getPath(), WallBlock::new, BlockBehaviour.Properties.ofFullCopy(sourceBlocks.pick(color))));
    }

    /**
     * Register a {@link Block} without registering a {@link BlockItem}
     *
     * @param name The block name
     * @param blockFactory The block factory
     * @param properties The {@link BlockBehaviour.Properties block properties}
     * @return The registered {@link Block}
     */
    private static Block registerBlockWithoutBlockItem(final String name, final Function<BlockBehaviour.Properties, Block> blockFactory, final BlockBehaviour.Properties properties) {
        final ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, IdentifierUtils.modded(name));
        final Block block = blockFactory.apply(properties.setId(blockResourceKey));
        return Registry.register(BuiltInRegistries.BLOCK, blockResourceKey, block);
    }

    /**
     * Register a {@link Block}
     *
     * @param name The block name
     * @param blockFactory The block factory
     * @param properties The {@link BlockBehaviour.Properties block properties}
     * @return The registered {@link Block}
     */
    private static Block register(final String name, final Function<BlockBehaviour.Properties, Block> blockFactory, final BlockBehaviour.Properties properties) {
        final Block block = registerBlockWithoutBlockItem(name, blockFactory, properties);
        final ResourceKey<Item> blockItemResourceKey = ResourceKey.create(Registries.ITEM, IdentifierUtils.modded(name));
        final BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(blockItemResourceKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, blockItemResourceKey, blockItem);
        return block;
    }

    /**
     * Register all {@link Block Blocks}
     */
    public static void register() {

    }
}