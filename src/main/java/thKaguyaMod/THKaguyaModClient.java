package thKaguyaMod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import thKaguyaMod.registry.LayerHandler;
import thKaguyaMod.registry.RendererHandler;

@Environment(EnvType.CLIENT)
public class THKaguyaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RendererHandler.register();
        LayerHandler.register();
    }
}
