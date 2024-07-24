package kannysta.plugins.mainMenu;

import kannysta.plugins.KannystraPluggins;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class WolrdsCommand implements CommandExecutor {
    private final KannystraPluggins plugin;
    private MenuInventory inventory;

    private Inventory Inventory;

    public WolrdsCommand(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.inventory = new MenuInventory();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(inventory.MainInventory(player));
        }

        return true;
    }
}
