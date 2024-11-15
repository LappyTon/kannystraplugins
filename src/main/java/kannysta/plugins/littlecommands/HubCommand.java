package kannysta.plugins.littlecommands;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {

    private final KannystraPluggins plugin;
    private Utils utils;

    public HubCommand(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.utils = new Utils(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Location hub = plugin.getConfig().getLocation("locations.hub");
        if (hub==null) {
            return false;
        }
        Player player = (Player) sender;
        player.teleport(hub);
        player.sendMessage(utils.event(plugin.getConfig().getString("messages.hubTp."+plugin.getConfig().getString("lang."+player.getName()))));
        return true;
    }
}