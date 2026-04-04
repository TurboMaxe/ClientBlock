package io.turbo.clientblockkotlin

import io.turbo.clientblockkotlin.listeners.PlayerListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ClientBlock : JavaPlugin() {


    override fun onEnable() {
        instance = this
        logger.info("Enabled ClientBlock (kotlin)")
        Bukkit.getPluginManager().registerEvents(PlayerListener(), this)
    }

    companion object {
        var instance: ClientBlock? = null

        fun i() = instance
    }
}
