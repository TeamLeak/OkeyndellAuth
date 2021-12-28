package com.github.lkapitman.controller;

import java.io.File;
import java.io.IOException;

import com.github.lkapitman.App;
import com.github.lkapitman.commands.LoginCommand;
import com.github.lkapitman.json.userdata.AccountConverter;
import com.github.lkapitman.json.userdata.Accounts;
import com.github.lkapitman.json.userdata.Player;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerController implements Listener {
    
    private final App instance;

    public PlayerController(App instance) {
        this.instance = instance;
    }

    private boolean isRegistered = false;
    private boolean sessionExpired = false;
    private String playerPassword;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        try {
            for (Player player : AccountConverter.fromJsonString(new File(instance.getDataFolder(), "/userdata.json")).getPlayers()) {
                if (player.getUsername().equalsIgnoreCase(event.getPlayer().getName())) {
                    isRegistered = true;
                    if (!player.getLastIP().equalsIgnoreCase(event.getPlayer().getAddress().getHostString())) {
                        event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Введите пароль! \n /login <пароль>!");
                        LoginCommand.playerController = this;
                        sessionExpired = true;
                        playerPassword = player.getPassword();
                        return;
                    }
                    return;
                }
            }
            event.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "У вас нет аккаунта! Зарегестрируйтесь \n /register <пароль> <пароль>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public boolean isSessionExpired() {
        return sessionExpired;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }
}
