package me.minebuilders.clearlag.modules;

import me.minebuilders.clearlag.Clearlag;
import org.bukkit.Bukkit;

public abstract class TaskModule implements ReloadableModule, StoppableModule, Runnable {
   protected int taskid = -2;

   public void resume() {
      this.taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Clearlag.getInstance(), this, (long)this.getInterval(), (long)this.getInterval());
   }

   public boolean isEnabled() {
      return true;
   }

   public void stop() {
      Bukkit.getScheduler().cancelTask(this.taskid);
   }

   public abstract int getInterval();
}
