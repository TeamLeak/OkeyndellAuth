package com.github.lkapitman.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.github.lkapitman.App;

public class FileManager {
    
    private final App instance;

    public FileManager(App instance) {
        this.instance = instance;
    }

    public void extract() {
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
            extractUserData();
            extractConfig();    
            extractMessages();
        } else {
            if (!new File(instance.getDataFolder(), "userdata.json").exists()) {
                extractUserData();
            }
            if (!new File(instance.getDataFolder(), "config.json").exists()) {
                extractConfig();
            }
            if (!new File(instance.getDataFolder(), "messages_en.json").exists() || !new File(instance.getDataFolder(), "messages_ru.json").exists()) {
                extractMessages();
            }
        }
    }

    private void extractUserData() {
        File file = new File(instance.getDataFolder(), "userdata.json");
        if (!file.exists()) {
            InputStream link = getClass().getResourceAsStream("/userdata.json");
            try {
                Files.copy(link, file.getAbsoluteFile().toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void extractConfig() {
        File file = new File(instance.getDataFolder(), "config.json");
        if (!file.exists()) {
            InputStream link = getClass().getResourceAsStream("/config.json");
            try {
                Files.copy(link, file.getAbsoluteFile().toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void extractMessages() {
        File file = new File(instance.getDataFolder(), "messages_en.json");
        if (!file.exists()) {
            InputStream link = getClass().getResourceAsStream("/messages_en.json");
            try {
                Files.copy(link, file.getAbsoluteFile().toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file = new File(instance.getDataFolder(), "messages_ru.json");
        if (!file.exists()) {
            InputStream link = getClass().getResourceAsStream("/messages_ru.json");
            try {
                Files.copy(link, file.getAbsoluteFile().toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
