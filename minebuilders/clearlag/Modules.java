package me.minebuilders.clearlag;

import me.minebuilders.clearlag.commands.AdminCmd;
import me.minebuilders.clearlag.commands.AreaCmd;
import me.minebuilders.clearlag.commands.CheckCmd;
import me.minebuilders.clearlag.commands.ChunkCmd;
import me.minebuilders.clearlag.commands.ClearCmd;
import me.minebuilders.clearlag.commands.GcCmd;
import me.minebuilders.clearlag.commands.HaltCmd;
import me.minebuilders.clearlag.commands.KillmobsCmd;
import me.minebuilders.clearlag.commands.ReloadCmd;
import me.minebuilders.clearlag.commands.TpChunkCmd;
import me.minebuilders.clearlag.commands.TpsCmd;
import me.minebuilders.clearlag.commands.UnloadChunksCmd;
import me.minebuilders.clearlag.listeners.ChunkEntityLimiterListener;
import me.minebuilders.clearlag.listeners.ChunkLimiterListener;
import me.minebuilders.clearlag.listeners.DispenceLimitEvent;
import me.minebuilders.clearlag.listeners.EggSpawnListener;
import me.minebuilders.clearlag.listeners.EntityAISpawnListener;
import me.minebuilders.clearlag.listeners.FireSpreadListener;
import me.minebuilders.clearlag.listeners.ItemMergeListener;
import me.minebuilders.clearlag.listeners.MobLimitListener;
import me.minebuilders.clearlag.listeners.MobSpawerListener;
import me.minebuilders.clearlag.listeners.SignListener;
import me.minebuilders.clearlag.listeners.TNTMinecartListener;
import me.minebuilders.clearlag.listeners.TntReduceListener;
import me.minebuilders.clearlag.managers.EntityManager;
import me.minebuilders.clearlag.modules.ClearModule;
import me.minebuilders.clearlag.modules.CommandModule;
import me.minebuilders.clearlag.modules.EventModule;
import me.minebuilders.clearlag.modules.Module;
import me.minebuilders.clearlag.modules.TaskModule;
import me.minebuilders.clearlag.removetype.AutoClear;
import me.minebuilders.clearlag.removetype.CheckClear;
import me.minebuilders.clearlag.removetype.CmdClear;
import me.minebuilders.clearlag.removetype.KillMobsClear;
import me.minebuilders.clearlag.removetype.LimitClear;
import me.minebuilders.clearlag.spawner.SpawnSettings;
import me.minebuilders.clearlag.tasks.ClearTask;
import me.minebuilders.clearlag.tasks.LimitTask;
import me.minebuilders.clearlag.tasks.LiveTask;
import me.minebuilders.clearlag.tasks.LogPurger;
import me.minebuilders.clearlag.tasks.SpawnTask;
import me.minebuilders.clearlag.tasks.TPSCheckTask;
import me.minebuilders.clearlag.tasks.TPSTask;
import me.minebuilders.clearlag.tasks.WarnTask;

public final class Modules {
   public static final Config Config = new Config();
   public static final CommandListener CommandListener   = new CommandListener();
   public static final EntityManager EntityManager       = new EntityManager();
   public static final SpawnSettings SpawnSettings       = new SpawnSettings();
   public static final CommandModule ClearCmd            = new ClearCmd();
   public static final CommandModule AreaCmd             = new AreaCmd();
   public static final CommandModule CheckCmd            = new CheckCmd();
   public static final CommandModule ChunkCmd            = new ChunkCmd();
   public static final CommandModule GcCmd               = new GcCmd();
   public static final CommandModule TpsCmd              = new TpsCmd();
   public static final CommandModule KillmobsCmd         = new KillmobsCmd();
   public static final CommandModule ReloadCmd           = new ReloadCmd();
   public static final CommandModule TpChunkCmd          = new TpChunkCmd();
   public static final CommandModule UnloadChunksCmd     = new UnloadChunksCmd();
   public static final CommandModule HaltCmd             = new HaltCmd();
   public static final CommandModule AdminCmd            = new AdminCmd();
   public static final EventModule ChunkLimiterListener  = new ChunkLimiterListener();
   public static final EventModule DispenceLimitEvent    = new DispenceLimitEvent();
   public static final EventModule EggSpawnListener      = new EggSpawnListener();
   public static final EventModule EntityAISpawnListener = new EntityAISpawnListener();
   public static final EventModule FireSpreadListener    = new FireSpreadListener();
   public static final EventModule ItemMergeListener     = new ItemMergeListener();
   public static final EventModule MobLimitListener      = new MobLimitListener();
   public static final EventModule MobSpawerListener     = new MobSpawerListener();
   public static final EventModule SignListener          = new SignListener();
   public static final EventModule TNTMinecartListener   = new TNTMinecartListener();
   public static final EventModule TntReduceListener     = new TntReduceListener();
   public static final EventModule ChunkEntityLimiterListener = new ChunkEntityLimiterListener();
   public static final EventModule WarnTask              = new WarnTask();
   public static final ClearModule AutoClear             = new AutoClear();
   public static final ClearModule CheckClear            = new CheckClear();
   public static final ClearModule CmdClear              = new CmdClear();
   public static final ClearModule KillMobsClear         = new KillMobsClear();
   public static final ClearModule LimitClear            = new LimitClear();
   public static final TPSTask TPSTask                   = new TPSTask();
   public static final TaskModule SpawnTask              = new SpawnTask();
   public static final TaskModule LiveTask               = new LiveTask();
   public static final TaskModule ClearTask              = new ClearTask();
   public static final TaskModule LimitTask              = new LimitTask();
   public static final TaskModule TPSCheckTask           = new TPSCheckTask();
   public static final TaskModule LogPurger              = new LogPurger();
   private static final Module[] values = Util.getFieldInstances(Modules.class, Module.class);

   public static Module[] getValues() 
   {
      return values;
   }

   public static Module getModule(String s) 
   {
      Module[] var4 = values;
      int var3 = values.length;

      for(int var2 = 0; var2 < var3; ++var2) 
      {
         Module m = var4[var2];
         if (m.getClass().getSimpleName().equalsIgnoreCase(s)) 
         {
            return m;
         }
      }

      return null;
   }
}