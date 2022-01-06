package com.github.lkapitman.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import com.github.lkapitman.App;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.lkapitman.controller.PlayerController;
import com.github.lkapitman.util.DateUtil;


public class PlayerController implements Listener, CommandExecutor {
    
    private final App instance;

    public PlayerController(App instance) {
        this.instance = instance;
    }
    
    private static HashSet<org.bukkit.entity.Player> players = new HashSet<>();
    private static HashSet<org.bukkit.entity.Player> neededRegistered = new HashSet<>();
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();

        instance.getLTool().log(player.getName() + " joined!");

        if (instance.getAccountManager().isRegistered(player.getName())) {
            if (instance.getAccountManager().isSessionExpired(player.getName(), player.getAddress().getHostString())) {
                players.add(player);
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getInputPassword());
            } else {
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getEntryMessage());
            }
        } else {
            neededRegistered.add(player);
            players.add(player);
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getNoAccount());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;

        if (!instance.getAccountManager().isSame(player.getName(), players))
            return false;

        if (command.getName().equalsIgnoreCase("register")) {
            instance.getLTool().log(player.getName() + " trying register!");

            if (!instance.getAccountManager().isSame(player.getName(), neededRegistered)) {
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getRegisterMessages().getHaveAccount());
                return false;
            }
            if (!(sender instanceof org.bukkit.entity.Player))
                return false;

            if (args.length <= 1) {
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getRegisterMessages().getRegisterUsage());
                return false;
            }
            
            if (!args[0].equals(args[1])) {
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getRegisterMessages().getPasswordNoEquals());
                return false;
            }

            String password = "";

            if (instance.getSettings().getEncryptionSettings().getUseEncrypt()) {
                password = instance.getHashManager().hash(args[0]);
            }

            try {
                instance.getAccountManager().addToAccounts(instance.getUserdataFile(), player.getName(), password, player.getAddress().getHostString(), DateUtil.getDate());
            } catch (IOException e) {
                sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getRegisterMessages().getRegisterError1() + ChatColor.RED + "" + ChatColor.ITALIC + instance.getMessages().getRegisterMessages().getRegisterError2());
                return false;
            }
            
            sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getEntryMessage());
            instance.getLTool().log(player.getName() + " succesfull register!");

            players.remove(player);
            neededRegistered.remove(player);
            return true;
        }

        if (command.getName().equalsIgnoreCase("login")) {
            
            if (!instance.getAccountManager().isSame(player.getName(), players)) {
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getNoAccount());
                return false;
            }

            instance.getLTool().log(player.getName() + " trying login!");

            if (args.length < 1) {
                sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getLoginUsage());
                return false;
            }

            if (instance.getAccountManager().isSame(player.getName(), players)) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        player.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getAttempsError());
                    }
                }, 300000);

                if (instance.getSettings().getEncryptionSettings().getUseEncrypt()) {
                    if (instance.getHashManager().hash(args[0]).equals(instance.getAccountManager().getPassword(player.getName()))) {
                        timer.cancel();
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getPasswordNoEqual());
                    }
                } else {
                    if (args[0].equals(instance.getAccountManager().getPassword(player.getName()))) {
                        timer.cancel();
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getLoginMessages().getPasswordNoEqual());
                    }
                }
            }

            sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + instance.getMessages().getEntryMessage());

            neededRegistered.remove(player);
            players.remove(player);

            instance.getLTool().log(player.getName() + " succesfull login!");

            try {
                instance.getAccountManager().addLoginInfo(new File(instance.getDataFolder(), "/userdata.json"), player.getName(), player.getAddress().getHostString(), DateUtil.getDate());
            } catch (IOException e) {
            }

            return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        if (instance.getAccountManager().isSame(event.getPlayer().getName(), players)) {
            event.setTo(event.getFrom());
            event.setCancelled(true);
            return;
        }
        event.setCancelled(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (instance.getAccountManager().isSame(event.getPlayer().getName(), players)) {
            event.setCancelled(true);
            return;
        }
        event.setCancelled(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event) {
        if (instance.getAccountManager().isSame(event.getPlayer().getName(), players)) {
            event.setCancelled(true);
            return;
        }
        event.setCancelled(false);
    }

    @EventHandler
    public void onHit(EntityDamageEvent event){
        if (event.getEntity() instanceof Player) {
            if (instance.getAccountManager().isSame(((Player) event.getEntity()).getName(), players)) {
                event.setCancelled(true);
                return;
            }
        }
        event.setCancelled(false);
    }
}
