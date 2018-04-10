package me.minebuilders.clearlag.listeners;

import java.util.Iterator;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLimiterListener extends EventModule {
   private int limit;
   private boolean createnew;

   @EventHandler
   public void onChunkLoad(ChunkLoadEvent event) {
      if (!this.createnew && event.isNewChunk() || this.countChunks() >= this.limit) {
         event.getChunk().unload(false);
         Chunk[] var5;
         int var4 = (var5 = event.getWorld().getLoadedChunks()).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            Chunk c = var5[var3];
            c.unload(true, true);
         }
      }

   }

   public int countChunks() {
      int size = 0;

      World w;
      for(Iterator var3 = Bukkit.getWorlds().iterator(); var3.hasNext(); size += w.getLoadedChunks().length) {
         w = (World)var3.next();
      }

      return size;
   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("chunk-limiter.enabled");
   }

   public void setValues() {
      this.limit = Config.getConfig().getInt("chunk-limiter.limit");
      this.createnew = Config.getConfig().getBoolean("chunk-limiter.create-new-chunks");
   }
}
