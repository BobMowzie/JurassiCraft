package com.ilexiconn.jurassicraft.data.tile.render;

import assets.fossils.textures.models.blocks.ModelCultivate;
import com.ilexiconn.jurassicraft.Util;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderCultivate extends TileEntitySpecialRenderer
{
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float h)
    {
        GL11.glPushMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        bindTexture(new ResourceLocation(Util.getModId() + "textures/blocks/cultivate_idle.png"));
        GL11.glRotatef(180f, 0f, 0f, 1f);
        new ModelCultivate().render();
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
    }
}
