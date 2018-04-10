package me.minebuilders.clearlag.commands;

import java.util.Iterator;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

public class GcCmd extends CommandModule {
   public GcCmd() {
      super.forcePlayer = false;
      super.cmdName = "gc";
      super.argLength = 1;
      super.usage = "(Request garbage collecter)";
   }

   public boolean run() 
   {
      // Babar
      Util.msg("Before: " + 
               " maxMemory ("  + Runtime.getRuntime().maxMemory()   + ")." + 
               "totalMemory ("+ Runtime.getRuntime().totalMemory() + ")." +
               "freeMemory (" + Runtime.getRuntime().freeMemory()  + ").",
               super.sender);

      // Babar unLoadChunks ==>
      {
         int chunkcount = 0;
         int var1 = 0;
         Iterator var3 = Bukkit.getServer().getWorlds().iterator();
      
         while(var3.hasNext()) 
         {
            World world = (World)var3.next();
            Chunk[] var7;
            int var6 = (var7 = world.getLoadedChunks()).length;
            var1 += var6;
      
            for(int var5 = 0; var5 < var6; ++var5) 
            {
               Chunk chunk = var7[var5];
               //Babar if (chunk.unload(true, true))
               if (chunk.unload(true))
               {
                  ++chunkcount;
               }
            }
         }
      
         Util.msg("All chunks ("+ var1 "). "+" &6" + chunkcount + " &bChunks have been unloaded!", super.sender);
         return true;
      } // <== Babar

      Util.msg(ChatColor.AQUA + "Attemping to free memory!", super.sender);

      System.gc();

      Util.msg("After: " + 
               "maxMemory ("  + Runtime.getRuntime().maxMemory()   + ")." + 
               "totalMemory ("+ Runtime.getRuntime().totalMemory() + ")." +
               "freeMemory (" + Runtime.getRuntime().freeMemory()  + ").",
               super.sender);

      return true;
   }
}