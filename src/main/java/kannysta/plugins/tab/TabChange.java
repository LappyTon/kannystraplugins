package kannysta.plugins.tab;

import kannysta.plugins.KannystraPluggins;
import kannysta.plugins.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TabChange {
    private final KannystraPluggins plugin;
    private final FileConfiguration config;
    private final Utils utils;

    public TabChange(KannystraPluggins plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.utils = new Utils(plugin);
    }

    public void registerTabChange(Player player, int seconds) {
        String header = ChatColor.WHITE + "ip: mc.Kannystra.com";
        String footer = ChatColor.GRAY + "Name: " + player.getName() + "\n\n" +
                ChatColor.RED + "You need to register!\n" +
                ChatColor.DARK_GREEN + "Open chat (T Button) and enter your password.";
    }

    public void loginTabChange(Player player, int seconds) {
        // String locale = config.getString("lang." + player.getName(), "en");
        // String header = ChatColor.WHITE + "" + ChatColor.BOLD + "ip: mc.Kannystra.com";
        // String footer = ChatColor.GRAY + config.getString("translate.name." + locale, "Name: ") + player.getName() + "\n\n" +
        //         ChatColor.RED + config.getString("translate.youNeedToLogin." + locale, "You need to log in!") + "\n" +
        //         ChatColor.DARK_GREEN + config.getString("translate.openChatAndEnterPassword." + locale, "Open chat (T) and enter your password.");

        // new BukkitRunnable() {
        //     private int remainingSeconds = seconds;

        //     @Override
        //     public void run() {
        //         if (remainingSeconds > 0) {
        //             player.setPlayerListHeaderFooter(header, footer);
        //             remainingSeconds--;
        //         } else {
        //             player.kickPlayer(utils.messages(player, "loginTimePassed"));
        //             cancel();
        //         }
        //     }
        // }.runTaskTimer(plugin, 0, 20);
    }
}
