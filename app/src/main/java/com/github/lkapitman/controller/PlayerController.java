package com.github.lkapitman.controller;

import java.io.File;
import java.io.IOException;

import com.github.lkapitman.App;
import com.github.lkapitman.json.userdata.AccountConverter;
import com.github.lkapitman.json.userdata.Player;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.github.lkapitman.controller.PlayerController;
import com.github.lkapitman.json.SetAccountUtil;
import com.github.lkapitman.util.DateUtil;
import com.github.lkapitman.util.HashUtil;
import com.github.lkapitman.util.PrintUtil;

public class PlayerController implements Listener, CommandExecutor {
    
    private final App instance;

    public PlayerController(App instance) {
        this.instance = instance;
    }
    
    private static boolean isRegistered = false;
    private static boolean isSessionExpired = false;
    private static String playerPassword;
    private static int tryCount = 0;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {

        try {
            for (Player player : AccountConverter.fromJsonString(new File(instance.getDataFolder(), "/userdata.json")).getPlayers()) {
                if (player.getUsername().equalsIgnoreCase(event.getPlayer().getDisplayName())) {
                    isRegistered = true;
                    if (!player.getLastIP().equalsIgnoreCase(event.getPlayer().getAddress().getHostString())) {
                        PrintUtil.printMSG(instance, event.getPlayer(), ChatColor.RED + "" + ChatColor.BOLD + instance.getMessages().getInputPassword());
                        isSessionExpired = true;
                        playerPassword = player.getPassword();
                        return;
                    }
                    return;
                }
            }
            PrintUtil.printMSG(instance, event.getPlayer(), ChatColor.GOLD + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getNoAccount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;

        if (command.getName().equalsIgnoreCase("register")) {
            if (!(sender instanceof org.bukkit.entity.Player))
                return false;
            if (args.length <= 1) {
                PrintUtil.printMSG(instance, player, ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getRegisterMessages().getRegisterUsage());
                return false;
            }
            
            if (!args[0].equalsIgnoreCase(args[1])) {
                PrintUtil.printMSG(instance, player, ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getRegisterMessages().getPasswordNoEquals());
                return false;
            }

            if (isRegistered) {
                sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "" + instance.getMessages().getRegisterMessages().getHaveAccount());
                return false;
            }

            String username = player.getName();
            String lastIP = player.getAddress().getHostString();
            String lastLogin = DateUtil.getDate();
            String password = "";

            if (instance.getSettings().getEncryptionSettings().getUseEncrypt()) {
                if (instance.getSettings().getEncryptionSettings().getEncryptType().equalsIgnoreCase("SHA256")) {
                    password = HashUtil.hashSHA256(args[0]);
                }
                if (instance.getSettings().getEncryptionSettings().getEncryptType().equalsIgnoreCase("MD5")) {
                    password = HashUtil.hashMD5(args[0]);
                }
            } else {
                password = args[0];
            }

            try {
                SetAccountUtil.addToAccounts(new File(instance.getDataFolder(), "/userdata.json"), username, password, lastIP, lastLogin);
            } catch (IOException e) {
                sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getRegisterMessages().getRegisterError1() + ChatColor.RED + "" + ChatColor.ITALIC + instance.getMessages().getRegisterMessages().getRegisterError2());
                e.printStackTrace();
                return false;
            }
            sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getEntryMessage());
            isRegistered = true;
            isSessionExpired = false;
            return true;
        }

        if (command.getName().equalsIgnoreCase("login")) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getLoginUsage());
                return false;
            }
            if (!isRegistered) {
                sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getNoAccount());
                return false;
            }
            if (isSessionExpired) {
    
                if (tryCount == 5) {
                    player.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getAttempsError());
                    tryCount = 0;
                    return false;
                }
    
                if (instance.getSettings().getEncryptionSettings().getUseEncrypt()) {
                    if (instance.getSettings().getEncryptionSettings().getEncryptType().equalsIgnoreCase("SHA256")) {
                        if (!HashUtil.isSame(HashUtil.hashSHA256(args[0]), playerPassword)) {
                            sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getPasswordNoEqual());
                            tryCount = tryCount + 1;
                            return false;
                        }
                    }
                    if (instance.getSettings().getEncryptionSettings().getEncryptType().equalsIgnoreCase("MD5")) {
                        if (!HashUtil.isSame(HashUtil.hashMD5(args[0]), playerPassword)) {
                            sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getPasswordNoEqual());
                            tryCount = tryCount + 1;
                            return false;
                        }
                    }
                } else {
                    if (!HashUtil.isSame(args[0], playerPassword)) {
                        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getPasswordNoEqual());
                        tryCount = tryCount + 1;
                        return false;
                    }
                }
            }
            sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getEntryMessage());
            isRegistered = true;
            isSessionExpired = false;
            try {
                SetAccountUtil.addLoginInfo(new File(instance.getDataFolder(), "/userdata.json"), player.getName(), player.getAddress().getHostString(), DateUtil.getDate());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
