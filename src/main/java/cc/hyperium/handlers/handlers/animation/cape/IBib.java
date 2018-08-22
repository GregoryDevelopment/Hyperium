package cc.hyperium.handlers.handlers.animation.cape;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public interface IBib {

    ResourceLocation get();

    void delete(TextureManager manager);
}
