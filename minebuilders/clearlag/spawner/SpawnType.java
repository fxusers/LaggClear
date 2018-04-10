package me.minebuilders.clearlag.spawner;

import org.bukkit.World;
import org.bukkit.World.Environment;

public class SpawnType 
{
   public static final WorldSpawn world = new WorldSpawn();
   public static final NetherSpawn nether = new NetherSpawn();
   public static final EndSpawn end = new EndSpawn();
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$org$bukkit$World$Environment;

   public static Spawn getType(World w) 
   {
      switch($SWITCH_TABLE$org$bukkit$World$Environment()[w.getEnvironment().ordinal()]) 
      {
         case 1:  return world;
         case 2:  return nether;
         case 3:  return end;
         default: return world;
      }
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$org$bukkit$World$Environment() 
   {
      int[] var10000 = $SWITCH_TABLE$org$bukkit$World$Environment;
      if ($SWITCH_TABLE$org$bukkit$World$Environment != null) 
      {
         return var10000;
      } 
      else 
      {
         int[] var0 = new int[Environment.values().length];

         try 
         {
            var0[Environment.NETHER.ordinal()] = 2;
         } catch (NoSuchFieldError var3) 
         {
            ;
         }

         try 
         {
            var0[Environment.NORMAL.ordinal()] = 1;
         } catch (NoSuchFieldError var2) 
         {
            ;
         }

         try 
         {
            var0[Environment.THE_END.ordinal()] = 3;
         } catch (NoSuchFieldError var1) 
         {
            ;
         }

         $SWITCH_TABLE$org$bukkit$World$Environment = var0;
         return var0;
      }
   }
}
