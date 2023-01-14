package thKaguyaMod.client.renderer.mob;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import thKaguyaMod.client.model.mob.CirnoEntityModel;
import thKaguyaMod.entity.mob.CirnoEntity;
import thKaguyaMod.registry.LayerHandler;

public class CirnoEntityRenderer extends MobEntityRenderer<CirnoEntity, CirnoEntityModel> {

    public CirnoEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CirnoEntityModel(context.getPart(LayerHandler.MODEL_CIRNO_LAYER)),0.5F);
    }

    @Override
    public Identifier getTexture(CirnoEntity entity) {
        return new Identifier("thkaguyamod", "textures/entity/mob/cirno.png");
    }
}
