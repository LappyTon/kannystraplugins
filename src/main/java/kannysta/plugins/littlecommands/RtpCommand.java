package kannysta.plugins.littlecommands;


import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;
import kannysta.plugins.logics.RtpLogic;

public class RtpCommand implements CommandExecutor {

    private RtpLogic rtpLogic;
    private Utils utils;
    public RtpCommand(KannystraPluggins pluggins) {
        this.rtpLogic = new RtpLogic(pluggins);
        this.utils = new Utils(pluggins);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (sender instanceof Player p) {
            World w = p.getWorld();
            if (w==Bukkit.getWorld("soupPvp")||
                w==Bukkit.getWorld("beastPvp")||
                w==Bukkit.getWorld("buildPve")||
                w==Bukkit.getWorld("redstonePve")||
                w==Bukkit.getWorld("smpPvp")||
                w==Bukkit.getWorld("crystalPvp")||
                w==Bukkit.getWorld("noSoupPvp")||
                w==Bukkit.getWorld("diamondPvp")||
                w==Bukkit.getWorld("netherPvp")||
                w==Bukkit.getWorld("anchorPvp")) {
                    rtpLogic.teleportPlayer(p, 200, w);
            } else if (                
                w==Bukkit.getWorld("plantsPve")||
                w==Bukkit.getWorld("buildPve"))  {
                    rtpLogic.teleportPlayer(p, 30000, w);
            } else {
                p.sendMessage(utils.issue(utils.messages(p, "rtpNotSupported")));
            }
        }
        
        return true;
    }
    
}
