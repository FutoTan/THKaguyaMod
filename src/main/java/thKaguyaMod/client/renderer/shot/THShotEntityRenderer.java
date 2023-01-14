package thKaguyaMod.client.renderer.shot;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import thKaguyaMod.entity.shot.THShotEntity;

public class THShotEntityRenderer extends EntityRenderer<Entity> {
    public THShotEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(Entity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return null;
    }

}
