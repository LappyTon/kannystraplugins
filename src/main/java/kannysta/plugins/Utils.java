package kannysta.plugins;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Utils {
    private final KannystraPluggins pluggins;
    public Utils(KannystraPluggins pluggins) {
        this.pluggins = pluggins;
    }
    public FileConfiguration getConfig() {
        return pluggins.getConfig();
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
    public String getLang(Player player) {
        return pluggins.getConfig().getString("lang."+player.getName());
    }
    public ItemStack createItem(Material material, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    public ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    public Inventory fullInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, "");
        inventory.setItem(0, createItem(Material.ORANGE_STAINED_GLASS_PANE, ""));
        return inventory;
    }

    public String messages(Player p, String string) {
        return pluggins.getConfig().getString("messages."+string+"."+getLang(p));
    }
    public String issue(String s) {
        StringBuilder string = new StringBuilder();
        string.append(ChatColor.RED+""+ChatColor.BOLD).append(" >>> ").append(s);
        return string.toString();
    }
    public String warning(String s) {
        StringBuilder string = new StringBuilder();
        string.append(ChatColor.YELLOW+""+ChatColor.BOLD).append("<!!!> ").append(s).append(" <!!!>");
        return string.toString();
    }
    public String succes(String s) {
        StringBuilder string = new StringBuilder();
        string.append(ChatColor.GREEN+""+ChatColor.BOLD).append(">>> ").append(s);
        return string.toString();
    }
    public String event(String s) {
        StringBuilder string = new StringBuilder();
        string.append(ChatColor.WHITE+""+ChatColor.BOLD).append("=== ").append(s).append(" ===");
        return string.toString();
    }

    public String info(String s) {
        StringBuilder string = new StringBuilder();
        String questionMark = ChatColor.BOLD+" "+ChatColor.WHITE+"<"+ChatColor.BLUE+"???"+ChatColor.WHITE+"> ";
        string.append(System.lineSeparator()).append(questionMark).append(s).append(questionMark).append(System.lineSeparator());
        return string.toString();
    }
    @SuppressWarnings("deprecation")
    public void showPlayers(Player p) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        players.forEach(e->{
            e.showPlayer(p);
        });
    }
    @SuppressWarnings("deprecation")
    public void hidePlayers(Player p) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        players.forEach(e->{
            e.hidePlayer(p);
        });
    }
}
