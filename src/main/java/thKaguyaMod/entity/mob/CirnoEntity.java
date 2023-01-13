package thKaguyaMod.entity.mob;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CirnoEntity extends DanmakuMobEntity {
    public boolean flying = false;
    public CirnoEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        /*this.goalSelector.add(5, new FlyGoal(this,1D));*/
        this.goalSelector.add(6, new WanderAroundGoal(this,0.25D));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return getBaseMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 38D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 1D);
    }
    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.35F;
    }
}
