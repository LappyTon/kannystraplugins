package kannysta.plugins.littlecommands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;

public class GuiCheckerListener implements Listener {
    private final Utils utils;
    public GuiCheckerListener(KannystraPluggins pluggins) {
        this.utils = new Utils(pluggins);
    }
    private GuiChecker checker = new GuiChecker();

    @EventHandler
    public void onInvChecker(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle()=="guichecker") {
            p.sendMessage(""+e.getSlot());
        }
    }
}
