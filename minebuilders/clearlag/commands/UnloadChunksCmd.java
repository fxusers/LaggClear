package me.minebuilders.clearlag.commands;

import java.util.Iterator;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

public class UnloadChunksCmd extends CommandModule 
{
   public UnloadChunksCmd() 
   {
      super.forcePlayer = false;
      super.cmdName = "unloadchunks";
      super.argLength = 1;
      super.usage = "(Unloads unused chunks)";
   }

   public boolean run() 
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

      // Babar
      Util.msg("All chunks ("+ var1 "). "+" &6" + chunkcount + " &bChunks have been unloaded!", super.sender);
      return true;
   }
}