package com.github.lkapitman.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import com.github.lkapitman.json.userdata.AccountConverter;
import com.github.lkapitman.json.userdata.Accounts;
import com.github.lkapitman.json.userdata.Player;

public class AccountManager {
    
    private final File jsonFile;

    public AccountManager(File file) {
        jsonFile = file;
    }
    
    public boolean isRegistered(String name) {
        try {
            var accounts = AccountConverter.fromJsonString(jsonFile);
            for (Player player : accounts.getPlayers()) {
                if (player.getUsername().equals(name)) {
                    return true;
                }
            }
        } catch (IOException ignored) {
        }

        return false;
    }

    public boolean isSessionExpired(String username, String IP) {
        try {
            var accounts = AccountConverter.fromJsonString(jsonFile);
            for (Player player : accounts.getPlayers()) {
                if (isRegistered(username)) {
                    if (player.getLastIP().equals(IP)) {
                        return false;
                    }
                }
            }
    
        } catch (IOException ignored) {
        }
        return true;
    }

    public boolean isSame(String username, HashSet<org.bukkit.entity.Player> players) {
        for (org.bukkit.entity.Player player : players) {
            if (username.equals(player.getName()))
                return true;
        }
        return false;
    }

    public String getPassword(String username) {
        try {
            Accounts accounts = AccountConverter.fromJsonString(jsonFile);
            for (Player player : accounts.getPlayers()) {
                if (isRegistered(username)) {
                    return player.getPassword();
                }
            }    
        } catch (IOException ignored) {
        }
        return null;
    }

    public void addToAccounts(File file, String username, String password, String lastIP, String lastLogin) throws IOException {
        Accounts accounts = AccountConverter.fromJsonString(file);
        Player player = new Player();
        player.setUsername(username);
        player.setPassword(password);
        player.setLastIP(lastIP);
        player.setLastLogin(lastLogin);
        accounts.getPlayers().add(player);
        AccountConverter.toJsonString(accounts, file);
    }

    public void addLoginInfo(File file, String username, String nowIP, String lastLogin) throws IOException {
        Accounts accounts = AccountConverter.fromJsonString(file);
        for (Player player : accounts.getPlayers()) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                player.setLastIP(nowIP);
                player.setLastLogin(lastLogin);
                AccountConverter.toJsonString(accounts, file);
                return;
            }
        }
    }
}
