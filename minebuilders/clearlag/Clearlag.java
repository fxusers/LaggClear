package me.minebuilders.clearlag;

import me.minebuilders.clearlag.modules.Module;
import org.bukkit.plugin.java.JavaPlugin;

public class Clearlag extends JavaPlugin {
   private static Clearlag instance;

   public void onEnable() {
      instance = this;
      Util.log("Loading modules...");
      Module[] var4;
      int var3 = (var4 = Modules.getValues()).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         Module mod = var4[var2];
         if (mod.isEnabled()) {
            mod.load();
         }
      }

      Util.log("Modules have been loaded!");
      if (Config.getConfig().getBoolean("settings.auto-update")) {
         new MyUpdater(this.getFile());
      }

      Util.log("Clearlag is now enabled!");
   }

   public void onDisable() {
   }

   public static Clearlag getInstance() {
      return instance;
   }
}
