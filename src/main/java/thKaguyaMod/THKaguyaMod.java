package thKaguyaMod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thKaguyaMod.registry.EffectHandler;
import thKaguyaMod.registry.ItemHandler;

public class THKaguyaMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("THKaguyaMod");
	@Override
	public void onInitialize() {
		ItemHandler.register();
		EffectHandler.register();
	}
}
