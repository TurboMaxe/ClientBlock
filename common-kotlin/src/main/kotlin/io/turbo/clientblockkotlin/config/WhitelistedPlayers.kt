package io.turbo.clientblockcommon.config

import ClientBlock
import de.exlll.configlib.Comment
import de.exlll.configlib.Configuration
import de.exlll.configlib.YamlConfigurations
import lombok.Getter
import lombok.NoArgsConstructor
import java.io.File
import java.nio.file.Path


@Configuration
@NoArgsConstructor
@Getter
class WhitelistedPlayers {
    @Comment("Players who can join on any client they would like. Stored using their names")
    var WhitelistedPlayers: MutableList<String?> = ArrayList<String?>()

    fun save() {
        YamlConfigurations.save<WhitelistedPlayers?>(
            File(
                ClientBlock.i()?.dataFolder, "WhitelistedPlayers.yml"
            ).toPath(), WhitelistedPlayers::class.java as Class<WhitelistedPlayers?>?, this
        )
    }

    companion object {
        private var instance: WhitelistedPlayers? = null

        fun reload() {
            val path: Path = File(
                ClientBlock.i()?.dataFolder,
                "WhitelistedPlayers.yml"
            ).toPath()
            instance = YamlConfigurations.update<WhitelistedPlayers?>(path, WhitelistedPlayers::class.java)
        }


        fun i(): WhitelistedPlayers? {
            if (instance != null) {
                return instance
            }

            return YamlConfigurations.update<WhitelistedPlayers?>(
                File(ClientBlock.i()?.dataFolder, "bounties.yml"
                ).toPath(), WhitelistedPlayers::class.java
            ).also { instance = it }
        }

        fun load(): WhitelistedPlayers? {
            val path: Path = File(
                ClientBlock.i()?.dataFolder,
                "WhitelistedPlayers.yml"
            ).toPath()
            return YamlConfigurations.load<WhitelistedPlayers?>(path, WhitelistedPlayers::class.java)
        }
    }
}
