package com.ilexiconn.jurassicraft;

import com.ilexiconn.jurassicraft.logger.LogType;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "jurassicraft", name = "Fossils and Archaeology mod", version = "1.1.2")
public class JurassiCraft extends Util
{
    @Mod.Instance("jurassicraft")
    public static JurassiCraft instance;

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event)
    {
        getLogger().print(LogType.INFO, "LOADING MOD...");
        getData().init(event);
    }
}
