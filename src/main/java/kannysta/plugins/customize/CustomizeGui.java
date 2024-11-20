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
        inv.setItem(0, monochrome);
        inv.setItem(1, utils.createItem(Material.FEATHER, ChatColor.BOLD+""+ChatColor.WHITE+utils.messages(p, "playersPrefix")));
        inv.setItem(2, newbieTips);
        inv.setItem(3, language);
        inv.setItem(4, globalChat);

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

        List<String> disabled = Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + utils.messages(p, "disabled"));
        List<String> enabled = Arrays.asList(ChatColor.GREEN + "" + ChatColor.BOLD + utils.messages(p, "enabled"));

        ItemStack ua = utils.createItem(Material.BLUE_BANNER, ChatColor.BOLD+ChatColor.translateAlternateColorCodes('&', utils.messages(p,"chatLangChange_ua")));
        ItemStack ru = utils.createItem(Material.BLUE_BANNER, ChatColor.BOLD+ChatColor.translateAlternateColorCodes('&', utils.messages(p,"chatLangChange_ru")));
        ItemStack eng = utils.createItem(Material.RED_BANNER, ChatColor.BOLD+ChatColor.translateAlternateColorCodes('&', utils.messages(p,"chatLangChange_eng")));
        BannerMeta uaBannerMeta = (BannerMeta) ua.getItemMeta();
        BannerMeta ruBannerMeta = (BannerMeta) ru.getItemMeta();
        BannerMeta engBannerMeta = (BannerMeta) eng.getItemMeta();
        uaBannerMeta.addPattern(new Pattern(DyeColor.YELLOW, PatternType.HALF_HORIZONTAL_BOTTOM));
        ruBannerMeta.addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_BOTTOM));
        ruBannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
        engBannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.SMALL_STRIPES));
        engBannerMeta.addPattern(new Pattern(DyeColor.BLUE, PatternType.SQUARE_TOP_LEFT));
        ua.setItemMeta(uaBannerMeta);
        ru.setItemMeta(ruBannerMeta);
        eng.setItemMeta(engBannerMeta);

        String currentLang = config.getString("customize.chat.language."+p.getUniqueId(), "");

        ua.getItemMeta().setLore(disabled);
        ru.getItemMeta().setLore(disabled);
        eng.getItemMeta().setLore(disabled);
        switch (currentLang) {
            case "ua":
                ua.getItemMeta().setLore(enabled);
                break;
            case "ru":
                ru.getItemMeta().setLore(enabled);
                break;
            case "eng":
                eng.getItemMeta().setLore(enabled);
                break;
            default:
                break;
        }
        
        inv.setItem(0, ua);
        inv.setItem(1, ru);
        inv.setItem(2, eng);
        
        return inv;
    }
}
