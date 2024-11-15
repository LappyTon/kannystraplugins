package kannysta.plugins.mainMenu;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainMenuCommand implements CommandExecutor {

    private Utils utils;

    public MainMenuCommand(KannystraPluggins plugin) {
        this.utils = new Utils(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(utils.fullInventory(player));
        }

        return true;
    }
}