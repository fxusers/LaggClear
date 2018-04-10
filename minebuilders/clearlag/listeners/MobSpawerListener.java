package me.minebuilders.clearlag.listeners;

import java.util.Iterator;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.Chunk;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class MobSpawerListener extends EventModule {
   private boolean mobspawner = false;
   private int mobamount = 0;
   private boolean entitylimit = false;
   private boolean cleanchunk = false;
   private int enlimit = 0;
   private boolean disableWither = false;

   @EventHandler
   public void onCreatureSpawn(CreatureSpawnEvent event) {
      Entity e = event.getEntity();
      Entity en;
      if (event.getSpawnReason().equals(SpawnReason.SPAWNER)) {
         if (!this.mobspawner) {
            return;
         }

         int count = 0;
         Iterator var5 = e.getNearbyEntities(15.0D, 15.0D, 15.0D).iterator();

         while(var5.hasNext()) {
            en = (Entity)var5.next();
            if (en instanceof LivingEntity) {
               ++count;
            }
         }

         if (count >= this.mobamount) {
            event.setCancelled(true);
         }
      } else if (event.getSpawnReason().equals(SpawnReason.BUILD_WITHER)) {
         if (this.disableWither) {
            e.remove();
         }
      } else {
         if (!this.entitylimit) {
            return;
         }

         Chunk c = e.getLocation().getChunk();
         if (c.getEntities().length >= this.enlimit) {
            event.setCancelled(true);
            if (this.cleanchunk) {
               Entity[] var7;
               int var6 = (var7 = c.getEntities()).length;

               for(int var9 = 0; var9 < var6; ++var9) {
                  en = var7[var9];
                  if (en instanceof Animals || en instanceof Creature || en instanceof Arrow) {
                     en.remove();
                  }
               }
            }
         }
      }

   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("mobspawner.enabled") || Config.getConfig().getBoolean("chunk-entity-limiter.enabled") || Config.getConfig().getBoolean("kill-mobs.disable-build-wither");
   }

   public void setValues() {
      this.mobspawner = Config.getConfig().getBoolean("mobspawner.enabled");
      this.mobamount = Config.getConfig().getInt("mobspawner.max-spawn");
      this.entitylimit = Config.getConfig().getBoolean("chunk-entity-limiter.enabled");
      this.cleanchunk = Config.getConfig().getBoolean("chunk-entity-limiter.clean-chunk");
      this.enlimit = Config.getConfig().getInt("chunk-entity-limiter.limit");
      this.disableWither = Config.getConfig().getBoolean("kill-mobs.disable-build-wither");
   }
}
