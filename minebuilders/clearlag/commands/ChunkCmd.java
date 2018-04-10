package me.minebuilders.clearlag.commands;

import java.util.Iterator;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

public class ChunkCmd extends CommandModule {
   public ChunkCmd() {
      super.forcePlayer = false;
      super.cmdName = "chunk";
      super.argLength = 1;
      super.usage = "(Finds laggy chunks)";
   }

   public boolean run() 
   {
      Chunk topchunk = null;
      int totalent = -1;
      Iterator var4 = Bukkit.getWorlds().iterator();

      while (var4.hasNext()) 
      {
         World world = (World)var4.next();
         Chunk[] var8;
         int var7 = (var8 = world.getLoadedChunks()).length;

         for (int var6 = 0; var6 < var7; ++var6) 
         {
            Chunk c = var8[var6];
            if (c.getEntities().length > totalent) 
            {
               topchunk = c;
               totalent = c.getEntities().length;
            }
         }
      }

      Util.msg("&3Largest Chunk in &b" + topchunk.getWorld().getName() + " " + topchunk.getX() + "&3,&b " + topchunk.getZ() + " &3with&b " + totalent + " &3entites!", super.sender);
      return true;
   }
}