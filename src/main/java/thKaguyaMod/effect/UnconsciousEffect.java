package thKaguyaMod.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.List;

public class UnconsciousEffect extends StatusEffect {
    public UnconsciousEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x98D982);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity playerEntity, int amplifier) {
        if (playerEntity instanceof PlayerEntity) {
            World world = playerEntity.getEntityWorld();
            List<Entity> entities = world.getOtherEntities(
                    playerEntity,
                    playerEntity.getBoundingBox().expand(30d, 30d,30d)
            );
            for (Entity entity:entities) {
                if (entity instanceof MobEntity)
                {
                    MobEntity mobEntity = (MobEntity)entity;
                    if(mobEntity.canSee(playerEntity))
                    {
                        if(mobEntity.getTarget() == playerEntity)
                        {
                            mobEntity.setTarget(null);
                        }
                    }
                }
            }
        }
    }
}
