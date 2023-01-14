package thKaguyaMod.registry;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import thKaguyaMod.client.renderer.mob.CirnoEntityRenderer;
import thKaguyaMod.client.renderer.shot.THShotEntityRenderer;

public class RendererHandler {
    public static void register(){
        EntityRendererRegistry.register(EntityHandler.TH_SHOT_ENTITY, THShotEntityRenderer::new);
        EntityRendererRegistry.register(EntityHandler.CIRNO_ENTITY, CirnoEntityRenderer::new);
    }
}
