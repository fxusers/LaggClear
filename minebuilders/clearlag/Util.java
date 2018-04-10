package me.minebuilders.clearlag;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import me.minebuilders.clearlag.modules.Module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class Util {
   private static final Logger log = Logger.getLogger("Minecraft");

   public static void log(String m) {
      log.info("[ClearLag] " + m);
   }

   public static void warning(String m) {
      log.warning("[ClearLag] " + m);
   }

   public static void msg(String m, CommandSender s) {
      s.sendMessage(color("&6[&aClearLag&6] &a" + m));
   }

   public static void scm(String m, CommandSender s) {
      s.sendMessage(color(m));
   }

   public static String color(String s) {
      return ChatColor.translateAlternateColorCodes('&', s);
   }

   public static List getEntityTypes(List s) {
      List types = new ArrayList();
      Iterator var3 = s.iterator();

      while(var3.hasNext()) {
         String st = (String)var3.next();
         EntityType t = getEntityTypeFromString(st);
         if (t != null) {
            types.add(t);
         } else {
            warning(st + " is NOT a valid Entity!");
         }
      }

      return types;
   }

   public static EntityType getEntityTypeFromString(String s) {
      EntityType et = EntityType.fromName(s);
      if (et != null) {
         return et;
      } else {
         s = s.replace("_", "").replace(" ", "");
         EntityType[] var5;
         int var4 = (var5 = EntityType.values()).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            EntityType e = var5[var3];
            if (e != null) {
               String name = e.name().replace("_", "");
               if (name.contains(s) || name.equalsIgnoreCase(s) || name.startsWith(s)) {
                  return e;
               }
            }
         }

         return null;
      }
   }

   public static Module[] getFieldInstances(Class clazz, Object bd) {
      List objects = new ArrayList();
      Field[] var6;
      int var5 = (var6 = clazz.getFields()).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         Field f = var6[var4];

         try {
            objects.add((Module)f.get(bd));
         } catch (IllegalArgumentException var8) {
            var8.printStackTrace();
         } catch (IllegalAccessException var9) {
            var9.printStackTrace();
         }
      }

      return (Module[])objects.toArray(new Module[objects.size()]);
   }

   public static int removeEntitys(List e) {
      int i = 0;

      for(Iterator var3 = e.iterator(); var3.hasNext(); ++i) {
         Entity en = (Entity)var3.next();
         en.remove();
      }

      return i;
   }

   public static boolean isClass(String clazz) {
      try {
         Class.forName(clazz);
         return true;
      } catch (ClassNotFoundException var2) {
         return false;
      }
   }

   public static String getBukkitVersion() {
      String[] v = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].split("-")[0].split("_");
      return v[0].replace("v", "") + "." + v[1];
   }

   public static boolean isInt(String str) {
      try {
         Integer.parseInt(str);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }

   public static Date parseTime(String time) {
      try {
         String[] frag = time.split("-");
         if (frag.length < 2) {
            return new Date();
         } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(frag[0] + "-" + frag[1] + "-" + frag[2]);
         }
      } catch (Exception var3) {
         return new Date();
      }
   }
}
