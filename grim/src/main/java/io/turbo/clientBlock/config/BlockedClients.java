package io.turbo.clientBlock.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import de.exlll.configlib.YamlConfigurations;
import io.turbo.clientBlock.ClientBlock;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Configuration
public class BlockedClients {
    private static BlockedClients instance;

    @Comment("List of Blocked Clients")
    List<String> blockedClients = new ArrayList<>();

    public void save() {
        YamlConfigurations.save(new File(ClientBlock.getInstance().getDataFolder(), "blockedclients.yml").toPath(), BlockedClients.class, this);
    }

    public static void reload() {
        Path path = new File(ClientBlock.getInstance().getDataFolder(), "blockedclients.yml").toPath();
        instance = YamlConfigurations.update(path, BlockedClients.class);
    }


    public static BlockedClients i() {
        if (instance != null) {
            return instance;
        }

        return instance = YamlConfigurations.update(new File(ClientBlock.getInstance().getDataFolder(), "bounties.yml").toPath(), BlockedClients.class);
    }

    public static BlockedClients load() {
        Path path = new File(ClientBlock.getInstance().getDataFolder(), "blockedclients.yml").toPath();
        return YamlConfigurations.load(path, BlockedClients.class);
    }
}
