package me.minebuilders.clearlag.listeners;

import java.util.Iterator;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.vehicle.VehicleCreateEvent;

public class TNTMinecartListener extends EventModule {
   private int radius;
   private int maxallowed;

   @EventHandler
   public void onspawn(VehicleCreateEvent event) {
      Entity mine = event.getVehicle();
      if (mine instanceof Minecart) {
         Minecart m = (Minecart)mine;
         if (m.getType().equals(EntityType.MINECART_TNT)) {
            int max = 0;
            Iterator var6 = mine.getNearbyEntities((double)this.radius, (double)this.radius, (double)this.radius).iterator();

            while(var6.hasNext()) {
               Entity tnt = (Entity)var6.next();
               if (tnt instanceof Minecart) {
                  ++max;
               }
            }

            if (max >= this.maxallowed) {
               m.remove();
            }
         }
      }

   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("tnt-minecart.enabled");
   }

   public void setValues() {
      this.radius = Config.getConfig().getInt("tnt-minecart.radius");
      this.maxallowed = Config.getConfig().getInt("tnt-minecart.max");
   }
}
