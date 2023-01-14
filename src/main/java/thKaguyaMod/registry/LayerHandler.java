package thKaguyaMod.registry;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import thKaguyaMod.client.model.mob.CirnoEntityModel;

public class LayerHandler {
    public static final EntityModelLayer MODEL_CIRNO_LAYER =
            new EntityModelLayer(new Identifier("thkaguyamod", "cirno"), "main");

    public static void register(){
        EntityModelLayerRegistry.registerModelLayer(MODEL_CIRNO_LAYER, CirnoEntityModel::getTexturedModelData);
    }

}
