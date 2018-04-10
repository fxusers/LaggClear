package me.minebuilders.clearlag.configupdater.configvalues;

import java.io.BufferedWriter;
import java.io.IOException;

public interface ConfigValue {
   String getKey();

   Object getValue();

   void merge(ConfigValue var1);

   void writeToFile(BufferedWriter var1) throws IOException;
}
