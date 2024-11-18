package kannysta.plugins.login;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;
import kannysta.plugins.tab.TabChange;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class LoginJoinListener implements Listener {
    private final KannystraPluggins plugin;
    private final Set<String> loggingInPlayers = new HashSet<>();
    private final TabChange tabChange;
    private Utils utils;

    public LoginJoinListener(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.utils = new Utils(plugin);
        this.tabChange = new TabChange(plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        String storedIpPath = "ip." + playerName;
        String passwordPath = "passwords." + playerName;

        String password = plugin.getConfig().getString(passwordPath);

        if (password != null && !password.isEmpty()) {
            String storedIp = plugin.getConfig().getString(storedIpPath);
            String currentIp = player.getAddress().getAddress().getHostAddress();

            if (storedIp == null || !currentIp.equals(storedIp)) {
                Location loginLocation = plugin.getConfig().getLocation("locations.login");
                if (loginLocation != null) {

                    player.teleport(loginLocation);
                    player.sendMessage(utils.event(plugin.getConfig().getString("messages.loginPlease."+plugin.getConfig().getString("lang."+player.getName()))));
                    player.sendMessage(utils.info(plugin.getConfig().getString("messages.howToLogin."+plugin.getConfig().getString("lang."+player.getName()))));
                    tabChange.loginTabChange(player, 20);
                    loggingInPlayers.add(playerName);
                } else {
                    player.sendMessage(utils.issue("Login location not set. Please contact an administrator."));
                }
            } else {
                Location leaveLocation = plugin.getConfig().getLocation("leaveLocation."+player.getName());
                if (leaveLocation!=null) {
                    player.teleport(leaveLocation);
                }
                player.sendMessage(utils.succes(plugin.getConfig().getString("messages.noNeedLogin."+plugin.getConfig().getString("lang."+player.getName()))));
                utils.showPlayers(player);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerSendMessage(PlayerChatEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();

        if (loggingInPlayers.contains(playerName)) {
            String password = plugin.getConfig().getString("passwords." + playerName);
            e.setCancelled(true);

            player.sendMessage(utils.event(plugin.getConfig().getString("messages.passwordCheck."+plugin.getConfig().getString("lang."+playerName))));

            if (e.getMessage().equalsIgnoreCase(password)) {
                loggingInPlayers.remove(playerName);
                player.sendMessage(utils.succes(plugin.getConfig().getString("messages.passwordCorrect."+plugin.getConfig().getString("lang."+player.getName()))));
                utils.showPlayers(player);
                if (player.getWorld().equals(plugin.getServer().getWorld("login"))) {
                    if (plugin.getConfig().getLocation("leaveLocation."+player.getName()) == null) {
                        player.teleport(plugin.getConfig().getLocation("locations.hub"));
                    } else {
                        player.teleport(plugin.getConfig().getLocation("leaveLocation."+player.getName()));
                    }
                }

                plugin.getConfig().set("ip." + playerName, player.getAddress().getAddress().getHostAddress());
                plugin.saveConfig();
            } else {
                player.kickPlayer(utils.issue(plugin.getConfig().getString("messages.passwordIncorrect."+plugin.getConfig().getString("lang."+player.getName()))));
            }
        }
    }


    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity().getWorld().equals(plugin.getServer().getWorld("login"))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (loggingInPlayers.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }

}