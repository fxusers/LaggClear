package me.minebuilders.clearlag.listeners;

import java.util.Iterator;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TntReduceListener extends EventModule {
   @EventHandler
   public void onBoom(EntityExplodeEvent event) {
      Entity e = event.getEntity();
      if (e instanceof TNTPrimed) {
         Iterator var4 = e.getNearbyEntities(4.0D, 4.0D, 4.0D).iterator();

         while(var4.hasNext()) {
            Entity tnt = (Entity)var4.next();
            if (tnt instanceof TNTPrimed) {
               tnt.remove();
            }
         }
      }

   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("tnt.tnt-reducer");
   }

   public void setValues() {
   }
}
