package com.github.lkapitman.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lkapitman.App;
import com.github.lkapitman.json.userdata.Player;
import com.github.lkapitman.json.userdata.UserDataConverter;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerController implements Listener {
    
    private final App instance;

    public PlayerController(App instance) {
        this.instance = instance;
    }

    private boolean isRegistered = false;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        try {
            Player player = UserDataConverter.fromJsonString(new File(instance.getDataFolder().getAbsolutePath() + "/userdata.json"));
            event.getPlayer().sendMessage(player.getUsername() + " \n" + player.getPassword() + " \n" + player.getLastIP());
        } catch (IOException e) {
            event.getPlayer().sendMessage("Случилось что то страшное! \n LOG: \n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isRegistered() {
        return isRegistered;
    }

}
