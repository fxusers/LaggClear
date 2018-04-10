package me.minebuilders.clearlag.spawner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.modules.ReloadableModule;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;

public class SpawnSettings implements ReloadableModule {
   private int armorchance;
   private int radius;
   private int maxtry;
   private int radiusfrom;
   private int rfe;
   private boolean forcespawn;
   private int monsterlight;
   private List mobs = new ArrayList();
   private List animals = new ArrayList();

   public int getMaxTry() {
      return this.maxtry;
   }

   public List getAnimals() {
      return this.animals;
   }

   public List getMobs() {
      return this.mobs;
   }

   public int getMonsterLight() {
      return this.monsterlight;
   }

   public boolean isForced() {
      return this.forcespawn;
   }

   public int getRadiusFromEntity() {
      return this.rfe;
   }

   public int getRadiusFrom() {
      return this.radiusfrom;
   }

   public int getArmorChance() {
      return this.armorchance;
   }

   public int getRadius() {
      return this.radius;
   }

   public SpawnSettings() {
      Configuration c = Config.getConfig();
      String s = "custom-mobspawner.";
      this.radius = c.getInt(s + "radius");
      this.maxtry = c.getInt(s + "max-tries");
      this.radiusfrom = c.getInt(s + "radius-from");
      this.forcespawn = c.getBoolean(s + "forcespawn");
      this.monsterlight = c.getInt(s + "lightlevel");
      this.armorchance = c.getInt(s + "armor-chance");
      this.rfe = c.getInt(s + "mob-radius");
      Iterator var4 = c.getStringList(s + "mobs").iterator();

      String n;
      String[] i;
      int amount;
      EntityType type;
      while(var4.hasNext()) {
         n = (String)var4.next();
         i = n.split(" ");
         amount = 1;
         if (i.length == 2) {
            amount = Integer.parseInt(i[1]);
         }

         type = EntityType.fromName(i[0]);

         while(amount > 0) {
            --amount;
            this.mobs.add(type);
         }
      }

      var4 = c.getStringList(s + "animals").iterator();

      while(var4.hasNext()) {
         n = (String)var4.next();
         i = n.split(" ");
         amount = 1;
         if (i.length == 2) {
            amount = Integer.parseInt(i[1]);
         }

         type = EntityType.fromName(i[0]);

         while(amount > 0) {
            --amount;
            if (type != null) {
               this.animals.add(type);
            }
         }
      }

   }

   public void load() {
      Configuration c = Config.getConfig();
      String s = "custom-mobspawner.";
      this.radius = c.getInt(s + "radius");
      this.maxtry = c.getInt(s + "max-tries");
      this.radiusfrom = c.getInt(s + "radius-from");
      this.forcespawn = c.getBoolean(s + "forcespawn");
      this.monsterlight = c.getInt(s + "lightlevel");
      this.armorchance = c.getInt(s + "armor-chance");
      this.rfe = c.getInt(s + "mob-radius");
      Iterator var4 = c.getStringList(s + "mobs").iterator();

      String n;
      String[] i;
      int amount;
      EntityType type;
      while(var4.hasNext()) {
         n = (String)var4.next();
         i = n.split(" ");
         amount = 1;
         if (i.length == 2) {
            amount = Integer.parseInt(i[1]);
         }

         type = EntityType.fromName(i[0]);

         while(amount > 0) {
            --amount;
            this.mobs.add(type);
         }
      }

      var4 = c.getStringList(s + "animals").iterator();

      while(var4.hasNext()) {
         n = (String)var4.next();
         i = n.split(" ");
         amount = 1;
         if (i.length == 2) {
            amount = Integer.parseInt(i[1]);
         }

         type = EntityType.fromName(i[0]);

         while(amount > 0) {
            --amount;
            if (type != null) {
               this.animals.add(type);
            }
         }
      }

   }

   public boolean isEnabled() {
      return Config.getConfig().getBoolean("custom-mobspawner.enabled");
   }

   public void reload() {
      this.load();
   }
}
