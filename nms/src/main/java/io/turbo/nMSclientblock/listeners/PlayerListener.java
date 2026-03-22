package io.turbo.nMSclientblock.listeners;

import io.turbo.nMSclientblock.config.BlockedClients;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent event) {
       if (!event.getPlayer().isOnline()) return;
       if (BlockedClients.i().getBlockedClients().isEmpty()) return;
       ServerPlayer player = (ServerPlayer) event.getPlayer();
       BlockedClients.i().getBlockedClients().forEach(clientName -> {
           if (clientName.equals(player.getBukkitEntity().getClientBrandName())) {
               player.disconnect();
           }
          });
    }
}
