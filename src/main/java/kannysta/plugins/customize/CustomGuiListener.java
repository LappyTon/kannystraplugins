package kannysta.plugins.customize;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.comphenix.protocol.PacketType.Play;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;

public class CustomGuiListener implements Listener {
    private final KannystraPluggins plugin;
    private final Utils utils;
    private final CustomizeGui customizeGui;
    private final FileConfiguration config;

    public CustomGuiListener(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.utils = new Utils(plugin);
        this.customizeGui = new CustomizeGui(plugin);
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onMainCustomInv(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;

        Inventory mainInventory = customizeGui.customizeInventory(player);

        if (utils.areInventoriesEqual(mainInventory, clickedInventory)) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null) return;

            switch (clickedItem.getType()) {
                case WRITABLE_BOOK -> player.openInventory(customizeGui.customizeChatInventory(player));
                case MAP -> player.openInventory(customizeGui.customizeTabInventory(player));
                default -> {} 
            }
        }
    }

    @EventHandler
    public void onChatCustomInv(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;

        Inventory chatInventory = customizeGui.customizeChatInventory(player);

        if (utils.areInventoriesEqual(chatInventory, clickedInventory)) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null) return;

            handleChatInventoryClick(player, clickedItem, chatInventory);
        }
    }

    @EventHandler
    public void onPrefixCustomInv(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;

        Inventory prefixInventory = customizeGui.prefixesInventory(player);

        if (utils.areInventoriesEqual(prefixInventory, clickedInventory)) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null) return;

            handlePrefixInventoryClick(player, clickedItem, prefixInventory);
        }
    }
    @EventHandler
    public void onChatLangInv(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) return;
        Inventory chatLangInventory = customizeGui.chatLanguageInventory(p);

        if (utils.areInventoriesEqual(chatLangInventory, e.getClickedInventory())) {
            e.setCancelled(true);

            ItemStack item = e.getCurrentItem();
            if (item == null) return;

            handleChatLangInv(p, item, chatLangInventory);
        }
    }

    @EventHandler
    public void onTabCustomInv(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) return;
        if (utils.areInventoriesEqual(e.getClickedInventory(), customizeGui.customizeTabInventory(p))) {
            e.setCancelled(true);
            ItemStack item = e.getCurrentItem();
            if (item == null) return;

            switch (item.getType()) {
                case BELL:
                    p.openInventory(customizeGui.customizeTab(p, "lobby"));
                    break;
                case IRON_AXE:
                    p.openInventory(customizeGui.customizeTab(p, "pvpWorld"));
                    break;
                case ENDER_PEARL:
                    p.openInventory(customizeGui.customizeTab(p, "minigamesWorld"));
                    break;
                case PAINTING:
                    break;
                case DIAMOND_PICKAXE:
                    p.openInventory(customizeGui.customizeTab(p, "pveWorld"));
                    break;
                case DIAMOND_SWORD:
                    p.openInventory(customizeGui.customizeTab(p, "pvp"));
                    break;
                case ENDER_EYE:
                    p.openInventory(customizeGui.customizeTab(p, "minigame"));
                    break;
                case YELLOW_DYE:
                    break;
                default:
                    break;
            }
        }
    }
    @EventHandler
    public void customizeTabListener(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p) {
            Inventory clickedInventory = e.getClickedInventory();
            for (String link : new String[]{"lobby","pvp", "pvpWorld", "lobby", "pveWorlds", "minigame", "minigamesWorld"}) {
                if (utils.areInventoriesEqual(clickedInventory, customizeGui.customizeTab(p, link))) {
                    p.sendMessage("2");
                    switch (e.getSlot()) {
                        case 28 -> toggleTabCustom(p, link, "clanPlayersOnline");
                        case 19 -> toggleTabCustom(p, link, "playerSorting");
                        case 40 -> toggleTabCustom(p, link, "balance");
                        case 41 -> toggleTabCustom(p, link, "killStreak");
                        case 13 -> toggleTabCustom(p, link, "name");
                        case 39 -> toggleTabCustom(p, link, "ahItems");
                        case 14 -> toggleTabCustom(p, link, "worldName");
                        case 25 -> toggleTabCustom(p, link, "nextEvent");
                        case 34 -> toggleTabCustom(p, link, "clanPlayersOnline");
                        case 12 -> toggleTabCustom(p, link, "clanPlayersOnline");
                        default -> {}
                    } 
                    e.setCancelled(true);
                    break;
                } else {
                    p.sendMessage("1");
                }
            }
        }
    }

    private void toggleTabCustom(Player p, String link, String confLink) {
        config.set("customizeTab_"+link+"."+confLink+"."+p.getUniqueId(), !(config.getBoolean("customizeTab_"+link+"."+confLink+"."+p.getUniqueId(), false)));
    }
    private void handleChatInventoryClick(Player p, ItemStack clickedItem, Inventory chatInventory) {
        if (clickedItem.isSimilar(chatInventory.getItem(20))) {
            toggleConfigBoolean("customize.chat.monochrome." + p.getUniqueId());
            p.openInventory(customizeGui.customizeChatInventory(p));
        } else if (clickedItem.isSimilar(chatInventory.getItem(21))) {
            p.openInventory(customizeGui.prefixesInventory(p));
        } else if (clickedItem.isSimilar(chatInventory.getItem(22))) {
            toggleConfigBoolean("customize.chat.newbieTips." + p.getUniqueId());
            p.openInventory(customizeGui.customizeChatInventory(p));
        } else if (clickedItem.isSimilar(chatInventory.getItem(23))) {
            p.openInventory(customizeGui.chatLanguageInventory(p));
        } else if (clickedItem.isSimilar(chatInventory.getItem(24))) {
            switch (clickedItem.getType()) {
                case ENDER_PEARL:
                    chatToggle(p, "clan");
                    break;
                case ENDER_EYE:
                    chatToggle(p, "none");
                    break;
                case BARRIER:
                    chatToggle(p, "local");
                    break;
                case MAP:
                    chatToggle(p, "global");
                default:
                    break;
            }
        }
    }
    private void chatToggle(Player p, String value) {
        config.set("customize.chat.globalchat."+p.getUniqueId(), value);
        plugin.saveConfig(); plugin.saveDefaultConfig();
        p.openInventory(customizeGui.customizeChatInventory(p));
    }

    private void handlePrefixInventoryClick(Player player, ItemStack clickedItem, Inventory prefixInventory) {
        String prefix = null;

        if (clickedItem.isSimilar(prefixInventory.getItem(19))) prefix = "none";
        else if (clickedItem.isSimilar(prefixInventory.getItem(20))) prefix = "kills";
        else if (clickedItem.isSimilar(prefixInventory.getItem(21))) prefix = "kd";
        else if (clickedItem.isSimilar(prefixInventory.getItem(22))) prefix = "playtime";
        else if (clickedItem.isSimilar(prefixInventory.getItem(23))) prefix = "money";
        else if (clickedItem.isSimilar(prefixInventory.getItem(24))) prefix = "claimedMoney";
        else if (clickedItem.isSimilar(prefixInventory.getItem(25))) prefix = "clan";

        if (prefix != null) {
            changePrefix(player, prefix);
        }
    }

    private void handleChatLangInv(Player p, ItemStack clickedItem, Inventory inv) {
        if (clickedItem.isSimilar(inv.getItem(20))) {
            changeLang(p, "eng");
        } else if (clickedItem.isSimilar(inv.getItem(22))) {
            changeLang(p, "ru");
        } else if (clickedItem.isSimilar(inv.getItem(24))) {
            changeLang(p, "ua");
        }
    }

    private void toggleConfigBoolean(String path) {
        boolean currentValue = config.getBoolean(path, false);
        config.set(path, !currentValue);
    }

    private void changePrefix(Player player, String prefix) {
        config.set("customize.chat.prefix." + player.getUniqueId(), prefix);
        player.openInventory(customizeGui.prefixesInventory(player));
        plugin.saveConfig(); plugin.saveDefaultConfig();
    }
    private void changeLang(Player p, String lang) {
        config.set("customize.chat.language." + p.getUniqueId(), lang);
        p.openInventory(customizeGui.chatLanguageInventory(p));
        plugin.saveConfig(); plugin.saveDefaultConfig();
    } 
    
}
