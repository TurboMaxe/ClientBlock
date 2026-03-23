package io.turbo.clientBlock;

import ac.grim.grimac.api.GrimAbstractAPI;
import ac.grim.grimac.api.plugin.BasicGrimPlugin;
import ac.grim.grimac.api.plugin.GrimPlugin;
import io.turbo.clientBlock.listeners.JoinListener;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClientBlock extends JavaPlugin {
    @Getter private static ClientBlock instance;
    @Getter public static GrimAbstractAPI grimAPI;

    public ClientBlock() {
        instance = this;
    }

    @Override
    @SuppressWarnings("all")
    public void onEnable() {
        getLogger().info("Enabled ClientBlocker v1.0.0");
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

        if (!Bukkit.getPluginManager().getPlugin("GrimAC").isEnabled()) getLogger().info("GrimAC was not found. Disabling ClientBlocker."); Bukkit.getPluginManager().disablePlugin(this);

        if (Bukkit.getPluginManager().isPluginEnabled("GrimAC")) {
            RegisteredServiceProvider<GrimAbstractAPI> provider = Bukkit.getServicesManager().getRegistration(GrimAbstractAPI.class);
            if (provider != null) {
                GrimPlugin plugin = new BasicGrimPlugin(
                        getLogger(),
                        getDataFolder(),
                        getPluginMeta().getVersion(),
                        getPluginMeta().getDescription(),
                        getPluginMeta().getAuthors()
                );
                GrimAbstractAPI api = provider.getProvider();
            }
        }
    }

}

