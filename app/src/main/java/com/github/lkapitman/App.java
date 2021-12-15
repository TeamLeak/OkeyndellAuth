package com.github.lkapitman;


import com.github.lkapitman.commands.ExampleCommand;
import com.github.lkapitman.listener.JoinListener;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("example").setExecutor(new ExampleCommand());
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }
}
