package me.minebuilders.clearlag.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.TaskModule;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class LimitTask extends TaskModule {
   public void load() {
      this.resume();
   }

   public void run() {
      List ents = new ArrayList();
      Iterator var3 = Bukkit.getWorlds().iterator();

      while(var3.hasNext()) {
         World w = (World)var3.next();
         ents.addAll(Modules.LimitClear.getRemovables(w.getEntities(), w));
      }

      int limit = Config.getConfig().getInt("limit.max");
      if (ents.size() > limit) {
         Iterator var4 = ents.iterator();

         while(var4.hasNext()) {
            Entity entity = (Entity)var4.next();
            entity.remove();
         }

         if (Config.getConfig().getBoolean("limit.broadcast-removal")) {
            Bukkit.broadcastMessage(Util.color(Config.getConfig().getString("limit.broadcast-message").replace("+RemoveAmount", "" + ents.size())));
         }

         ents.clear();
      }
   }

   public void reload() {
      this.stop();
      this.load();
   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("limit.enable-limit");
   }

   public int getInterval() {
      return Config.getConfig().getInt("limit.check-interval") * 20;
   }
}
