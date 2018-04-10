package me.minebuilders.clearlag.configupdater.configvalues;

import java.io.BufferedWriter;
import java.io.IOException;

public class ConfigBasicValue implements ConfigValue {
   private String key;
   private String value;

   public ConfigBasicValue(String key, String value) {
      this.key = key;
      this.value = value;
   }

   public String getKey() {
      return this.key;
   }

   public void merge(ConfigValue value) {
      this.value = (String)value.getValue();
   }

   public void writeToFile(BufferedWriter writer) throws IOException {
      writer.write("  " + this.key + ":" + this.value);
   }

   public boolean equals(Object b) {
      return b instanceof ConfigBasicValue && ((ConfigBasicValue)b).getKey().equals(this.key);
   }

   public String getValue() {
      return this.value;
   }
}
