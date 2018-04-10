package me.minebuilders.clearlag.tasks;

import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.Clearlag;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.TaskModule;
import org.bukkit.Bukkit;

public class TPSCheckTask extends TaskModule {
   private List commands;
   private double tpslimit;

   public void load() {
      this.commands = Config.getConfig().getStringList("tps-meter.commands");
      this.tpslimit = Config.getConfig().getDouble("tps-meter.tps-trigger");
      this.resume();
   }

   public void run() {
      if (Modules.TPSTask.getTPS() < this.tpslimit) {
         try {
            Iterator var2 = this.commands.iterator();

            while(var2.hasNext()) {
               String s = (String)var2.next();
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
            }
         } catch (Exception var3) {
            Util.warning("TPSCheckTask was unable to dispatch commands!");
         }
      }

   }

   public void reload() {
      this.stop();
      this.load();
   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("tps-meter.enabled");
   }

   public void resume() {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(Clearlag.getInstance(), this, (long)Config.getConfig().getInt("tps-meter.interval") * 20L, (long)Config.getConfig().getInt("tps-meter.interval") * 20L);
   }

   public int getInterval() {
      return Config.getConfig().getInt("tps-meter.interval") * 20;
   }
}
