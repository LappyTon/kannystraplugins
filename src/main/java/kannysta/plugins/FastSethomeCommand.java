package kannysta.plugins;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FastSethomeCommand implements CommandExecutor {
    private final KannystraPluggins pluggins;

    public FastSethomeCommand(KannystraPluggins pluggins) {
        this.pluggins = pluggins;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("homes:");
            if (args.length>1) {
                pluggins.getConfig().set("home."+player.getName(), player.getLocation());
                pluggins.saveConfig();
                player.sendMessage("home set!");
            } else {
                if (pluggins.getConfig().getLocation("home."+player.getName())!=null) {
                    player.teleport(pluggins.getConfig().getLocation("home."+player.getName()));
                    player.sendMessage("tp");
                } else {
                    player.sendMessage("you didnt set up a home!");
                }
            }
        }


        return true;
    }
}
