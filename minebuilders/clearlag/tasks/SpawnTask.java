package me.minebuilders.clearlag.tasks;

import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.TaskModule;
import me.minebuilders.clearlag.spawner.SpawnEntityNearPlayer;

public class SpawnTask extends TaskModule 
{
   public void load() 
   {
      this.resume();
   }

   public void run() 
   {
      new SpawnEntityNearPlayer();
   }

   public void reload() 
   {
      this.stop();
      this.resume();
   }

   public boolean isEnabled() 
   {
      return Config.getConfig().getBoolean("custom-mobspawner.enabled");
   }

   public int getInterval() 
   {
      return Config.getConfig().getInt("custom-mobspawner.interval") * 20;
   }
}