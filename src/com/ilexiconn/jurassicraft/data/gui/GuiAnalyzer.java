package com.ilexiconn.jurassicraft.data.gui;

import com.ilexiconn.jurassicraft.Util;
import com.ilexiconn.jurassicraft.data.tile.TileAnalyzer;
import com.ilexiconn.jurassicraft.data.gui.container.ContainerAnalyzer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiAnalyzer extends GuiContainer
{
    private TileAnalyzer tileEntityAnalyzer;

    public GuiAnalyzer(InventoryPlayer inventory, TileAnalyzer tileEntity)
    {
        super(new ContainerAnalyzer(inventory, tileEntity));
        this.tileEntityAnalyzer = tileEntity;

    }

    @Override
    public void drawGuiContainerForegroundLayer(int i, int d)
    {
        fontRendererObj.drawString(StatCollector.translateToLocal("container.analyzer"), 8, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96, 4210752);
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float i, int d, int k)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(new ResourceLocation(Util.getModId() + "textures/gui/guiAnalyzer.png"));
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7 = this.tileEntityAnalyzer.getCookPrograssScaled(21);
        this.drawTexturedModalRect(var5 + 80, var6 + 22, 177, 18, var7 + 1, 9);
    }
}
