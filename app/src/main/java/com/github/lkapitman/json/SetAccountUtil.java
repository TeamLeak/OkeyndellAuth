package com.github.lkapitman.json;

import java.io.File;
import java.io.IOException;

import com.github.lkapitman.json.userdata.AccountConverter;
import com.github.lkapitman.json.userdata.Accounts;
import com.github.lkapitman.json.userdata.Player;

public class SetAccountUtil {
    
    public static void addToAccounts(File file, String username, String password, String lastIP, String lastLogin) throws IOException {
        Accounts accounts = AccountConverter.fromJsonString(file);
        Player player = new Player();
        player.setUsername(username);
        player.setPassword(password);
        player.setLastIP(lastIP);
        player.setLastLogin(lastLogin);
        accounts.getPlayers().add(player);
        AccountConverter.toJsonString(accounts, file);
    }

    public static void addLoginInfo(File file, String username, String nowIP, String lastLogin) throws IOException {
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
