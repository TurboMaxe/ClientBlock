package io.turbo.cli

import org.bukkit.plugin.java.JavaPlugin

class ClientBlock : JavaPlugin() {
    
    override fun onEnable() {
        instance = this;
        logger.info("Enabled ClientBlock (kotlin) v1.0.0")
        Bukkit.getPluginManager().registerEvents(PlayerListener(), this)
    }

    companion object {
        var instance: ClientBlock? = null

        fun i() = instance
    }
}
