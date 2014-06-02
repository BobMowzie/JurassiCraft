package com.ilexiconn.jurassicraft.data.tile;

import com.ilexiconn.jurassicraft.Util;
import net.minecraft.tileentity.TileEntity;

public class TileCultivate extends TileEntity
{
    public boolean getActive()
    {
        return worldObj.getBlock(xCoord, yCoord, zCoord) == Util.getBlock(0);
    }
}
