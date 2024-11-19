package kannysta.plugins.customize;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;

public class CustomGuiListener implements Listener {
    private final KannystraPluggins pluggins;
    private Utils utils;
    private CustomizeGui customizeGui;
    public CustomGuiListener(KannystraPluggins pluggins) {
        this.pluggins = pluggins;
        this.utils = new Utils(pluggins);
        this.customizeGui = new CustomizeGui(pluggins);
    }

    @EventHandler
    public void onMainCustomInv(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inventory = customizeGui.customizeInventory(p);
        if (utils.areInventoriesEqual(inventory, e.getClickedInventory())) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case WRITABLE_BOOK:
                    p.openInventory(customizeGui.customizeChatInventory(p));
                    break;
                default:
                    break;
            }
        }
    }
    @EventHandler
    public void onChatCustomInv(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = customizeGui.customizeChatInventory(p);
        if (utils.areInventoriesEqual(inv, e.getClickedInventory())) {
            e.setCancelled(true);
            ItemStack item = e.getCurrentItem();
            switch (item.getType()) {
                case LIGHT_GRAY_CONCRETE_POWDER:
                    break;
                case FEATHER:
                    break;
                case BELL:
                    break;
                default:
                    break;
            }
            if (item == inv.getItem(3)) {

            }
            if (item == inv.getItem(4)) {
                
            }
        }
    }
}
