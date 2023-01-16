package thKaguyaMod.client.renderer.shot;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import thKaguyaMod.entity.shot.THShotEntity;
import thKaguyaMod.data.DanmakuData;

public class THShotEntityRenderer extends EntityRenderer<THShotEntity> {

    public THShotEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(THShotEntity entity) {
        int color = entity.getShotColor();
        if (entity.getAnimationCount() < 0) {
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
        RenderSystem.enableDepthTest();

        float size = entity.getShotSize();
        float size2;
        int color = entity.getShotColor();

        Identifier texture = this.getTexture(entity);

        if (entity.getAnimationCount() < 0) {
            int delayCount = -entity.getAnimationCount();
            if (delayCount > 10) {
                delayCount = 10;
            }
            size2 = delayCount * 0.3F * size;
            if (size2 > 1.0F) {
                size2 = 1.0F;
            }
            renderLightEffect(color, size2, entity.getAnimationCount(), texture, matrices, light);
        } else {
            switch (entity.getShotForm()) {
                case DanmakuData.FORM_SMALL, DanmakuData.FORM_TINY, DanmakuData.FORM_MEDIUM, DanmakuData.FORM_PEARL, DanmakuData.FORM_CIRCLE -> {
                    renderNormalShot(size, color, false, texture, matrices, light);
                }
                default -> {}
            }
        }
    }

    public void renderNormalShot(float size, int color, boolean blend, Identifier texture, MatrixStack matrices, int light) {

        if (blend) {
            RenderSystem.depthMask(false);
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

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void renderLightEffect(int color, float size, int count, Identifier texture, MatrixStack matrices, int light)
    {
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR);

        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
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

        float u1 = (float)(count * 32) / 64F;
        float u2 = (float)(count * 32 + 32) / 64F;
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

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    private void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, 0F).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0F, 1F, 0F).next();
    }

    private void vertexWithColor(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float u, float v, int red, int green, int blue, int alpha, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, 0F).texture(u, v).color(red, green, blue, alpha).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0F, 1F, 0F).next();
    }
}
