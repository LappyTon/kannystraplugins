package kannysta.plugins.littlecommands;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.utils.ChatTypes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {

    private final KannystraPluggins plugin;
    private final ChatTypes types = new ChatTypes();

    public HubCommand(KannystraPluggins plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.teleport(plugin.getConfig().getLocation("locations.hub"));
        player.sendMessage(types.event(plugin.getConfig().getString("messages.hubTp."+plugin.getConfig().getString("lang."+player.getName()))));
        return true;
    }
}