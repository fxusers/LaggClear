package me.minebuilders.clearlag.tasks;

import java.util.HashMap;
import java.util.Iterator;
import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class HaltTask extends EventModule 
{
   private HashMap valuelist = new HashMap();

   public void setValues() 
   {
      Iterator var2 = Bukkit.getWorlds().iterator();

      label38:
      while(var2.hasNext()) 
      {
         World w = (World)var2.next();
         Iterator var4 = w.getEntities().iterator();

         while(true) 
         {
            Entity e;
            do 
            {
               if (!var4.hasNext()) 
               {
                  Integer[] values = new Integer[6];
                  values[0] = w.getAmbientSpawnLimit();
                  w.setAmbientSpawnLimit(0);
                  values[1] = w.getAnimalSpawnLimit();
                  w.setAnimalSpawnLimit(0);
                  values[2] = w.getMonsterSpawnLimit();
                  w.setMonsterSpawnLimit(0);
                  values[3] = (int)w.getTicksPerAnimalSpawns();
                  w.setTicksPerAnimalSpawns(0);
                  values[4] = (int)w.getTicksPerMonsterSpawns();
                  w.setTicksPerMonsterSpawns(0);
                  values[5] = w.getWaterAnimalSpawnLimit();
                  w.setWaterAnimalSpawnLimit(0);
                  this.valuelist.put(w, values);
                  continue label38;
               }

               e = (Entity)var4.next();
            } while(!(e instanceof Item) && !(e instanceof TNTPrimed) && !(e instanceof ExperienceOrb) && !(e instanceof FallingBlock) && !(e instanceof Monster));

            if (Modules.EntityManager.isRemovable(e)) 
            {
               e.remove();
            }
         }
      }

   }

   public void stop() 
   {
      super.stop();
      Iterator var2 = this.valuelist.keySet().iterator();

      while(var2.hasNext()) 
      {
         World w = (World)var2.next();
         Integer[] values = (Integer[])this.valuelist.get(w);
         w.setAmbientSpawnLimit(values[0]);
         w.setAnimalSpawnLimit(values[1]);
         w.setMonsterSpawnLimit(values[2]);
         w.setTicksPerAnimalSpawns(values[3]);
         w.setTicksPerMonsterSpawns(values[4]);
         w.setWaterAnimalSpawnLimit(values[5]);
      }

   }

   @EventHandler(priority = EventPriority.LOWEST)
   public void onFire(BlockIgniteEvent e) 
   {
      e.setCancelled(true);
   }

   @EventHandler(priority = EventPriority.LOWEST)
   public void onFireBurn(BlockBurnEvent e) 
   {
      e.setCancelled(true);
   }

   @EventHandler(priority = EventPriority.LOWEST)
   public void onExplode(EntityExplodeEvent e) 
   {
      e.setCancelled(true);
      e.blockList().clear();
   }

   @EventHandler(priority = EventPriority.LOWEST)
   public void onDecay(LeavesDecayEvent e) 
   {
      e.setCancelled(true);
   }

   @EventHandler(priority = EventPriority.LOWEST)
   public void onForm(BlockFormEvent e) 
   {
      e.setCancelled(true);
   }

   @EventHandler(priority = EventPriority.LOWEST)
   public void onSpread(BlockSpreadEvent e) 
   {
      e.setCancelled(true);
   }

   @EventHandler(priority = EventPriority.LOWEST)
   public void onFade(BlockFadeEvent e) 
   {
      e.setCancelled(true);
   }
}
