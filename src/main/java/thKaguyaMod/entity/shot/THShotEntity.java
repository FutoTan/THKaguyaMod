package thKaguyaMod.entity.shot;

import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import thKaguyaMod.data.DanmakuData;
import thKaguyaMod.data.ShotData;
import thKaguyaMod.shot.THShot;


public class THShotEntity extends ProjectileEntity {
    // 19
    private static final TrackedData<Integer> DATA_SHOT_ID = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    // 20
    private static final TrackedData<Float> DATA_SHOT_SIZE = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.FLOAT);
    // 17
    private static final TrackedData<Integer> DATA_LIVE_TICK = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    // 16
    private static final TrackedData<Float> DATA_ANGLE_Z = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.FLOAT);
    // 18
    private static final TrackedData<Integer> DATA_SHOT_END_TIME = DataTracker.registerData(THShotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public float animationTickCount;
    public LivingEntity userEntity;
    public Entity sourceEntity;
    public ShotData shotData;
    public double shotSpeed;
    public double shotMaxSpeed;
    public double shotAcceleration;
    protected int lastTime;
    public Vec3d directionVector;
    public double stopSpeed;
    public Vec3d lastRotation;
    public Vec3d lastShotVelocity;
    public Vec3d gravityVector;
    public float damageRate;
    public Vec3d rotationVector;
    public float rotationYawSpeed;
    public int rotationEndTime;

    public THShotEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
        //test
        this.shotData = ShotData.shot(DanmakuData.FORM_KNIFE, DanmakuData.RED);
        this.setShotId(this.shotData.form * 8 + this.shotData.color);
        this.setShotSize(this.shotData.size);
        this.rotationVector = new Vec3d(1F,0,0);
        this.setVelocity(1F,0,0,1F,0);
        this.setOwner(world.getClosestPlayer(this,10F));
        this.setLiveTick(1);
        this.calculateDimensions();
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.discard();
        }
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        float size = this.getShotSize();
        return EntityDimensions.changing(size, size);
    }

    public void init(LivingEntity userEntity, Entity sourceEntity,
                     Vec3d pos, Vec3d direction, float slope,
                     Vec3d rotate, float rotationSpeed, int rotationEnd,
                     double startSpeed, double maxSpeed, double acceleration, Vec3d gravity,
                     ShotData shotData) {
        this.userEntity = userEntity;
        this.sourceEntity = sourceEntity;

        this.setPosition(pos); // uncheck

        this.directionVector = direction;
        this.setAngleZ(slope);

        this.rotationVector = rotate;
        this.rotationYawSpeed = rotationSpeed;
        this.rotationEndTime = rotationEnd;

        this.shotSpeed = startSpeed;
        this.shotMaxSpeed = maxSpeed;
        this.shotAcceleration = acceleration;

        this.gravityVector = gravity;

        this.shotData = shotData;

        this.setShotId(this.shotData.form * 8 + this.shotData.color);
        this.setShotSize(this.shotData.size);
        this.calculateDimensions();
        this.setShotEndTime(this.shotData.endTime);

        this.stopSpeed = this.shotSpeed;

        this.setVelocity(direction.multiply(this.shotSpeed)); // uncheck
        this.lastShotVelocity = this.getVelocity();

        this.updateYawAndPitch();
        this.setLiveTick(-this.shotData.delayTime);

        this.updateAngle();
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(DATA_SHOT_ID, 0);
        this.dataTracker.startTracking(DATA_SHOT_SIZE, 0F);
        this.dataTracker.startTracking(DATA_SHOT_END_TIME, 0);
        this.dataTracker.startTracking(DATA_LIVE_TICK, 0);
        this.dataTracker.startTracking(DATA_ANGLE_Z, 0F);
    }

    @Override
    public boolean shouldRender(double distance) {
        double d = this.getBoundingBox().getAverageSideLength() * 4.0;
        if (Double.isNaN(d)) {
            d = 4.0;
        }
        return distance < (d *= 64.0) * d;
    }

    public void updateAngle()
    {
        float motionX = (float)this.getVelocity().x;
        float motionY = (float)this.getVelocity().y;
        float motionZ = (float)this.getVelocity().z;
        float f = MathHelper.fastInverseSqrt(motionX * motionX + motionZ * motionZ);
        float rotationYaw = 0;
        float rotationPitch = 0;
        if(this.world.isClient())
        {
            rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / Math.PI);
            double v = (Math.atan2(motionY, f) * 180D) / Math.PI;
            for (rotationPitch = (float) v; rotationPitch - this.prevPitch < -180F; this.prevPitch -= 360F) {}
            for (rotationPitch = (float) v; rotationPitch - this.prevPitch < -180F; this.prevPitch -= 360F) {}
        }
        if(rotationYaw - this.prevYaw > 180F)
            for (; rotationPitch - this.prevPitch >= 180F; this.prevPitch += 360F) {}
        for (; rotationYaw - this.prevYaw < -180F; this.prevYaw -= 360F) {}
        for (; rotationYaw - this.prevYaw >= 180F; this.prevYaw += 360F) {}
        rotationPitch = this.prevPitch + (rotationPitch - this.prevPitch);
        this.setRotation(rotationYaw,rotationPitch);
    }

    @Override
    public void tick() {
        super.tick();
    }

    protected void updateYawAndPitch()
    {
        float rotationYaw = THShot.getYawFromVector(this.directionVector.x, this.directionVector.z);
        float rotationPitch = THShot.getPitchFromVector(this.directionVector.x, this.directionVector.y, this.directionVector.z);
        this.setRotation(rotationYaw, rotationPitch);
        this.lastRotation.add(rotationPitch,rotationYaw,0);
    }

    public void setShotEndTime(int time)
    {
        this.dataTracker.set(DATA_SHOT_END_TIME, time);
    }

    public void setShotId(int id) {
        this.dataTracker.set(DATA_SHOT_ID, id);
    }

    public void setShotForm(int form) {
        this.setShotId(form * 8 + getShotColor());
    }

    public void setShotColor(int color) {
        setShotId(getShotForm() * 8 + color);
    }

    public void setShotSize(float size) {
        this.dataTracker.set(DATA_SHOT_SIZE, size);
    }

    public void setAngleZ(float angleZ) {
        this.dataTracker.set(DATA_ANGLE_Z, angleZ);
    }

    public void setLiveTick(int tick) {
        this.dataTracker.set(DATA_LIVE_TICK, tick + 1000);
    }

    public void setAnimationTickCount(float count) {
        this.animationTickCount = count;
    }

    public int getShotEndTime()
    {
        return this.dataTracker.get(DATA_SHOT_END_TIME);
    }

    public int getShotId()
    {
        return this.dataTracker.get(DATA_SHOT_ID);
    }

    public int getShotForm()
    {
        return (this.getShotId() - this.getShotColor()) / 8;
    }

    public int getShotColor()
    {
        return this.getShotId() % 8;
    }

    public float getShotSize()
    {
        return this.dataTracker.get(DATA_SHOT_SIZE);
    }

    public float getAngleZ()
    {
        return this.dataTracker.get(DATA_ANGLE_Z);
    }

    public int getLiveTick()
    {
        return this.dataTracker.get(DATA_LIVE_TICK) - 1000;
    }

    public float getAnimationTickCount() {
        return this.animationTickCount;
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
