package me.minebuilders.clearlag.removetype;

import java.util.ArrayList;
import java.util.List;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.ClearModule;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class KillMobsClear extends ClearModule {
   private List types = new ArrayList();

   public void load() {
      Configuration config = Config.getConfig();
      this.types = Util.getEntityTypes(config.getStringList(this.getPath() + ".mob-filter"));
   }

   public boolean isRemoveable(Entity e) {
      if (e instanceof LivingEntity && !(e instanceof Player)) {
         return !this.types.contains(e.getType());
      } else {
         return false;
      }
   }

   public boolean isWorldEnabled(World w) {
      return true;
   }

   public String getPath() {
      return "kill-mobs";
   }
}
