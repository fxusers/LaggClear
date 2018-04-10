package me.minebuilders.clearlag.listeners;

import java.util.Iterator;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class ItemMergeListener extends EventModule {
   private int mergradius = 0;
   private boolean itemmerger = false;

   @EventHandler
   public void onItemDrop(ItemSpawnEvent event) {
      if (this.itemmerger) {
         ItemStack i = event.getEntity().getItemStack();
         int type = i.getTypeId();
         int c = i.getAmount();
         MaterialData data = i.getData();
         Iterator var7 = event.getEntity().getNearbyEntities((double)this.mergradius, (double)this.mergradius, (double)this.mergradius).iterator();

         while(var7.hasNext()) {
            Entity entity = (Entity)var7.next();
            if (entity instanceof Item) {
               ItemStack ni = ((Item)entity).getItemStack();
               if (type == ni.getTypeId() && data.equals(ni.getData()) && ni.getAmount() + c <= 64 && i.getMaxStackSize() >= ni.getAmount() + c) {
                  if (entity.isDead()) {
                     return;
                  }

                  entity.remove();
                  i.setAmount(ni.getAmount() + c);
                  return;
               }
            }
         }

      }
   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("item-merger.enabled");
   }

   public void setValues() {
      this.mergradius = Config.getConfig().getInt("item-merger.radius");
      this.itemmerger = Config.getConfig().getBoolean("item-merger.enabled");
   }
}
