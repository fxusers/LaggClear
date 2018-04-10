package me.minebuilders.clearlag.configupdater.configvalues;

import java.io.BufferedWriter;
import java.io.IOException;

public class ConfigComment implements ConfigValue {
   private String key;

   public ConfigComment(String value) {
      this.key = value;
   }

   public String getKey() {
      return this.key;
   }

   public void merge(ConfigValue value) {
   }

   public void writeToFile(BufferedWriter writer) throws IOException {
      if (this.key != null) {
         writer.write(this.key);
      }

   }

   public boolean equals(Object b) {
      return false;
   }

   public Object getValue() {
      return this.key;
   }
}
