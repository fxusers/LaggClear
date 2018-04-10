package me.minebuilders.clearlag.listeners;

import java.util.Iterator;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.EventModule;
import net.minecraft.server.v1_7_R4.EntityLiving;
import net.minecraft.server.v1_7_R4.GenericAttributes;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class EntityAISpawnListener extends EventModule {
   private int zombie;
   private int skeleton;
   private int creeper;

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = true
   )
   public void onCreatureSpawn(CreatureSpawnEvent event) {
      if (event.getEntity() instanceof LivingEntity) {
         this.setEntityRange(event.getEntity());
      }

   }

   public boolean isEnabled() {
      if (!Util.isClass("org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity")) {
         Util.warning("mob-range requires you to use bukkit v1.7_r4 (1.7.10)");
         return false;
      } else {
         return Config.getConfig().getBoolean("mob-range.enabled");
      }
   }

   public void setValues() {
      this.zombie = Config.getConfig().getInt("mob-range.zombie");
      this.skeleton = Config.getConfig().getInt("mob-range.skeleton");
      this.creeper = Config.getConfig().getInt("mob-range.creeper");
      Iterator var2 = Bukkit.getWorlds().iterator();

      while(var2.hasNext()) {
         World w = (World)var2.next();
         Iterator var4 = w.getEntities().iterator();

         while(var4.hasNext()) {
            Entity e = (Entity)var4.next();
            this.setEntityRange(e);
         }
      }

   }

   public void setEntityRange(Entity e) {
      if (e instanceof Zombie) {
         ((EntityLiving)((CraftEntity)e).getHandle()).getAttributeInstance(GenericAttributes.b).setValue((double)this.zombie);
      } else if (e instanceof Skeleton) {
         ((EntityLiving)((CraftEntity)e).getHandle()).getAttributeInstance(GenericAttributes.b).setValue((double)this.skeleton);
      } else if (e instanceof Creeper) {
         ((EntityLiving)((CraftEntity)e).getHandle()).getAttributeInstance(GenericAttributes.b).setValue((double)this.creeper);
      }

   }
}
