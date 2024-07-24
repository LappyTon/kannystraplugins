package kannysta.plugins.login;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.utils.ChatTypes;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

import java.util.Arrays;
import java.util.HashMap;

public class RegisterListeners implements Listener {
    private final HashMap<String, Boolean> registeringPlayers = new HashMap<>();
    private final HashMap<String, Boolean> langChoosingPlayers = new HashMap<>();
    private final KannystraPluggins plugin;
    private final ChatTypes types = new ChatTypes();

    public RegisterListeners(KannystraPluggins plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String password = plugin.getConfig().getString("passwords." + player.getName());
        String lang = plugin.getConfig().getString("lang." + player.getName());

        Location registerLocation = plugin.getConfig().getLocation("locations.register");
        if (registerLocation == null) {
            player.sendMessage(types.issue("Register location not set. Please contact an administrator."));
            return;
        }

        if (lang == null || lang.isEmpty()) {
            player.teleport(registerLocation);
            langChoosingPlayers.put(player.getUniqueId().toString(), true);
            player.sendMessage(types.event("Choose language"));
            player.sendMessage(types.info("Type /language, /мова, /язык"));
        } else {
            if (password == null || password.isEmpty()) {
                player.teleport(registerLocation);
                registeringPlayers.put(player.getUniqueId().toString(), true);
                player.sendMessage(types.event(plugin.getConfig().getString("messages.register." + lang, "Please register")));
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (Boolean.TRUE.equals(registeringPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            player.sendMessage("");
            player.sendMessage(types.issue(plugin.getConfig().getString("messages.registerFirst." + plugin.getConfig().getString("lang." + player.getName()))));
            player.sendMessage(types.info(plugin.getConfig().getString("messages.howToLogin." + plugin.getConfig().getString("lang." + player.getName()))));
            player.sendMessage("");
        } else if (Boolean.TRUE.equals(langChoosingPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            player.sendMessage("");
            player.sendMessage(types.warning("Choose language first!"));
            player.sendMessage(types.info("Type /language, /мова, /язык"));
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        if (Boolean.TRUE.equals(langChoosingPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            player.sendMessage("");
            player.sendMessage(types.warning("You can't do this while choosing a language."));
            player.sendMessage(types.info("Type /language, /мова, /язык"));
        } else if (Boolean.TRUE.equals(registeringPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            plugin.getConfig().set("passwords." + player.getName(), e.getMessage());
            plugin.getConfig().set("ip." + player.getName(), player.getAddress().getAddress().getHostAddress());
            registeringPlayers.remove(player.getUniqueId().toString());
            player.sendMessage(types.succes(plugin.getConfig().getString("messages.registrationSuccess."+plugin.getConfig().getString("lang."+player.getName()))));
            Location hubLocation = plugin.getConfig().getLocation("locations.hub");
            if (hubLocation != null) {
                player.teleport(hubLocation);
            } else {
                player.sendMessage(types.issue("Hub location not set. Please contact an administrator."));
            }
            plugin.saveConfig();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Boolean isLangChoosing = langChoosingPlayers.get(player.getUniqueId().toString());
        Boolean isRegistering = registeringPlayers.get(player.getUniqueId().toString());
        if (Boolean.TRUE.equals(isLangChoosing) || Boolean.TRUE.equals(isRegistering)) {
            Location registerLocation = plugin.getConfig().getLocation("locations.register");
            if (registerLocation != null) {
                player.teleport(registerLocation);
            } else {
                player.sendMessage(types.issue("Register location not set. Please contact an administrator."));
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity().getWorld().equals(plugin.getServer().getWorld("register"))) {
            e.setCancelled(true);
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
                player.sendMessage(types.event("Now register - just send your password in chat"));
                registeringPlayers.put(player.getUniqueId().toString(), true);
            } else {
                e.setCancelled(true);
                player.sendMessage(types.issue("You can't send commands while choosing a language! "));
                player.sendMessage(types.info("Type /language, /мова, /язык"));
            }
        } else if (Boolean.TRUE.equals(registeringPlayers.get(player.getUniqueId().toString()))) {
            e.setCancelled(true);
            player.sendMessage(types.issue(plugin.getConfig().getString("messages.whileRegister." + plugin.getConfig().getString("lang." + player.getName()))));
        }
    }
}