package me.minebuilders.clearlag.managers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.Map.Entry;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.ClearModule;
import me.minebuilders.clearlag.modules.Module;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

public class EntityManager implements Module 
{
   private HashMap ens = new HashMap();

   public void resetUnremovableEntities(Plugin p) 
   {
      Iterator iter = this.ens.entrySet().iterator();
      String s = p.getName();

      while(iter.hasNext()) 
      {
         Entry entry = (Entry)iter.next();
         if (s.equalsIgnoreCase((String)entry.getValue())) 
         {
            iter.remove();
         }
      }
   }

   public void addUnremovableEntity(Plugin p, Entity e) 
   {
      this.ens.put(e.getUniqueId(), p.getName());
   }

   public void addUnremovableEntity(Plugin p, UUID uuid) 
   {
      this.ens.put(uuid, p.getName());
   }

   public boolean removeUnremovableEntity(Plugin p, Entity e) 
   {
      return this.removeUnremovableEntity(p, e.getUniqueId());
   }

   public boolean removeUnremovableEntity(Plugin p, UUID uuid) 
   {
      Iterator iter = this.ens.entrySet().iterator();
      String s = p.getName();

      Entry entry;
      do 
      {
         if (!iter.hasNext()) 
         {
            return false;
         }

         entry = (Entry)iter.next();
      } while(!((UUID)entry.getKey()).equals(uuid) || !s.equalsIgnoreCase((String)entry.getValue()));

      iter.remove();
      return true;
   }

   public boolean isRemovable(Entity e) 
   {
      return !this.ens.containsKey(e.getUniqueId());
   }

   public boolean isRemovable(UUID uuid) 
   {
      return !this.ens.containsKey(uuid);
   }

   public void removeEntitys(ClearModule mod, CommandSender sender) 
   {
      int removed = 0;

      World w;
      for (Iterator i = Bukkit.getWorlds().iterator(); 
                                          i.hasNext(); 
                                          removed += Util.removeEntitys(mod.getRemovables(w.getEntities(), w))) 
      {
         w = (World)i.next();
      }

      if (Config.getConfig().getBoolean(mod.getPath() + ".broadcast-removal")) 
      {
         Bukkit.broadcastMessage(Util.color(Config.getConfig().getString("auto-removal.broadcast-message").replace("+RemoveAmount", "" + removed)));
      } 
      else 
      {
         Util.msg("&bYou just removed " + removed + " entities!", (CommandSender)(sender == null ? Bukkit.getConsoleSender() : sender));
      }

   }

   public void load() 
   {
   }

   public boolean isEnabled() 
   {
      return Config.getConfig().getBoolean("settings.enable-api");
   }
}