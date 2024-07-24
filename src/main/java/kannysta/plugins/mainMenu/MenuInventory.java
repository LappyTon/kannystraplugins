package kannysta.plugins.mainMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import kannysta.plugins.KannystraPluggins;

import java.util.Arrays;
import java.util.List;

public class MenuInventory {
    private final KannystraPluggins plugin;

    public MenuInventory(KannystraPluggins plugin) {
        this.plugin = plugin;
    }

    private ItemStack createItem(Material material, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    private ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    public Inventory MainInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, "");
        inventory.setItem(0, createItem(Material.ORANGE_STAINED_GLASS_PANE, ""));
        return inventory;
    }

    public Inventory WorldsInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 45, "");
        String playerLang = plugin.getConfig().getString("lang." + player.getName());

        String pvpWorldsLore = plugin.getConfig().getString("messages.pvpWorldsLore." + playerLang);
        String pveWorldsLore = plugin.getConfig().getString("messages.pveWorldsLore." + playerLang);
        String minigamesWorldsLore = plugin.getConfig().getString("messages.minigamesWorldsLore." + playerLang);

        List<String> pvpLore = Arrays.asList(ChatColor.RED + pvpWorldsLore);
        List<String> pveLore = Arrays.asList(ChatColor.RED + pveWorldsLore);
        List<String> minigamesLore = Arrays.asList(ChatColor.RED + minigamesWorldsLore);

        inventory.setItem(11, createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + plugin.getConfig().getString("messages.pvpFacts1." + playerLang)));
        inventory.setItem(12, createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + plugin.getConfig().getString("messages.pvpFacts2." + playerLang)));
        inventory.setItem(13, createItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + plugin.getConfig().getString("messages.pveFacts1." + playerLang)));
        inventory.setItem(14, createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + plugin.getConfig().getString("messages.minigamesFacts1." + playerLang)));
        inventory.setItem(15, createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + plugin.getConfig().getString("messages.minigamesFacts2." + playerLang)));
        inventory.setItem(19, createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + plugin.getConfig().getString("messages.pvpFacts3." + playerLang)));
        inventory.setItem(20, createItem(Material.NETHERITE_SWORD, ChatColor.RED + "" + ChatColor.BOLD + plugin.getConfig().getString("messages.pvpWorlds." + playerLang), pvpLore));
        inventory.setItem(21, createItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + plugin.getConfig().getString("messages.pveFacts2." + playerLang)));
        inventory.setItem(22, createItem(Material.GRASS_BLOCK, ChatColor.GREEN + "" + ChatColor.BOLD + plugin.getConfig().getString("messages.pveWorlds." + playerLang), pveLore));
        inventory.setItem(23, createItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + plugin.getConfig().getString("messages.pveFacts3." + playerLang)));
        inventory.setItem(24, createItem(Material.ENDER_EYE, ChatColor.YELLOW + "" + ChatColor.BOLD + plugin.getConfig().getString("messages.minigamesWorlds." + playerLang), minigamesLore));
        inventory.setItem(25, createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + plugin.getConfig().getString("messages.minigamesFacts3." + playerLang)));
        inventory.setItem(29, createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + plugin.getConfig().getString("messages.pvpFacts4." + playerLang)));
        inventory.setItem(30, createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + plugin.getConfig().getString("messages.pvpFacts5." + playerLang)));
        inventory.setItem(31, createItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + plugin.getConfig().getString("messages.pveFacts4." + playerLang)));
        inventory.setItem(32, createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + plugin.getConfig().getString("messages.minigamesFacts4." + playerLang)));
        inventory.setItem(33, createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + plugin.getConfig().getString("messages.minigamesFacts5." + playerLang)));
        return inventory;
    }
}
