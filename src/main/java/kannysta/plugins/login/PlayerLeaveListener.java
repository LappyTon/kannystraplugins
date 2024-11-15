package kannysta.plugins.login;

import kannysta.plugins.KannystraPluggins;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerLeaveListener implements Listener {
    private final KannystraPluggins plugin;

    public PlayerLeaveListener(KannystraPluggins plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();
        Location leaveLocation = e.getPlayer().getLocation();

        if (!Objects.equals(leaveLocation.getWorld(), plugin.getServer().getWorld("register")) &&
                !Objects.equals(leaveLocation.getWorld(), plugin.getServer().getWorld("login")) &&
                !Objects.equals(leaveLocation.getWorld(), plugin.getServer().getWorld("lobby"))) {

            System.out.println(player.getName()+"  :  "+player.getLocation()+" [kannystra]");

            plugin.getConfig().set("leaveLocation."+player.getName(), leaveLocation);
            plugin.saveConfig();
            plugin.saveDefaultConfig();
        }

    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Location leaveLocation = e.getPlayer().getLocation();
        
        if (!Objects.equals(leaveLocation.getWorld(), plugin.getServer().getWorld("register")) &&
                !Objects.equals(leaveLocation.getWorld(), plugin.getServer().getWorld("login")) &&
                !Objects.equals(leaveLocation.getWorld(), plugin.getServer().getWorld("testhub"))) {

            System.out.println(player.getName()+"  :  "+player.getLocation()+" [kannystra]");

            plugin.getConfig().set("leaveLocation."+player.getName(), leaveLocation);
            plugin.saveConfig();
            plugin.saveDefaultConfig();
        }

    }
}