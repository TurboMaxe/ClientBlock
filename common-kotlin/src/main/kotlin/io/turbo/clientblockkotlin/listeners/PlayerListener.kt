package io.turbo.clientblockkotlin.listeners;

import io.turbo.clientblockkotlin.config.BlockedClients
import io.turbo.clientblockkotlin.ClientBlock
import io.turbo.clientblockkotlin.config.WhitelistedPlayers
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener : Listener {

    @EventHandler
    fun on(event: PlayerJoinEvent) {
      if (!(event.player.isOnline)) return;
      if (WhitelistedPlayers().WhitelistedPlayers.contains(event.player.name)) return;
        BlockedClients.i()?.blockedClients?.forEach { string ->
            if (event.player.clientBrandName.equals(string)) {
                event.player.kick()
                ClientBlock().logger.info {
                    " Player $event.player.name (IP: $event.player.address) was kicked for logging in on blacklisted client $string"}
        }
      }
    }
}

