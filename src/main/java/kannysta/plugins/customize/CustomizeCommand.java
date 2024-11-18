package kannysta.plugins.customize;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kannysta.plugins.KannystraPluggins;

public class CustomizeCommand implements CommandExecutor {

    private final KannystraPluggins pluggins;
    private CustomizeGui customizeGui;
    public CustomizeCommand(KannystraPluggins pluggins) {
        this.pluggins = pluggins;
        this.customizeGui = new CustomizeGui(pluggins);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(sender instanceof Player p) {
            p.openInventory(customizeGui.customizeInventory(p));
        }

        return true;

    }
    
}
