package me.minebuilders.clearlag.removetype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.ClearModule;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TNTPrimed;

public class CmdClear extends ClearModule {
   private boolean item = false;
   private List types = new ArrayList();
   private List ids = new ArrayList();
   private List worlds = new ArrayList();
   private boolean itemframe = false;
   private boolean fallingblock = false;
   private boolean boat = false;
   private boolean experience = false;
   private boolean painting = false;
   private boolean projectile = false;
   private boolean tnt = false;
   private boolean minecart = false;
   private boolean arrow = false;
   private boolean snowball = false;
   private boolean fireball = false;
   private boolean ender = false;

   public void load() {
      Configuration config = Config.getConfig();
      String path = this.getPath();
      this.item = config.getBoolean(path + ".item");
      this.itemframe = config.getBoolean(path + ".itemframe");
      this.fallingblock = config.getBoolean(path + ".falling-block");
      this.boat = config.getBoolean(path + ".boat");
      this.experience = config.getBoolean(path + ".experienceOrb");
      this.painting = config.getBoolean(path + ".painting");
      this.projectile = config.getBoolean(path + ".projectile");
      this.tnt = config.getBoolean(path + ".primed-tnt");
      this.minecart = config.getBoolean(path + ".minecart");
      this.arrow = config.getBoolean(path + ".arrow");
      this.snowball = config.getBoolean(path + ".snowball");
      this.fireball = config.getBoolean(path + ".fireball");
      this.ender = config.getBoolean(path + ".ender-signal");
      Iterator var4 = config.getIntegerList(path + ".item-filter").iterator();

      while(var4.hasNext()) {
         int s = (Integer)var4.next();
         this.ids.add(s);
      }

      this.types = Util.getEntityTypes(config.getStringList(path + ".remove-mobs"));
      var4 = config.getStringList(path + ".world-filter").iterator();

      while(var4.hasNext()) {
         String s = (String)var4.next();
         this.worlds.add(s);
      }

   }

   public boolean isRemoveable(Entity e) {
      if (e instanceof LivingEntity) {
         return this.types.contains(e.getType());
      } else if (e instanceof Item) {
         return this.item && !this.ids.contains(((Item)e).getItemStack().getTypeId());
      } else if (e instanceof ItemFrame) {
         return this.itemframe;
      } else if (e instanceof FallingBlock) {
         return this.fallingblock;
      } else if (e instanceof Boat) {
         return ((Boat)e).isEmpty() && this.boat;
      } else if (e instanceof ExperienceOrb) {
         return this.experience;
      } else if (e instanceof Painting) {
         return this.painting;
      } else if (e instanceof Projectile) {
         return this.projectile;
      } else if (e instanceof TNTPrimed) {
         return this.tnt;
      } else if (e instanceof Minecart) {
         return ((Minecart)e).isEmpty() && this.minecart;
      } else if (e instanceof Arrow) {
         return this.arrow;
      } else if (e instanceof Snowball) {
         return this.snowball;
      } else if (e instanceof Fireball) {
         return this.fireball;
      } else {
         return e instanceof EnderPearl ? this.ender : false;
      }
   }

   public boolean isWorldEnabled(World w) {
      return !this.worlds.contains(w.getName());
   }

   public String getPath() {
      return "command-remove";
   }
}
