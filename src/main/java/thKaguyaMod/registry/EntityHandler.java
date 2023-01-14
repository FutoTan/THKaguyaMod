package thKaguyaMod.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import thKaguyaMod.entity.mob.CirnoEntity;
import thKaguyaMod.entity.shot.THShotEntity;

public class EntityHandler {
    public static final EntityType<CirnoEntity> CIRNO_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("thkaguyamod", "cirno"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CirnoEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75F, 1.75F))
                    .build()
    );

    public static final EntityType<Entity> TH_SHOT_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("thkaguyamod", "th_shot"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, THShotEntity::new)
                    .dimensions(EntityDimensions.changing(0F, 0F))
                    .build()
    );


    public static void register(){
        FabricDefaultAttributeRegistry.register(CIRNO_ENTITY, CirnoEntity.createMobAttributes());
    }
}
