package org.hendrix.betterfalldrop.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.fish.Salmon;
import net.minecraft.world.entity.animal.polarbear.PolarBear;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.hendrix.betterfalldrop.core.BFDEntityTypes;
import org.hendrix.betterfalldrop.core.BFDSounds;
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
        this.targetSelector.addGoal(6, new BrownBearCatchSalmonGoal());
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
    }

}
