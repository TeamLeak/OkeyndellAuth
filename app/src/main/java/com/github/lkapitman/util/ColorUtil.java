package com.github.lkapitman.util;

import org.bukkit.ChatColor;

public class ColorUtil {
    
    public static String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
