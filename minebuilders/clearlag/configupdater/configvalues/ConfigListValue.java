package me.minebuilders.clearlag.configupdater.configvalues;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConfigListValue implements ConfigValue {
   private String key;
   private List strings = new ArrayList();

   public ConfigListValue(String value) {
      this.key = value;
   }

   public String getKey() {
      return this.key;
   }

   public void merge(ConfigValue value) {
      this.strings = (List)value.getValue();
   }

   public void addValue(String s) {
      this.strings.add(s);
   }

   public void writeToFile(BufferedWriter writer) throws IOException {
      writer.write("  " + this.key + ":");
      Iterator var3 = this.strings.iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         writer.newLine();
         writer.write(s);
      }

   }

   public boolean equals(Object b) {
      return b instanceof ConfigListValue && ((ConfigListValue)b).getKey().equals(this.key);
   }

   public Object getValue() {
      return this.strings;
   }
}
