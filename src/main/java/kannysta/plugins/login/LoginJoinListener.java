package kannysta.plugins.login;

import kannysta.plugins.KannystraPluggins;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;

public class LoginJoinListener implements Listener {
    private final KannystraPluggins plugin;
    private final Set<String> loggingInPlayers = new HashSet<>();

    public LoginJoinListener(KannystraPluggins plugin) {
        this.plugin = plugin;
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
                player.teleport(plugin.getConfig().getLocation("home.LappyTon"));
                player.sendMessage("Please log in");
                loggingInPlayers.add(playerName);
            }
        }
    }

    @EventHandler
    public void onPlayerSendMessage(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();

        if (loggingInPlayers.contains(playerName)) {
            e.setCancelled(true);
            String password = plugin.getConfig().getString("passwords." + playerName);

            if (e.getMessage().equalsIgnoreCase(password)) {
                loggingInPlayers.remove(playerName);
                player.sendMessage("You have been logged in successfully");
                plugin.getConfig().set("ip." + playerName, player.getAddress().getAddress().getHostAddress());
                plugin.saveConfig();
            } else {
                player.kickPlayer("Incorrect password!");
                loggingInPlayers.remove(playerName);
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (loggingInPlayers.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerHurt(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (loggingInPlayers.contains(e.getEntity().getName())) {
                e.setCancelled(true);
            }
        }
    }
}
