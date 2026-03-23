package io.turbo.clientblockcommon;

import io.turbo.clientblockcommon.listeners.PlayerListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClientBlock extends JavaPlugin {
   @Getter private static ClientBlock instance;

    public ClientBlock() {
        instance = this;
    }

    @Override
    public void onEnable() {
      getLogger().info("Enabled ClientBlock (common)");
      Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
