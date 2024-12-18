package kannysta.plugins.worlds.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.worlds.PveWorlds;

public class PveWorldsCommand implements CommandExecutor {
    private PveWorlds pveWorlds;
    public PveWorldsCommand(KannystraPluggins pluggins) {
        this.pveWorlds = new PveWorlds(pluggins);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            p.openInventory(pveWorlds.pveWorldsInv(p));
        }
        return true;
    }
    
}
