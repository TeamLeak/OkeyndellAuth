package com.github.lkapitman.commands;

import com.github.lkapitman.App;
import com.github.lkapitman.controller.PlayerController;
import com.github.lkapitman.util.HashUtil;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor {

    public static PlayerController playerController;
    private final App instance;
    private int tryCount = 0;

    public LoginCommand(App instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (!playerController.isRegistered()) {
            sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "У вас нет аккаунта! \n /register <пароль> <пароль>");
            return false;
        }
        if (playerController.isSessionExpired()) {

            if (tryCount == 5) {
                Player player = (Player) sender;
                player.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "Вы исчерпали кол-во попыток!");
                tryCount = 0;
                return false;
            }

            if (instance.getSettings().getEncryptionSettings().getUseEncrypt()) {
                if (instance.getSettings().getEncryptionSettings().getEncryptType().equalsIgnoreCase("SHA256")) {
                    if (!isSame(HashUtil.hashSHA256(args[0]), playerController.getPlayerPassword())) {
                        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Неправильный пароль! \n Попробуйте ещё-раз!");
                        tryCount = tryCount + 1;
                        return false;
                    }
                }
                if (instance.getSettings().getEncryptionSettings().getEncryptType().equalsIgnoreCase("MD5")) {
                    if (!isSame(HashUtil.hashMD5(args[0]), playerController.getPlayerPassword())) {
                        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Неправильный пароль! \n Попробуйте ещё-раз!");
                        tryCount = tryCount + 1;
                        return false;
                    }
                }
            } else {
                if (!isSame(args[0], playerController.getPlayerPassword())) {
                    sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Неправильный пароль! \n Попробуйте ещё-раз!");
                    tryCount = tryCount + 1;
                    return false;
                }
            }
        }
        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Приятной игры!");
        return true;
    }
    
    public boolean isSame(String s, String s2) {
        if (s.equalsIgnoreCase(s2))
            return true;
        return false;
    }

}
