package me.minebuilders.clearlag.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;
import me.minebuilders.clearlag.modules.ReloadableModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class AreaCmd extends CommandModule implements ReloadableModule {
   private List types = new ArrayList();

   public AreaCmd() {
      super.forcePlayer = true;
      super.cmdName = "area";
      super.argLength = 2;
      super.extra = "<radius>";
      super.usage = "(Clears entities from your radius)";
   }

   public boolean run() {
      int removed = 0;

      try {
         Integer rad = Integer.parseInt(super.args[1]);
         Iterator var4 = super.player.getNearbyEntities((double)rad, (double)rad, (double)rad).iterator();

         while(var4.hasNext()) {
            Entity e = (Entity)var4.next();
            if (!(e instanceof Player) && Modules.EntityManager.isRemovable(e.getUniqueId()) && !this.types.contains(e.getType())) {
               e.remove();
               ++removed;
            }
         }

         Util.msg("&6" + removed + " &bEntities have been removed within a radius of &6" + rad + "&b!", super.sender);
      } catch (Exception var5) {
         super.sender.sendMessage(ChatColor.RED + "Thats not an Integer:" + this.sendArgsLine());
      }

      return true;
   }

   public void load() {
      super.load();
      this.reload();
   }

   public void reload() {
      this.types.clear();
      Iterator var2 = Config.getConfig().getStringList("area-filter").iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         EntityType type = Util.getEntityTypeFromString(s);
         if (type != null) {
            this.types.add(type);
         }
      }

   }
}
