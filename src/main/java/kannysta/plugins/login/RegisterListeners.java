package kannysta.plugins.login;

import kannysta.plugins.KannystraPluggins;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.Arrays;
import java.util.HashMap;

public class RegisterListeners implements Listener {
    private final HashMap<String, Boolean> registeringPlayers = new HashMap<>();
    private final HashMap<String, Boolean> langChoosingPlayers = new HashMap<>();
    private final KannystraPluggins plugin;

    public RegisterListeners(KannystraPluggins plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String password = plugin.getConfig().getString("passwords." + player.getName());
        String lang = plugin.getConfig().getString("lang." + player.getName());

        if (lang == null || lang.isEmpty()) {
            player.teleport(plugin.getConfig().getLocation("home.LappyTon"));
            langChoosingPlayers.put(player.getUniqueId().toString(), true);
            player.sendMessage("Choose language");
        } else {
            if (password == null || password.isEmpty()) {
                player.teleport(plugin.getConfig().getLocation("home.LappyTon"));
                registeringPlayers.put(player.getUniqueId().toString(), true);
                player.sendMessage(plugin.getConfig().getString("messages.register." + lang));
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (Boolean.TRUE.equals(registeringPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            player.sendMessage(plugin.getConfig().getString("messages.registerFirst." + plugin.getConfig().getString("lang." + player.getName())));
        } else if (Boolean.TRUE.equals(langChoosingPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            player.sendMessage("Choose language first!");
        }
    }

    @EventHandler
    public void onLangChoosingPlayerChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        if (Boolean.TRUE.equals(langChoosingPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            player.sendMessage("You can't do this while choosing a language.");
        }
    }

    @EventHandler
    public void onRegisteringPlayerChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        if (Boolean.TRUE.equals(registeringPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            plugin.getConfig().set("passwords." + player.getName(), e.getMessage());
            plugin.getConfig().set("ip." + player.getName(), player.getAddress().getAddress().getHostAddress());
            registeringPlayers.remove(player.getUniqueId().toString());
            String lang = plugin.getConfig().getString("lang." + player.getName());
            player.sendMessage(plugin.getConfig().getString("messages.registrationSuccess." + lang));
            plugin.saveConfig();
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        if (Boolean.TRUE.equals(langChoosingPlayers.get(player.getUniqueId().toString()))) {
            String command = e.getMessage().split(" ")[0];
            String[] commands = {"/language", "/мова", "/язык"};

            if (Arrays.asList(commands).contains(command)) {
                langChoosingPlayers.remove(player.getUniqueId().toString());
                player.sendMessage(plugin.getConfig().getString("messages.register." + plugin.getConfig().getString("lang."+player.getName())));
                registeringPlayers.put(player.getUniqueId().toString(), true);
            } else {
                e.setCancelled(true);
                player.sendMessage("You can't send commands while choosing a language! ");
                player.sendMessage("Try /<word language in your lang>");
            }
        } else if (Boolean.TRUE.equals(registeringPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            player.sendMessage(plugin.getConfig().getString("messages.whileRegister." + plugin.getConfig().getString("lang." + player.getName())));
        }
    }
}
