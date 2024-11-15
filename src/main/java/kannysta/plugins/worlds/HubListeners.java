package kannysta.plugins.worlds;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

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
}
