package me.minebuilders.clearlag.listeners;

import java.util.Iterator;
import me.minebuilders.clearlag.Clearlag;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobLimitListener extends EventModule {
   private int animallimit = 0;
   private int moblimit = 0;
   private int interval = 0;
   private int sched = -1;
   private World[] worlds;
   public boolean canAnimalspawn = true;
   public boolean canMobspawn = true;

   public void spawnCheck() {
      int animals = 0;
      int mosters = 0;
      World[] var6 = this.worlds;
      int var5 = this.worlds.length;

      label45:
      for(int var4 = 0; var4 < var5; ++var4) {
         World world = var6[var4];
         Iterator var8 = world.getEntities().iterator();

         while(true) {
            Entity e;
            do {
               if (!var8.hasNext()) {
                  continue label45;
               }

               e = (Entity)var8.next();
               if (e instanceof Animals || e instanceof Villager) {
                  ++animals;
               }
            } while(!(e instanceof Creature) && !(e instanceof Monster));

            ++mosters;
         }
      }

      if (mosters >= this.moblimit) {
         this.canAnimalspawn = false;
      } else {
         this.canAnimalspawn = true;
      }

      if (animals >= this.animallimit) {
         this.canMobspawn = false;
      } else {
         this.canMobspawn = true;
      }

   }

   public void resetSheds() {
      if (this.sched != -1) {
         Bukkit.getServer().getScheduler().cancelTask(this.sched);
      }

      this.sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Clearlag.getInstance(), new Runnable() {
         public void run() {
            MobLimitListener.this.spawnCheck();
         }
      }, (long)this.interval * 20L, (long)this.interval * 20L);
   }

   @EventHandler
   public void onMobSpawn(CreatureSpawnEvent e) {
      Entity en = e.getEntity();
      if (en instanceof Animals && !this.canAnimalspawn) {
         e.setCancelled(true);
      } else if (en instanceof Creature && !this.canMobspawn) {
         e.setCancelled(true);
      }

   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("spawn-limiter.enabled");
   }

   public void setValues() {
      this.worlds = (World[])Bukkit.getServer().getWorlds().toArray(new World[0]);
      this.animallimit = Config.getConfig().getInt("spawn-limiter.animals");
      this.moblimit = Config.getConfig().getInt("spawn-limiter.monsters");
      this.interval = Config.getConfig().getInt("spawn-limiter.interval");
      this.spawnCheck();
      this.resetSheds();
   }
}
