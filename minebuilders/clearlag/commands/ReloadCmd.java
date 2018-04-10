package me.minebuilders.clearlag.commands;

import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.modules.CommandModule;
import me.minebuilders.clearlag.modules.Module;
import me.minebuilders.clearlag.modules.ReloadableModule;
import me.minebuilders.clearlag.modules.StoppableModule;
import org.bukkit.ChatColor;

public class ReloadCmd extends CommandModule {
   public ReloadCmd() {
      super.forcePlayer = false;
      super.cmdName = "reload";
      super.argLength = 1;
      super.usage = "(Reloads clearlag)";
   }

   public boolean run() {
      super.sender.sendMessage(ChatColor.GREEN + "Attempting to reload modules...");
      Module[] var4;
      int var3 = (var4 = Modules.getValues()).length;

      Module mod;
      int var2;
      for(var2 = 0; var2 < var3; ++var2) {
         mod = var4[var2];
         if (mod instanceof StoppableModule) {
            ((StoppableModule)mod).stop();
         }
      }

      var3 = (var4 = Modules.getValues()).length;

      for(var2 = 0; var2 < var3; ++var2) {
         mod = var4[var2];
         if (mod.isEnabled() && mod instanceof ReloadableModule) {
            ((ReloadableModule)mod).reload();
         } else if (mod.isEnabled() && mod instanceof StoppableModule) {
            ((StoppableModule)mod).resume();
         }
      }

      super.sender.sendMessage(ChatColor.GREEN + "Modules have been reloaded!");
      return true;
   }
}
