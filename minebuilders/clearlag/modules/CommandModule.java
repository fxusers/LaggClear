package me.minebuilders.clearlag.modules;

import me.minebuilders.clearlag.Clearlag;
import me.minebuilders.clearlag.Modules;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandModule implements Module {
   public CommandSender sender;
   public String[] args;
   public String cmdName;
   public Clearlag plugin;
   public int argLength = 0;
   public String extra;
   public boolean forcePlayer = true;
   public String usage;
   public Player player;

   public boolean processCmd(Clearlag p, CommandSender s, String[] arg) {
      this.plugin = p;
      this.sender = s;
      this.args = arg;
      if (this.forcePlayer) {
         if (!(s instanceof Player)) {
            this.sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
            return false;
         }

         this.player = (Player)s;
      }

      if (!s.hasPermission("lagg." + this.cmdName)) {
         this.sender.sendMessage(ChatColor.RED + "You do not have permission to use: " + ChatColor.GOLD + "/lagg " + this.cmdName);
      } else {
         if (this.argLength <= arg.length) {
            return this.run();
         }

         s.sendMessage(ChatColor.RED + "Wrong usage: " + this.sendArgsLine());
      }

      return false;
   }

   public abstract boolean run();

   public String sendHelpLine() {
      return ChatColor.DARK_AQUA + "/lagg " + this.cmdName + ChatColor.AQUA + "  - " + this.usage;
   }

   public String sendArgsLine() {
      return ChatColor.DARK_AQUA + "/lagg " + this.cmdName + " " + this.extra;
   }

   public void load() {
      Modules.CommandListener.addCmd(this);
   }

   public boolean isEnabled() {
      return true;
   }
}
