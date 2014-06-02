package com.ilexiconn.jurassicraft.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger
{
    public void print(LogType type, String message, Object... obj)
    {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

        switch (type)
        {
            case INFO:
                System.out.println("[" + time + "] [JurassiCraft/INFO] " + message);
                break;
            case WARNING:
                System.out.println("[" + time + "] [JurassiCraft/WARNING] " + message);
                if (obj[0] != null && obj[0] instanceof Exception) ((Exception) obj[0]).printStackTrace();
                break;
            case ERROR:
                System.err.println("[" + time + "] [JurassiCraft/ERROR] " + message);
                if (obj[0] != null && obj[0] instanceof Exception) ((Exception) obj[0]).printStackTrace();
                break;
        }
    }
}
