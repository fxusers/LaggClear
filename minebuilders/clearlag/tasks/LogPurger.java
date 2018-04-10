package me.minebuilders.clearlag.tasks;

import java.io.File;
import java.util.Date;
import me.minebuilders.clearlag.Clearlag;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.TaskModule;
import org.bukkit.Bukkit;

public class LogPurger extends TaskModule {
   public void run() {
      long time = (new Date()).getTime() - (long)(86400000 * Config.getConfig().getInt("log-purger.days-old"));
      File folder = new File(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/logs");
      if (folder.exists()) {
         File[] files = folder.listFiles();
         int deleted = 0;

         for(int i = 0; i < files.length; ++i) {
            if (files[i].isFile() && time > Util.parseTime(files[i].getName().split("//.")[0]).getTime()) {
               files[i].delete();
               ++deleted;
            }
         }

         Util.log(deleted + " Logs have been removed!");
      }
   }

   public void resume() {
   }

   public void reload() {
   }

   public void load() {
      super.taskid = Bukkit.getScheduler().runTaskLaterAsynchronously(Clearlag.getInstance(), this, 0L).getTaskId();
   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("log-purger.enabled");
   }

   public int getInterval() {
      return 0;
   }
}
