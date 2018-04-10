package me.minebuilders.clearlag;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.configupdater.ConfigSection;
import me.minebuilders.clearlag.configupdater.configvalues.ConfigBasicValue;
import me.minebuilders.clearlag.configupdater.configvalues.ConfigComment;
import me.minebuilders.clearlag.configupdater.configvalues.ConfigListValue;
import me.minebuilders.clearlag.modules.ReloadableModule;
import org.bukkit.configuration.Configuration;

public class Config implements ReloadableModule {
   private static Configuration c = Clearlag.getInstance().getConfig();
   private static boolean isReset = false;
   private final int version = 3;

   public Config() {
      this.reload();
   }

   public static boolean isReset() {
      return isReset;
   }

   public static Configuration getConfig() {
      return c;
   }

   public boolean isConfigUpdated() {
      return Util.isInt(c.getString("settings.config-version")) && c.getInt("settings.config-version") >= 3;
   }

   public boolean isEnabled() {
      return true;
   }

   public void reload() {
      if (!(new File(Clearlag.getInstance().getDataFolder(), "config.yml")).exists()) {
         Util.log("Config not found. Generating default config...");
         Clearlag.getInstance().saveDefaultConfig();
      }

      isReset = false;
      Clearlag.getInstance().reloadConfig();
      c = Clearlag.getInstance().getConfig();
   }

   private void resetConfig() {
      File newf = new File(Clearlag.getInstance().getDataFolder().getAbsolutePath(), "config.yml");
      File oldf = new File(Clearlag.getInstance().getDataFolder().getAbsolutePath(), "OLDconfig.yml");
      if (oldf.exists()) {
         oldf.delete();
      }

      newf.renameTo(new File(Clearlag.getInstance().getDataFolder().getAbsolutePath(), "OLDconfig.yml"));
      Clearlag.getInstance().saveDefaultConfig();
      Clearlag.getInstance().reloadConfig();
   }

   public void load() {
      if (c.getBoolean("config-updater.force-update") && !this.isConfigUpdated()) {
         this.resetConfig();
         isReset = true;
      } else if (!this.isConfigUpdated()) {
         try {
            this.updateConfig();
         } catch (IOException var2) {
            var2.printStackTrace();
         }
      }

   }

   public void updateConfig() throws IOException {
      Util.log("Updating config to v" + Clearlag.getInstance().getDescription().getVersion() + "...");
      List oldfile = this.loadFile(new FileInputStream(Clearlag.getInstance().getDataFolder() + "/config.yml"));
      List newfile = this.loadFile(Clearlag.getInstance().getResource("config.yml"));
      File file = new File(Clearlag.getInstance().getDataFolder() + "/config.yml");
      BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
      Iterator var6 = newfile.iterator();

      while(var6.hasNext()) {
         ConfigSection v = (ConfigSection)var6.next();
         Iterator var8 = oldfile.iterator();

         while(var8.hasNext()) {
            ConfigSection vs = (ConfigSection)var8.next();
            if (v.equals(vs)) {
               v.merge(vs);
            }
         }

         v.writeToFile(bw);
      }

      bw.close();
      Util.log("Successfully updated config to v" + Clearlag.getInstance().getDescription().getVersion() + "!");
   }

   public List loadFile(InputStream f) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(f));
      List list = new ArrayList();
      ConfigSection cs = new ConfigSection();

      try {
         String line;
         for(line = reader.readLine(); line != null; line = reader.readLine()) {
            String info = line.trim();
            if (info.equals("")) {
               cs.addConfigValue(new ConfigComment(line));
               list.add(cs);
               cs = new ConfigSection();
            } else if (info.startsWith("#")) {
               cs.addConfigValue(new ConfigComment(line));
            } else if (!line.contains(":")) {
               cs.addConfigValue(new ConfigComment(line));
            } else {
               String[] sl = info.split(":");
               if (sl.length > 1) {
                  cs.addConfigValue(new ConfigBasicValue(line.startsWith("    ") ? "  " + sl[0] : sl[0], sl[0].startsWith("config-version") ? " 3" : sl[1]));
               } else if (!line.startsWith(" ")) {
                  cs.setKey(info);
               } else {
                  ConfigListValue clist = new ConfigListValue(sl[0]);

                  for(line = reader.readLine(); line != null && line.trim().startsWith("-"); line = reader.readLine()) {
                     clist.addValue(line);
                  }

                  cs.addConfigValue(clist);
                  cs.addConfigValue(new ConfigComment(line));
               }
            }
         }

         cs.addConfigValue(new ConfigComment(line));
         list.add(cs);
      } catch (IOException var9) {
         var9.printStackTrace();
      }

      return list;
   }
}
