package me.minebuilders.clearlag;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MyUpdater implements Runnable {
   public static final String host = "https://api.curseforge.com/servermods/files?projectIds=37824";
   public static String newversion;
   public static String download;
   public static File file;

   public MyUpdater(File file) {
      file = file;
      (new Thread(this)).start();
   }

   public int versionToInt(String s) {
      return Integer.parseInt(s.replaceAll("[^\\d.]", "").replace(".", "").trim());
   }

   private boolean updateAvailable() {
      Util.log("Checking for updates...");

      try {
         URLConnection conn = (new URL("https://api.curseforge.com/servermods/files?projectIds=37824")).openConnection();
         conn.setConnectTimeout(6050);
         conn.addRequestProperty("User-Agent", "ClearLag Updater");
         BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         String response = reader.readLine();
         String bukkitversion = Util.getBukkitVersion();
         JSONArray array = (JSONArray)JSONValue.parse(response);
         JSONObject line = null;

         for(int i = array.size() - 1; i > 17; --i) {
            line = (JSONObject)array.get(i);
            if (((String)line.get("gameVersion")).contains(bukkitversion)) {
               break;
            }
         }

         newversion = ((String)line.get("name")).split(" ")[1];
         download = (String)line.get("downloadUrl");
         return this.versionToInt(Clearlag.getInstance().getDescription().getVersion()) < this.versionToInt(newversion);
      } catch (IOException var8) {
         Util.warning("Unable to check for updates!");
         return false;
      }
   }

   private void saveFile() throws Exception {
      if (!Bukkit.getUpdateFolderFile().exists()) {
         Bukkit.getUpdateFolderFile().mkdir();
      }

      BufferedInputStream in = null;
      FileOutputStream fout = null;

      try {
         in = new BufferedInputStream((new URL(download)).openStream());
         fout = new FileOutputStream(Bukkit.getUpdateFolderFile().getAbsolutePath() + "/" + file.getName());
         byte[] data = new byte[1024];

         int count;
         while((count = in.read(data, 0, 1024)) != -1) {
            fout.write(data, 0, count);
         }

         Util.log("Updating finished! Restart your server for files to take effect");
      } finally {
         if (in != null) {
            in.close();
         }

         if (fout != null) {
            fout.close();
         }

      }
   }

   public void run() {
      if (this.updateAvailable()) {
         Util.log("Clearlag version " + newversion + " update available! Downloading...");

         try {
            this.saveFile();
         } catch (Exception var2) {
            Util.log("Clearlag was unable to download the update!");
            var2.printStackTrace();
         }
      } else {
         Util.log("No updates found!");
      }

   }
}
