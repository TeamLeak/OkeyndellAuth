package com.github.lkapitman.commands;

import com.github.lkapitman.App;
import com.github.lkapitman.controller.PlayerController;
import com.github.lkapitman.json.config.Player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RegisterCommand implements CommandExecutor {

    private final App instance;
    public static PlayerController playerController;

    public RegisterCommand(App instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;
        if (playerController.isRegistered())
            return false;
        if (playerController.isLogin())
            return false;
        //TODO
        return true;
    }
    
}
