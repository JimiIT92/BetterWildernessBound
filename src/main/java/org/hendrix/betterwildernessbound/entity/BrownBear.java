package org.hendrix.betterwildernessbound.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.fish.Salmon;
import net.minecraft.world.entity.animal.polarbear.PolarBear;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.hendrix.betterwildernessbound.core.BFDEntityTypes;
import org.hendrix.betterwildernessbound.core.BFDSounds;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Implementation class for a Brown Bear
 */
public final class BrownBear extends PolarBear {

    /**
     * Constructor. Set the entity properties
     *
     * @param type The {@link EntityType}
     * @param level The {@link Level} reference
     */
    public BrownBear(final EntityType<? extends BrownBear> type, final Level level) {
        super(type, level);
    }

    /**
     * Register the mob goals
     */
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(6, new BrownBearReachBeeHiveGoal());
        this.targetSelector.addGoal(7, new BrownBearCatchSalmonGoal());
    }

    /**
     * Get the baby variant of the brown bear
     *
     * @param level The {@link ServerLevel} reference
     * @param partner The {@link AgeableMob partner}
     * @return The baby mob
     */
    @Override
    public @Nullable AgeableMob getBreedOffspring(final @NonNull ServerLevel level, final @NonNull AgeableMob partner) {
        return BFDEntityTypes.BROWN_BEAR.create(level, EntitySpawnReason.BREEDING);
    }

    /**
     * Get the ambient sound
     *
     * @return The ambient sound
     */
    @Override
    protected @NonNull SoundEvent getAmbientSound() {
        return this.isBaby() ? BFDSounds.BROWN_BEAR_AMBIENT_BABY : BFDSounds.BROWN_BEAR_AMBIENT;
    }

    /**
     * Get the hurt sound
     *
     * @return The hurt sound
     */
    @Override
    protected @NonNull SoundEvent getHurtSound(final @NonNull DamageSource source) {
        return BFDSounds.BROWN_BEAR_HURT;
    }

    /**
     * Get the death sound
     *
     * @return The death sound
     */
    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return BFDSounds.BROWN_BEAR_DEATH;
    }

    /**
     * Play the step sound
     *
     * @param pos The current {@link BlockPos}
     * @param blockState The current {@link BlockState}
     */
    @Override
    protected void playStepSound(final @NonNull BlockPos pos, final @NonNull BlockState blockState) {
        this.playSound(BFDSounds.BROWN_BEAR_STEP, 0.15F, 1.0F);
    }

    /**
     * Play a warning sound
     */
    protected void playWarningSound() {
        if (this.warningSoundTicks <= 0) {
            this.makeSound(SoundEvents.POLAR_BEAR_WARNING);
            this.warningSoundTicks = 40;
        }
    }

    /**
     * Make the brown bear catch Salmons
     */
    private class BrownBearCatchSalmonGoal extends NearestAttackableTargetGoal {

        /**
         * Constructor. Set the goal properties
         */
        public BrownBearCatchSalmonGoal() {
            super(BrownBear.this, Salmon.class, 10, false, true, (target, level) -> !BrownBear.this.isBaby());
        }

        /**
         * Get the target search area
         *
         * @param followDistance The target follow distance
         * @return The target search area
         */
        @Override
        protected @NonNull AABB getTargetSearchArea(final double followDistance) {
            return this.mob.getBoundingBox().inflate(followDistance, 1, followDistance);
        }

        /**
         * Check whether this goal can be used
         *
         * @return False if the bear is a baby
         */
        @Override
        public boolean canUse() {
            return !BrownBear.this.isBaby();
        }
    }

    /**
     * Make the brown bear reach a {@link Blocks#BEEHIVE} or a {@link Blocks#BEE_NEST}
     */
    private class BrownBearReachBeeHiveGoal extends MoveToBlockGoal {

        private static final int MAX_HONEY_EXTRACTION_TICKS = 100;
        private int honeyExtractionTicks;
        private boolean isExtractingHoney;

        /**
         * Constructor. Set the goal properties
         */
        public BrownBearReachBeeHiveGoal() {
            super(BrownBear.this, 1.5F, 15, 3);
            this.honeyExtractionTicks = MAX_HONEY_EXTRACTION_TICKS;
            this.isExtractingHoney = false;
        }

        /**
         * Check if the block is a valid target
         *
         * @param level The {@link LevelReader} instance
         * @param pos The {@link BlockPos} to check
         * @return True if is a {@link Blocks#BEEHIVE} or a {@link Blocks#BEE_NEST}
         */
        @Override
        protected boolean isValidTarget(final LevelReader level, final @NonNull BlockPos pos) {
            final BlockState blockState = level.getBlockState(pos);
            return blockState.is(BlockTags.BEEHIVES) && blockState.getValue(BeehiveBlock.HONEY_LEVEL) > 0;
        }

        /**
         * Make the brown bear standing when it reaches the target
         */
        @Override
        public void tick() {
            super.tick();
            if(this.isExtractingHoney) {
                this.honeyExtractionTicks--;
            }
            if(this.honeyExtractionTicks <= 0) {
                final Level level = BrownBear.this.level();
                final BlockState blockState = level.getBlockState(this.blockPos);
                if(this.isValidTarget(level, this.blockPos)) {
                    level.setBlockAndUpdate(this.blockPos, blockState.setValue(BeehiveBlock.HONEY_LEVEL, 0));
                }
                this.isExtractingHoney = false;
                this.honeyExtractionTicks = MAX_HONEY_EXTRACTION_TICKS;
                BrownBear.this.setStanding(false);
            }
            if (this.isReachedTarget() && !this.isExtractingHoney) {
                BrownBear.this.setStanding(true);
                this.isExtractingHoney = true;
                this.honeyExtractionTicks = MAX_HONEY_EXTRACTION_TICKS;
            }
        }

        /**
         * Check whether the brown bear reached its target
         *
         * @return True if it reached a bee hive or bee nest or is under it
         */
        @Override
        protected boolean isReachedTarget() {
            final Level level = BrownBear.this.level();
            final BlockPos above = BrownBear.this.blockPosition().above();
            return super.isReachedTarget() ||
            this.isValidTarget(level, above) ||
            (level.isEmptyBlock(above) && this.isValidTarget(level, above.above()));
        }

        /**
         * Check if the goal can continue
         *
         * @return True if the bear is still looking for the bee hive or bee nest or is extracting honey
         */
        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() || this.isExtractingHoney;
        }

        /**
         * Check whether this goal can be used
         *
         * @return False if the bear is a baby
         */
        @Override
        public boolean canUse() {
            return !BrownBear.this.isBaby();
        }
    }

}
