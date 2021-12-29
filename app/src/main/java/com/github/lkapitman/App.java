package com.github.lkapitman;

import java.io.IOException;

import com.github.lkapitman.config.MessagesList;
import com.github.lkapitman.config.Settings;
import com.github.lkapitman.controller.PlayerController;

import com.github.lkapitman.util.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    private final FileManager extractFiles = new FileManager(this);    
    private final Settings settings = new Settings(this);
    private final MessagesList messages = new MessagesList(this);
    
    private boolean isDedicatedLogs;

    @Override
    public void onEnable() {
        extractFiles.extract(); 
        try {
            settings.init();
            messages.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!settings.getPluginSettings().getEnabled())
            return;
        if (settings.getPluginSettings().getDedicatedLogs())
            isDedicatedLogs = settings.getPluginSettings().getDedicatedLogs();

        this.getCommand("login").setExecutor(new PlayerController(this));
        this.getCommand("register").setExecutor(new PlayerController(this));
        getServer().getPluginManager().registerEvents(new PlayerController(this), this);        
    }

    public boolean isDedicatedLogs() {
        return isDedicatedLogs;
    }

    public Settings getSettings() {
        return settings;
    }

    public MessagesList getMessages() {
        return messages;
    }
}
