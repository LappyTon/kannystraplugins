package kannysta.plugins.login;

import kannysta.plugins.KannystraPluggins;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
    private final KannystraPluggins plugin;

    public PlayerLeaveListener(KannystraPluggins plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {

    }
}
