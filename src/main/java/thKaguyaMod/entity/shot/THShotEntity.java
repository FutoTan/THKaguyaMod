package thKaguyaMod.entity.shot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import thKaguyaMod.data.ShotData;
import thKaguyaMod.entity.mob.DanmakuMobEntity;
import thKaguyaMod.registry.EntityHandler;

public class THShotEntity extends Entity {
    public THShotEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    public THShotEntity(World world, LivingEntity user, Entity source, Vec3d pos, Vec3d angle, float slope, Vec3d rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3d gravity, ShotData shot2) {
        super(EntityHandler.TH_SHOT_ENTITY, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
