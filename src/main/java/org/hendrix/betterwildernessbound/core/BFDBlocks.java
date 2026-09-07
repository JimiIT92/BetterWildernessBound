package org.hendrix.betterwildernessbound.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import org.hendrix.betterwildernessbound.BetterWildernessBound;
import org.hendrix.betterwildernessbound.utils.IdentifierUtils;

import java.util.Locale;
import java.util.function.Function;

/**
 * {@link BetterWildernessBound} {@link Block Blocks}
 */
public final class BFDBlocks {

    //#region Blocks

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

    public static final Block TINTED_GLASS_STAIRS = registerStair(Blocks.TINTED_GLASS);
    public static final Block TINTED_GLASS_SLAB = registerSlab(Blocks.TINTED_GLASS);

    public static final ColorCollection<Block> STAINED_GLASS_STAIRS = registerStairs("stained_glass", Blocks.STAINED_GLASS);
    public static final ColorCollection<Block> STAINED_GLASS_SLABS = registerSlabs("stained_glass", Blocks.STAINED_GLASS);

    public static final Block CALCITE_STAIRS = registerStair(Blocks.CALCITE);
    public static final Block CALCITE_SLAB = registerSlab(Blocks.CALCITE);
    public static final Block CALCITE_WALL = registerWall(Blocks.CALCITE);
    public static final Block POLISHED_CALCITE = register("polished_calcite", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final Block POLISHED_CALCITE_SLAB = registerSlab(POLISHED_CALCITE);
    public static final Block POLISHED_CALCITE_STAIRS = registerStair(POLISHED_CALCITE);
    public static final Block POLISHED_CALCITE_WALL = registerWall(POLISHED_CALCITE);
    public static final Block CHISELED_CALCITE = register("chiseled_calcite", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final Block CALCITE_BRICKS = register("calcite_bricks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final Block CALCITE_BRICK_SLAB = registerSlab("calcite_brick", CALCITE_BRICKS);
    public static final Block CALCITE_BRICK_STAIRS = registerStair("calcite_brick", CALCITE_BRICKS);
    public static final Block CALCITE_BRICK_WALL = registerWall("calcite_brick", CALCITE_BRICKS);

    public static final Block RED_MOSS_BLOCK = registerMossBlock(DyeColor.RED);
    public static final Block RED_MOSS_CARPET = registerMossCarpet(DyeColor.RED);
    public static final Block ORANGE_MOSS_BLOCK = registerMossBlock(DyeColor.ORANGE);
    public static final Block ORANGE_MOSS_CARPET = registerMossCarpet(DyeColor.ORANGE);
    public static final Block YELLOW_MOSS_BLOCK = registerMossBlock(DyeColor.YELLOW);
    public static final Block YELLOW_MOSS_CARPET = registerMossCarpet(DyeColor.YELLOW);

    public static final Block ORANGE_SHRUB = registerShrub(DyeColor.ORANGE);
    public static final Block YELLOW_SHRUB = registerShrub(DyeColor.YELLOW);

    public static final Block POTTED_RED_SHRUB = registerFlowerPot(Blocks.RED_SHRUB);
    public static final Block POTTED_ORANGE_SHRUB = registerFlowerPot(ORANGE_SHRUB);
    public static final Block POTTED_YELLOW_SHRUB = registerFlowerPot(YELLOW_SHRUB);

    //#endregion

    /**
     * Register a stair
     *
     * @param sourceBlock The source {@link Block}
     * @return The registered {@link Block}
     */
    private static Block registerStair(final Block sourceBlock) {
        return registerStair(
                sourceBlock.properties().blockId().identifier().getPath(),
                sourceBlock
        );
    }

    /**
     * Register a stair
     *
     * @param materialName The block material name
     * @param sourceBlock The source {@link Block}
     * @return The registered {@link Block}
     */
    private static Block registerStair(final String materialName, final Block sourceBlock) {
        return register(
                materialName + "_stairs",
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
        return registerSlab(
                sourceBlock.properties().blockId().identifier().getPath(),
                sourceBlock
        );
    }

    /**
     * Register a slab
     *
     * @param materialName The block material name
     * @param sourceBlock The source {@link Block}
     * @return The registered {@link Block}
     */
    private static Block registerSlab(final String materialName, final Block sourceBlock) {
        return register(
                materialName + "_slab",
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
        return registerWall(
                sourceBlock.properties().blockId().identifier().getPath(),
                sourceBlock
        );
    }

    /**
     * Register a wall
     *
     * @param materialName The block material name
     * @param sourceBlock The source {@link Block}
     * @return The registered {@link Block}
     */
    private static Block registerWall(final String materialName, final Block sourceBlock) {
        return register(
                materialName + "_wall",
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
     * Register a moss block
     *
     * @param color The moss color
     * @return The registered {@link Block}
     */
    private static Block registerMossBlock(final DyeColor color) {
        final String name = color.name().toLowerCase(Locale.ROOT) + "_moss";
        return register(
                name + "_block",
                (properties) -> new BonemealableFeaturePlacerBlock(ResourceKey.create(Registries.FEATURE, IdentifierUtils.modded(name + "_patch_bonemeal")), properties),
                BlockBehaviour.Properties.of().mapColor(color).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED),
                new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)
        );
    }

    /**
     * Register a moss carpet
     *
     * @param color The moss color
     * @return The registered {@link Block}
     */
    private static Block registerMossCarpet(final DyeColor color) {
        return register(
                color.name().toLowerCase(Locale.ROOT) + "_moss_carpet",
                CarpetBlock::new,
                BlockBehaviour.Properties.of().mapColor(color).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED),
                new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW)
        );
    }

    /**
     * Register a shrub
     *
     * @param color The shrub color
     * @return The registered {@link Block}
     */
    private static Block registerShrub(final DyeColor color) {
        return register(
                color.name().toLowerCase(Locale.ROOT) + "_shrub",
                BushBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SHRUB).mapColor(color),
                new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW)
        );
    }

    /**
     * Register a flower pot
     *
     * @param block The {@link Block} that can be potted
     * @return The registered {@link Block}
     */
    private static Block registerFlowerPot(final Block block) {
        return registerBlockWithoutBlockItem(
                "potted_" + block.properties().blockId().identifier().getPath(),
                properties -> new FlowerPotBlock(block, properties),
                Blocks.flowerPotProperties()
        );
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
        return register(name, blockFactory, properties, new Item.Properties());
    }

    /**
     * Register a {@link Block} with additional item properties
     *
     * @param name The block name
     * @param blockFactory The block factory
     * @param properties The {@link BlockBehaviour.Properties block properties}
     * @param itemProperties The {@link Item.Properties item properties}
     * @return The registered {@link Block}
     */
    private static Block register(final String name, final Function<BlockBehaviour.Properties, Block> blockFactory, final BlockBehaviour.Properties properties, final Item.Properties itemProperties) {
        final Block block = registerBlockWithoutBlockItem(name, blockFactory, properties);
        final ResourceKey<Item> blockItemResourceKey = ResourceKey.create(Registries.ITEM, IdentifierUtils.modded(name));
        final BlockItem blockItem = new BlockItem(block, itemProperties.setId(blockItemResourceKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, blockItemResourceKey, blockItem);
        return block;
    }

    /**
     * Register all {@link Block Blocks}
     */
    public static void register() {

    }
}