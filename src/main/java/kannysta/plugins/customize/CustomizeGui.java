package kannysta.plugins.customize;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import com.viaversion.viaversion.api.Via;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;
import net.md_5.bungee.api.ChatColor;

public class CustomizeGui {
    private final KannystraPluggins pluggins;
    private final Utils utils;
    private FileConfiguration config;


    public CustomizeGui(KannystraPluggins pluggins) {
        this.pluggins = pluggins;
        this.utils = new Utils(pluggins);
        this.config = pluggins.getConfig();
    }

    public Inventory customizeInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 54, " ");
        inv.setItem(0, utils.createItem(Material.WRITABLE_BOOK, ChatColor.BOLD+utils.messages(p, "chatConfig")));
        inv.setItem(1, utils.createItem(Material.MAP, ChatColor.BOLD+utils.messages(p, "tabConfig")));
        return inv;
    }

    public Inventory customizeChatInventory(Player p) {
        int version = Via.getAPI().getPlayerVersion(p.getUniqueId());
        Inventory inv = Bukkit.createInventory(p, 54, " ");


        List<String> disabled = Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + utils.messages(p, "disabled"));
        List<String> enabled = Arrays.asList(ChatColor.GREEN + "" + ChatColor.BOLD + utils.messages(p, "enabled"));
        // Monochrome
        ItemStack monochrome;
        if (config.getBoolean("customize.chat.monochrome."+p.getUniqueId(), false)) {
            monochrome = utils.createItem(Material.LIGHT_GRAY_CONCRETE_POWDER, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "monochrome"), enabled);
        } else {
            monochrome = utils.createItem(Material.LIGHT_GRAY_CONCRETE_POWDER, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "monochrome"), disabled);
        }

        // Newbie Tips
        ItemStack newbieTips;
        if (config.getBoolean("customize.chat.newbieTips."+p.getUniqueId(), false)) {
            newbieTips = utils.createItem(Material.BELL, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "newbieTips"), enabled);
        } else {
            newbieTips = utils.createItem(Material.BELL, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "newbieTips"), disabled);
        }

        // Language
        ItemStack language;
        String chatLang = config.getString("customize.chat.language."+p.getUniqueId());
        if (chatLang == null) { chatLang=""; }
        switch (chatLang) {
            case "ua":
                language = utils.createItem(Material.BLUE_BANNER, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p,"chatLang_ua"));
                BannerMeta uaBannerMeta = (BannerMeta) language.getItemMeta();
                uaBannerMeta.addPattern(new Pattern(DyeColor.YELLOW, PatternType.HALF_HORIZONTAL_BOTTOM));
                language.setItemMeta(uaBannerMeta);
                break;
            case "ru":
                language = utils.createItem(Material.BLUE_BANNER, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "chatLang_ru"));
                BannerMeta ruBannerMeta = (BannerMeta) language.getItemMeta();
                ruBannerMeta.addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_BOTTOM));
                ruBannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
                language.setItemMeta(ruBannerMeta);
                break;
            default:
                language = utils.createItem(Material.RED_BANNER, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "chatLang_eng"));
                BannerMeta bannerMeta = (BannerMeta) language.getItemMeta();
                bannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.SMALL_STRIPES));
                bannerMeta.addPattern(new Pattern(DyeColor.BLUE, PatternType.SQUARE_TOP_LEFT));
                language.setItemMeta(bannerMeta);
                break;
        }

        // Global Chat
        ItemStack globalChat;
        String globalChatConfig = config.getString("customize.chat.globalchat."+p.getUniqueId());
        if (globalChatConfig==null) { globalChatConfig = ""; }
        switch (globalChatConfig) {
            case "global":
                globalChat = utils.createItem(Material.ENDER_PEARL, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "globalChat"));
                break;
            case "clan":
                globalChat = utils.createItem(Material.ENDER_EYE, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "clanChat"));
                break;
            case "none":
                globalChat = utils.createItem(Material.BARRIER, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "noChat"));
                break;
            default:
                globalChat = utils.createItem(Material.MAP, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "worldChat"));
                break;
        }

        // Set Items
        inv.setItem(20, monochrome);
        inv.setItem(21, utils.createItem(Material.FEATHER, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "playersPrefix")));
        inv.setItem(22, newbieTips);
        inv.setItem(23, language);
        inv.setItem(24, globalChat);

        return inv;
    }
    public Inventory prefixesInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 45, " ");
        ItemStack none = prefixItemstack(p, "none", "", Material.BARRIER, ChatColor.RED);
        ItemStack kd = prefixItemstack(p, "kd", ChatColor.translateAlternateColorCodes('&', "&414&8/&c89&f | "), Material.DIAMOND_AXE, ChatColor.WHITE);
        ItemStack kills = prefixItemstack(p, "kills", ChatColor.translateAlternateColorCodes('&', "&4☠18&f | "), Material.DIAMOND_SWORD, ChatColor.WHITE);
        ItemStack playtime = prefixItemstack(p, "playtime", ChatColor.translateAlternateColorCodes('&', "&714h &f| "), Material.CLOCK, ChatColor.WHITE);
        ItemStack money = prefixItemstack(p, "money", ChatColor.translateAlternateColorCodes('&', "&c12k &f| "), Material.SUNFLOWER, ChatColor.WHITE);
        ItemStack claimedMoney = prefixItemstack(p, "claimedMoney", ChatColor.translateAlternateColorCodes('&', "&c21.1kk &f| "), Material.ROSE_BUSH, ChatColor.WHITE);
        ItemStack clan = prefixItemstack(p, "clan", ChatColor.translateAlternateColorCodes('&', "&8<&9PvE&8> &f| "), Material.BELL, ChatColor.WHITE);



        inv.setItem(19, none);
        inv.setItem(20, kills);
        inv.setItem(21, kd);
        inv.setItem(22, playtime);
        inv.setItem(23, money);
        inv.setItem(24, claimedMoney);
        inv.setItem(25, clan);
        return inv;
    }
    private ItemStack prefixItemstack(Player p, String link, String prefix, Material material, ChatColor color) {
        ItemStack item;
        String currentPrefix = config.getString("customize.chat.prefix."+p.getUniqueId());
        String exclusion="";
        if (link=="kills") {
            exclusion = exclusion+utils.messages(p, "prefix_kills2Lore");
        }
        if (material==Material.BARRIER) {
            if (currentPrefix==""||currentPrefix==null) {
                currentPrefix = link;
            }
        }
        if (currentPrefix==link) {
            item = utils.createItem(material, ChatColor.GREEN+utils.messages(p, "enabled"),
            Arrays.asList(
            "",
            ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "prefix_"+link),
            ChatColor.GRAY+utils.messages(p, "prefix_"+link+"Lore"),
            ChatColor.GRAY+exclusion,
            ChatColor.translateAlternateColorCodes('&', prefix+"&3PlayerName:&f test"))); 
        } else {
            item = utils.createItem(material, ChatColor.RED+utils.messages(p, "disabled"),
            Arrays.asList(
            "",
            ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "prefix_"+link),
            ChatColor.GRAY+utils.messages(p, "prefix_"+link+"Lore"),
            ChatColor.GRAY+exclusion,
            ChatColor.translateAlternateColorCodes('&', prefix+"&3PlayerName:&f test"))); 
        }
        return item;
    }

    public Inventory chatLanguageInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 45, " ");
        String currentLang = config.getString("customize.chat.language."+p.getUniqueId(), "");
        String DISABLED = ChatColor.RED+utils.messages(p, "disabled");
        String ENABLED = ChatColor.GREEN+utils.messages(p, "enabled");
        String uaString = DISABLED;
        String ruString = DISABLED;
        String engString = DISABLED;
        switch (currentLang) {
            case "ua":
                uaString = ENABLED;
                break;
            case "ru":
                ruString = ENABLED;
                break;
            default:
                engString = ENABLED;
                break;
        }

        ItemStack ua = utils.createItem(Material.BLUE_BANNER, uaString);
        ItemStack ru = utils.createItem(Material.BLUE_BANNER, ruString);
        ItemStack eng = utils.createItem(Material.RED_BANNER, engString);
        BannerMeta uaBannerMeta = (BannerMeta) ua.getItemMeta();
        BannerMeta ruBannerMeta = (BannerMeta) ru.getItemMeta();
        BannerMeta engBannerMeta = (BannerMeta) eng.getItemMeta();
        uaBannerMeta.addPattern(new Pattern(DyeColor.YELLOW, PatternType.HALF_HORIZONTAL_BOTTOM));
        ruBannerMeta.addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_BOTTOM));
        ruBannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
        engBannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.SMALL_STRIPES));
        engBannerMeta.addPattern(new Pattern(DyeColor.BLUE, PatternType.SQUARE_TOP_LEFT));


        uaBannerMeta.setLore(Arrays.asList(ChatColor.BOLD+ChatColor.translateAlternateColorCodes('&', utils.messages(p,"chatLangChange_ua"))));
        ruBannerMeta.setLore(Arrays.asList(ChatColor.BOLD+ChatColor.translateAlternateColorCodes('&', utils.messages(p,"chatLangChange_ru"))));
        engBannerMeta.setLore(Arrays.asList(ChatColor.BOLD+ChatColor.translateAlternateColorCodes('&', utils.messages(p,"chatLangChange_eng"))));

        ua.setItemMeta(uaBannerMeta);
        ru.setItemMeta(ruBannerMeta);
        eng.setItemMeta(engBannerMeta);


        inv.setItem(20, eng);
        inv.setItem(22, ru);
        inv.setItem(24, ua);
        return inv;
    }


    public Inventory customizeTabInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 54);

        inv.setItem(20, utils.createItem(Material.BELL, ChatColor.WHITE+utils.messages(p, "tabItem.lobby")));        
        inv.setItem(29, utils.createItem(Material.DIAMOND_PICKAXE, ChatColor.GREEN+utils.messages(p, "tabItem.pveWorld")));        
        inv.setItem(21, utils.createItem(Material.IRON_AXE, ChatColor.RED+utils.messages(p, "tabItem.pvpWorld")));        
        inv.setItem(30, utils.createItem(Material.DIAMOND_SWORD, ChatColor.DARK_RED+utils.messages(p, "tabItem.pvp")));        
        inv.setItem(22, utils.createItem(Material.ENDER_PEARL, ChatColor.GREEN+utils.messages(p, "tabItem.minigamesWorld")));        
        inv.setItem(31, utils.createItem(Material.ENDER_EYE, ChatColor.DARK_GREEN+utils.messages(p, "tabItem.minigame")));    
        inv.setItem(24, utils.createItem(Material.PAINTING, ChatColor.WHITE+utils.messages(p, "tabItem.style")));
        inv.setItem(33, utils.createItem(Material.YELLOW_DYE, ChatColor.WHITE+utils.messages(p, "tabItem.colors")));
        return inv;
    }

    public Inventory customizeTab(Player p, String link) {
        Inventory inv = Bukkit.createInventory(p, 54);
        String tabLink = "customizeTab_" + link;
    
        addTabItem(inv, 28, Material.BELL, "tabItem.clanPlayersOnline", p, "clanPlayersOnline");
        addTabItem(inv, 19, Material.COMPARATOR, "tabItem.playerSorting", p, "playerSorting");
        addTabItem(inv, 40, Material.SUNFLOWER, "tabItem.balance", p, "balance");
        addTabItem(inv, 41, Material.DIAMOND_AXE, "tabItem.killStreak", p, "killStreak");
        addTabItem(inv, 13, Material.NAME_TAG, "tabItem.name", p, "name");
        addTabItem(inv, 39, Material.AMETHYST_CLUSTER, "tabItem.ahItems", p, "ahItems");
        addTabItem(inv, 14, Material.NAME_TAG, "tabItem.worldName", p, "worldName");
    
        if (link.equals("pvp") || link.equals("pvpWorld")) {
            addTabItem(inv, 25, Material.RECOVERY_COMPASS, utils.messages(p, "tabItem.nextEvent"), p, "nextEvent");
        } else inv.setItem(25, utils.createItem(Material.BARRIER, ChatColor.RED+utils.messages(p, "tabItem.nextEventInaccessible")));
    
        addTabItem(inv, 34, Material.CLOCK, "tabItem.nextRestart", p, "nextRestart");
    
        String quest = config.getString(tabLink + ".currentQuest", "");
        ItemStack currentQuest;
        switch (quest) {
            case "pve" -> currentQuest = utils.createItem(Material.WHEAT_SEEDS, ChatColor.GREEN + utils.messages(p, "currentQuest.pve"));
            case "minigames" -> currentQuest = utils.createItem(Material.ENDER_EYE, ChatColor.DARK_AQUA + utils.messages(p, "currentQuest.minigames"));
            case "clan" -> currentQuest = utils.createItem(Material.GOAT_HORN, ChatColor.YELLOW + utils.messages(p, "currentQuest.clan"));
            case "pvp" -> currentQuest = utils.createItem(Material.IRON_SWORD, ChatColor.DARK_RED + utils.messages(p, "currentQuest.pvp"));
            default /*none*/ -> currentQuest = utils.createItem(Material.BARRIER, ChatColor.RED + utils.messages(p, "currentQuest.none"));
        }
        inv.setItem(12, currentQuest);
    
        return inv;
    }
    
    private void addTabItem(Inventory inv, int slot, Material material, String link, Player p, String confLink) {
        
        List<String> disabled = Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + utils.messages(p, "disabled"));
        List<String> enabled = Arrays.asList(ChatColor.GREEN + "" + ChatColor.BOLD + utils.messages(p, "enabled"));

        if (config.getBoolean("customizeTab_"+link+"."+confLink+"."+p.getUniqueId())) {
            inv.setItem(slot, utils.createItem(material, ChatColor.RESET+""+ChatColor.WHITE+utils.messages(p, link), enabled));
        } else {
            inv.setItem(slot, utils.createItem(material, ChatColor.RESET+""+ChatColor.WHITE+utils.messages(p, link), disabled));
        }
    }
    
}
