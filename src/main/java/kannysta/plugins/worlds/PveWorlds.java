package kannysta.plugins.worlds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.viaversion.viaversion.api.Via;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;
import kannysta.plugins.logics.RtpLogic;
import net.md_5.bungee.api.ChatColor;

public class PveWorlds implements Listener {
    private Utils utils;
    private RtpLogic rtpLogic;
    private FileConfiguration config;
    public PveWorlds(KannystraPluggins pluggins) {
        this.utils = new Utils(pluggins);
        this.config = pluggins.getConfig();
        this.rtpLogic = new RtpLogic(pluggins);
    }
    
    @EventHandler
    public void onPveWorldsInv(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p) {
            int version = Via.getAPI().getPlayerVersion(p.getUniqueId());
            if (utils.areInventoriesEqual(e.getClickedInventory(), pveWorldsInv(p))) {
                e.setCancelled(true);
                switch (e.getSlot()) {
                    case 12 -> {
                        if (version>=757) {
                            if (Bukkit.getWorld("plantsPve")==null) return;
                            rtpLogic.teleportPlayer(p, 30000, Bukkit.getWorld("plantsPve"));
                        } else {badVersion(p);}
                    }
                    case 13 -> {
                        if (version>=757) {
                            if (Bukkit.getWorld("redstonePve")==null) return;
                            rtpLogic.teleportPlayer(p, 30000, Bukkit.getWorld("redstonePve"));
                        } else {badVersion(p);}
                    }
                    case 14 -> {
                        if (version>=763) {
                            if (Bukkit.getWorld("buildPve")==null) return;
                            rtpLogic.teleportPlayer(p, 200, Bukkit.getWorld("buildPve"));
                        } else {badVersion(p);}
                    }
                }
            }
        }
    }
    
    public void badVersion(Player player) {
        
        int version = Via.getAPI().getPlayerVersion(player.getUniqueId());
        player.sendMessage(utils.issue(utils.messages(player, "badVersion")+" (Your version: "+version+")"));
    }

    public Inventory pveWorldsInv(Player p) {
        int version = Via.getAPI().getPlayerVersion(p.getUniqueId());
        Inventory inv = Bukkit.createInventory(p, 27, "");

        ItemStack badVersion = utils.createItemCustom(p, Material.BARRIER, "pveWorldsItem.", Arrays.asList(ChatColor.RED+utils.messages(p, "badVersion")));

        inv.setItem(12, (version>757) ? createPveWorldsItemStack(p, "plants", Material.WHEAT_SEEDS) :
            utils.createItemCustom(p, Material.BARRIER, "pveWorldsItem.", Arrays.asList(ChatColor.RED+utils.messages(p, "badVersion"))));
        inv.setItem(13, (version>757) ? createPveWorldsItemStack(p, "redstone", Material.WHEAT_SEEDS) :
        utils.createItemCustom(p, Material.BARRIER, "pveWorldsItem.", Arrays.asList(ChatColor.RED+utils.messages(p, "badVersion"))));
        inv.setItem(14, (version>757) ? createPveWorldsItemStack(p, "redstone", Material.WHEAT_SEEDS) :
        utils.createItemCustom(p, Material.BARRIER, "pveWorldsItem.", Arrays.asList(ChatColor.RED+utils.messages(p, "badVersion"))));
        
        inv.setItem(13, createPveWorldsItemStack(p, "redstone", Material.REDSTONE));
        inv.setItem(14, createPveWorldsItemStack(p, "build", Material.IRON_PICKAXE));
        return inv;
    }

    private ItemStack createPveWorldsItemStack(Player p, String nameLink, Material material) {
        String[] modifiersArray = utils.messages(p, "pveWorldsItem." + nameLink + ".modifiersList").split(">");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BOLD + "" + ChatColor.RED + utils.messages(p, "pveWorldsItem." + nameLink + ".version"));
        lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + utils.messages(p, "pveWorldsItem.modifiers"));
        for (String modifier : modifiersArray) { lore.add(ChatColor.WHITE + modifier.trim()); }
        return utils.createItemCustom(p, material, "pveWorldsItem." + nameLink, lore);
    }
    
}
