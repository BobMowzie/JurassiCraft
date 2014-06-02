package com.ilexiconn.jurassicraft.data.gui;

import com.ilexiconn.jurassicraft.data.tile.TileAnalyzer;
import com.ilexiconn.jurassicraft.data.gui.container.ContainerAnalyzer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileAnalyzer) return new ContainerAnalyzer(player.inventory, (TileAnalyzer) tileEntity);
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileAnalyzer) return new GuiAnalyzer(player.inventory, (TileAnalyzer) tileEntity);
        return null;
    }
}
