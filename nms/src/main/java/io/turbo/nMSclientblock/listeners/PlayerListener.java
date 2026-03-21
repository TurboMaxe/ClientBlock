package io.turbo.nMSclientblock.listeners;

import net.minecraft.server.level.ServerPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent event) {
       if (!event.getPlayer().isOnline()) return;

       ServerPlayer player = (ServerPlayer) event.getPlayer();
       if (player.getBukkitEntity().getClientBrandName().equals("")) {
           player.disconnect();
       }
    }

}
