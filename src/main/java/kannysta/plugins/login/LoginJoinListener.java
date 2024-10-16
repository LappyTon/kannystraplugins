package kannysta.plugins.login;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.tab.TabChange;
import kannysta.plugins.utils.ChatTypes;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;

public class LoginJoinListener implements Listener {
    private final KannystraPluggins plugin;
    private final Set<String> loggingInPlayers = new HashSet<>();
    private final ChatTypes types;
    private final TabChange tabChange;

    public LoginJoinListener(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.types = new ChatTypes();
        this.tabChange = new TabChange(plugin);
    }

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
                } else {
                    tabChange.LoginTabChange(player);
                    player.sendMessage(types.issue("Login location not set. Please contact an administrator."));
                    player.sendMessage(types.event(plugin.getConfig().getString("messages.loginPlease."+plugin.getConfig().getString("lang."+player.getName()))));
                    player.sendMessage(types.info(plugin.getConfig().getString("messages.howToLogin."+plugin.getConfig().getString("lang."+player.getName()))));
                    loggingInPlayers.add(playerName);
                }
            } else {
                Location leaveLocation = plugin.getConfig().getLocation("leaveLocation."+player.getName());
                if (leaveLocation!=null) {
                    player.teleport(leaveLocation);
                }
                player.sendMessage(types.succes(plugin.getConfig().getString("messages.noNeedLogin."+plugin.getConfig().getString("lang."+player.getName()))));
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerSendMessage(PlayerChatEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();

        // Check if player is in the logging in list
        if (loggingInPlayers.contains(playerName)) {
            String password = plugin.getConfig().getString("passwords." + playerName);
            e.setCancelled(true);

            // Debug message
            player.sendMessage(types.event(plugin.getConfig().getString("messages.passwordCheck."+plugin.getConfig().getString("lang."+playerName))));

            // Check if the entered password is correct
            if (e.getMessage().equalsIgnoreCase(password)) {
                loggingInPlayers.remove(playerName);
                player.sendMessage(types.succes(plugin.getConfig().getString("messages.passwordCorrect."+plugin.getConfig().getString("lang."+player.getName()))));

                if (player.getWorld().equals(plugin.getServer().getWorld("login"))) {
                    if (plugin.getConfig().getLocation("leaveLocation."+player.getName()) == null) {
                        player.sendMessage("1");
                        player.teleport(plugin.getConfig().getLocation("locations.hub"));
                    } else {
                        player.teleport(plugin.getConfig().getLocation("leaveLocation."+player.getName()));
                    }
                }

                plugin.getConfig().set("ip." + playerName, player.getAddress().getAddress().getHostAddress());
                plugin.saveConfig();
            } else {
                player.kickPlayer(types.issue(plugin.getConfig().getString("messages.passwordIncorrect."+plugin.getConfig().getString("lang."+player.getName()))));
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