package thKaguyaMod.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public abstract class DanmakuMobEntity extends PathAwareEntity {
    protected DanmakuMobEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder getBaseMobAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.25D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,30D);
    }

}
