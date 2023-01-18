package thKaguyaMod.client.renderer.shot;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import thKaguyaMod.client.model.shot.SilverKnifeModel;
import thKaguyaMod.entity.shot.THShotEntity;
import thKaguyaMod.data.DanmakuData;

import java.util.Random;

public class THShotEntityRenderer extends EntityRenderer<THShotEntity> {

    public THShotEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected int getBlockLight(THShotEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public Identifier getTexture(THShotEntity entity) {
        int color = entity.getShotColor();
        if (entity.getLiveTick() < 0) {
            return new Identifier("thkaguyamod", "textures/entity/shot/musou_fuuin.png");
        } else {
            switch (entity.getShotForm()) {
                case DanmakuData.FORM_SMALL, DanmakuData.FORM_TINY, DanmakuData.FORM_MEDIUM -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/small_shot.png");
                }
                case DanmakuData.FORM_PEARL -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/pearl_shot.png");
                }
                case DanmakuData.FORM_CIRCLE -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/circle_shot.png");
                }
                case DanmakuData.FORM_SCALE, DanmakuData.FORM_OVAL, DanmakuData.FORM_RICE -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/laser.png");
                }
                case DanmakuData.FORM_BUTTERFLY -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/butterfly_shot.png");
                }
                case DanmakuData.FORM_SMALLSTAR, DanmakuData.FORM_CRYSTAL, DanmakuData.FORM_STAR -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/star_shot.png");
                }
                case DanmakuData.FORM_LIGHT, DanmakuData.FORM_BIGLIGHT, DanmakuData.FORM_PHANTOM, DanmakuData.FORM_KISHITU -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/musou_fuuin.png");
                }
                case DanmakuData.FORM_HEART -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/heart_shot.png");
                }
                case DanmakuData.FORM_KUNAI -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/kunai_shot.png");
                }
                case DanmakuData.FORM_TALISMAN -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/talisman_shot.png");
                }
                case DanmakuData.FORM_FAMILIAR -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/familiar.png");
                }
                case DanmakuData.FORM_ARROW -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/arrow_shot.png");
                }
                case DanmakuData.FORM_AMULET -> {
                    if (color == 1) {
                        return new Identifier("thkaguyamod", "textures/entity/shot/diffusion_amulet.png");
                    }
                    return new Identifier("thkaguyamod", "textures/entity/shot/homing_amulet.png");
                }
                case DanmakuData.FORM_KNIFE -> {
                    switch (color)
                    {
                        case 0 -> {
                            return new Identifier("thkaguyamod", "textures/entity/shot/silver_knife_red.png");
                        }
                        case 1 -> {
                            return new Identifier("thkaguyamod", "textures/entity/shot/silver_knife_blue.png");
                        }
                        case 2 -> {
                            return new Identifier("thkaguyamod", "textures/entity/shot/silver_knife_green.png");
                        }
                        case 3 -> {
                            return new Identifier("thkaguyamod", "textures/entity/shot/silver_knife_yellow.png");
                        }
                        case 4 -> {
                            return new Identifier("thkaguyamod", "textures/entity/shot/silver_knife_purple.png");
                        }
                        case 5 -> {
                            return new Identifier("thkaguyamod", "textures/entity/shot/silver_knife_aqua.png");
                        }
                        case 6 -> {
                            return new Identifier("thkaguyamod", "textures/entity/shot/silver_knife_orange.png");
                        }
                        default -> {
                            return new Identifier("thkaguyamod", "textures/entity/shot/silver_knife_white.png");
                        }
                    }
                }
                case DanmakuData.FORM_WIND -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/wind_shot.png");
                }
                case DanmakuData.FORM_BIG -> {
                    return new Identifier("thkaguyamod", "textures/entity/shot/big_shot.png");
                }
                default -> {
                    return null;
                    //return new Identifier("thkaguyamod", "textures/entity/shot/musou_fuuin.png");
                }
            }
        }
    }

    @Override
    public void render(THShotEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        entity.setAnimationTickCount(entity.getAnimationTickCount() + tickDelta);

        float size = entity.getShotSize();
        float size2;
        int color = entity.getShotColor();

        Identifier texture = this.getTexture(entity);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getItemEntityTranslucentCull(texture));

        if (entity.getLiveTick() < 0) {
            int delayCount = -entity.getLiveTick();
            if (delayCount > 10) {
                delayCount = 10;
            }
            size2 = delayCount * 0.3F * size;
            if (size2 > 1.0F) {
                size2 = 1.0F;
            }
            this.renderLightEffect(color, size2, entity.getAnimationTickCount(), texture, matrices, light);
        } else {
            switch (entity.getShotForm()) {
                case DanmakuData.FORM_SMALL, DanmakuData.FORM_TINY, DanmakuData.FORM_MEDIUM, DanmakuData.FORM_PEARL, DanmakuData.FORM_CIRCLE -> {
                    this.renderNormalShot(size, color, false, texture, matrices, light);
                }
                case DanmakuData.FORM_BIG -> {
                    this.renderNormalShot(size, color, true, texture, matrices, light);
                }
                case DanmakuData.FORM_SMALLSTAR, DanmakuData.FORM_STAR -> {
                    this.renderStarShot(size, color, entity.getAnimationTickCount(), texture, matrices, light);
                }
                case DanmakuData.FORM_LIGHT, DanmakuData.FORM_BIGLIGHT -> {
                    this.renderLightShot(size, color, entity.getAnimationTickCount() % 2, texture, matrices, light);
                }
                case DanmakuData.FORM_FAMILIAR -> {
                    this.renderFamiliar(size, color, entity.getAnimationTickCount(), texture, matrices, light);
                }
                case DanmakuData.FORM_BUTTERFLY -> {
                    this.renderButterflyShot(size, color, entity.getAnimationTickCount(), yaw, (float) entity.rotationVector.getX(), entity.getAngleZ(), texture, matrices, light);
                }
                case DanmakuData.FORM_KISHITU -> {
                    this.renderKishituShot(size, color, entity.getAnimationTickCount(), texture, matrices, light);
                }
                case DanmakuData.FORM_TALISMAN -> {
                    this.renderTalismanShot(size, color, yaw, (float) entity.rotationVector.getX(), entity.getAngleZ(), texture, matrices, light);
                }
                case DanmakuData.FORM_KUNAI -> {
                    this.renderKunaiShot(size, color, yaw, (float) entity.rotationVector.getX(), entity.getAngleZ(), texture, matrices, light);
                }
                case DanmakuData.FORM_ARROW -> {
                    this.renderArrowShot(size, color, yaw, (float) entity.rotationVector.getX(), entity.getAngleZ(), texture, matrices, light);
                }
                case DanmakuData.FORM_SCALE -> {
                    this.renderScaleShot(size, color, yaw, (float) entity.rotationVector.getX(), entity.getAngleZ(), entity.getX(), entity.getY(), entity.getZ(), texture, matrices, light);
                }
                case DanmakuData.FORM_RICE -> {
                    this.renderRiceShot(size, yaw, (float) entity.rotationVector.getX(), 4.0F,  1.20F, -2.0F, 7, 5, color, 180, texture, matrices, light);
                }
                case DanmakuData.FORM_CRYSTAL -> {
                    this.renderCrystalShot(size, color, yaw, (float) entity.rotationVector.getX(), texture, matrices, light);
                }
                case DanmakuData.FORM_AMULET -> {
                    this.renderAmuletShot(size, color, entity.getAnimationTickCount(), yaw, (float) entity.rotationVector.getX(), entity.getAngleZ(), texture, matrices, light);
                }
                case DanmakuData.FORM_WIND -> {
                    this.renderWindShot(size, color, entity.getAnimationTickCount(), texture, matrices, light);
                }
                case DanmakuData.FORM_PHANTOM -> {
                    this.renderPhantomShot(size, color, entity.getAnimationTickCount(), texture, matrices, light);
                }
                case DanmakuData.FORM_KNIFE -> {
                    SilverKnifeModel silverKnifeModel = new SilverKnifeModel();

                    matrices.push();

                    matrices.translate(0F, 0.1F, 0F);
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float) entity.rotationVector.getX()));
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getAngleZ()));
                    matrices.scale(size * 3.0F, size * 3.0F, size * 3.0F);

                    silverKnifeModel.render(matrices,vertexConsumer, light, OverlayTexture.DEFAULT_UV,
                            1.0F, 1.0F, 1.0F, 1.0F);

                    matrices.pop();
                }
                default -> {}
            }
        }
    }

    public void renderNormalShot(float size, int color, boolean blend, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        if (blend) {
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);
        }

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);

        float width = 1F;
        float height = 1F;

        float u1 = (float)color / 8F;
        float u2 = (float)(color + 1) / 8F;
        float v1 = 0F;
        float v2 = 1F;

        this.vertex(bufferBuilder,positionMatrix,normalMatrix,-width,-height,u1,v2,light);
        this.vertex(bufferBuilder,positionMatrix,normalMatrix, width,-height,u2,v2,light);
        this.vertex(bufferBuilder,positionMatrix,normalMatrix, width, height,u2,v1,light);
        this.vertex(bufferBuilder,positionMatrix,normalMatrix,-width, height,u1,v1,light);

        tessellator.draw();

        matrices.pop();

        if (blend) {
            RenderSystem.disableBlend();
        }
        RenderSystem.depthMask(false);
    }

    public void renderLightEffect(int color, float size, float count, Identifier texture, MatrixStack matrices, int light)
    {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);

        count = count % 2;

        float u1 = (count * 32) / 64F;
        float u2 = (count * 32 + 32) / 64F;
        float v1 = 0F;
        float v2 = 1F;
        float width = 1F;

        int r = DanmakuData.COLOR_R[color];
        int g = DanmakuData.COLOR_G[color];
        int b = DanmakuData.COLOR_B[color];

        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width, -width, u1, v2, r, g, b, 128, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width, -width, u2, v2, r, g, b, 128, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width,  width, u2, v1, r, g, b, 128, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width,  width, u1, v1, r, g, b, 128, light);

        tessellator.draw();

        matrices.pop();

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    public void renderStarShot(float size, int color, float count, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(count * 3.7F));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float[] topPointX = new float[11];
        float[] topPointY = new float[11];
        float angle = 0F;
        float spanAngle = (float)Math.PI * 0.2F;
        for(int i = 0; i < 10; i+=2)
        {
            topPointX[i] = (float)Math.cos(angle) * 2.0F;
            topPointY[i] = (float)Math.sin(angle) * 2.0F;
            angle += spanAngle;
            topPointX[i+1] = (float)Math.cos(angle) * 1.2F;
            topPointY[i+1] = (float)Math.sin(angle) * 1.2F;
            angle += spanAngle;
        }
        topPointX[10] = topPointX[0];
        topPointY[10] = topPointY[0];
        for(int i = 0; i < 9; i+=2)
        {
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
            int r = DanmakuData.COLOR_R[color];
            int g = DanmakuData.COLOR_G[color];
            int b = DanmakuData.COLOR_B[color];
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, topPointX[i], topPointY[i], 0F, 0F, r, g, b, 180, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, topPointX[i+1], topPointY[i+1], 1F, 0F, r, g, b, 180, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, topPointX[i+2], topPointY[i+2], 1F, 1F, r, g, b, 180, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, 0F, 1F, r, g, b, 180, light);
            tessellator.draw();

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
            r = 255;
            g = 255;
            b = 255;
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, topPointX[i]   * 0.7F, topPointY[i]   * 0.7F, 0.01F, 0F, 0F, r, g, b, 180, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, topPointX[i+1] * 0.95F, topPointY[i+1] * 0.95F, 0.01F, 1F, 0F, r, g, b, 180, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, topPointX[i+2] * 0.7F, topPointY[i+2] * 0.7F, 0.01F, 1F, 1F, r, g, b, 180, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, 0.01F, 0F, 1F, r, g, b, 180, light);
            tessellator.draw();
        }
        matrices.pop();

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    public void renderLightShot(float size, int color, float count, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        color %= 8;
        float uMin = (count * 32 +  0) / 64F;
        float uMax = (count * 32 + 32) / 64F;
        float vMin = 0F;
        float vMax = 1F;
        float width = 2F;

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        int r = DanmakuData.COLOR_R[color];
        int g = DanmakuData.COLOR_G[color];
        int b = DanmakuData.COLOR_B[color];
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width, -width, uMin, vMax, r, g, b, 205, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width, -width, uMax, vMax, r, g, b, 205, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width,  width, uMax, vMin, r, g, b, 205, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width,  width, uMin, vMin, r, g, b, 205, light);
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        r = 255;
        g = 255;
        b = 255;
        width = 1F;
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width, -width, 0.01F, uMin, vMax, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width, -width, 0.01F, uMax, vMax, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width,  width, 0.01F, uMax, vMin, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width,  width, 0.01F, uMin, vMin, r, g, b, 255, light);
        tessellator.draw();

        matrices.pop();

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }
    public void renderFamiliar(float size, int color, float count, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(count * 3.7F));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float width = 1F;
        float height = 1F;

        float u1 = 0F;
        float u2 = 1F;
        float v1 = 0F;
        float v2 = 1F;

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        int r = DanmakuData.COLOR_R[color];
        int g = DanmakuData.COLOR_G[color];
        int b = DanmakuData.COLOR_B[color];
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width, -height, u1, v2, r, g, b, 205, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width, -height, u2, v2, r, g, b, 205, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width,  height, u2, v1, r, g, b, 205, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width,  height, u1, v1, r, g, b, 205, light);
        tessellator.draw();

        matrices.pop();

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    public void renderKishituShot(float size, int color, float count, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        count = count % 2;
        float uMin = (float)(count * 32 +  0) / 64F;
        float uMax = (float)(count * 32 + 32) / 64F;
        float vMin = 0F;
        float vMax = 1F;
        float width = 2.0F;

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        int r = DanmakuData.COLOR_R[color];
        int g = DanmakuData.COLOR_G[color];
        int b = DanmakuData.COLOR_B[color];
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width, -width, uMin, vMax, r, g, b, 128, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width, -width, uMax, vMax, r, g, b, 128, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width,  width, uMax, vMin, r, g, b, 128, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width,  width, uMin, vMin, r, g, b, 128, light);
        tessellator.draw();

        width = 1.6F;
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        r = 255;
        g = 255;
        b = 255;
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width, -width, 0.01F, uMin, vMax, r, g, b, 205, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width, -width, 0.01F, uMax, vMax, r, g, b, 205, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width,  width, 0.01F, uMax, vMin, r, g, b, 205, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -width,  width, 0.01F, uMin, vMin, r, g, b, 205, light);
        tessellator.draw();

        matrices.pop();

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    public void renderButterflyShot(float size, int color, float count, float yaw, float pitch, float slope, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);
        RenderSystem.disableCull();

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        float wingAngle = (float) (Math.sin(count / 3F) * 45F);
        if(pitch > 90F)
        {
            pitch = 90F - pitch % 90F;
        }
        else if(pitch < -90F)
        {
            pitch = -90F - pitch % 90F;
        }

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-pitch));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(slope));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(wingAngle));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        color %= 8;
        float minU =  0F / 128F;
        float maxU = 32F / 128F;
        float minV = 0F;
        float maxV = 1F;
        float width = 2F;
        float width2 = 1.8F;

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        int r = DanmakuData.COLOR_R[color];
        int g = DanmakuData.COLOR_G[color];
        int b = DanmakuData.COLOR_B[color];
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, width, maxU, minV, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width, 0F, width, minU, minV, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width, 0F, -width, minU, maxV, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, -width, maxU, maxV, r, g, b, 255, light);
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        r = 255;
        g = 255;
        b = 255;
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, width2, maxU, minV, r, g, b, 102, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width2, 0F, width2, minU, minV, r, g, b, 102, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  width2, 0F, -width2, minU, maxV, r, g, b, 102, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, -width2, maxU, maxV, r, g, b, 102, light);
        tessellator.draw();

        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-wingAngle * 2F));
        width *= 1F;
        width2 *= 1F;

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        r = DanmakuData.COLOR_R[color];
        g = DanmakuData.COLOR_G[color];
        b = DanmakuData.COLOR_B[color];
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, width, maxU, minV, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  -width, 0F, width, minU, minV, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  -width, 0F, -width, minU, maxV, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, -width, maxU, maxV, r, g, b, 255, light);
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        r = 255;
        g = 255;
        b = 255;
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, width2, maxU, minV, r, g, b, 102, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  -width2, 0F, width2, minU, minV, r, g, b, 102, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  -width2, 0F, -width2, minU, maxV, r, g, b, 102, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F, -width2, maxU, maxV, r, g, b, 102, light);
        tessellator.draw();

        matrices.pop();

        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    public void renderTalismanShot(float size, int color, float yaw, float pitch, float slope, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.disableCull();

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-pitch));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(slope));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float u1 = (float)color / 8F;
        float u2 = (float)(color + 1) / 8F;
        float v1 = 0F;
        float v2 = 1F;

        float width = 1.6F;
        float length = 2.0F;

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,  width, 0F,  length, u2, v1, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, -width, 0F,  length, u1, v1, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, -width, 0F, -length, u1, v2, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,  width, 0F, -length, u2, v2, light);
        tessellator.draw();

        matrices.pop();

        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
    }

    public void renderKunaiShot(float size, int color, float yaw, float pitch, float slope, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-pitch));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(slope));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float width = 0.8F;
        float flont = 1.12F;
        float back  = -0.8F;
        float back2 = -1F;
        float height = 0.24F;
        float width2 = 0.6F;
        float root  = -2.6F;

        //上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, -width, 0F, back, 0.5F, 1F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, -width, 0F, back, 0F, 1F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, 0F, height,  back, 0.25F, 1F, light);
        tessellator.draw();
        //左下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 0F, light);
        tessellator.draw();
        //右下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,-width,  0F, back, 0.5F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 0F, light);
        tessellator.draw();
        //左上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  height,  back, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 0F, light);
        tessellator.draw();
        //左下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 0F, light);
        tessellator.draw();
        //右上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,-width, 0F,  back, 0.5F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  height,  back, 0.25F, 0F, light);
        tessellator.draw();
        //右下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,-width, 0F,  back, 0.5F, 0F, light);
        tessellator.draw();

        width = 1F;
        flont = 1.4F;
        height = 0.3F;

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        int r = DanmakuData.COLOR_R[color];
        int g = DanmakuData.COLOR_G[color];
        int b = DanmakuData.COLOR_B[color];

        //上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width,   0F,  back, 0.5F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F,   0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, width,   0F,  back, 0F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F, height,  back, 0.25F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //左下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //右下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width,  0F, back, 0.5F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //左上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  height,  back, 0.25F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //左下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //右上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width, 0F,  back, 0.5F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  height,  back, 0.25F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //右下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width, 0F,  back, 0.5F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //把手
        RenderSystem.disableCull();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, width2,  0F,  back2, 0.5F, 0.375F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width2,  0F,  back2, 0.75F, 0.375F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width2,  0F,  root, 0.75F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, width2,  0F,  root, 0.5F, 1F, r, g, b, 255, light);
        tessellator.draw();

        matrices.pop();

        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    public void renderArrowShot(float size, int color, float yaw, float pitch, float slope, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-pitch));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(slope));
        matrices.scale(size, size, size);
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float width = 0.8F;
        float flont = 1.12F;
        float back  = -0.8F;
        float back2 = -1F;
        float height = 0.24F;
        float width2 = 0.6F;
        float root  = -5.8F;

        //上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, -width, 0F, back, 0.5F, 1F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, 0F, 0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, -width, 0F, back, 0F, 1F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, 0F, height,  back, 0.25F, 1F, light);
        tessellator.draw();
        //左下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 0F, light);
        tessellator.draw();
        //右下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,-width,  0F, back, 0.5F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 0F, light);
        tessellator.draw();
        //左上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  height,  back, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 0F, light);
        tessellator.draw();
        //左下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 0F, light);
        tessellator.draw();
        //右上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,-width, 0F,  back, 0.5F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  height,  back, 0.25F, 0F, light);
        tessellator.draw();
        //右下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 0F, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,-width, 0F,  back, 0.5F, 0F, light);
        tessellator.draw();

        width = 1F;
        flont = 1.4F;
        height = 0.3F;

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        int r = DanmakuData.COLOR_R[color];
        int g = DanmakuData.COLOR_G[color];
        int b = DanmakuData.COLOR_B[color];

        //上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width,   0F,  back, 0.5F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F,   0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, width,   0F,  back, 0F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F, height,  back, 0.25F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //左下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //右下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F,  flont, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width,  0F, back, 0.5F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //左上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  height,  back, 0.25F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //左下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,width,  0F,  back, 0F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //右上
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width, 0F,  back, 0.5F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  height,  back, 0.25F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //右下
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F,  0F, back2, 0.25F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,0F, -height,  back, 0.25F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width, 0F,  back, 0.5F, 1F, r, g, b, 255, light);
        tessellator.draw();
        //把手
        RenderSystem.disableCull();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, width2,  0F,  back2, 0.5F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width2,  0F,  back2, 0.75F, 0F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width2,  0F,  root, 0.75F, 1F, r, g, b, 255, light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, width2,  0F,  root, 0.5F, 1F, r, g, b, 255, light);
        tessellator.draw();

        matrices.pop();

        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    protected void renderRiceShot(float size, float yaw, float pitch, float length, float width, float zPos, int zAngleDivNum, int zDivNum, int color, int alpha, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-pitch));
        matrices.scale(size, size, size);

        this.renderRiceShotPart(matrices,light, size * 0.8F, yaw, pitch, length * 0.8D, width * 0.8F, zPos + length * 0.1F, zAngleDivNum, zDivNum, 7, 255);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);

        this.renderRiceShotPart(matrices, light, size, yaw, pitch, length, width, zPos, zAngleDivNum, zDivNum, color, alpha);

        matrices.pop();

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    private void renderRiceShotPart(MatrixStack matrices, int light, float size, float yaw, float pitch,  double length, float width, float zPos, int zAngleDivNum, int zDivNum, int color, int alpha) {
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float maxWidth = width;

        double angleZ = 0F;
        double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;

        //int zDivNum = 5;
        double zLength = length;
        double zDivLength = zLength / (double)(zDivNum - 1);
        //double zPos = 0.0D;
        //double zPos = -zLength2;
        //zPos = Math.sin(-Math.PI / 2.0D) * maxWidth;
        float zPosOld = zPos;

        float xPos = 0F;
        float yPos = 0F;
        float xPos2 = 0F;
        float yPos2 = 0F;

        float xPosOld = xPos;
        float yPosOld = yPos;
        float xPos2Old = xPos2;
        float yPos2Old = yPos2;

        float angle = -(float)Math.PI / 2.0F;
        float angleSpan = (float)Math.PI / (float)(zDivNum);
        angle += angleSpan;

        //width = (float)Math.sin(angle) * maxWidth;
        float widthOld = 0.0F;

        //while(zPos < zLength2)
        for(int j = 0; j < zDivNum; j++)
        {
            zPos += zDivLength;
            //widthOld = width;
            //angle += angleSpan;
            width = (float)Math.cos(angle) * maxWidth;
            //XとY座標は初期値、0度のときの座標に戻る。
            xPos = width;
            yPos = 0F;
            xPosOld = (float)Math.cos(angleZ) * width;
            yPosOld = (float)Math.sin(angleZ) * width;
            xPos2Old = (float)Math.cos(angleZ) * widthOld;
            yPos2Old = (float)Math.sin(angleZ) * widthOld;
            //Z軸回転の始点
            angleZ = angleSpanZ;


            for(int i = 0; i <= zAngleDivNum; i++)
            {
                xPos = (float)Math.cos(angleZ) * width;
                yPos = (float)Math.sin(angleZ) * width;
                xPos2 = (float)Math.cos(angleZ) * widthOld;
                yPos2 = (float)Math.sin(angleZ) * widthOld;

                bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
                int r = DanmakuData.COLOR_R[color];
                int g = DanmakuData.COLOR_G[color];
                int b = DanmakuData.COLOR_B[color];
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPosOld , yPosOld , zPos   , 0F, 0F, r, g, b, 255, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPos2Old, yPos2Old, zPosOld, 0F, 1F, r, g, b, 255, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPos2   , yPos2   , zPosOld, 1F, 1F, r, g, b, 255, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPos    , yPos    , zPos   , 1F, 0F, r, g, b, 255, light);
                tessellator.draw();

                xPosOld = xPos;
                yPosOld = yPos;
                xPos2Old = xPos2;
                yPos2Old = yPos2;
                angleZ += angleSpanZ;
            }
            zPosOld = zPos;
            angle += angleSpan;
            widthOld = width;
        }
    }

    public void renderScaleShot(float size, int color, float yaw, float pitch, float slope, double x, double y, double z, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(pitch));
        matrices.scale(size, size, size);

        double length = 2D;
        float width = 1F;
        float zPos = -1F;
        int zAngleDivNum = 7;
        int zDivNum = 8;
        int alpha = 80;

        this.renderOvalShotPart(matrices, light, size, yaw, pitch ,length * 0.4D, width * 0.5F, -0.85D, zAngleDivNum, 5, 7, 1F);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);

        this.renderHarfOvalShotPart(matrices, light, size, yaw, pitch ,length, width * 1.2F, zPos, zAngleDivNum, zDivNum, color, alpha);

        matrices.pop();

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    private void renderOvalShotPart(MatrixStack matrices, int light, float size, float yaw, float pitch, double length, float width, double zOffset, int zAngleDivNum, int zDivNum, int color, float alpha) {
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float maxWidth = width;

        double angleZ = 0F;//Z軸回転変数
        double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;

        //double zPos = 0.0D;
        //double zPos = -zLength2;
        //zPos = Math.sin(-Math.PI / 2.0D) * maxWidth;
        float zPos;
        float zPosOld = (float) (zOffset - length);//zPos

        float xPos = 0F;
        float yPos = 0F;
        float xPos2 = 0F;
        float yPos2 = 0F;

        float xPosOld = xPos;
        float yPosOld = yPos;
        float xPos2Old = xPos2;
        float yPos2Old = yPos2;

        float angle = -(float)Math.PI / 2.0F;
        float angleSpan = (float)Math.PI / (float)(zDivNum);
        angle += angleSpan;

        //width = (float)Math.sin(angle) * maxWidth;
        float widthOld = 0.0F;

        //while(zPos < zLength2)
        for(int j = 0; j < zDivNum; j++)
        {
            //zPos += zDivLength;
            zPos = (float) (zOffset + Math.sin(angle) * length);
            //widthOld = width;
            //angle += angleSpan;
            width = (float)Math.cos(angle) * maxWidth;
            xPos = width;
            yPos = 0F;
            xPosOld = (float)Math.cos(angleZ) * width;
            yPosOld = (float)Math.sin(angleZ) * width;
            xPos2Old = (float)Math.cos(angleZ) * widthOld;
            yPos2Old = (float)Math.sin(angleZ) * widthOld;
            angleZ = angleSpanZ;

            for(int i = 0; i <= zAngleDivNum; i++)
            {
                xPos = (float)Math.cos(angleZ) * width;
                yPos = (float)Math.sin(angleZ) * width;
                xPos2 = (float)Math.cos(angleZ) * widthOld;
                yPos2 = (float)Math.sin(angleZ) * widthOld;

                bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
                int r = DanmakuData.COLOR_R[color];
                int g = DanmakuData.COLOR_G[color];
                int b = DanmakuData.COLOR_B[color];
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPosOld , yPosOld , zPos   , 0F, 0F, r, g, b, 255, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPos2Old, yPos2Old, zPosOld, 0F, 1F, r, g, b, 255, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPos2   , yPos2   , zPosOld, 1F, 1F, r, g, b, 255, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPos    , yPos    , zPos   , 1F, 0F, r, g, b, 255, light);
                tessellator.draw();

                xPosOld = xPos;
                yPosOld = yPos;
                xPos2Old = xPos2;
                yPos2Old = yPos2;
                angleZ += angleSpanZ;
            }
            zPosOld = zPos;
            angle += angleSpan;
            widthOld = width;
        }
    }

    private void renderHarfOvalShotPart(MatrixStack matrices, int light, float size, float yaw, float pitch, double length, float width, float zPos, int zAngleDivNum, int zDivNum, int color, int alpha) {
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float maxWidth = width;

        double angleZ = 0F;
        double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;

        //double zPos = 0.0D;
        //double zPos = -zLength2;
        //zPos = Math.sin(-Math.PI / 2.0D) * maxWidth;
        float zPosOld = (float) -length;//zPos;
        float xPos = 0F;
        float yPos = 0F;
        float xPos2 = 0F;
        float yPos2 = 0F;
        float xPosOld = xPos;
        float yPosOld = yPos;
        float xPos2Old = xPos2;
        float yPos2Old = yPos2;
        float angle = -(float)Math.PI / 2.0F;
        float angleSpan = (float)Math.PI / (float)(zDivNum);
        angle += angleSpan;
        //width = (float)Math.sin(angle) * maxWidth;
        float widthOld = 0.0F;

        zDivNum = (zDivNum / 2) + 1;

        RenderSystem.disableCull();

        //while(zPos < zLength2)
        for(int j = 0; j < zDivNum; j++)
        {
            //zPos += zDivLength;
            zPos = (float) (Math.sin(angle) * length);
            //widthOld = width;
            //angle += angleSpan;
            width = (float)Math.cos(angle) * maxWidth;
            xPos = width;
            yPos = 0F;
            xPosOld = (float)Math.cos(angleZ) * width;
            yPosOld = (float)Math.sin(angleZ) * width;
            xPos2Old = (float)Math.cos(angleZ) * widthOld;
            yPos2Old = (float)Math.sin(angleZ) * widthOld;

            angleZ = angleSpanZ;

            for(int i = 0; i <= zAngleDivNum; i++)
            {
                xPos = (float)Math.cos(angleZ) * width;
                yPos = (float)Math.sin(angleZ) * width;
                xPos2 = (float)Math.cos(angleZ) * widthOld;
                yPos2 = (float)Math.sin(angleZ) * widthOld;

                bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
                int r = DanmakuData.COLOR_R[color];
                int g = DanmakuData.COLOR_G[color];
                int b = DanmakuData.COLOR_B[color];
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPosOld , yPosOld , zPos   , 0F, 0F, r, g, b, alpha, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPos2Old, yPos2Old, zPosOld, 0F, 1F, r, g, b, alpha, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPos2   , yPos2   , zPosOld, 1F, 1F, r, g, b, alpha, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, xPos    , yPos    , zPos   , 1F, 0F, r, g, b, alpha, light);
                tessellator.draw();

                xPosOld = xPos;
                yPosOld = yPos;
                xPos2Old = xPos2;
                yPos2Old = yPos2;
                angleZ += angleSpanZ;
            }
            alpha -= j * 0.05F;

            zPosOld = zPos;
            angle += angleSpan;
            widthOld = width;
        }
        RenderSystem.enableCull();
    }

    public void renderCrystalShot(float size, int color, float yaw, float pitch, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-pitch));
        matrices.scale(size, size, size);

        float width = 0.96F;
        float length = 4.0F, length_b = 1.0F;
        float width2 = width * 0.8F;
        float length2 = length * 0.8F, length2_b = length * 0.4F;
        int i;

        int alpha = 255;
        int r,g,b;

        for(i = 0; i < 4; i++)
        {
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90F));

            MatrixStack.Entry entry = matrices.peek();

            Matrix4f positionMatrix = entry.getPositionMatrix();
            Matrix3f normalMatrix = entry.getNormalMatrix();

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
            r = 255;
            g = 255;
            b = 255;
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F  , width2  , 0F  , 1F, 0F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width2  , 0F   , 0F  , 0F, 0F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F  , 0F   , length2, 0F, 1F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F  , 0F   , length2, 0F, 1F, r, g, b, alpha, light);
            tessellator.draw();

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F   , width2  ,  0F  , 1F, 0F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, width2  , 0F   ,  0F  , 0F, 0F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F   , 0F   , -length2_b, 0F, 1F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F   , 0F   , -length2_b, 0F, 1F, r, g, b, alpha, light);
            tessellator.draw();
        }
        for(i = 0; i < 4; i++)
        {
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90F));

            MatrixStack.Entry entry = matrices.peek();

            Matrix4f positionMatrix = entry.getPositionMatrix();
            Matrix3f normalMatrix = entry.getNormalMatrix();

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
            r = DanmakuData.COLOR_R[color];
            g = DanmakuData.COLOR_G[color];
            b = DanmakuData.COLOR_B[color];
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F  , width  , 0F  , 1F, 0F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,-width  , 0F   , 0F  , 0F, 0F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F  , 0F   , length, 0F, 1F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0F  , 0F   , length, 0F, 1F, r, g, b, alpha, light);
            tessellator.draw();

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F   , width  ,  0F  , 1F, 0F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, width  , 0F   ,  0F  , 0F, 0F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F   , 0F   , -length_b, 0F, 1F, r, g, b, alpha, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F   , 0F   , -length_b, 0F, 1F, r, g, b, alpha, light);
            tessellator.draw();
        }

        matrices.pop();

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    public void renderAmuletShot(float size, int color, float count, float yaw, float pitch, float slope, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, texture);

        RenderSystem.disableCull();

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-pitch));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-slope));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F - count * 23F));
        matrices.scale(size, size, size);

        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float xLength = 1.0F;
        float zLength = 1.0F;
        float uMin = 0.0F;
        float uMax = 0.5F;
        float vMin = 0.0F;
        float vMax = 1.0F;

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, -xLength, 0.0F,  zLength, uMin, vMin, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,  xLength, 0.0F,  zLength, uMax, vMin, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix,  xLength, 0.0F, -zLength, uMax, vMax, light);
        this.vertex(bufferBuilder, positionMatrix, normalMatrix, -xLength, 0.0F, -zLength, uMin, vMax, light);
        tessellator.draw();

        xLength = 1.2F;
        zLength = 1.2F;
        uMin = 0.5F;
        uMax = 1.0F;

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        int r = DanmakuData.COLOR_R[color];
        int g = DanmakuData.COLOR_G[color];
        int b = DanmakuData.COLOR_B[color];

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -xLength, 0.0F,  zLength, uMin, vMin, r, g, b, 255 , light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  xLength, 0.0F,  zLength, uMax, vMin, r, g, b, 255 , light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  xLength, 0.0F, -zLength, uMax, vMax, r, g, b, 255 , light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -xLength, 0.0F, -zLength, uMin, vMax, r, g, b, 255 , light);
        tessellator.draw();

        matrices.pop();

        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    public void renderWindShot(float size, int color, float count, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR);

        RenderSystem.disableCull();

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        matrices.push();

        matrices.translate(0F, 0.1F, 0F);
        matrices.scale(size * 0.5F, size * 0.5F, size * 0.5F);

        Random random = new Random();
        float rand1 = (float)random.nextInt(50) / 100F;
        float rand2 = (float)random.nextInt(100) / 100F + 4F;
        int pattern =  (int)count % 4;
        float u1 = (float)((pattern % 2) * 32) / 64F;
        float u2 = (float)((pattern % 2) * 32 + 32) / 64F;
        float v1 = (float)((int) (pattern / 2) * 16) / 32F;
        float v2 = (float)((int)(pattern / 2) * 16 + 16) / 32F;
        float f9 = 0.5F;

        int r = DanmakuData.COLOR_R[color];
        int g = DanmakuData.COLOR_G[color];
        int b = DanmakuData.COLOR_B[color];

        for (int i = 0; i < 8; i++)
        {
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90F));

            MatrixStack.Entry entry = matrices.peek();

            Matrix4f positionMatrix = entry.getPositionMatrix();
            Matrix3f normalMatrix = entry.getNormalMatrix();

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F - rand1, -1F - f9, 0F, u1, v2, r, g, b, 180, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F + rand1, -1F - f9, 0F, u2, v2, r, g, b, 180, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F + rand2,  2F - f9, 2F, u2, v1, r, g, b, 180, light);
            this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, 0F - rand2,  2F - f9, 2F, u1, v1, r, g, b, 180, light);
            tessellator.draw();
        }

        matrices.pop();

        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    public void renderPhantomShot(float size, int color, float count, Identifier texture, MatrixStack matrices, int light) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);

        matrices.push();

        double time_r = count / 180.0D * Math.PI;;

        size = 0.7F;

        matrices.translate(0F, 0.1F, 0F);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
        matrices.scale(size, size, size);

        this.renderPhantomPart(texture, matrices, light, color, time_r, 0);

        matrices.translate((float)Math.sin(time_r * 5) * 0.2F, 0.1F + (count % 20) * 0.06F, 0F);
        size = 0.9F * ((20F - (count % 20)) / 20F);
        matrices.scale(size, size, size);
        this.renderPhantomPart(texture, matrices, light, color, time_r, 0);

        matrices.translate((float)Math.cos(time_r * 5) * 0.2F, 0.1F + (count % 20) * 0.07F, 0F);
        size = 0.8F * ((20F - (count % 20)) / 20F);
        matrices.scale(size, size, size);
        this.renderPhantomPart(texture, matrices, light, color, time_r,0);

        matrices.pop();

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
    }

    private void renderPhantomPart(Identifier texture, MatrixStack matrices, int light, int color, double time_r, int damage)
    {
        MatrixStack.Entry entry = matrices.peek();

        Matrix4f positionMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        int pattern = 0;//time % 2;
        float uMin = (float)((pattern % 32) * 32 + 0) / 64F;
        float uMax = (float)((pattern % 32) * 32 + 32) / 64F;
        float vMin = 0.0F;
        float vMax = 1.0F;

        float alpha = ((40F - (float)damage) / 40F) * 255F;

        int r = (int) (DanmakuData.COLOR_R[color] * 0.3F);
        int g = (int) (DanmakuData.COLOR_G[color] * 0.3F);
        int b = (int) (DanmakuData.COLOR_B[color] * 0.3F);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE);

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
        RenderSystem.setShaderTexture(0, texture);

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -0.7F + (float)Math.sin(time_r * 3.0D) * 0.1F, -0.2F - (float)Math.cos(time_r * 7.0F) * 0.1F, 0F, uMin, vMax, r, g, b, (int) (0.3 * alpha), light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0.7F - (float)Math.cos(time_r * 4.0D) * 0.1F, -0.2F - (float)Math.sin(time_r * 5.0F) * 0.1F, 0F, uMax, vMax, r, g, b, (int) (0.3 * alpha), light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0.7F + (float)Math.sin(time_r * 5.0D) * 0.1F,  1.2F + (float)Math.cos(time_r * 4.0F) * 0.1F, 0F, uMax, vMin, r, g, b, (int) (0.3 * alpha), light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -0.7F - (float)Math.cos(time_r * 7.0D) * 0.1F,  1.2F + (float)Math.sin(time_r * 3.0F) * 0.1F, 0F, uMin, vMin, r, g, b, (int) (0.3 * alpha), light);
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -0.6F - (float)Math.cos(time_r * 7.0D) * 0.1F, -0.1F + (float)Math.sin(time_r * 5.0D) * 0.1F, 0.001F, uMin, vMax, r, g, b, (int) (0.7 * alpha), light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0.6F + (float)Math.sin(time_r * 3.0D) * 0.1F, -0.1F + (float)Math.cos(time_r * 4.0D) * 0.1F, 0.001F, uMax, vMax, r, g, b, (int) (0.7 * alpha), light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0.6F - (float)Math.cos(time_r * 4.0D) * 0.1F,  1.1F - (float)Math.sin(time_r * 3.0D) * 0.1F, 0.001F, uMax, vMin, r, g, b, (int) (0.7 * alpha), light);
        this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -0.6F + (float)Math.sin(time_r * 5.0D) * 0.1F,  1.1F - (float)Math.cos(time_r * 7.0D) * 0.1F, 0.001F, uMin, vMin, r, g, b, (int) (0.7 * alpha), light);
        tessellator.draw();

        RenderSystem.disableBlend();
        for (int i = 0; i < 3; i++) {
            if (damage > 0) {
                r = 255;
                g = 0;
                b = 0;
                RenderSystem.enableBlend();
                RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE);

                RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
                RenderSystem.setShaderTexture(0, texture);

                bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -0.5F,  0.0F, 0.002F, uMin, vMax, r, g, b, (int)alpha, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0.5F,  0.0F, 0.002F, uMax, vMax, r, g, b, (int)alpha, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix,  0.5F,  1.0F, 0.002F, uMax, vMin, r, g, b, (int)alpha, light);
                this.vertexWithColor(bufferBuilder, positionMatrix, normalMatrix, -0.5F,  1.0F, 0.002F, uMin, vMin, r, g, b, (int)alpha, light);
                tessellator.draw();

                RenderSystem.disableBlend();
            } else {
                RenderSystem.setShader(GameRenderer::getPositionTexProgram);
                RenderSystem.setShaderTexture(0, texture);

                bufferBuilder.begin(VertexFormat.DrawMode.QUADS,VertexFormats.POSITION_TEXTURE);
                this.vertex(bufferBuilder, positionMatrix, normalMatrix, -0.5F,  0.0F, 0.002F, uMin, vMax, light);
                this.vertex(bufferBuilder, positionMatrix, normalMatrix,  0.5F,  0.0F, 0.002F, uMax, vMax, light);
                this.vertex(bufferBuilder, positionMatrix, normalMatrix,  0.5F,  1.0F, 0.002F, uMax, vMin, light);
                this.vertex(bufferBuilder, positionMatrix, normalMatrix, -0.5F,  1.0F, 0.002F, uMin, vMin, light);
                tessellator.draw();
            }
        }
    }

    private void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, 0F).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0F, 1F, 0F).next();
    }

    private void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float z, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, z).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0F, 1F, 0F).next();
    }

    private void vertexWithColor(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float u, float v, int red, int green, int blue, int alpha, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, 0F).texture(u, v).color(red, green, blue, alpha).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0F, 1F, 0F).next();
    }

    private void vertexWithColor(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float z, float u, float v, int red, int green, int blue, int alpha, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, z).texture(u, v).color(red, green, blue, alpha).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0F, 1F, 0F).next();
    }
}
