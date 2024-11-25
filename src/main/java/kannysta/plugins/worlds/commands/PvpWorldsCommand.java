package kannysta.plugins.worlds.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.worlds.PvpWorlds;

public class PvpWorldsCommand implements CommandExecutor {
    private PvpWorlds pvpWorlds;
    public PvpWorldsCommand(KannystraPluggins pluggins) {
        this.pvpWorlds = new PvpWorlds(pluggins);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            p.openInventory(pvpWorlds.pvpWorldsInventory(p));
        }
        return true;
    }
    
}
