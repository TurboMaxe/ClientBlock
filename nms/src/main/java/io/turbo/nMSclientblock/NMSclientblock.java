package io.turbo.nMSclientblock;

import io.turbo.nMSclientblock.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NMSclientblock extends JavaPlugin {
   private static NMSclientblock instance;

   public NMSclientblock() {
       instance = this;
   }

    @Override
    public void onEnable() {
      List.of("           BOOTUP",
              "Loaded ClientBlock (Using NMS) v1.0.0!",
              "          BOOTUP")
              .forEach(Bukkit.getConsoleSender()::sendMessage);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
