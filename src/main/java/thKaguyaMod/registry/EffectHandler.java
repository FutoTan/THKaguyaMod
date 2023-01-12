package thKaguyaMod.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import thKaguyaMod.effect.UnconsciousEffect;

public class EffectHandler {
    public static final StatusEffect Unconscious_Effect = new UnconsciousEffect();
    public static void register(){
        Registry.register(
                Registries.STATUS_EFFECT,
                new Identifier("thkaguyamod", "unconscious"),
                Unconscious_Effect
        );
    }
}
