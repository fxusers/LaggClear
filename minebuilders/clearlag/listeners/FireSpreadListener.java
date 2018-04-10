package me.minebuilders.clearlag.listeners;

import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

public class FireSpreadListener extends EventModule {
   private long lastspread = 0L;
   private long time = 0L;

   @EventHandler
   public void fireSpread(BlockIgniteEvent e) {
      if (e.getCause() == IgniteCause.SPREAD && System.currentTimeMillis() - this.lastspread < this.time) {
         e.setCancelled(true);
         this.lastspread = System.currentTimeMillis();
      }

   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("dispenser-reducer.enabled");
   }

   public void setValues() {
      this.time = Config.getConfig().getLong("firespread-reducer.time");
      this.lastspread = System.currentTimeMillis();
   }
}
