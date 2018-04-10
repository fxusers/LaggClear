package me.minebuilders.clearlag.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkEntityLimiterListener extends EventModule {
   private int limit;

   public Entity[] filter(Entity[] e) {
      List ed = new ArrayList();
      Entity[] var6 = e;
      int var5 = e.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         Entity es = var6[var4];
         if (!(es instanceof Player) && Modules.EntityManager.isRemovable(es)) {
            ed.add(es);
         }
      }

      return (Entity[])ed.toArray(new Entity[ed.size()]);
   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = false
   )
   public void onChunkLoad(ChunkLoadEvent event) {
      Entity[] ents = this.filter(event.getChunk().getEntities());
      if (ents.length > this.limit) {
         this.trim(ents);
      }

   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = false
   )
   public void onSpawn(CreatureSpawnEvent event) {
      Entity[] ents = this.filter(event.getEntity().getLocation().getChunk().getEntities());
      if (ents.length > this.limit) {
         this.trim(ents);
      }

   }

   public void trim(Entity[] es) {
      int id = es.length - this.limit;

      for(int i = 0; i < id; ++i) {
         es[i].remove();
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
      return Config.getConfig().getBoolean("chunk-entity-limiter.enabled");
   }

   public void setValues() {
      this.limit = Config.getConfig().getInt("chunk-entity-limiter.limit") + 1;
   }
}
