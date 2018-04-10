package me.minebuilders.clearlag.commands;

import me.minebuilders.clearlag.Modules;
import me.minebuilders.clearlag.Util;
import me.minebuilders.clearlag.modules.CommandModule;
import me.minebuilders.clearlag.modules.EventModule;
import me.minebuilders.clearlag.modules.Module;
import me.minebuilders.clearlag.modules.ReloadableModule;
import me.minebuilders.clearlag.modules.StoppableModule;
import me.minebuilders.clearlag.modules.TaskModule;

public class AdminCmd extends CommandModule {
   public AdminCmd() {
      super.forcePlayer = false;
      super.cmdName = "admin";
      super.argLength = 1;
      super.usage = "(Control Clearlag modules)";
   }

   public boolean run() {
      if (super.args.length == 1) {
         Util.scm("&3=-------------[&6-&3] &b&lModule Commands &3[&6-&3]---------------=", super.sender);
         Util.scm("&4  - &3/lagg admin &creload &b<module>", super.sender);
         Util.scm("&4  - &3/lagg admin &cstop &b<module>", super.sender);
         Util.scm("&4  - &3/lagg admin &cresume &b<module>", super.sender);
         Util.scm("&4  - &3/lagg admin &clist", super.sender);
         Util.scm("&3-----------------------------------------------------", super.sender);
      } else if (super.args.length > 1) {
         String cmd = super.args[1];
         StringBuilder sb;
         Module m;
         int var4;
         int var5;
         Module[] var6;
         Module mod;
         if (cmd.equalsIgnoreCase("reload")) {
            if (super.args.length == 2) {
               sb = new StringBuilder();
               var5 = (var6 = Modules.getValues()).length;

               for(var4 = 0; var4 < var5; ++var4) {
                  m = var6[var4];
                  if (m instanceof ReloadableModule && m.isEnabled()) {
                     sb.append(", " + m.getClass().getSimpleName());
                  }
               }

               Util.scm("&aEnabled Reloadable modules: &7" + sb.toString().substring(2), super.sender);
            } else {
               mod = Modules.getModule(super.args[2]);
               if (!(mod instanceof ReloadableModule)) {
                  Util.msg(super.args[2] + " &cis not a reloadable module!", super.sender);
               } else if (!mod.isEnabled()) {
                  Util.msg("&cThis module is not enabled!", super.sender);
               } else {
                  ((ReloadableModule)mod).reload();
                  Util.msg("&aModule " + mod.getClass().getSimpleName() + " has been reloaded!", super.sender);
               }
            }
         } else if (cmd.equalsIgnoreCase("stop")) {
            if (super.args.length == 2) {
               sb = new StringBuilder();
               var5 = (var6 = Modules.getValues()).length;

               for(var4 = 0; var4 < var5; ++var4) {
                  m = var6[var4];
                  if (m instanceof StoppableModule && m.isEnabled()) {
                     sb.append(", " + m.getClass().getSimpleName());
                  }
               }

               Util.scm("&aEnabled Stoppable modules: &7" + sb.toString().substring(2), super.sender);
            } else {
               mod = Modules.getModule(super.args[2]);
               if (!(mod instanceof StoppableModule)) {
                  Util.msg(super.args[2] + " &cis not a stoppable module!", super.sender);
               } else if (!mod.isEnabled()) {
                  Util.msg("&cThis module is not enabled!", super.sender);
               } else {
                  ((StoppableModule)mod).stop();
                  Util.msg("&aModule " + mod.getClass().getSimpleName() + " has been &cstopped&a!", super.sender);
               }
            }
         } else if (cmd.equalsIgnoreCase("resume")) {
            if (super.args.length == 2) {
               sb = new StringBuilder();
               var5 = (var6 = Modules.getValues()).length;

               for(var4 = 0; var4 < var5; ++var4) {
                  m = var6[var4];
                  if (m instanceof StoppableModule) {
                     sb.append(", " + m.getClass().getSimpleName());
                  }
               }

               Util.scm("&aResumable modules: &7" + sb.toString().substring(2), super.sender);
            } else {
               mod = Modules.getModule(super.args[2]);
               if (!(mod instanceof StoppableModule)) {
                  Util.msg(super.args[2] + " &cis not a stoppable module!", super.sender);
               } else if (!mod.isEnabled()) {
                  Util.msg("&cThis module is not enabled!", super.sender);
               } else {
                  ((StoppableModule)mod).stop();
                  ((StoppableModule)mod).resume();
                  Util.msg("&aModule " + mod.getClass().getSimpleName() + " has been resumed!", super.sender);
               }
            }
         } else if (cmd.equalsIgnoreCase("list")) {
            sb = new StringBuilder();
            StringBuilder commands = new StringBuilder();
            StringBuilder tasks = new StringBuilder();
            StringBuilder modules = new StringBuilder();
            Module[] var9;
            int var8 = (var9 = Modules.getValues()).length;

            for(int var7 = 0; var7 < var8; ++var7) {
               Module m = var9[var7];
               String s = "&8, " + (m.isEnabled() ? "&a" : "&7") + m.getClass().getSimpleName();
               if (m instanceof EventModule) {
                  sb.append(s);
               } else if (m instanceof CommandModule) {
                  commands.append(s);
               } else if (m instanceof TaskModule) {
                  tasks.append(s);
               } else {
                  modules.append(s);
               }
            }

            Util.scm("&3=-------------[&6-&3] &b&lModule Status &3[&6-&3]---------------=", super.sender);
            Util.scm("            &8[&7Gray = Disabled&8]   &8[&aGreen = Enabled&8] ", super.sender);
            Util.scm("&6Events: " + sb.toString().substring(4), super.sender);
            Util.scm("&6Commands: " + commands.toString().substring(4), super.sender);
            Util.scm("&6Tasks: " + tasks.toString().substring(4), super.sender);
            Util.scm("&6Modules: " + modules.toString().substring(4), super.sender);
         }
      }

      return true;
   }
}
