package kannysta.plugins.worlds;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import kannysta.plugins.KannystraPluggins;

public class HubListeners implements Listener {
    private final KannystraPluggins pluggins;
    public HubListeners(KannystraPluggins pluggins) {
        this.pluggins=pluggins;
    }
    @EventHandler
    public void onEntityHurt(EntityDamageEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void joinMessageDisable(PlayerJoinEvent e) {
        e.setJoinMessage("");
    }
}
