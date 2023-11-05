package com.rae.creatingspace.client.gui.screen.elements;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rae.creatingspace.init.graphics.GuiTexturesInit;
import com.simibubi.create.foundation.gui.widget.ScrollInput;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class BackgroundScrollInput extends ScrollInput {
    public BackgroundScrollInput(int xIn, int yIn, int widthIn, int heightIn) {
        super(xIn, yIn, widthIn, heightIn);
    }

    @Override
    public void renderButton(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        GuiTexturesInit.SCROLL_BACKGROUND.render(graphics,getX()-1,getY()-1);
        super.renderButton(graphics, mouseX, mouseY, partialTicks);
    }
}