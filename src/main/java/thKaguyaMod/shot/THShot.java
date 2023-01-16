package thKaguyaMod.shot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import thKaguyaMod.data.ShotData;

import thKaguyaMod.data.DanmakuData;
import thKaguyaMod.entity.shot.THShotEntity;
import thKaguyaMod.registry.EntityHandler;

import java.util.Random;

public class THShot {

    public static final THShotEntity createShot(LivingEntity user, Entity source, Vec3d pos,
                                                Vec3d angle, float slope,
                                                Vec3d rotate, float rotationSpeed, int rotationEnd,
                                                double firstSpeed, double limitSpeed, double acceleration,
                                                Vec3d gravity, ShotData shot)
    {
        World world = source.getEntityWorld();

        THShotEntity thShotEntity;
        ShotData shot2 = shot;
        boolean random = false;
        boolean rainbow = false;
        if(shot2 != null)
        {
            if(shot2.color == DanmakuData.RAINBOW)
            {
                shot2.color = new Random().nextInt(7);
                rainbow = true;
            }
            else if(shot2.color == DanmakuData.RANDOM)
            {
                shot2.color = new Random().nextInt(8);
                random = true;
            }
        }
        thShotEntity = new THShotEntity(EntityHandler.TH_SHOT_ENTITY,world);

        thShotEntity.init(user, source, pos,
                angle, slope,
                rotate, rotationSpeed, rotationEnd,
                firstSpeed, limitSpeed, acceleration,
                gravity, shot2);

        if(world.isClient())
        {
            world.spawnEntity(thShotEntity);
        }
        if(random)
        {
            shot.color = DanmakuData.RANDOM;
        }
        else if(rainbow)
        {
            shot.color = DanmakuData.RAINBOW;
        }
        return thShotEntity;
    }

    public static float getYawFromVector(double vectorX, double vectorZ)
    {
        return (float)((Math.atan2(vectorX, vectorZ) * 180D) / Math.PI);
    }

    public static float getPitchFromVector(double vectorX, double vectorY, double vectorZ)
    {
        double f = MathHelper.fastInverseSqrt(vectorX * vectorX + vectorZ * vectorZ);
        return (float)((Math.atan2(vectorY, f) * 180D) / Math.PI);
    }

    public static Vec3d getVectorFromAngle(float pitch, float yaw, double force)
    {
        double yawRad = (double)yaw / 180.0D * Math.PI;
        double pitchRad = (double)pitch / 180.0D * Math.PI;
        double vectorX = -Math.sin(yawRad) * Math.cos(pitchRad) * force;//X方向　水平方向
        double vectorY = -Math.sin(pitchRad) * force;//Y方向　上下
        double vectorZ =  Math.cos(yawRad) * Math.cos(pitchRad) * force;//Z方向　水平方向
        return new Vec3d(vectorX, vectorY, vectorZ);
    }

    public static Vec3d getVectorFromAngle(float pitch,float yaw)
    {
        return getVectorFromAngle(pitch,yaw,1D);
    }

    public static Vec3d getRandomRotationVector(){
        Random rand = new Random();
        return getVectorFromAngle(rand.nextFloat() * 180F - 90F, rand.nextFloat() * 360F);
    }
}
