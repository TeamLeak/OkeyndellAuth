package com.github.lkapitman.listener;

import com.github.lkapitman.utils.ColorAPI;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    
    private String hello = "Привет, ";

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        hello += player.getName();
        hello += 
        "\n X: " + location.getX() + 
        "\n Y: " + location.getY() + 
        "\n Z: " + location.getZ();

        player.sendMessage(ColorAPI.process("<SOLID:FF0080> " + hello + " </SOLID:FF0080>"));
    }

}
