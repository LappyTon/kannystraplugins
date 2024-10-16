package kannysta.plugins.mainMenu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import kannysta.plugins.KannystraPluggins;

public class WorldsInventoryListener implements Listener {
    private final KannystraPluggins plugin;
    private MenuInventory inventory;
    public WorldsInventoryListener(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.inventory = new MenuInventory(plugin);
    }

    public boolean areInventoriesEqual(Inventory inv1, Inventory inv2) {
        if (inv1.getSize() != inv2.getSize()) {
            return false;
        }

        org.bukkit.inventory.ItemStack[] items1 = inv1.getContents();
        org.bukkit.inventory.ItemStack[] items2 = inv2.getContents();

        for (int i = 0; i < items1.length; i++) {
            if (items1[i] == null && items2[i] == null) {
                continue;
            }
            if (items1[i] == null || items2[i] == null || !items1[i].isSimilar(items2[i])) {
                return false;
            }
        }

        return true;
    }

    @EventHandler
    public void WorldsInventoryClickEvent(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        String lang = plugin.getConfig().getString("lang."+player.getName());
        Inventory worldsInventory = inventory.WorldsInventory(player);
        if (areInventoriesEqual(worldsInventory, e.getClickedInventory())) {
            e.setCancelled(true);
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem != null) {
                switch (clickedItem.getType()) {
                    case NETHERITE_SWORD:
                        player.sendMessage("pvp inventory");
                        break;
                    case GRASS_BLOCK:
                        player.sendMessage("pve inventory");
                        break;
                    case ENDER_EYE:
                        player.sendMessage("minigames inv");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}