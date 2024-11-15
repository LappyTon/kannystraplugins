package kannysta.plugins.logics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class RtpLogic {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private Utils utils;

    public RtpLogic(KannystraPluggins pluggins) {
        this.utils = new Utils(pluggins);
    }

    
    public void teleportPlayer(Player player, int length, World world) {
        UUID playerId = player.getUniqueId();
        long currentTimeMillis = System.currentTimeMillis();
        
        long cooldownTimeMillis;
        if (utils.getConfig().contains("cooldowns.rtp."+player.getUniqueId())) {
            cooldownTimeMillis = utils.getConfig().getLong("cooldown.rtp."+player.getUniqueId());
        } else {
            cooldownTimeMillis = 30000;
        }

        if (cooldowns.containsKey(playerId)) {
            long lastUsedMillis = cooldowns.get(playerId);
            if (currentTimeMillis - lastUsedMillis < cooldownTimeMillis) {
                long timeLeftMillis = cooldownTimeMillis - (currentTimeMillis - lastUsedMillis);
                long timeLeftSeconds = timeLeftMillis / 1000;
                player.sendMessage(utils.issue(utils.messages(player, "rtpCooldown")+timeLeftSeconds+"sec"));
                return;
            }
        }

        Location rtpLocation = rLocation(player, world, length);
        int tryes = 0;
        while (rtpLocation.getBlock().getType()==Material.LAVA||rtpLocation.getBlock().getType()==Material.WATER) {
            if (tryes<10) {
                rtpLocation = rLocation(player, world, length);
                tryes++;
            } else {
                player.sendMessage(utils.issue(utils.messages(player, "noRtpLocation")));
            }
        }

        player.teleport(rtpLocation);
        player.sendMessage(ChatColor.GREEN + "You have been teleported randomly!");

        cooldowns.put(playerId, currentTimeMillis);
    }
    private Location rLocation(Player player, World world, int length) {
        Random rand = new Random();
        double x = rand.nextInt(length * 2) - length;
        double z = rand.nextInt(length * 2) - length;
        double y = world.getHighestBlockYAt((int) x, (int) z);
        
        return new Location(world, x, y, z);
    }
}
