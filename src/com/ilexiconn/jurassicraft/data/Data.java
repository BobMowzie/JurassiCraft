package com.ilexiconn.jurassicraft.data;

import com.ilexiconn.jurassicraft.Util;
import com.ilexiconn.jurassicraft.data.block.*;
import com.ilexiconn.jurassicraft.data.gui.GuiHandler;
import com.ilexiconn.jurassicraft.data.item.ItemAmber;
import com.ilexiconn.jurassicraft.data.item.ItemCultivate;
import com.ilexiconn.jurassicraft.data.item.ItemDNA;
import com.ilexiconn.jurassicraft.data.item.ItemFossil;
import com.ilexiconn.jurassicraft.data.tile.TileAnalyzer;
import com.ilexiconn.jurassicraft.data.tile.TileCultivate;
import com.ilexiconn.jurassicraft.data.tile.render.RenderCultivate;
import com.ilexiconn.jurassicraft.data.world.gen.WorldGenAmberOre;
import com.ilexiconn.jurassicraft.data.world.gen.WorldGenFossilOre;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public final class Data extends Util
{
    public void init(FMLPreInitializationEvent event)
    {
        { /** Creativetabs */
            addCreativeTab(0, new CreativeTabs("fossilTab")
            {
                public Item getTabIconItem()
                {
                    return Items.arrow;
                }
            });
        }
        { /** Blocks */
            addBlockWithTileEntity(0, new BlockCultivate(), TileCultivate.class, true); //cultivate
            addBlock(1, new GhostBlock("cultivate_ghost", new int[]{-1}, -1, 0, 0f, -1f, 0f, 1f, 1f, 1f)); //cultivate_ghost
            addBlockWithTileEntity(2, new BlockAnalyzer(true), TileAnalyzer.class, true); //analyzer_active
            addBlockWithTileEntity(3, new BlockAnalyzer(false), TileAnalyzer.class, false); //analyzer_idle
            addBlock(4, new BlockAmberOre());
            addBlock(5, new BlockFossilOre());
        }
        { /** Items */
            addItem(0, new ItemCultivate());
            addItem(1, new ItemAmber());
            addItem(2, new ItemFossil());
        }
        { /** DNAs */
            addDNA(new ItemDNA("base"));
        }
        { /** Entities */

        }
        { /** World Gens */
            addWorldGenerator(new WorldGenFossilOre(), 1);
            addWorldGenerator(new WorldGenAmberOre(), 2);
        }
        { /** Renderers */
            addTileEntityRenderer(TileCultivate.class, new RenderCultivate());
        }
        { /** Other stuff */
            addGuiHandler(new GuiHandler());
        }
    }
}
