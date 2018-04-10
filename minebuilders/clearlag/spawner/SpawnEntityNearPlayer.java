package me.minebuilders.clearlag.spawner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.Clearlag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnEntityNearPlayer implements Runnable 
{
   private Iterator playerQueue;
   private int timerID;

   public SpawnEntityNearPlayer() 
   {
      Iterator var2 = Bukkit.getWorlds().iterator();

      while(var2.hasNext()) 
      {
         World w = (World)var2.next();
         w.setAmbientSpawnLimit(0);
         w.setAnimalSpawnLimit(0);
         w.setMonsterSpawnLimit(0);
         w.setTicksPerAnimalSpawns(0);
         w.setTicksPerMonsterSpawns(0);
         w.setWaterAnimalSpawnLimit(0);
      }

      List list = new ArrayList();
      Player[] var5;
      int var4 = (var5 = Bukkit.getOnlinePlayers()).length;

      for(int var3 = 0; var3 < var4; ++var3) 
      {
         Player p = var5[var3];
         list.add(p);
      }

      this.playerQueue = list.iterator();
      if (this.playerQueue.hasNext()) 
      {
         this.timerID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Clearlag.getInstance(), this, 1L, 1L);
      }
   }

   public void run() 
   {
      if (this.playerQueue.hasNext()) 
      {
         Player p = (Player)this.playerQueue.next();
         if (p != null) 
         {
            SpawnType.getType(p.getWorld()).spawnEntity(p.getLocation());
         }
      } 
      else 
      {
         Bukkit.getServer().getScheduler().cancelTask(this.timerID);
      }

   }
}