package me.minebuilders.clearlag.commands;

import java.util.Iterator;
import me.minebuilders.clearlag.Config;
import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;
import me.minebuilders.clearlag.modules.ReloadableModule;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;

public class CheckCmd extends CommandModule implements ReloadableModule {
   private boolean monster;
   private boolean animals;
   private boolean creature;

   public CheckCmd() {
      super.forcePlayer = true;
      super.cmdName = "check";
      super.argLength = 1;
      super.usage = "(Counts entities in your worlds)";
   }

   public boolean run() {
      Configuration config = Config.getConfig();
      int removed1 = 0;
      int removed = 0;
      int chunks = 0;
      int spawners = 0;
      Iterator var7 = Bukkit.getServer().getWorlds().iterator();

      while(true) {
         World w;
         do {
            if (!var7.hasNext()) {
               Util.scm("&4*&3&m                          &8(&a&lServer Status&8)&3&m                           &4*", super.sender);
               super.sender.sendMessage(ChatColor.DARK_AQUA + "   Items on the ground: " + ChatColor.AQUA + removed1);
               super.sender.sendMessage(ChatColor.DARK_AQUA + "   Mobs alive: " + ChatColor.AQUA + removed);
               super.sender.sendMessage(ChatColor.DARK_AQUA + "   Chunks Loaded: " + ChatColor.AQUA + chunks);
               super.sender.sendMessage(ChatColor.DARK_AQUA + "   Active mob spawners: " + ChatColor.AQUA + spawners);
               super.sender.sendMessage(ChatColor.DARK_AQUA + "   Current TPS: " + ChatColor.AQUA + Modules.TPSTask.getStringTPS());
               super.sender.sendMessage(ChatColor.DARK_AQUA + "   Players online: " + ChatColor.AQUA + Bukkit.getServer().getOnlinePlayers().length);
               Util.scm("&4*&3&m                                                                             &4*", super.sender);
               return true;
            }

            w = (World)var7.next();
         } while(config.getStringList("check.world-filter").contains(w.getName()));

         Chunk[] var11;
         int var10 = (var11 = w.getLoadedChunks()).length;

         for(int var9 = 0; var9 < var10; ++var9) {
            Chunk c = var11[var9];
            BlockState[] var15;
            int var14 = (var15 = c.getTileEntities()).length;

            int var13;
            for(var13 = 0; var13 < var14; ++var13) {
               BlockState bt = var15[var13];
               if (bt instanceof CreatureSpawner) {
                  ++spawners;
               }
            }

            Entity[] var17;
            var14 = (var17 = c.getEntities()).length;

            for(var13 = 0; var13 < var14; ++var13) {
               Entity e = var17[var13];
               if (e instanceof Monster && this.monster) {
                  ++removed;
               } else if (e instanceof Animals && this.animals) {
                  ++removed;
               } else if (e instanceof Creature && this.creature) {
                  ++removed;
               } else if (e instanceof Item) {
                  ++removed1;
               }
            }

            ++chunks;
         }
      }
   }

   public void load() {
      super.load();
      this.reload();
   }

   public void reload() {
      this.monster = Config.getConfig().getBoolean("check.mobs.monster");
      this.animals = Config.getConfig().getBoolean("check.mobs.monster");
      this.creature = Config.getConfig().getBoolean("check.mobs.monster");
   }
}
