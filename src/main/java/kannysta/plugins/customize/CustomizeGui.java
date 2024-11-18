package kannysta.plugins.customize;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
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

    public CustomizeGui(KannystraPluggins pluggins) {
        this.pluggins = pluggins;
        this.utils = new Utils(pluggins);
    }

    public Inventory customizeInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 54);
        inv.setItem(0, utils.createItem(Material.WRITABLE_BOOK, "chatConfig"));
        return inv;
    }

    public Inventory customizeChatInventory(Player p) {
        int version = Via.getAPI().getPlayerVersion(p.getUniqueId());

        List<String> disabled = Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + utils.messages(p, "disabled"));
        List<String> enabled = Arrays.asList(ChatColor.GREEN + "" + ChatColor.BOLD + utils.messages(p, "enabled"));

        Inventory inv = Bukkit.createInventory(p, 54);

        // Monochrome
        ItemStack monochrome;
        if (pluggins.getConfig().getBoolean("customize.chat.monochrome")) {
            monochrome = utils.createItem(Material.INK_SAC, "monochrome", enabled);
            if (version > 400) {
                monochrome.addEnchantment(Enchantment.MENDING, 1);
            }
        } else {
            monochrome = utils.createItem(Material.INK_SAC, "monochrome", disabled);
        }

        // Newbie Tips
        ItemStack newbieTips;
        if (pluggins.getConfig().getBoolean("customize.chat.newbieTips")) {
            newbieTips = utils.createItem(Material.BELL, "newbieTips", enabled);
            if (version > 400) {
                newbieTips.addEnchantment(Enchantment.MENDING, 1);
            }
        } else {
            newbieTips = utils.createItem(Material.BELL, "newbieTips", disabled);
        }

        // Language
        ItemStack language;
        switch (pluggins.getConfig().getString("customize.chat.language")) {
            case "ua":
                language = utils.createItem(Material.BLUE_BANNER, "chatLang_ua");
                BannerMeta uaBannerMeta = (BannerMeta) language.getItemMeta();
                uaBannerMeta.addPattern(new Pattern(DyeColor.YELLOW, PatternType.HALF_HORIZONTAL_BOTTOM));
                language.setItemMeta(uaBannerMeta);
                break;
            case "ru":
                language = utils.createItem(Material.BLUE_BANNER, "chatLang_ru");
                BannerMeta ruBannerMeta = (BannerMeta) language.getItemMeta();
                ruBannerMeta.addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_BOTTOM));
                ruBannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
                language.setItemMeta(ruBannerMeta);
                break;
            default:
                language = utils.createItem(Material.RED_BANNER, "chatLang_eng");
                BannerMeta bannerMeta = (BannerMeta) language.getItemMeta();
                bannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.SMALL_STRIPES));
                bannerMeta.addPattern(new Pattern(DyeColor.BLUE, PatternType.SQUARE_TOP_LEFT));
                language.setItemMeta(bannerMeta);
                break;
        }

        // Global Chat
        ItemStack globalChat;
        switch (pluggins.getConfig().getString("customize.chat.globalchat")) {
            case "global":
                globalChat = utils.createItem(Material.ENDER_PEARL, "globalChat");
                break;
            case "clan":
                globalChat = utils.createItem(Material.ENDER_EYE, "clanChat");
                break;
            case "none":
                globalChat = utils.createItem(Material.BARRIER, "noChat");
                break;
            default:
                globalChat = utils.createItem(Material.MAP, "worldChat");
                break;
        }

        // Set Items
        inv.setItem(0, monochrome);
        inv.setItem(1, utils.createItem(Material.FEATHER, "playersPrefix"));
        inv.setItem(2, newbieTips);
        inv.setItem(3, language);
        inv.setItem(4, globalChat);

        return inv;
    }
}
