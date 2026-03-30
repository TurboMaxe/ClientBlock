package io.turbo.clientblockkotlin.listeners;

import ClientBlock
import io.turbo.clientblockcommon.config.BlockedClients
import io.turbo.clientblockcommon.config.WhitelistedPlayers
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
                ClientBlock.i().logger.info {
                    " Player $event.player.name (IP: ${event.player.address}) was kicked for logging in on blacklisted client $string"}
        }
      }
    }
}

