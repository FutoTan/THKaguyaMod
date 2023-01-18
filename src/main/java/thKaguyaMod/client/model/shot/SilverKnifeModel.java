package thKaguyaMod.client.model.shot;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Collections;

public class SilverKnifeModel {
    private final ModelPart edge;
    private final ModelPart handle;
    private final ModelPart blade;

    public SilverKnifeModel(){
        this.edge = new ModelPart(ModelPartBuilder.create().uv(0, 0)
                .cuboid(-4F, -5F, -1F, 7, 1, 3)
                .build().stream()
                .map((modelCuboidData) -> modelCuboidData.createCuboid(64, 32))
                .collect(ImmutableList.toImmutableList()), Collections.emptyMap());

        this.handle = new ModelPart(ModelPartBuilder.create().uv(0, 16)
                .cuboid(-2F, -9F, 0F, 3, 14, 1)
                .build().stream()
                .map((modelCuboidData) -> modelCuboidData.createCuboid(64, 32))
                .collect(ImmutableList.toImmutableList()), Collections.emptyMap());

        this.blade = new ModelPart(ModelPartBuilder.create().uv(32, 16)
                .cuboid(-1F, 5F, 0F, 1, 1, 1)
                .build().stream()
                .map((modelCuboidData) -> modelCuboidData.createCuboid(64, 32))
                .collect(ImmutableList.toImmutableList()), Collections.emptyMap());

        this.edge.pitch = (float)Math.PI / 2F;
        this.handle.pitch = (float)Math.PI / 2F;
        this.blade.pitch = (float)Math.PI / 2F;
    }

    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(
                this.edge,
                this.handle,
                this.blade
        ).forEach((modelRenderer) -> modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha));
    }
}
