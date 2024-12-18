package kannysta.plugins.worlds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.viaversion.viaversion.api.Via;
import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;
import kannysta.plugins.logics.RtpLogic;
import kannysta.plugins.mainMenu.MenuInventory;
import net.md_5.bungee.api.ChatColor;

public class PvpWorlds implements Listener {
    private final Utils utils;
    private final RtpLogic rtpLogic;
    private final MenuInventory menuInventory;
    private final KannystraPluggins pluggins;

    public PvpWorlds(KannystraPluggins pluggins) {
        this.utils = new Utils(pluggins);
        this.rtpLogic = new RtpLogic(pluggins);
        this.pluggins = pluggins;
        this.menuInventory = new MenuInventory(pluggins);
    }

    @EventHandler
    public void onPvpWorldsInventory(InventoryClickEvent e) {
        if (utils.areInventoriesEqual(e.getClickedInventory(), pvpWorldsInventory((Player) e.getWhoClicked()))) {
            Player player = (Player) e.getWhoClicked();
            e.setCancelled(true);

            int version = Via.getAPI().getPlayerVersion(player.getUniqueId());

            switch (e.getSlot()) {
                case 19:
                    if (version == 47) {
                        rtpLogic.teleportPlayer(player, 200, Bukkit.getWorld("soupPvp"));
                    } else { badVersion(player); }
                    break;
                case 20:
                    if (version == 340) {
                        rtpLogic.teleportPlayer(player, 200, Bukkit.getWorld("beastPvp"));
                    } else { badVersion(player); }
                    break;
                case 21:
                    if (version == 1101) {
                        rtpLogic.teleportPlayer(player, 200, Bukkit.getWorld("smpPvp"));
                    } else { badVersion(player); }
                    break;
                case 22:
                    if (version == 340) {
                        rtpLogic.teleportPlayer(player, 200, Bukkit.getWorld("crystalPvp"));
                    } else { badVersion(player); }
                    break;
                case 28:
                    if (version == 47) {
                        rtpLogic.teleportPlayer(player, 200, Bukkit.getWorld("noSoupPvp"));
                    } else { badVersion(player); }
                    break;
                case 29:
                    if (version == 340) {
                        rtpLogic.teleportPlayer(player, 200, Bukkit.getWorld("diamondPvp"));
                    } else { badVersion(player); }
                    break;
                case 30:
                    player.performCommand("netherpvp");
                    break;
                case 31:
                    if (version == 1101) {
                        rtpLogic.teleportPlayer(player, 200, Bukkit.getWorld("anchorPvp"));
                    } else { badVersion(player); }
                    break;
                default:
                    break;
            }
        }
    }

    public void badVersion(Player player) {
        
        int version = Via.getAPI().getPlayerVersion(player.getUniqueId());
        player.sendMessage(utils.issue(utils.messages(player, "badVersion")+" ("+version+")"));
    }

    public Inventory pvpWorldsInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, "");
        int version = Via.getAPI().getPlayerVersion(player.getUniqueId());
        Material recoveryCompass;
        Material respawnAnchor;
        Material netheriteSword;
        Material shield;
        if (version<759) {
            recoveryCompass = Material.COMPASS;
        } else { recoveryCompass = Material.RECOVERY_COMPASS;}
        if (version<736) {
            netheriteSword = Material.STONE_SWORD;
            respawnAnchor = Material.GLOWSTONE;
        } else { netheriteSword = Material.NETHERITE_SWORD; respawnAnchor = Material.RESPAWN_ANCHOR; }
        if (version<107) {
            shield = Material.COBBLESTONE;
        } else { shield = Material.SHIELD; }
        ItemStack soupPvp = utils.createItem(Material.MUSHROOM_STEW, ChatColor.BOLD + utils.messages(player, "soupPvp"));
        ItemStack beastPvp = utils.createItem(Material.DIAMOND_SWORD, ChatColor.BOLD + utils.messages(player, "beastPvp"));
        ItemStack smpPvp = utils.createItem(shield, ChatColor.BOLD + utils.messages(player, "smpPvp"));
        ItemStack crystalPvp = utils.createItem(Material.END_CRYSTAL, ChatColor.BOLD + utils.messages(player, "crystalPvp"));
        ItemStack customDuel = utils.createItem(recoveryCompass, ChatColor.BOLD + utils.messages(player, "customDuel"));

        ItemStack noSoupPvp = utils.createItem(Material.SPLASH_POTION, ChatColor.BOLD + utils.messages(player, "noSoupPvp"));
        ItemStack diamondPvp = utils.createItem(Material.ARROW, ChatColor.BOLD + utils.messages(player, "diamondPvp"));
        ItemStack netheritePvp = utils.createItem(netheriteSword, ChatColor.BOLD + utils.messages(player, "netherPvp"));
        ItemStack anchorPvp = utils.createItem(respawnAnchor, ChatColor.BOLD + utils.messages(player, "anchorPvp"));
        ItemStack classicDuel = utils.createItem(Material.COMPASS, ChatColor.BOLD + utils.messages(player, "classicDuel"));

        if (version>400) {
            if (version == 47) {
                soupPvp.addEnchantment(Enchantment.MENDING, 1);
                noSoupPvp.addEnchantment(Enchantment.MENDING, 1);
            }
            if (version == 340) {
                beastPvp.addEnchantment(Enchantment.MENDING, 1);
                crystalPvp.addEnchantment(Enchantment.MENDING, 1);
                diamondPvp.addEnchantment(Enchantment.MENDING, 1);
            }
            if (version >= 754) {
                netheritePvp.addEnchantment(Enchantment.MENDING, 1);
            }
            if (version == 1101) {
                smpPvp.addEnchantment(Enchantment.MENDING, 1);
                anchorPvp.addEnchantment(Enchantment.MENDING, 1);
            }
        }

        inventory.setItem(19, soupPvp);
        inventory.setItem(20, beastPvp);
        inventory.setItem(21, smpPvp);
        inventory.setItem(22, crystalPvp);
        inventory.setItem(25, customDuel);

        inventory.setItem(28, noSoupPvp);
        inventory.setItem(29, diamondPvp);
        inventory.setItem(30, netheritePvp);
        inventory.setItem(31, anchorPvp);
        inventory.setItem(34, classicDuel);

        return inventory;
    }
    @EventHandler
    public void onPvpWorldsClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        Inventory pvpInventory = pvpWorldsInventory(p);
        if (utils.areInventoriesEqual(pvpInventory, e.getInventory())) {
            Bukkit.getScheduler().runTask(pluggins, () -> p.openInventory(menuInventory.WorldsInventory(p)));
        }
    }
}
