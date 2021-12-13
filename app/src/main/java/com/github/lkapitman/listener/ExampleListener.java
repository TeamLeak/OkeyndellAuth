package com.github.lkapitman.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ExampleListener implements Listener {

    @EventHandler    
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("You're moving! Yes, yes, " + player.getName());
    }
}
