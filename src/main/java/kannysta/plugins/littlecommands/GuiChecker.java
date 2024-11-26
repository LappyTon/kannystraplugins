package kannysta.plugins.littlecommands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiChecker implements CommandExecutor {

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            p.openInventory(getInv(p));
        }
        return true;
    }
    public Inventory getInv(Player p) {
        Inventory inv = Bukkit.createInventory(p, 54, "guichecker");
        return inv;
    }
    
}
