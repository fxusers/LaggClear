package me.minebuilders.clearlag.configupdater;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.configupdater.configvalues.ConfigComment;
import me.minebuilders.clearlag.configupdater.configvalues.ConfigValue;

public class ConfigSection {
   private String key;
   private List body = new ArrayList();

   public boolean hasKey() {
      return this.key != null;
   }

   public void setKey(String key) {
      this.key = key;
      this.body.add(new ConfigComment(key));
   }

   public String getKey() {
      return this.key;
   }

   public void addConfigValue(ConfigValue cv) {
      this.body.add(cv);
   }

   public List getBody() {
      return this.body;
   }

   public void merge(ConfigSection cs) {
      Iterator var3 = cs.getBody().iterator();

      while(var3.hasNext()) {
         ConfigValue cv = (ConfigValue)var3.next();
         Iterator var5 = this.body.iterator();

         while(var5.hasNext()) {
            ConfigValue cv2 = (ConfigValue)var5.next();
            if (cv.equals(cv2)) {
               cv2.merge(cv);
            }
         }
      }

   }

   public void writeToFile(BufferedWriter writer) throws IOException {
      Iterator var3 = this.body.iterator();

      while(var3.hasNext()) {
         ConfigValue s = (ConfigValue)var3.next();
         s.writeToFile(writer);
         writer.newLine();
      }

   }

   public String toString() {
      return this.key;
   }

   public boolean equals(Object ob) {
      return this.key.equals(ob.toString());
   }
}
