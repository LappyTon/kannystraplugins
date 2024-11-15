package kannysta.plugins.mainMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;

import java.util.Arrays;
import java.util.List;

public class MenuInventory {
    private final KannystraPluggins plugin;
    private final Utils utils;

    public MenuInventory(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.utils = new Utils(plugin);
    }



    public Inventory WorldsInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, "");
        String playerLang = plugin.getConfig().getString("lang." + player.getName());

        String pvpWorldsLore = plugin.getConfig().getString("messages.pvpWorldsLore." + playerLang);
        String pveWorldsLore = plugin.getConfig().getString("messages.pveWorldsLore." + playerLang);
        String minigamesWorldsLore = plugin.getConfig().getString("messages.minigamesWorldsLore." + playerLang);

        List<String> pvpLore = Arrays.asList(ChatColor.RED + pvpWorldsLore);
        List<String> pveLore = Arrays.asList(ChatColor.RED + pveWorldsLore);
        List<String> minigamesLore = Arrays.asList(ChatColor.RED + minigamesWorldsLore);


        inventory.setItem(11, utils.createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + utils.messages(player, "pvpFacts1.")));
        inventory.setItem(12, utils.createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + utils.messages(player, "pvpFacts2.")));
        inventory.setItem(13, utils.createItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + utils.messages(player, "pveFacts1.")));
        inventory.setItem(14, utils.createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + utils.messages(player, "minigamesFacts1.")));
        inventory.setItem(15, utils.createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + utils.messages(player, "minigamesFacts2.")));
        inventory.setItem(19, utils.createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + utils.messages(player, "pvpFacts3.")));
        inventory.setItem(20, utils.createItem(Material.NETHERITE_SWORD, ChatColor.RED + "" + ChatColor.BOLD + utils.messages(player, "pvpWorlds."), pvpLore));
        inventory.setItem(21, utils.createItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + utils.messages(player, "pveFacts2.")));
        inventory.setItem(22, utils.createItem(Material.GRASS_BLOCK, ChatColor.GREEN + "" + ChatColor.BOLD + utils.messages(player, "pveWorlds."), pveLore));
        inventory.setItem(23, utils.createItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + utils.messages(player, "pveFacts3.")));
        inventory.setItem(24, utils.createItem(Material.ENDER_EYE, ChatColor.YELLOW + "" + ChatColor.BOLD + utils.messages(player, "minigamesWorlds."), minigamesLore));
        inventory.setItem(25, utils.createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + utils.messages(player, "minigamesFacts3.")));
        inventory.setItem(29, utils.createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + utils.messages(player, "pvpFacts4.")));
        inventory.setItem(30, utils.createItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + utils.messages(player, "pvpFacts5.")));
        inventory.setItem(31, utils.createItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + utils.messages(player, "pveFacts4.")));
        inventory.setItem(32, utils.createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + utils.messages(player, "minigamesFacts4.")));
        inventory.setItem(33, utils.createItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + utils.messages(player, "minigamesFacts5.")));
        return inventory;
    }
}