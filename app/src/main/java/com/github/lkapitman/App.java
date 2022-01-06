package com.github.lkapitman;

import java.io.File;
import java.io.IOException;

import com.github.lkapitman.config.MessagesList;
import com.github.lkapitman.config.Settings;
import com.github.lkapitman.controller.PlayerController;
import com.github.lkapitman.util.AccountManager;
import com.github.lkapitman.util.FileManager;
import com.github.lkapitman.util.LoggerTool;
import com.github.lkapitman.util.hash.HashManager;
import com.github.lkapitman.util.hash.HashType;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    private final FileManager extractFiles = new FileManager(this);    
    private final AccountManager accountManager = new AccountManager(new File(getDataFolder(), "/userdata.json"));
    private HashManager hashManager;

    private final Settings settings = new Settings(this);
    private final MessagesList messages = new MessagesList(this);

    private LoggerTool lTool;
    private boolean useDedicatedLogs;

    private File userdata;
    private File config;

    @Override
    public void onEnable() {
        extractFiles.extract(); 
        lTool = new LoggerTool(this, "AuthLogger");

        userdata = new File(getDataFolder(), "/userdata.json");
        config = new File(getDataFolder(), "/config.json");

        try {
            settings.init();
            messages.init();
        } catch (IOException e) {
        }

        if (!settings.getPluginSettings().getEnabled())
            return;

        if (settings.getPluginSettings().getDedicatedLogs()) {
            useDedicatedLogs = settings.getPluginSettings().getDedicatedLogs();
            try {
                lTool.init();
            } catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
        }

        switch (settings.getEncryptionSettings().getEncryptType().toLowerCase()) {
            case "sha256":
                hashManager = new HashManager(HashType.SHA256);
                break;
            case "md_5":
                hashManager = new HashManager(HashType.MD_5);
                break;
        }

        this.getCommand("login").setExecutor(new PlayerController(this));
        this.getCommand("register").setExecutor(new PlayerController(this));
        getServer().getPluginManager().registerEvents(new PlayerController(this), this);        
    }

    @Override
    public void onDisable() {
        lTool.log("OkeyndellAuth - disabled! \n ==========================");
    }

    public boolean useDedicatedLogs() {
        return useDedicatedLogs;
    }

    public Settings getSettings() {
        return settings;
    }

    public MessagesList getMessages() {
        return messages;
    }

    public LoggerTool getLTool() {
        return lTool;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public HashManager getHashManager() {
        return hashManager;
    }

    public File getUserdataFile() {
        return userdata;
    }

    public File getConfigFile() {
        return config;
    }
}
