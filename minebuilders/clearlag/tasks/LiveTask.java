package me.minebuilders.clearlag.tasks;

import java.util.Iterator;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.TaskModule;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;

public class LiveTask extends TaskModule {
   private int itemkilltime;
   private int entitykilltime;
   private int arrowkilltime;
   private boolean checkItem;
   private boolean checkEntity;
   private boolean checkArrow;

   public void load() {
      Configuration c = Config.getConfig();
      String p = "live-time.";
      this.itemkilltime = c.getInt(p + "itemlivetime") * 20;
      this.entitykilltime = c.getInt(p + "moblivetime") * 20;
      this.arrowkilltime = c.getInt(p + "arrowkilltime") * 20;
      this.checkItem = c.getBoolean(p + "itemtimer");
      this.checkEntity = c.getBoolean(p + "mobtimer");
      this.checkArrow = c.getBoolean(p + "checkArrow");
      this.resume();
   }

   public void run() {
      Iterator var2 = Bukkit.getWorlds().iterator();

      label44:
      while(var2.hasNext()) {
         World w = (World)var2.next();
         Iterator var4 = w.getEntities().iterator();

         while(true) {
            while(true) {
               if (!var4.hasNext()) {
                  continue label44;
               }

               Entity e = (Entity)var4.next();
               if (this.checkEntity && e instanceof LivingEntity && !(e instanceof HumanEntity)) {
                  if (e.getTicksLived() > this.entitykilltime) {
                     e.remove();
                  }
               } else if (this.checkItem && e instanceof Item) {
                  if (e.getTicksLived() > this.itemkilltime) {
                     e.remove();
                  }
               } else if (this.checkArrow && e instanceof Arrow && e.getTicksLived() > this.arrowkilltime) {
                  e.remove();
               }
            }
         }
      }

   }

   public void reload() {
      this.stop();
      this.load();
   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("live-time.enabled");
   }

   public int getInterval() {
      return Config.getConfig().getInt("live-time.interval") * 20;
   }
}
