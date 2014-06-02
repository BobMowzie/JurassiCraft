package com.ilexiconn.jurassicraft.data.tile.render;

import assets.jurassicraft.textures.models.blocks.ModelCultivate;
import com.ilexiconn.jurassicraft.Util;
import com.ilexiconn.jurassicraft.data.tile.TileCultivate;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CultivateRenderer extends TileEntitySpecialRenderer
{
    private ModelCultivate model = new ModelCultivate();
    private ResourceLocation active = new ResourceLocation(Util.getModId() + "textures/blocks/cultivate_active.png");
    private ResourceLocation idle = new ResourceLocation(Util.getModId() + "textures/blocks/cultivate_idle.png");

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float h)
    {
        TileCultivate tile = (TileCultivate) tileEntity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        bindTexture(tile.getActive() ? active : idle);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        model.render();
        GL11.glPopMatrix();
    }
}
