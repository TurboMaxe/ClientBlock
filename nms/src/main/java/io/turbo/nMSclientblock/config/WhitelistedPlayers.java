package io.turbo.nMSNMSclientblock.config;


import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import de.exlll.configlib.YamlConfigurations;
import io.turbo.NMSclientblock.NMSclientblock;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Configuration
@NoArgsConstructor
@Getter
public class WhitelistedPlayers {
    private static WhitelistedPlayers instance;
    
    @Comment("Players who can join on any client they would like. Stored using their names")
    List<String> WhitelistedPlayers = new ArrayList<>();

    public void save() {
        YamlConfigurations.save(new File(NMSclientblock.getInstance().getDataFolder(), "WhitelistedPlayers.yml").toPath(), WhitelistedPlayers.class, this);
    }

    public static void reload() {
        Path path = new File(NMSclientblock.getInstance().getDataFolder(), "WhitelistedPlayers.yml").toPath();
        instance = YamlConfigurations.update(path, WhitelistedPlayers.class);
    }


    public static WhitelistedPlayers i() {
        if (instance != null) {
            return instance;
        }

        return instance = YamlConfigurations.update(new File(NMSclientblock.getInstance().getDataFolder(), "bounties.yml").toPath(), WhitelistedPlayers.class);
    }

    public static WhitelistedPlayers load() {
        Path path = new File(NMSclientblock.getInstance().getDataFolder(), "WhitelistedPlayers.yml").toPath();
        return YamlConfigurations.load(path, WhitelistedPlayers.class);
    }
}