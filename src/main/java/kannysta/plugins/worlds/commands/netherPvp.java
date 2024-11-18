package kannysta.plugins.worlds.commands;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.viaversion.viaversion.api.Via;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;
import kannysta.plugins.logics.RtpLogic;

public class netherPvp implements CommandExecutor {
    private final KannystraPluggins plugin;
    private final RtpLogic rtpLogic;
    private final Utils utils;
    private final HashMap<Player, Long> cooldowns = new HashMap<>();
    private static final long COOLDOWN_TIME = 2000; // 2 seconds in milliseconds

    public netherPvp(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.rtpLogic = new RtpLogic(plugin);
        this.utils = new Utils(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be run by players.");
            return true;
        }

        // Check player's client version
        int version = Via.getAPI().getPlayerVersion(player.getUniqueId());
        if (version != 754) { // 1.16.5 version code
            player.sendMessage(utils.issue(utils.messages(player, "badVersion") + " (1.16.5)"));
            return true;
        }

        // Handle cooldowns
        long currentTime = System.currentTimeMillis();
        Long lastUsed = cooldowns.get(player);
        if (lastUsed != null && currentTime - lastUsed < COOLDOWN_TIME) {
            player.sendMessage(utils.issue(utils.messages(player, "wait")));
            return true;
        }

        rtpLogic.teleportPlayer(player, 300, Bukkit.getWorld("netherPvp"));

        return true;
    }
}
