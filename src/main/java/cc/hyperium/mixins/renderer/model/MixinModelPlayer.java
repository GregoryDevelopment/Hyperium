package cc.hyperium.mixins.renderer.model;

import cc.hyperium.event.EventBus;
import cc.hyperium.event.PostCopyPlayerModelAnglesEvent;
import cc.hyperium.event.PreCopyPlayerModelAnglesEvent;
import cc.hyperium.mixinsimp.renderer.model.IMixinModelPlayer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelPlayer.class)
public class MixinModelPlayer extends MixinModelBiped implements IMixinModelPlayer {

    /**
     * Will now be the left upperarm wear.
     */
    @Shadow
    public ModelRenderer bipedLeftArmwear;
    /**
     * Will now be the right upperarm wear.
     */
    @Shadow
    public ModelRenderer bipedRightArmwear;
    /**
     * Will now be the left upperleg wear.
     */
    @Shadow
    public ModelRenderer bipedLeftLegwear;
    /**
     * Will now be the right upperleg wear.
     */
    @Shadow
    public ModelRenderer bipedRightLegwear;
    @Shadow
    public ModelRenderer bipedBodyWear;
    private ModelRenderer bipedLeftForeArmwear;
    private ModelRenderer bipedRightForeArmwear;
    private ModelRenderer bipedLeftLowerLegwear;
    private ModelRenderer bipedRightLowerLegwear;
    @Shadow
    private ModelRenderer bipedCape;
    private ModelRenderer bipedRightLowerLeg_adjArmwear;
    private ModelRenderer bipedLeftLowerLeg_adjArmwear;
    private ModelRenderer bipedRightForeArm_adjArmwear;
    private ModelRenderer bipedLeftForeArm_adjArmwear;

    private ModelRenderer modelBib;

    @Override
    public ModelRenderer getBib() {
        return modelBib;
    }

    public void renderBib(float p_178728_1_)
    {
        this.modelBib.render(p_178728_1_);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectModelChanges(float modelSize, boolean useSmallArms, CallbackInfo ci) {
        this.modelBib = new ModelRenderer(this, 0, 0);
        this.modelBib.setTextureSize(64, 32);
        this.modelBib.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, modelSize);

        if (useSmallArms) {
            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 6, 4, modelSize);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
            this.bipedLeftForeArm = new ModelRenderer(this, 32, 54);
            this.bipedLeftForeArm.addBox(-1.0F, 4.0F, -2.0F, 3, 6, 4, modelSize);
            this.bipedLeftForeArm.setRotationPoint(5.0F, 2.5F, 0.0F);

            this.bipedLeftForeArm_adj = new ModelRenderer(this, 32, 54);
            this.bipedLeftForeArm_adj.addBox(-1.0F, 0, -2.0F, 3, 6, 4, modelSize);
            this.bipedLeftForeArm_adj.setRotationPoint(5.0F, 2.5F, 0.0F);


            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 6, 4, modelSize + 0.25F);
            this.bipedLeftArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);
            this.bipedLeftForeArmwear = new ModelRenderer(this, 48, 54);
            this.bipedLeftForeArmwear.addBox(-1.0F, 4.0F, -2.0F, 3, 6, 4, modelSize + 0.25F);
            this.bipedLeftForeArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);

            this.bipedRightArm = new ModelRenderer(this, 40, 16);
            this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 6, 4, modelSize);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
            this.bipedRightForeArm = new ModelRenderer(this, 40, 22);
            this.bipedRightForeArm.addBox(-2.0F, 4.0F, -2.0F, 3, 6, 4, modelSize);
            this.bipedRightForeArm.setRotationPoint(-5.0F, 2.5F, 0.0F);

            this.bipedRightForeArm_adj = new ModelRenderer(this, 40, 22);
            this.bipedRightForeArm_adj.addBox(-2.0F, 0, -2.0F, 3, 6, 4, modelSize);
            this.bipedRightForeArm_adj.setRotationPoint(-5.0F, 2.5F, 0.0F);


            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 6, 4, modelSize + 0.25F);
            this.bipedRightArmwear.setRotationPoint(-5.0F, 2.5F, 10.0F);

            this.bipedRightForeArmwear = new ModelRenderer(this, 40, 38);
            this.bipedRightForeArmwear.addBox(-2.0F, 4.0F, -2.0F, 3, 6, 4, modelSize + 0.25F);
            this.bipedRightForeArmwear.setRotationPoint(-5.0F, 2.5F, 10.0F);

            this.bipedRightForeArm_adjArmwear = new ModelRenderer(this, 40, 38);
            this.bipedRightForeArm_adjArmwear.addBox(-2.0F, 0, -2.0F, 3, 6, 4, modelSize + 0.25F);
            this.bipedRightForeArm_adjArmwear.setRotationPoint(-5.0F, 2.5F, 10.0F);

            this.bipedLeftForeArm_adjArmwear = new ModelRenderer(this, 48, 54);
            this.bipedLeftForeArm_adjArmwear.addBox(-1.0F, 0, -2.0F, 3, 6, 4, modelSize + 0.25F);
            this.bipedLeftForeArm_adjArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);

        } else {
            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 6, 4, modelSize);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
            this.bipedLeftForeArm = new ModelRenderer(this, 32, 54);
            this.bipedLeftForeArm.addBox(-1.0F, 4.0F, -2.0F, 4, 6, 4, modelSize);
            this.bipedLeftForeArm.setRotationPoint(5.0F, 2.0F, 0.0F);

            this.bipedLeftForeArm_adj = new ModelRenderer(this, 32, 54);
            this.bipedLeftForeArm_adj.addBox(-1.0F, 0, -2.0F, 4, 6, 4, modelSize);
            this.bipedLeftForeArm_adj.setRotationPoint(5.0F, 2.0F, 0.0F);


            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 6, 4, modelSize + 0.25F);
            this.bipedLeftArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);
            this.bipedLeftForeArmwear = new ModelRenderer(this, 48, 54);
            this.bipedLeftForeArmwear.addBox(-1.0F, 4.0F, -2.0F, 4, 6, 4, modelSize + 0.25F);
            this.bipedLeftForeArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);

            this.bipedRightArm = new ModelRenderer(this, 40, 16);
            this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 6, 4, modelSize);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
            this.bipedRightForeArm = new ModelRenderer(this, 40, 22);
            this.bipedRightForeArm.addBox(-3.0F, 4.0F, -2.0F, 4, 6, 4, modelSize);
            this.bipedRightForeArm.setRotationPoint(-5.0F, 2.0F, 0.0F);

            this.bipedRightForeArm_adj = new ModelRenderer(this, 40, 22);
            this.bipedRightForeArm_adj.addBox(-3.0F, 0.0F, -2.0F, 4, 6, 4, modelSize);
            this.bipedRightForeArm_adj.setRotationPoint(-5.0F, 2.0F, 0.0F);


            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 6, 4, modelSize + 0.25F);
            this.bipedRightArmwear.setRotationPoint(-5.0F, 2.0F, 10.0F);

            this.bipedRightForeArmwear = new ModelRenderer(this, 40, 38);
            this.bipedRightForeArmwear.addBox(-3.0F, 4.0F, -2.0F, 4, 6, 4, modelSize + 0.25F);
            this.bipedRightForeArmwear.setRotationPoint(-5.0F, 2.0F, 10.0F);

            this.bipedRightForeArm_adjArmwear = new ModelRenderer(this, 40, 38);
            this.bipedRightForeArm_adjArmwear.addBox(-3.0F, 0, -2.0F, 4, 6, 4, modelSize + 0.25F);
            this.bipedRightForeArm_adjArmwear.setRotationPoint(-5.0F, 2.0F, 10.0F);

            this.bipedLeftForeArm_adjArmwear = new ModelRenderer(this, 48, 54);
            this.bipedLeftForeArm_adjArmwear.addBox(-1.0F, 0, -2.0F, 4, 6, 4, modelSize + 0.25F);
            this.bipedLeftForeArm_adjArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);

        }
        this.butt = new ModelRenderer(this, 16, 16 + 8);
        this.butt.addBox(-4.0F, 0.0F, 0.0F, 8, 4, 4, modelSize);
        this.butt.setRotationPoint(0, 16, 0);
        this.butt.showModel = false;

        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSize);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLowerLeg = new ModelRenderer(this, 16, 54);
        this.bipedLeftLowerLeg.addBox(-2.0F, 6.0F, -2.0F, 4, 6, 4, modelSize);
        this.bipedLeftLowerLeg.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
        this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSize + 0.25F);
        this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLowerLegwear = new ModelRenderer(this, 0, 54);
        this.bipedLeftLowerLegwear.addBox(-2.0F, 6.0F, -2.0F, 4, 6, 4, modelSize + 0.25F);
        this.bipedLeftLowerLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSize);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightLowerLeg = new ModelRenderer(this, 0, 22);
        this.bipedRightLowerLeg.addBox(-2.0F, 6.0F, -2.0F, 4, 6, 4, modelSize);
        this.bipedRightLowerLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);

        this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
        this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSize + 0.25F);
        this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightLowerLegwear = new ModelRenderer(this, 0, 38);
        this.bipedRightLowerLegwear.addBox(-2.0F, 6.0F, -2.0F, 4, 6, 4, modelSize + 0.25F);
        this.bipedRightLowerLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);

        this.bipedLeftLowerLeg_adj = new ModelRenderer(this, 0, 22);
        this.bipedLeftLowerLeg_adj.addBox(-2.0F, 0, -2.0F, 4, 6, 4, modelSize);
        this.bipedLeftLowerLeg_adj.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.bipedRightLowerLeg_adj = new ModelRenderer(this, 0, 22);
        this.bipedRightLowerLeg_adj.addBox(-2.0F, 0, -2.0F, 4, 6, 4, modelSize);
        this.bipedRightLowerLeg_adj.setRotationPoint(-1.9F, 12.0F, 0.0F);


        this.bipedLeftLowerLeg_adjArmwear = new ModelRenderer(this, 0, 54);
        this.bipedLeftLowerLeg_adjArmwear.addBox(-2.0F, 0, -2.0F, 4, 6, 4, modelSize + 0.25F);
        this.bipedLeftLowerLeg_adjArmwear.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.bipedRightLowerLeg_adjArmwear = new ModelRenderer(this, 0, 38);
        this.bipedRightLowerLeg_adjArmwear.addBox(-2.0F, 0, -2.0F, 4, 6, 4, modelSize + 0.25F);
        this.bipedRightLowerLeg_adjArmwear.setRotationPoint(-1.9F, 12.0F, 0.0F);

//
        this.bipedRightLowerLeg_adjArmwear.showModel = false;
        this.bipedLeftLowerLeg_adjArmwear.showModel = false;
        this.bipedRightLowerLeg_adj.showModel = false;
        this.bipedLeftLowerLeg_adj.showModel = false;


        bipedRightForeArm_adj.showModel = false;
        bipedLeftForeArm_adj.showModel = false;
        bipedLeftForeArm_adjArmwear.showModel = false;
        bipedRightForeArm_adjArmwear.showModel = false;


        this.butt.showModel = false;
        fixTopAndBottomOfLimbWrongTextures(
                this.bipedLeftForeArm, this.bipedLeftForeArmwear, //
                this.bipedRightForeArm, this.bipedRightForeArmwear, //
                this.bipedLeftLowerLeg, this.bipedLeftLowerLegwear, //
                this.bipedRightLowerLeg, this.bipedRightLowerLegwear, //
                this.bipedLeftLowerLeg_adj, bipedRightLowerLeg_adj,
                bipedLeftLowerLeg_adjArmwear, bipedRightLowerLeg_adjArmwear,
                bipedRightForeArm_adj, bipedLeftForeArm_adj,
                bipedLeftForeArm_adjArmwear, bipedRightForeArm_adjArmwear
        );
    }

    @Override
    public ModelRenderer getBipedLeftForeArm_adjArmwear() {
        return bipedLeftForeArm_adjArmwear;
    }

    @Override
    public ModelRenderer getBipedRightForeArm_adjArmwear() {
        return bipedRightForeArm_adjArmwear;
    }

    /**
     * @author 9Y0, Mojang
     */
    @Overwrite
    public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) {
        super.render(entityIn, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale);
        GlStateManager.pushMatrix();

        if (this.isChild) {
            float f = 2.0F;
            GlStateManager.scale(1.0F / f, 1.0F / f, 1.0F / f);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.bipedLeftLegwear.render(scale);
            this.bipedRightLegwear.render(scale);
            this.bipedLeftArmwear.render(scale);
            this.bipedRightArmwear.render(scale);
            this.bipedBodyWear.render(scale);

            // Rendering the extra parts we created
            this.bipedLeftForeArmwear.render(scale);
            this.bipedLeftForeArm.render(scale);
            this.bipedRightForeArmwear.render(scale);
            this.bipedRightForeArm.render(scale);
            this.bipedLeftLowerLeg.render(scale);
            this.bipedLeftLowerLegwear.render(scale);
            this.bipedRightLowerLeg.render(scale);
            this.bipedRightLowerLegwear.render(scale);
            this.bipedRightLowerLeg_adj.render(scale);
            this.bipedLeftLowerLeg_adj.render(scale);
            this.bipedRightLowerLeg_adjArmwear.render(scale);
            this.bipedLeftLowerLeg_adjArmwear.render(scale);

            this.bipedLeftForeArm_adj.render(scale);
            this.bipedRightForeArm_adj.render(scale);
            this.bipedLeftForeArm_adjArmwear.render(scale);
            this.bipedRightForeArm_adjArmwear.render(scale);

            this.butt.render(scale);
        } else {
            if (entityIn.isSneaking()) {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }

            this.bipedLeftLegwear.render(scale);
            this.bipedRightLegwear.render(scale);
            this.bipedLeftArmwear.render(scale);
            this.bipedRightArmwear.render(scale);
            this.bipedBodyWear.render(scale);

            // Rendering the extra parts we created
            this.bipedLeftForeArmwear.render(scale);
            this.bipedLeftForeArm.render(scale);
            this.bipedRightForeArmwear.render(scale);
            this.bipedRightForeArm.render(scale);
            this.bipedLeftLowerLeg.render(scale);
            this.bipedLeftLowerLegwear.render(scale);
            this.bipedRightLowerLeg.render(scale);
            this.bipedRightLowerLegwear.render(scale);
            this.bipedRightLowerLeg_adj.render(scale);
            this.bipedLeftLowerLeg_adj.render(scale);
            this.bipedRightLowerLeg_adjArmwear.render(scale);
            this.bipedLeftLowerLeg_adjArmwear.render(scale);

            this.bipedLeftForeArm_adj.render(scale);
            this.bipedRightForeArm_adj.render(scale);
            this.bipedLeftForeArm_adjArmwear.render(scale);
            this.bipedRightForeArm_adjArmwear.render(scale);

            this.butt.render(scale);
        }

        GlStateManager.popMatrix();
    }

    @Inject(method = "renderRightArm", at = @At("RETURN"))
    private void renderRightArm(CallbackInfo ci) {
        this.bipedRightForeArm.render(0.0625F);
        this.bipedRightForeArmwear.render(0.0625F);
    }

    @Inject(method = "renderLeftArm", at = @At("RETURN"))
    private void renderLeftArm(CallbackInfo ci) {
        this.bipedLeftForeArm.render(0.0625F);
        this.bipedLeftForeArmwear.render(0.0625F);
    }

    @Inject(method = "setRotationAngles", at = @At("RETURN"))
    private void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn, CallbackInfo ci) {
        // This should always be the case I guess, but we better be safe
        if (entityIn instanceof AbstractClientPlayer) {
            EventBus.INSTANCE.post(new PreCopyPlayerModelAnglesEvent(((AbstractClientPlayer) entityIn), this));
        }

        copyModelAnglesAndOffest(this.bipedLeftArm, this.bipedLeftForeArm);
        copyModelAnglesAndOffest(this.bipedRightArm, this.bipedRightForeArm);
        copyModelAnglesAndOffest(this.bipedLeftArmwear, this.bipedLeftForeArmwear);
        copyModelAnglesAndOffest(this.bipedRightArmwear, this.bipedRightForeArmwear);

        copyModelAnglesAndOffest(this.bipedLeftLeg, this.bipedLeftLowerLeg);
        copyModelAnglesAndOffest(this.bipedRightLeg, this.bipedRightLowerLeg);
        copyModelAnglesAndOffest(this.bipedLeftLegwear, this.bipedLeftLowerLegwear);
        copyModelAnglesAndOffest(this.bipedRightLegwear, this.bipedRightLowerLegwear);

        if (entityIn instanceof AbstractClientPlayer) {
            EventBus.INSTANCE.post(new PostCopyPlayerModelAnglesEvent(((AbstractClientPlayer) entityIn), this));
        }
    }

    @Inject(method = "setInvisible", at = @At("RETURN"))
    private void setInvisible(boolean invisble, CallbackInfo ci) {
        this.bipedLeftForeArmwear.showModel = invisble;
        this.bipedRightForeArmwear.showModel = invisble;
        this.bipedLeftLowerLegwear.showModel = invisble;
        this.bipedRightLowerLegwear.showModel = invisble;
        this.bipedRightLowerLeg_adjArmwear.showModel = invisble;
        this.bipedLeftLowerLeg_adjArmwear.showModel = invisble;
        this.butt.showModel = invisble;

    }

    /* Right leg wrappers */
    @Override
    public ModelRenderer getBipedRightUpperLegwear() {
        return this.bipedRightLegwear;
    }

    @Override
    public ModelRenderer getBipedRightLowerLegwear() {
        return this.bipedRightLowerLegwear;
    }

    /* Left leg wrappers */
    @Override
    public ModelRenderer getBipedLeftUpperLegwear() {
        return this.bipedLeftLegwear;
    }

    @Override
    public ModelRenderer getBipedLeftLowerLegwear() {
        return this.bipedLeftLowerLegwear;
    }

    /* Right arm wrappers */
    @Override
    public ModelRenderer getBipedRightUpperArmwear() {
        return this.bipedRightArmwear;
    }

    @Override
    public ModelRenderer getBipedRightForeArmwear() {
        return this.bipedRightForeArmwear;
    }

    /* Left arm wrappers */
    @Override
    public ModelRenderer getBipedLeftUpperArmwear() {
        return this.bipedLeftArmwear;
    }

    @Override
    public ModelRenderer getBipedLeftForeArmwear() {
        return this.bipedLeftForeArmwear;
    }

    /* Body wrappers */
    @Override
    public ModelRenderer getBipedBodywear() {
        return this.bipedBodyWear;
    }

    @Override
    public ModelRenderer getCape() {
        return bipedCape;
    }

    @Override
    public ModelRenderer getBipedRightLowerLeg_adjLegwear() {
        return bipedRightLowerLeg_adjArmwear;
    }

    @Override
    public ModelRenderer getBipedLeftLowerLeg_adjLegwear() {
        return bipedLeftLowerLeg_adjArmwear;
    }
}
