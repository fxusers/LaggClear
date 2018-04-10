package me.minebuilders.clearlag.spawner;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class EndSpawn extends Spawn {
   public void spawnRandomMob(Location l) {
      l.getWorld().spawnEntity(l, EntityType.ENDERMAN);
   }
}
