package kannysta.plugins.tab;

import kannysta.plugins.KannystraPluggins;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TabChange {
    private final KannystraPluggins pluggins;
    private FileConfiguration config;
    public TabChange(KannystraPluggins pluggins){
        this.pluggins = pluggins;
        this.config = pluggins.getConfig();
    }
    public void RegisterTabChange(Player p, int onlinePlayers) {
        String header = ChatColor.WHITE + "ip: mc.Kannystra.com";
        String footer = ChatColor.GRAY + "name: " + p.getName() + "\n" +
                "\n" + // Space between the lines
                ChatColor.RED + "You need to register!\n" +
                ChatColor.DARK_GREEN + "Open chat (T Button) and enter password\n";
        p.setPlayerListHeaderFooter(header, footer);
    }
    public void LoginTabChange(Player p) {
        String locale = config.getString("lang."+p.getName());
        String header = ChatColor.WHITE+""+ChatColor.BOLD + "ip: mc.Kannystra.com";
        String footer = ChatColor.GRAY + config.getString("translate.name."+locale) + p.getName() + "\n" +
                "\n" +
                ChatColor.RED + config.getString("translate.youNeedToLogin."+locale)+"\n" +
                ChatColor.DARK_GREEN + config.getString("translate.openChatAndEnterPassword."+locale) +"\n";
        p.setPlayerListHeaderFooter(header, footer);
    }
//    public void PvpTabChange(Player p, Player enemy) {
//        Scoreboard scoreboard = p.getScoreboard();
//        if (scoreboard == null) {
//            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
//            p.setScoreboard(scoreboard);
//        }
//
//        Team team = scoreboard.getTeam(enemy.getName());
//        if (team == null) {
//            team = scoreboard.registerNewTeam(enemy.getName());
//        }
//
//        team.setPrefix(ChatColor.WHITE+enemy.getName()+" "+ChatColor.DARK_RED+enemy.getHealth()+"hp"); // The new display name
//        team.addEntry(enemy.getName());
//
//        String locale = config.getString("lang."+p.getName());
//        String header = ChatColor.WHITE+""+ChatColor.BOLD + "ip: mc.Kannystra.com";
//        String footer = ChatColor.RED+config.getString("translate.inPvpMode."+locale);
//        p.setPlayerListHeaderFooter(header, footer);
//    }

}
