package kannysta.plugins.mainMenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MenuInventory {
    private ItemStack createItem(Material material, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private ItemStack createItem(Material material, String name, List<String> strings) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(strings);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public Inventory MainInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 52, "");
        inventory.setItem(0, createItem(Material.ORANGE_STAINED_GLASS_PANE, ""));
        return inventory;
    }
}
