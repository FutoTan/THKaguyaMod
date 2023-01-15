package thKaguyaMod.entity.shot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import thKaguyaMod.data.ShotData;

public class THShotEntity extends Entity {
    // 19
    private static final TrackedData<Integer> DATA_SHOT_ID = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    // 20
    private static final TrackedData<Float> DATA_SHOT_SIZE = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.FLOAT);
    // 17
    private static final TrackedData<Integer> DATA_ANIMATION_COUNT = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    // 16
    private static final TrackedData<Float> DATA_ANGLE_Z = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.FLOAT);
    // 18
    private static final TrackedData<Integer> DATA_TIME = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public THShotEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public void init(LivingEntity user, Entity source, Vec3d pos, Vec3d angle, float slope, Vec3d rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3d gravity, ShotData shot2) {

    }
    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(DATA_SHOT_ID, 0);
        this.dataTracker.startTracking(DATA_SHOT_SIZE, 0F);
        this.dataTracker.startTracking(DATA_TIME, 0);
        this.dataTracker.startTracking(DATA_ANIMATION_COUNT, 0);
        this.dataTracker.startTracking(DATA_ANGLE_Z, 0F);
    }

    public int getShotId()
    {
        return this.dataTracker.get(DATA_SHOT_ID);
    }

    public int getShotForm()
    {
        return (getShotId() - getShotColor()) / 8;
    }

    public int getShotColor()
    {
        return getShotId() % 8;
    }

    public float getShotSize()
    {
        return this.dataTracker.get(DATA_SHOT_SIZE);
    }

    public float getAngleZ()
    {
        return this.dataTracker.get(DATA_ANGLE_Z);
    }

    public int getAnimationCount()
    {
        return this.dataTracker.get(DATA_ANIMATION_COUNT) - 1000;
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
