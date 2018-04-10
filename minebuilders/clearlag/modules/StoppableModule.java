package me.minebuilders.clearlag.modules;

public interface StoppableModule extends Module {
   void stop();

   void resume();
}
