package me.minebuilders.clearlag.listeners;

import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class EggSpawnListener extends EventModule {
   private int max;
   private int radius;

   @EventHandler
   public void onCreatureSpawn(CreatureSpawnEvent event) {
      if (event.getSpawnReason() == SpawnReason.SPAWNER_EGG && !event.isCancelled()) {
         Entity e = event.getEntity();
         if (e.getNearbyEntities((double)this.radius, (double)this.radius, (double)this.radius).size() > this.max) {
            event.setCancelled(true);
            return;
         }
      }

   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("mobegg-limiter.enabled");
   }

   public void setValues() {
      this.max = Config.getConfig().getInt("mobegg-limiter.max-mobs") + 1;
      this.radius = Config.getConfig().getInt("mobegg-limiter.check-radius");
   }
}
