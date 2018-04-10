package me.minebuilders.clearlag.listeners;

import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDispenseEvent;

public class DispenceLimitEvent extends EventModule {
   private long lastspread = 0L;
   private long time = 0L;

   @EventHandler
   public void dispense(BlockDispenseEvent e) {
      if (System.currentTimeMillis() - this.lastspread >= this.time) {
         this.lastspread = System.currentTimeMillis();
      } else {
         e.setCancelled(true);
      }

   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("dispenser-reducer.enabled");
   }

   public void setValues() {
      this.time = Config.getConfig().getLong("dispenser-reducer.time");
      this.lastspread = System.currentTimeMillis();
   }
}
