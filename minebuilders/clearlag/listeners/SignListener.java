package me.minebuilders.clearlag.listeners;

import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener extends EventModule {
   @EventHandler
   public void onPlayerInteractEvent(PlayerInteractEvent event) {
      Player player = event.getPlayer();
      if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
         Block targetBlock = event.getClickedBlock();
         if (targetBlock.getType() == Material.SIGN || targetBlock.getType() == Material.WALL_SIGN) {
            Sign sign = (Sign)targetBlock.getState();
            if (sign.getLine(0).equalsIgnoreCase(ChatColor.DARK_BLUE + "[clearlag]") && (!sign.getLine(1).equalsIgnoreCase("area") || !player.hasPermission("lagg.area")) && sign.getLine(1).equalsIgnoreCase("check")) {
               player.hasPermission("lagg.check");
            }
         }
      }

   }

   @EventHandler
   public void onSignChange(SignChangeEvent event) {
      if (event.getLine(0).equalsIgnoreCase("[clearlag]") && event.getPlayer().hasPermission("lagg.clear")) {
         event.setLine(0, ChatColor.DARK_BLUE + "[ClearLag]");
      }

   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("settings.enable-signs");
   }

   public void setValues() {
   }
}
