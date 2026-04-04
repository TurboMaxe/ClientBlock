package io.turbo.clientblockkotlin.config

import de.exlll.configlib.Comment
import de.exlll.configlib.Configuration
import de.exlll.configlib.YamlConfigurations
import io.turbo.clientblockkotlin.ClientBlock
import lombok.Getter
import lombok.NoArgsConstructor
import java.io.File
import java.nio.file.Path

@Getter
@NoArgsConstructor
@Configuration
class BlockedClients {
    @Comment("List of Blocked Clients")
    var blockedClients: MutableList<String?> = ArrayList<String?>()

    fun save() {
        YamlConfigurations.save<BlockedClients?>(
            File(
                ClientBlock.i()?.dataFolder, "blockedclients.yml"
            ).toPath(), BlockedClients::class.java, this
        )
    }

    companion object {
        private var instance: BlockedClients? = null

        fun reload() {
            val path: Path = File(
                ClientBlock.i()?.dataFolder,
                "blockedclients.yml"
            ).toPath()
            instance = YamlConfigurations.update<BlockedClients?>(path, BlockedClients::class.java)
        }


        fun i(): BlockedClients? {
            if (instance != null) {
                return instance
            }

            return YamlConfigurations.update<BlockedClients?>(
                File(
                    ClientBlock.i()?.dataFolder, "bounties.yml"
                ).toPath(), BlockedClients::class.java
            ).also { instance = it }
        }

        fun load(): BlockedClients? {
            val path: Path = File(
                ClientBlock.i()?.dataFolder,
                "blockedclients.yml"
            ).toPath()
            return YamlConfigurations.load<BlockedClients?>(path, BlockedClients::class.java)
        }
    }
}
