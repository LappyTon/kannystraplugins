package kannysta.plugins.worlds;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;
import kannysta.plugins.mainMenu.MenuInventory;

public class WorldsInventoryListener implements Listener {
    private final KannystraPluggins plugin;
    private MenuInventory inventory;
    private Utils utils;
    private PvpWorlds pvpWorlds;
    public WorldsInventoryListener(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.inventory = new MenuInventory(plugin);
        this.utils = new Utils(plugin);
        this.pvpWorlds = new PvpWorlds(plugin);
    }



    @EventHandler
    public void WorldsInventoryClickEvent(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Inventory worldsInventory = inventory.WorldsInventory(player);
        if (utils.areInventoriesEqual(worldsInventory, e.getClickedInventory())) {
            e.setCancelled(true);
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem != null) {
                switch (clickedItem.getType()) {
                    case NETHERITE_SWORD:
                        player.openInventory(pvpWorlds.pvpWorldsInventory(player));
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