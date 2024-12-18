package kannysta.plugins.vaultevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import kannysta.plugins.KannystraPluggins;


public class ChangeMoney extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    
    private final KannystraPluggins pluggins;
    private final Player p;
    private final int money;
    
    public ChangeMoney(KannystraPluggins pluggins, Player p, int money) {
        this.money = money;
        this.p = p;
        this.pluggins = pluggins;
    }

    public Player getPlayer() {
        return p;
    }
    public int getMoney() {
        return money;
    }
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
