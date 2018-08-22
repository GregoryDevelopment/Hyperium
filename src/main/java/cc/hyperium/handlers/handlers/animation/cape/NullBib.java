package cc.hyperium.handlers.handlers.animation.cape;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class NullBib implements IBib {
    public static final NullBib INSTANCE = new NullBib();

    private NullBib() {

    }

    @Override
    public ResourceLocation get() {
        return null;
    }

    @Override
    public void delete(TextureManager manager) {

    }
}
