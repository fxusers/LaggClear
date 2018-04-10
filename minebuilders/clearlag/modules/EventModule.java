package me.minebuilders.clearlag.modules;

import me.minebuilders.clearlag.Clearlag;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class EventModule implements ReloadableModule, StoppableModule, Listener {
   public void load() {
      this.setValues();
      this.resume();
   }

   public abstract void setValues();

   public void reload() {
      this.setValues();
      this.stop();
      this.resume();
   }

   public boolean isEnabled() {
      return false;
   }

   public void stop() {
      HandlerList.unregisterAll(this);
   }

   public void resume() {
      Bukkit.getPluginManager().registerEvents(this, Clearlag.getInstance());
   }
}
