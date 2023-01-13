package thKaguyaMod.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import thKaguyaMod.item.*;

public class ItemHandler {
    public static final Item Item_Closed_Third_Eye = new ClosedThirdEyeItem();
    public static final Item Item_Third_Eye = new ThirdEyeItem();
    public static void register(){
        Registry.register(
                Registries.ITEM,
                new Identifier("thkaguyamod", "closed_third_eye"),
                Item_Closed_Third_Eye
        );
        Registry.register(
                Registries.ITEM,
                new Identifier("thkaguyamod", "third_eye"),
                Item_Third_Eye
        );
    }
}
