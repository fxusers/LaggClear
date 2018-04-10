package me.minebuilders.clearlag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.modules.CommandModule;
import me.minebuilders.clearlag.modules.Module;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor, Module {
   private List cmds = new ArrayList();

   public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
      if (args.length != 0 && this.getCmd(args[0]) != null) {
         return this.getCmd(args[0]).processCmd(Clearlag.getInstance(), s, args);
      } else {
         List cmds = this.getUserCmds(s);
         if (cmds.size() == 0) {
            s.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return false;
         } else {
            s.sendMessage(ChatColor.DARK_AQUA + "-------------(" + ChatColor.AQUA + ChatColor.BOLD + "Your Clearlag Commands" + ChatColor.DARK_AQUA + ")-------------");
            Iterator var7 = cmds.iterator();

            while(var7.hasNext()) {
               CommandModule cmd = (CommandModule)var7.next();
               s.sendMessage(ChatColor.DARK_RED + "  - " + cmd.sendHelpLine());
            }

            s.sendMessage(ChatColor.DARK_AQUA + "----------------------------------------------------");
            return true;
         }
      }
   }

   public void addCmd(CommandModule cmd) {
      this.cmds.add(cmd);
   }

   private CommandModule getCmd(String s) {
      Iterator var3 = this.cmds.iterator();

      while(var3.hasNext()) {
         CommandModule cmd = (CommandModule)var3.next();
         if (cmd.cmdName.equalsIgnoreCase(s)) {
            return cmd;
         }
      }

      return null;
   }

   public List getUserCmds(CommandSender p) {
      List mod = new ArrayList();
      Iterator var4 = this.cmds.iterator();

      while(var4.hasNext()) {
         CommandModule cmd = (CommandModule)var4.next();
         if (p.hasPermission("lagg." + cmd.cmdName)) {
            mod.add(cmd);
         }
      }

      return mod;
   }

   public void load() {
      Clearlag.getInstance().getCommand("lagg").setExecutor(this);
   }

   public boolean isEnabled() {
      return true;
   }
}
