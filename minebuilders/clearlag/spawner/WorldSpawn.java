package me.minebuilders.clearlag.spawner;

import me.minebuilders.clearlag.RandomUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class WorldSpawn extends Spawn {
   public void spawnRandomMob(Location l) {
      if (!this.isNearOthers(l)) {
         if (l.getBlock().getLightLevel() > super.settings.getMonsterLight()) {
            if (this.isDay(l.getWorld()) && l.getY() >= 55.0D) {
               LivingEntity s = (LivingEntity)l.getWorld().spawnEntity(l, (EntityType)super.settings.getAnimals().get(super.rg.nextInt(super.settings.getAnimals().size())));
               s.setRemoveWhenFarAway(true);
            }
         } else {
            EntityType t = (EntityType)super.settings.getMobs().get(super.rg.nextInt(super.settings.getMobs().size()));
            LivingEntity s = (LivingEntity)l.getWorld().spawnEntity(l, t);
            if (t == EntityType.ZOMBIE || t == EntityType.SKELETON || t == EntityType.PIG_ZOMBIE) {
               EntityEquipment inv = s.getEquipment();
               inv.setHelmet(new ItemStack(RandomUtil.getHelm(super.settings.getArmorChance())));
               inv.setChestplate(new ItemStack(RandomUtil.getShirt(super.settings.getArmorChance())));
               inv.setLeggings(new ItemStack(RandomUtil.getPants(super.settings.getArmorChance())));
               inv.setBoots(new ItemStack(RandomUtil.getBoots(super.settings.getArmorChance())));
               if (t == EntityType.SKELETON) {
                  inv.setItemInHand(new ItemStack(Material.BOW));
               } else {
                  inv.setItemInHand(new ItemStack(RandomUtil.getSword(super.settings.getArmorChance())));
               }
            }
         }

      }
   }
}
