package thKaguyaMod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import thKaguyaMod.registry.EffectHandler;

public class ClosedThirdEyeItem extends Item {
    public ClosedThirdEyeItem() {
        super(new FabricItemSettings().maxCount(1));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.getItemCooldownManager().set(this, 20);
        playerEntity.addStatusEffect(new StatusEffectInstance(EffectHandler.UNCONSCIOUS_EFFECT,20));
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
