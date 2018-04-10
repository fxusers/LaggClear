package me.minebuilders.clearlag.tasks;

import java.util.HashMap;
import java.util.Iterator;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.TaskModule;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ClearTask extends TaskModule {
   private int time;
   private HashMap warns = new HashMap();

   public void load() {
      this.time = Config.getConfig().getInt("auto-removal.autoremoval-interval");
      this.warns.clear();

      int i;
      String msg;
      for(Iterator var2 = Config.getConfig().getStringList("auto-removal.warnings").iterator(); var2.hasNext(); this.warns.put(i, msg)) {
         String s = (String)var2.next();
         i = Integer.parseInt(s.split(" ")[0].replace("time:", ""));
         msg = Util.color(s.replace("time:" + i, "").replace("msg:", "").trim());
         if (i > 0 && msg != null && i < this.time) {
            i = this.time - i;
         }
      }

      this.resume();
   }

   public void run() {
      --this.time;
      if (this.warns.containsKey(this.time)) {
         Bukkit.broadcastMessage(Util.color(((String)this.warns.get(this.time)).replace("+remaining", "" + this.time)));
      }

      if (this.time <= 0) {
         Modules.EntityManager.removeEntitys(Modules.AutoClear, (CommandSender)null);
         this.time = Config.getConfig().getInt("auto-removal.autoremoval-interval");
      }

   }

   public void reload() {
      this.stop();
      this.load();
   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("auto-removal.enable-scheduler");
   }

   public int getInterval() {
      return 20;
   }
}
