package me.minebuilders.clearlag.modules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.managers.EntityManager;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public abstract class ClearModule implements ReloadableModule 
{
   public List getRemovables(List list, World w)
   {
      List en = new ArrayList();
      EntityManager man = Modules.EntityManager;
      if (!this.isWorldEnabled(w)) 
      {
         return en;
      } 
      else 
      {
         Iterator var6 = list.iterator();

         while (var6.hasNext()) 
         {
            Entity ent = (Entity)var6.next();
            if (this.isRemoveable(ent) && man.isRemovable(ent.getUniqueId())) 
            {
               en.add(ent);
            }
         }

         return en;
      }
   }

   public List getEntities(World w, EntityType... type) 
   {
      List en = new ArrayList();
      if (!this.isWorldEnabled(w)) 
      {
         return en;
      } 
      else 
      {
         Iterator var5 = w.getEntities().iterator();

         while (var5.hasNext()) 
         {
            Entity ent = (Entity)var5.next();
            EntityType et = ent.getType();
            EntityType[] var10 = type;
            int var9 = type.length;

            for (int var8 = 0; var8 < var9; ++var8) 
            {
               EntityType t = var10[var8];
               if (t == et) 
               {
                  en.add(ent);
               }
            }
         }

         return en;
      }
   }

   public abstract boolean isRemoveable(Entity var1);

   public abstract boolean isWorldEnabled(World var1);

   public abstract String getPath();

   public void reload() 
   {
      this.load();
   }

   public boolean isEnabled() 
   {
      return true;
   }
}
