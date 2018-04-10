package me.minebuilders.clearlag.spawner;

import me.minebuilders.clearlag.RandomUtil;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class NetherSpawn extends Spawn {
   public void spawnRandomMob(Location l) {
      EntityType t = this.getRandomEntity();
      LivingEntity s = (LivingEntity)l.getWorld().spawnEntity(l, t);
      if (t == EntityType.PIG_ZOMBIE) {
         EntityEquipment inv = s.getEquipment();
         inv.setHelmet(new ItemStack(RandomUtil.getHelm(super.settings.getArmorChance())));
         inv.setChestplate(new ItemStack(RandomUtil.getShirt(super.settings.getArmorChance())));
         inv.setLeggings(new ItemStack(RandomUtil.getPants(super.settings.getArmorChance())));
         inv.setBoots(new ItemStack(RandomUtil.getBoots(super.settings.getArmorChance())));
         inv.setItemInHand(new ItemStack(RandomUtil.getSword(super.settings.getArmorChance())));
      }

   }

   private EntityType getRandomEntity() {
      int ran = super.rg.nextInt(20);
      return ran < 2 ? EntityType.GHAST : EntityType.PIG_ZOMBIE;
   }
}
