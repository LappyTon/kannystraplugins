package kannysta.plugins.mainMenu;

import kannysta.plugins.KannystraPluggins;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class WorldsCommand implements CommandExecutor {
    private final KannystraPluggins plugin;
    private MenuInventory inventory;
    private Inventory Inventory;

    public WorldsCommand(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.inventory = new MenuInventory(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(inventory.WorldsInventory(player));
        }

        return true;
    }
}