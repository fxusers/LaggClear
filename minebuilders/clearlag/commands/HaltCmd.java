package me.minebuilders.clearlag.commands;

import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;
import me.minebuilders.clearlag.tasks.HaltTask;

public class HaltCmd extends CommandModule {
   private HaltTask halttask = null;

   public HaltCmd() {
      super.forcePlayer = true;
      super.cmdName = "halt";
      super.argLength = 1;
      super.usage = "(Halts most server activity)";
   }

   public boolean run() 
   {
      if (this.halttask == null) 
      {
         Util.msg("&cAttempting to halt server activity!", super.sender);
         this.halttask = new HaltTask();
         this.halttask.load();
         Util.msg("&aServer activity has been halted!", super.sender);
      } 
      else 
      {
         this.halttask.stop();
         this.halttask = null;
         Util.msg("&aServer is no longer halted!", super.sender);
      }

      return true;
   }
}