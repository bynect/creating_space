package com.rae.creatingspace.mixin.recipe;

import com.google.gson.JsonObject;
import com.rae.creatingspace.recipes.IMoreNbtConditions;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ProcessingRecipeSerializer.class)
public abstract class ProcessingRecipeRecipeSerializerMixin {
    @Inject(method = "readFromJson", at = @At("RETURN"), remap = false, cancellable = true)
    public void readKeepNbtJson(ResourceLocation recipeId, JsonObject json, CallbackInfoReturnable<ProcessingRecipe> cir) {
        ProcessingRecipe<?> recipe = cir.getReturnValue();
        if (GsonHelper.isValidNode(json, "keepNbt")) {
            ((IMoreNbtConditions) recipe).setKeepNbt(GsonHelper.getAsBoolean(json, "keepNbt"));
        }
        if (GsonHelper.isValidNode(json, "machNbt")) {
            ((IMoreNbtConditions) recipe).setMachNbt(GsonHelper.getAsBoolean(json, "machNbt"));
        }
        cir.setReturnValue(recipe);
    }
}