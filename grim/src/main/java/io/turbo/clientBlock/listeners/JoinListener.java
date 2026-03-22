package io.turbo.clientBlock.listeners;

import ac.grim.grimac.api.GrimUser;
import io.papermc.paper.connection.PlayerLoginConnection;
import io.turbo.clientBlock.ClientBlock;
import io.turbo.clientBlock.config.BlockedClients;
import io.turbo.clientBlock.config.WhitelistedPlayers;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent event) {
        GrimUser playerAsUser = ClientBlock.getGrimAPI().getGrimUser(event.getPlayer().getUniqueId());

        if (WhitelistedPlayers.i().getWhitelistedPlayers().contains(event.getPlayer().getName())) return; // is player whitelisted?
        if (!event.getPlayer().isOnline()) return; // online check
        if (playerAsUser == null) return; // null check for grim user

        BlockedClients.i().getBlockedClients().forEach(clientName -> {
            if (playerAsUser.getBrand().equals(clientName)) {
                ClientBlock.getInstance().getLogger().info("Player " + event.getPlayer().getName() + " attempted to login using a blocked client! (" + playerAsUser.getBrand() + ")");
            }
        });
    }
}
