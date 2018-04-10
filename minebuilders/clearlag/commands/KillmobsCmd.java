package me.minebuilders.clearlag.commands;

import java.util.Iterator;
import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class KillmobsCmd extends CommandModule 
{
   public KillmobsCmd() 
   {
      super.forcePlayer = false;
      super.cmdName = "killmobs";
      super.argLength = 1;
      super.usage = "(Clears mobs from your worlds)";
   }

   public boolean run() 
   {
      int removed = 0;

      World world;
      for (Iterator var3 = Bukkit.getWorlds().iterator(); 
                                          var3.hasNext(); 
                                          removed += Util.removeEntitys(Modules.KillMobsClear.getRemovables(world.getEntities(), world))) 
      {
         world = (World)var3.next();
      }

      Util.msg("&6" + removed + " &bMobs have been removed!", super.sender);
      return true;
   }
}