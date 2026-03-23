import org.bukkit.plugin.java.JavaPlugin

class ClientBlock : JavaPlugin() {
    var instance: ClientBlock

    init {
        instance = this
    }

    override fun onEnable() {
        logger.info("Enabled ClientBlock (kotlin) v1.0.0")
    }
}
