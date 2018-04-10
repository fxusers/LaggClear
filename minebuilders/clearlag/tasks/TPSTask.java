package me.minebuilders.clearlag.tasks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import me.minebuilders.clearlag.Clearlag;
import me.minebuilders.clearlag.modules.Module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class TPSTask implements Module, Runnable {
   private long mills = System.currentTimeMillis();
   private int time;
   private double TPS = 20.0D;

   public void load() {
      this.mills = System.currentTimeMillis();
      Bukkit.getScheduler().scheduleSyncRepeatingTask(Clearlag.getInstance(), this, 1L, 1L);
   }

   public double getTPS() {
      return this.TPS;
   }

   public String getStringTPS() {
      return this.getColor() + String.valueOf(this.TPS);
   }

   public ChatColor getColor() {
      if (this.TPS > 17.0D) {
         return ChatColor.GREEN;
      } else {
         return this.TPS > 13.0D ? ChatColor.GOLD : ChatColor.RED;
      }
   }

   public void run() 
   {
    ++this.time;
      if (this.time == 200) 
      {
         long outmils = System.currentTimeMillis();
         double r = ((double)(outmils - this.mills) / 50.0D - 200.0D) / 10.0D;
         double tick = 20.0D - r;
         BigDecimal bd = (new BigDecimal(tick)).setScale(2, RoundingMode.HALF_EVEN);
         tick = Double.valueOf(bd.doubleValue());
         if (tick >= 21.0D) {
            tick = 20.0D - (tick - 20.0D);
         } else if (tick >= 19.9D) {
            tick = 20.0D;
         }

         this.TPS = tick;
         this.time = 0;
         this.mills = System.currentTimeMillis();
      }

   }

   public boolean isEnabled() {
      return true;
   }
}
