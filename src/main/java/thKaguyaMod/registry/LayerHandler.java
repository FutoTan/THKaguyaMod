package thKaguyaMod.registry;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import thKaguyaMod.client.model.CirnoEntityModel;
import thKaguyaMod.client.renderer.CirnoEntityRenderer;

public class LayerHandler {
    public static final EntityModelLayer Model_Cirno_Layer =
            new EntityModelLayer(new Identifier("thkaguyamod", "cirno"), "main");

    public static void register(){
        EntityRendererRegistry.register(EntityHandler.Cirno_Entity, CirnoEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(Model_Cirno_Layer, CirnoEntityModel::getTexturedModelData);
    }

}
