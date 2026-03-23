import org.bukkit.plugin.java.JavaPlugin

class ClientBlock : JavaPlugin() {
    var instance: ClientBlock

    init {
        instance = this
    }

    fun getInstance(): ClientBlock {
        return instance;
    }


    override fun onEnable() {
        logger.info("Enabled ClientBlock (kotlin)")
    }
}