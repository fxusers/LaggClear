package me.minebuilders.clearlag.commands;

import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class TpChunkCmd extends CommandModule {
   public TpChunkCmd() {
      super.forcePlayer = true;
      super.cmdName = "tpchunk";
      super.argLength = 4;
      super.extra = "<x> <z> <world>";
      super.usage = "(Teleport to chunks)";
   }

   public boolean run() {
      try {
         World world = Bukkit.getServer().getWorld(super.args[3]);
         Block b = world.getChunkAt(Integer.parseInt(super.args[1]), Integer.parseInt(super.args[2])).getBlock(8, 0, 8);
         int X = b.getX();
         int Y = b.getY();
         int Z = b.getZ();
         super.player.teleport(new Location(world, (double)X, (double)world.getHighestBlockYAt(new Location(world, (double)X, (double)Y, (double)Z)), (double)Z));
         Util.msg("Teleported to chunk: &6" + X + "&a, &6" + Z, super.player);
         return true;
      } catch (Exception var6) {
         Util.msg("&cInvalid arguments!", super.player);
         return false;
      }
   }
}
