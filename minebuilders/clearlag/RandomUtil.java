package me.minebuilders.clearlag;

import java.util.Random;
import org.bukkit.Material;

public class RandomUtil {
   public static final Random rg = new Random();

   public static Material getBoots(int chance) {
      int i = rg.nextInt(chance);
      if (i < 3) {
         return Material.DIAMOND_BOOTS;
      } else if (i < 7) {
         return Material.GOLD_BOOTS;
      } else if (i < 13) {
         return Material.IRON_BOOTS;
      } else {
         return i < 21 ? Material.LEATHER_BOOTS : Material.AIR;
      }
   }

   public static Material getPants(int chance) {
      int i = rg.nextInt(chance);
      if (i < 3) {
         return Material.DIAMOND_LEGGINGS;
      } else if (i < 7) {
         return Material.GOLD_LEGGINGS;
      } else if (i < 13) {
         return Material.IRON_LEGGINGS;
      } else {
         return i < 21 ? Material.LEATHER_LEGGINGS : Material.AIR;
      }
   }

   public static Material getShirt(int chance) {
      int i = rg.nextInt(chance);
      if (i < 3) {
         return Material.DIAMOND_CHESTPLATE;
      } else if (i < 7) {
         return Material.GOLD_CHESTPLATE;
      } else if (i < 13) {
         return Material.IRON_CHESTPLATE;
      } else {
         return i < 21 ? Material.LEATHER_CHESTPLATE : Material.AIR;
      }
   }

   public static Material getHelm(int chance) {
      int i = rg.nextInt(chance);
      if (i < 3) {
         return Material.DIAMOND_HELMET;
      } else if (i < 7) {
         return Material.GOLD_HELMET;
      } else if (i < 13) {
         return Material.IRON_HELMET;
      } else {
         return i < 21 ? Material.LEATHER_HELMET : Material.AIR;
      }
   }

   public static Material getSword(int chance) {
      int i = rg.nextInt(chance);
      if (i < 3) {
         return Material.DIAMOND_SWORD;
      } else if (i < 7) {
         return Material.GOLD_SWORD;
      } else if (i < 13) {
         return Material.IRON_SWORD;
      } else {
         return i < 21 ? Material.WOOD_SWORD : Material.AIR;
      }
   }
}
