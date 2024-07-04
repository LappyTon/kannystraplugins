package kannysta.plugins.login;

import kannysta.plugins.KannystraPluggins;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;

public class RegisterJoinListener implements Listener {
    private final KannystraPluggins plugin;
    private final Set<String> registeringPlayers = new HashSet<>();
    private final Set<String> langChoosingPlayers = new HashSet<>();

    public RegisterJoinListener(KannystraPluggins plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();

        String password = plugin.getConfig().getString("passwords." + playerName);
        String lang = plugin.getConfig().getString("lang." + playerName);

        if (lang == null || lang.isEmpty()) {
            player.teleport(plugin.getConfig().getLocation("home.LappyTon"));
            langChoosingPlayers.add(playerName);
            player.sendMessage("Please choose your language.");
        } else if (password == null || password.isEmpty()) {
            player.teleport(plugin.getConfig().getLocation("home.LappyTon"));
            registeringPlayers.add(playerName);
            player.sendMessage("Please register by setting a password.");
        }
    }

    @EventHandler
    public void onChatSendMessage(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();

        if (langChoosingPlayers.contains(playerName)) {
            e.setCancelled(true);
            // Handle language selection here, if necessary
            // Remove player from langChoosingPlayers after selection is done
            // langChoosingPlayers.remove(playerName);
        }

        if (registeringPlayers.contains(playerName)) {
            e.setCancelled(true);
            plugin.getConfig().set("passwords." + playerName, e.getMessage());
            plugin.getConfig().set("ip." + playerName, player.getAddress().getAddress().getHostAddress());
            registeringPlayers.remove(playerName);
            player.sendMessage("Registration successful!");
            plugin.saveConfig();
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();

        if (langChoosingPlayers.contains(playerName) || registeringPlayers.contains(playerName)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerHurt(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            String playerName = player.getName();

            if (langChoosingPlayers.contains(playerName) || registeringPlayers.contains(playerName)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCommandExecution(PlayerCommandSendEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        if (langChoosingPlayers.contains(playerName)) {

            String lang = plugin.getConfig().getString("lang." + playerName);
            if (lang != null && !lang.isEmpty()) {
                langChoosingPlayers.remove(playerName);
            }
        }
    }
}
