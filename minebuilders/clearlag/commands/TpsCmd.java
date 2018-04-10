package me.minebuilders.clearlag.commands;

import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;

public class TpsCmd extends CommandModule {
   public TpsCmd() {
      super.forcePlayer = false;
      super.cmdName = "tps";
      super.argLength = 1;
      super.usage = "(Checks the servers tick rate)";
   }

   public boolean run() {
      Util.msg(Modules.TPSTask.getStringTPS(), super.sender);
      return true;
   }
}
