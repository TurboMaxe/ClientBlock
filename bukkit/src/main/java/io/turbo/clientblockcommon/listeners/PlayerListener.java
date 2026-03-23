package io.turbo.clientblockcommon.listeners;

import io.turbo.clientblockcommon.config.BlockedClients;
import io.turbo.clientblockcommon.config.WhitelistedPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent event) {
        if (WhitelistedPlayers.i().getWhitelistedPlayers().contains(event.getPlayer().getName())) return;
        BlockedClients.i().getBlockedClients().forEach(clientName -> {
          if (clientName.equals(event.getPlayer().getClientBrandName())) event.getPlayer().kick();
        });
    }
}
