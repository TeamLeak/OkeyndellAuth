package com.github.lkapitman.config;

import java.io.File;
import java.io.IOException;

import com.github.lkapitman.App;
import com.github.lkapitman.json.config.ConfigConverter;
import com.github.lkapitman.json.config.Database;
import com.github.lkapitman.json.config.Encryption;
import com.github.lkapitman.json.config.Player;
import com.github.lkapitman.json.config.Plugin;

public class Settings {
    
    private final App instance;
    private Encryption encryptionSettings;
    private Database databaseSettings;
    private Plugin pluginSettings;


    public Settings(App instance) {
        this.instance = instance;
    }

    public void init() throws IOException {
        Player player = ConfigConverter.fromJsonString(new File(instance.getDataFolder(), "/config.json"));
        encryptionSettings = player.getEncryption();
        databaseSettings = player.getDatabase();
        pluginSettings = player.getPlugin();
    }

    public Database getDatabaseSettings() {
        return databaseSettings;
    }
    
    public Encryption getEncryptionSettings() {
        return encryptionSettings;
    }

    public Plugin getPluginSettings() {
        return pluginSettings;
    }
}
