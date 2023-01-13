package thKaguyaMod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class ThirdEyeItem extends Item {
    public ThirdEyeItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.getItemCooldownManager().set(this, 20);

        if (world.isClient()) {

            double reachDistance = 64D;

            Vec3d look = playerEntity.getRotationVector();
            Vec3d startPos = new Vec3d(playerEntity.getX(),playerEntity.getEyeY(),playerEntity.getZ());

            Vec3d endPos = new Vec3d(
                    playerEntity.getX() + look.x * reachDistance,
                    playerEntity.getEyeY()+ look.y * reachDistance,
                    playerEntity.getZ()+ look.z * reachDistance
            );

            BlockHitResult blockHitResult = world.raycast(new RaycastContext(
                    startPos,
                    endPos,
                    RaycastContext.ShapeType.OUTLINE,
                    RaycastContext.FluidHandling.ANY,
                    playerEntity
            ));

            if (blockHitResult != null) {
                endPos = blockHitResult.getPos();
            }

            List<Entity> entities = world.getOtherEntities(
                    playerEntity,
                    playerEntity.getBoundingBox()
                            .stretch(look.multiply(reachDistance))
                            .expand(1D, 1D, 1D)
            );

            double lastDistance = 0D;
            Entity nearestEntity = null;

            for (Entity entity:entities) {
                if (entity instanceof LivingEntity)
                {
                    Box box = entity.getBoundingBox().expand(0.3F, 0.3F, 0.3F);
                    Optional<Vec3d> optionalVec3d = box.raycast(startPos,endPos);
                    if (optionalVec3d.isPresent())
                    {
                        double distance = startPos.distanceTo(optionalVec3d.get());
                        if (distance < lastDistance || lastDistance == 0D)
                        {
                            nearestEntity = entity;
                            lastDistance = distance;
                        }
                    }
                }
            }

            if (nearestEntity != null) {
                playerEntity.sendMessage(nearestEntity.getName());
                if (nearestEntity instanceof LivingEntity){
                    float hp = ((LivingEntity) nearestEntity).getHealth();
                    float maxHP = ((LivingEntity) nearestEntity).getMaxHealth();
                    playerEntity.sendMessage(Text.of(String.format(" HP: %.2f/%.2f",hp,maxHP)));
                }
                if (nearestEntity instanceof TameableEntity){
                    String owner = String.valueOf(((TameableEntity) nearestEntity).getOwner().getDisplayName());
                    playerEntity.sendMessage(Text.of(" Owner: " + owner));
                }
            }

        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
