package thKaguyaMod.shot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import thKaguyaMod.data.ShotData;

import thKaguyaMod.data.DanmakuData;
import thKaguyaMod.entity.shot.THShotEntity;

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

        thShotEntity = new THShotEntity(world, user, source, pos,
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
}
