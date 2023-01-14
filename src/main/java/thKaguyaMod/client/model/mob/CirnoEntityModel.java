package thKaguyaMod.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import thKaguyaMod.entity.mob.CirnoEntity;

public class CirnoEntityModel extends EntityModel<CirnoEntity> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart skirt;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightWing2;
    private final ModelPart leftWing2;
    private final ModelPart rightWing3;
    private final ModelPart leftWing3;
    private float leaningPitch;
    private float angleTick;

    public CirnoEntityModel(ModelPart root){
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
        this.skirt = root.getChild("skirt");
        this.rightWing = root.getChild("right_wing");
        this.leftWing = root.getChild("left_wing");
        this.rightWing2 = root.getChild("right_wing_2");
        this.leftWing2 = root.getChild("left_wing_2");
        this.rightWing3 = root.getChild("right_wing_3");
        this.leftWing3 = root.getChild("left_wing_3");

        this.rightArm.setAngles(-0.7679449F, 0F, -0.6457718F);
        this.leftArm.setAngles(-0.7679449F, 0F, 0.6457718F);
        this.rightWing.setAngles(0F, 0.4886922F, 0.3141593F);
        this.leftWing.setAngles(0F, -0.4886922F, -0.3141593F);
        this.rightWing2.setAngles(0F, 0.4886922F, -0.3141593F);
        this.leftWing2.setAngles(0F, -0.4886922F, 0.3141593F);
        this.rightWing3.setAngles(0F, 0.4886922F, 0F);
        this.leftWing3.setAngles(0F, -0.4886922F, 0F);
    }

    @Override
    public void setAngles(CirnoEntity entity, float limbAngle, float limbDistance,
                          float animationProgress, float headYaw, float headPitch) {
        this.head.yaw = headYaw * 0.017453292F;

        if (entity.getRoll() > 4) {
            this.head.pitch = -0.7853982F;
        } else {
            this.head.pitch = headPitch * 0.017453292F;
        }

        float k = 1.0F;
        if (entity.getRoll() > 4) {
            k = (float)entity.getVelocity().lengthSquared();
            k /= 0.2F;
            k *= k * k;
        }

        if (k < 1.0F) {
            k = 1.0F;
        }

        this.skirt.pitch = 0F;

        if (this.riding) {
            this.rightLeg.setAngles(-((float)Math.PI * 2F / 5F),((float)Math.PI / 14F),((float)Math.PI / 14F));
            this.leftLeg.setAngles(-((float)Math.PI * 2F / 5F),-((float)Math.PI / 14F),-((float)Math.PI / 14F));

            this.skirt.pitch = -((float)Math.PI / 5F);
        }

        if (entity.isOnGround()) {
            this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6F) * 0.7F * limbDistance / k;
            this.leftLeg.pitch = MathHelper.sin(limbAngle * 0.6F) * 0.7F * limbDistance / k;
            this.rightLeg.yaw = 0F;
            this.leftLeg.yaw = 0F;
            this.rightLeg.roll = 0F;
            this.leftLeg.roll = 0F;

            if (entity.getMoveControl().isMoving()) {
                this.rightArm.pitch = MathHelper.sin(limbAngle * 0.4F) * 1F * limbDistance * 0.5F / k;
                this.leftArm.pitch = MathHelper.cos(limbAngle * 0.4F) * 1F * limbDistance * 0.5F / k;
                this.rightArm.yaw = -10F / 180F * (float)Math.PI;
                this.rightArm.roll = 20F / 180F * (float)Math.PI;
                this.leftArm.yaw = -rightArm.yaw;
                this.leftArm.roll = -rightArm.roll;
            }

            if (this.leaningPitch > 0F) {
                this.leftLeg.pitch = MathHelper.lerp(
                        this.leaningPitch,
                        this.leftLeg.pitch,
                        0.3F * MathHelper.sin(limbAngle * 0.33333334F)
                );
                this.rightLeg.pitch = MathHelper.lerp(
                        this.leaningPitch, this.rightLeg.pitch, 0.3F * MathHelper.cos(limbAngle * 0.33333334F));
            }
        }
    }

    @Override
    public void animateModel(CirnoEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        this.leaningPitch = entity.getLeaningPitch(tickDelta);

        angleTick += (tickDelta / 200F);

        if (angleTick > Math.PI) angleTick = 0;

        this.rightArm.setAngles(this.riding ?
                -1.1679449F + MathHelper.sin(angleTick) * 0.2F :
                -0.9679449F + MathHelper.sin(angleTick) * 0.2F,0F,-0.6457718F);
        this.leftArm.setAngles(this.rightArm.pitch,0F,0.6457718F);

        if (entity.isOnGround()) {
            float newYaw = MathHelper.sin(angleTick * 2) * (float)Math.PI * 0.1F + (float)Math.PI * 0.15F;
            this.rightWing.yaw = newYaw;
            this.leftWing.yaw = -newYaw;
            this.rightWing2.yaw = newYaw;
            this.leftWing2.yaw = -newYaw;
            this.rightWing3.yaw = newYaw;
            this.leftWing3.yaw = -newYaw;
        } else {
            rightLeg.roll = Math.abs(MathHelper.sin(angleTick) * 0.1F);
            leftLeg.roll = -rightLeg.roll;
            rightLeg.pitch = Math.abs(MathHelper.sin(angleTick) * 0.2F);
            leftLeg.pitch = rightLeg.roll;

            float newYaw = MathHelper.sin(angleTick * 8) * (float)Math.PI * 0.1F + (float)Math.PI * 0.15F;
            this.rightWing.yaw = newYaw;
            this.leftWing.yaw = -newYaw;
            this.rightWing2.yaw = newYaw;
            this.leftWing2.yaw = -newYaw;
            this.rightWing3.yaw = newYaw;
            this.leftWing3.yaw = -newYaw;
        }
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(
                this.body,
                this.head,
                this.rightArm,
                this.leftArm,
                this.rightLeg,
                this.leftLeg,
                this.skirt,
                this.rightWing,
                this.leftWing,
                this.rightWing2,
                this.leftWing2,
                this.rightWing3,
                this.leftWing3
        ).forEach((modelRenderer) -> modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha));
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(
                "body",
                ModelPartBuilder.create()
                        .uv(32, 8)
                        .cuboid(-3F, 0F, -2F, 6, 7, 4),
                ModelTransform.pivot(0F, 5F, 0F));

        modelPartData.addChild(
                "head",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-4F, -8F, -4F, 8, 8, 8),
                ModelTransform.pivot(0F, 5F, 0F));

        modelPartData.addChild(
                "right_arm",
                ModelPartBuilder.create()
                        .uv(48, 0)
                        .cuboid(-1F, -1F, -1F, 2, 8, 2),
                ModelTransform.pivot(-4F, 6F, 0F));

        modelPartData.addChild(
                "left_arm",
                ModelPartBuilder.create()
                        .uv(56, 0)
                        .cuboid(-1F, -1F, -1F, 2, 8, 2),
                ModelTransform.pivot(4F, 6F, 0F));

        modelPartData.addChild(
                "right_leg",
                ModelPartBuilder.create()
                        .uv(50, 16)
                        .cuboid(-1F, 0F, -2F, 3, 12, 4),
                ModelTransform.pivot(-2F, 12F, 0F));

        modelPartData.addChild(
                "left_leg",
                ModelPartBuilder.create()
                        .uv(50, 16)
                        .cuboid(-2F, 0F, -2F, 3, 12, 4),
                ModelTransform.pivot(2F, 12F, 0F));

        modelPartData.addChild(
                "skirt",
                ModelPartBuilder.create()
                        .uv(0, 16)
                        .cuboid(-4F, 0F, -4F, 8, 5, 8),
                ModelTransform.pivot(0F, 10F, 0F));

        modelPartData.getChild("skirt").addChild(
                "skirt_2",
                ModelPartBuilder.create()
                        .uv(24, 32)
                        .cuboid(-5F, 0F, -5F, 10, 6, 10),
                ModelTransform.pivot(0F, 4F, 0F));

        modelPartData.addChild(
                "right_wing",
                ModelPartBuilder.create()
                        .uv(0, 48)
                        .cuboid(-14F, 0F, 0F, 14, 3, 3),
                ModelTransform.pivot(-1F, 3F, 2F));

        modelPartData.addChild(
                "left_wing",
                ModelPartBuilder.create()
                        .uv(0, 48)
                        .cuboid(0F, 0F, 0F, 14, 3, 3),
                ModelTransform.pivot(0F, 3F, 2F));

        modelPartData.addChild(
                "right_wing_2",
                ModelPartBuilder.create()
                        .uv(0, 48)
                        .cuboid(-14F, 0F, 0F, 14, 3, 3),
                ModelTransform.pivot(-1F, 6F, 2F));

        modelPartData.addChild(
                "left_wing_2",
                ModelPartBuilder.create()
                        .uv(0, 48)
                        .cuboid(0F, 0F, 0F, 14, 3, 3),
                ModelTransform.pivot(1F, 6F, 2F));

        modelPartData.addChild(
                "right_wing_3",
                ModelPartBuilder.create()
                        .uv(0, 48)
                        .cuboid(-14F, 0F, 0F, 14, 3, 3),
                ModelTransform.pivot(-1F, 5F, 2F));

        modelPartData.addChild(
                "left_wing_3",
                ModelPartBuilder.create()
                        .uv(0, 48)
                        .cuboid(0F, 0F, 0F, 14, 3, 3),
                ModelTransform.pivot(1F, 5F, 2F));

        modelPartData.getChild("head").addChild(
                "long_hair",
                ModelPartBuilder.create()
                        .uv(24, 0)
                        .cuboid(-4F, 0F, -3F, 8, 5, 3),
                ModelTransform.pivot(0F, 0F, 4F));

        modelPartData.getChild("head").addChild(
                "ribbon_center",
                ModelPartBuilder.create()
                        .uv( 0, 32)
                        .cuboid(-1F, -1F, 0F, 2, 2, 1),
                ModelTransform.pivot(0F, -8F, 4F));

        modelPartData.getChild("head").addChild(
                "ribbon_left",
                ModelPartBuilder.create()
                        .uv( 6, 32)
                        .cuboid(0F, -2F, 0F, 5, 4, 1),
                ModelTransform.pivot(1F, -8F, 4F));

        modelPartData.getChild("head").addChild(
                "ribbon_right",
                ModelPartBuilder.create()
                        .uv( 6, 32)
                        .cuboid(-5F, -2F, 0F, 5, 4, 1),
                ModelTransform.pivot(-1F, -8F, 4F));

        return TexturedModelData.of(modelData, 64, 64);
    }
}
