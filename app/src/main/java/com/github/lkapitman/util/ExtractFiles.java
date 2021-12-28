package com.github.lkapitman.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.github.lkapitman.App;

public class ExtractFiles {
    
    private final App instance;

    public ExtractFiles(App instance) {
        this.instance = instance;
    }

    public void extract() {
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
            extractUserData();
            extractConfig();    
        } else {
            if (!new File(instance.getDataFolder(), "userdata.json").exists()) {
                extractUserData();
            }
            if (!new File(instance.getDataFolder(), "config.json").exists()) {
                extractConfig();
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
}
