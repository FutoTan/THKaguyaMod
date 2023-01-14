package thKaguyaMod.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import thKaguyaMod.item.*;

public class ItemHandler {
    public static final Item CLOSED_THIRD_EYE_ITEM = new ClosedThirdEyeItem();
    public static final Item THIRD_EYE_ITEM = new ThirdEyeItem();
    public static void register(){
        Registry.register(
                Registries.ITEM,
                new Identifier("thkaguyamod", "closed_third_eye"),
                CLOSED_THIRD_EYE_ITEM
        );
        Registry.register(
                Registries.ITEM,
                new Identifier("thkaguyamod", "third_eye"),
                THIRD_EYE_ITEM
        );
    }
}
