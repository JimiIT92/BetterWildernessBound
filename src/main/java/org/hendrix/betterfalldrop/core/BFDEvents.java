package org.hendrix.betterfalldrop.core;

import net.fabricmc.fabric.api.event.player.BlockEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.hendrix.betterfalldrop.BetterFallDrop;
import org.jspecify.annotations.Nullable;

/**
 * {@link BetterFallDrop} Events
 */
public final class BFDEvents {

    //#region Events

    /**
     * Disconnect a fence from other blocks when right-clicked with an axe
     *
     * @param itemStack The {@link ItemStack} representing the axe
     * @param blockState The clicked {@link BlockState}
     * @param level The {@link Level} reference
     * @param blockPos The clicked {@link BlockPos}
     * @param player The {@link Player} using the axe
     * @param interactionHand The {@link InteractionHand} used to interact with the block
     * @param blockHitResult The {@link BlockHitResult}
     * @return The {@link InteractionResult}
     */
    private static @Nullable InteractionResult disconnectFenceWithAxe(final ItemStack itemStack, BlockState blockState, final Level level, final BlockPos blockPos, final Player player, final InteractionHand interactionHand, final BlockHitResult blockHitResult) {
        if(itemStack.is(ItemTags.AXES) && blockState.is(BlockTags.FENCES)) {
            boolean shouldDamage = false;
            if(shouldUpdateFence(blockState, level, blockPos, FenceBlock.EAST, Direction.EAST)) {
                blockState = blockState.setValue(FenceBlock.EAST, false);
                shouldDamage = true;
            }
            if(shouldUpdateFence(blockState, level, blockPos, FenceBlock.WEST, Direction.WEST)) {
                blockState = blockState.setValue(FenceBlock.WEST, false);
                shouldDamage = true;
            }
            if(shouldUpdateFence(blockState, level, blockPos, FenceBlock.NORTH, Direction.NORTH)) {
                blockState = blockState.setValue(FenceBlock.NORTH, false);
                shouldDamage = true;
            }
            if(shouldUpdateFence(blockState, level, blockPos, FenceBlock.SOUTH, Direction.SOUTH)) {
                blockState = blockState.setValue(FenceBlock.SOUTH, false);
                shouldDamage = true;
            }
            if(shouldDamage) {
                level.setBlockAndUpdate(blockPos, blockState);
                player.playSound(SoundEvents.AXE_STRIP.value());
                itemStack.hurtAndBreak(1, player, interactionHand);
                player.swing(interactionHand, SwingAnimation.DEFAULT, true);
            }
        }
        return null;
    }

    //#endregion

    /**
     * Check whether a Fence should be updated
     *
     * @param blockState The current {@link BlockState}
     * @param level The {@link Level} reference
     * @param blockPos The current {@link BlockPos}
     * @param directionProperty The {@link BooleanProperty fence direction to check}
     * @param direction The {@link Direction} to check
     * @return True if the fence is not attached to another fence in the provided direction
     */
    private static boolean shouldUpdateFence(final BlockState blockState, final Level level, final BlockPos blockPos, final BooleanProperty directionProperty, final Direction direction) {
        return blockState.getValue(directionProperty) && !level.getBlockState(blockPos.relative(direction)).is(BlockTags.FENCES);
    }

    /**
     * Register all events
     */
    public static void register() {
        BlockEvents.USE_ITEM_ON.register(BFDEvents::disconnectFenceWithAxe);
    }

}