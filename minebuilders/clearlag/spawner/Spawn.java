package me.minebuilders.clearlag.spawner;

import java.util.Iterator;
import java.util.Random;
import me.minebuilders.clearlag.Modules;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public abstract class Spawn {
   public SpawnSettings settings;
   public Random rg;
   public int maxtrys;

   public Spawn() {
      this.settings = Modules.SpawnSettings;
      this.rg = new Random();
      this.maxtrys = this.settings.getMaxTry();
   }

   public void spawnEntity(Location l) {
      World w = l.getWorld();
      int x = l.getBlockX();
      int z = l.getBlockZ();
      int y = l.getBlockY();
      int ran1 = this.getRandomNumber();
      int ran2 = this.getRandomNumber();
      x += ran1;
      z += ran2;
      l = this.getSafeLoc(w, x, y, z);
      if (l != null) {
         this.spawnRandomMob(l);
      }

   }

   public boolean isDay(World w) {
      long time = w.getTime();
      return time < 13300L || time > 23000L;
   }

   public boolean isNearOthers(Location l) {
      int d = this.settings.getRadiusFromEntity();
      Iterator var4 = l.getWorld().getEntitiesByClass(LivingEntity.class).iterator();

      Entity e;
      do {
         if (!var4.hasNext()) {
            return false;
         }

         e = (Entity)var4.next();
      } while(e instanceof Player || l.distanceSquared(e.getLocation()) >= (double)d);

      return true;
   }

   public abstract void spawnRandomMob(Location var1);

   public int getRandomNumber() {
      int r = this.rg.nextInt(this.settings.getRadius()) - this.rg.nextInt(this.settings.getRadius());
      return r <= this.settings.getRadiusFrom() && r >= -this.settings.getRadiusFrom() ? this.getRandomNumber() : r;
   }

   public Location getSafeLoc(World w, int x, int y, int z) {
      while(this.maxtrys > 0) {
         --this.maxtrys;
         Material m = Material.getMaterial(w.getBlockTypeIdAt(x, y, z));
         if (m.isSolid()) {
            ++y;
         } else if (w.getBlockTypeIdAt(x, y - 1, z) == 0) {
            --y;
         } else {
            if (!Material.getMaterial(w.getBlockTypeIdAt(x, y + 1, z)).isSolid()) {
               return new Location(w, (double)x, (double)y, (double)z);
            }

            int ran1 = this.getRandomNumber();
            int ran2 = this.getRandomNumber();
            x += ran1;
            z += ran2;
         }
      }

      return null;
   }
}
