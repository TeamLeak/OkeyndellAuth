package com.github.lkapitman.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.github.lkapitman.App;

import org.bukkit.entity.Player;

public class PrintUtil {
    
    public static void printMSG(App instance, Player player, String message) {
        if (instance.isDedicatedLogs()) {
            try {
                FileWriter fileWriter = new FileWriter(new File(instance.getDataFolder(), "latest.log"));
                fileWriter.write(message);
                fileWriter.flush();    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.sendMessage(message);
    }
}
