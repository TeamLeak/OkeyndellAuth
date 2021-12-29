package com.github.lkapitman;

import java.io.IOException;

import com.github.lkapitman.config.Settings;
import com.github.lkapitman.controller.PlayerController;

import com.github.lkapitman.util.ExtractFiles;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    private final ExtractFiles extractFiles = new ExtractFiles(this);    
    private final Settings settings = new Settings(this);

    private boolean isDedicatedLogs;

    @Override
    public void onEnable() {
        extractFiles.extract(); 
        try {
            settings.init();
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

}
