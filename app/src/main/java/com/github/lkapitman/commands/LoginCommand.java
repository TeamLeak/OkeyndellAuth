package com.github.lkapitman.commands;

import com.github.lkapitman.controller.PlayerController;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LoginCommand implements CommandExecutor {

    private final PlayerController playerController;

    public LoginCommand(PlayerController controller) {
        playerController = controller;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (playerController.isRegistered()) {
            // TODO: такой аккаунт есть. ждём.
            return true;
        }
        return false;
    }
    
}
