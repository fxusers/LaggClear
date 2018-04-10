package me.minebuilders.clearlag.commands;

import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.modules.CommandModule;

public class ClearCmd extends CommandModule 
{
   public ClearCmd() 
   {
      super.forcePlayer = false;
      super.cmdName = "clear";
      super.argLength = 1;
      super.usage = "(Clears entities from your worlds)";
   }

   public boolean run() 
   {
      Modules.EntityManager.removeEntitys(Modules.CmdClear, super.sender);
      return true;
   }
}