package me.minebuilders.clearlag.tasks;

import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.EventModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

public class WarnTask extends EventModule {
   public void setValues() {
   }

   public boolean isEnabled() {
      return Config.isReset();
   }

   @EventHandler(
      priority = EventPriority.HIGHEST
   )
   public void onJoin(PlayerJoinEvent e) {
      Player p = e.getPlayer();
      if (p.hasPermission("lagg.clear")) {
         Util.msg("&cWARNING: &7Clearlag was forced to reset your config due to an update!", p);
         Util.msg("&cWARNING: &7Please re-modify your clearlag config!", p);
         Util.msg("&8This message will go away once you either reboot, or reload clearlag!", p);
      }

   }
}
