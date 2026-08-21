package org.hendrix.betterfalldrop.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.hendrix.betterfalldrop.core.BFDItems;
import org.jspecify.annotations.NonNull;

/**
 * Implementation class for a Marshmallow {@link Item}
 */
public final class MarshmallowItem extends Item {

    /**
     * Constructor. Set the {@link Properties}
     *
     * @param properties The {@link Properties}
     */
    public MarshmallowItem(final Properties properties) {
        super(properties);
    }

    /**
     * Start cooking the marshmallow if the player is holding it near a campfire
     *
     * @param level The {@link Level} reference
     * @param player The {@link Player} holding the marshmallow
     * @param hand The {@link InteractionHand} used to hold the marshmallow
     * @return The {@link InteractionResult}
     */
    public @NonNull InteractionResult use(final @NonNull Level level, final @NonNull Player player, final @NonNull InteractionHand hand) {
        final boolean isNearCampfire = isNearLitCampfire(level, player);
        if (!isNearCampfire) {
            return InteractionResult.FAIL;
        }
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    /**
     * Stop cooking the marshmallow if the {@link Player} is no longer near an active campfire
     *
     * @param level The {@link Level} reference
     * @param livingEntity The {@link LivingEntity} cooking the marshmallow
     * @param itemStack The current {@link ItemStack}
     * @param ticksRemaining The cooking ticks remaining
     */
    @Override
    public void onUseTick(final Level level, final @NonNull LivingEntity livingEntity, final @NonNull ItemStack itemStack, final int ticksRemaining) {
        if(!level.isClientSide() && livingEntity instanceof Player player) {
            if(!isNearLitCampfire(level, player)) {
                player.stopUsingItem();
            }
        }
        super.onUseTick(level, livingEntity, itemStack, ticksRemaining);
    }

    /**
     * Get how long the marshmallow needs to be hold for cooking
     *
     * @param itemStack The current {@link ItemStack}
     * @param user The {@link LivingEntity} holding the marshmallow
     * @return 200
     */
    @Override
    public int getUseDuration(final @NonNull ItemStack itemStack, final @NonNull LivingEntity user) {
        return 200;
    }

    /**
     * Make the player cook the marshmallow if held near a campfire
     *
     * @param itemStack The current {@link ItemStack}
     * @param level The {@link Level} reference
     * @param entity The {@link LivingEntity} holding the marshmallow
     * @return The decreased {@link ItemStack}
     */
    @Override
    public @NonNull ItemStack finishUsingItem(final @NonNull ItemStack itemStack, final @NonNull Level level, final @NonNull LivingEntity entity) {
        if(!level.isClientSide()) {
            itemStack.shrink(1);
            if(entity instanceof Player player) {
                player.addItem(BFDItems.COOKED_MARSHMALLOW.getDefaultInstance());
            }
        }
        return itemStack;
    }

    /**
     * Check whether the {@link Player} is near a lit {@link CampfireBlock}.
     * If so, start cooking the marshmallow
     *
     * @param level The {@link Level} reference
     * @param player The {@link Player}
     * @return True if is near a lit campfire
     */
    private boolean isNearLitCampfire(final Level level, final Player player) {
        final BlockPos pos = player.blockPosition();
        final Direction direction = player.getDirection();
        final BlockPos facingPos = pos.relative(direction);
        return isLitCampfire(level, pos) ||
                isLitCampfire(level, facingPos) ||
                (level.isEmptyBlock(facingPos) && isLitCampfire(level, pos.relative(direction, 2)));
    }

    /**
     * Check if there is a lit campfire at the specified {@link BlockPos}
     *
     * @param level The {@link Level} reference
     * @param pos The {@link BlockPos} to check
     * @return True if there is a lit campfire
     */
    private boolean isLitCampfire(final Level level, final BlockPos pos) {
        final BlockState blockState = level.getBlockState(pos);
        return blockState.is(BlockTags.CAMPFIRES) && blockState.getValue(CampfireBlock.LIT);
    }

}