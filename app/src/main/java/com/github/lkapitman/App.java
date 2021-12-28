package com.github.lkapitman;

import com.github.lkapitman.controller.PlayerController;
import com.github.lkapitman.util.ExtractFiles;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    private final ExtractFiles extractFiles = new ExtractFiles(this);

    @Override
    public void onEnable() {
        extractFiles.extract();    
        // this.getCommand("example").setExecutor(new ExampleCommand());
         getServer().getPluginManager().registerEvents(new PlayerController(this), this);
    }

}
