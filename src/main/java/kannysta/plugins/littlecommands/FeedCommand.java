package kannysta.plugins.littlecommands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;

public class FeedCommand implements CommandExecutor {

    private final KannystraPluggins pluggins;
    private final Utils utils;
    private final HashMap<Player, Long> cooldowns = new HashMap<>();

    public FeedCommand(KannystraPluggins pluggins) {
        this.pluggins = pluggins;
        this.utils = new Utils(pluggins);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            int feedCooldown = utils.getConfig().getInt("cooldowns.feed." + p.getUniqueId(), -1);

            if (feedCooldown <= 0) {
                p.sendMessage(utils.issue("You don't have access to the /feed command!"));
                return true;
            }

            long currentTime = System.currentTimeMillis();
            if (cooldowns.containsKey(p)) {
                long lastUsedTime = cooldowns.get(p);
                long timeLeftSeconds = feedCooldown - (currentTime - lastUsedTime) / 1000;

                if (timeLeftSeconds > 0) {
                    p.sendMessage(utils.issue(utils.messages(p, "feedCooldown") + timeLeftSeconds + " sec"));
                    return true; 
                }
            }

            p.setSaturation(20);

            cooldowns.put(p, currentTime);
            return true;
        }
        return true;
    }
}
