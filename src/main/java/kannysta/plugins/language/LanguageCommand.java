package kannysta.plugins.language;

import kannysta.plugins.KannystraPluggins;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class LanguageCommand implements CommandExecutor {
    private final KannystraPluggins plugin;
    public static final String[] langs = {"en_US", "uk_UA", "ru_RU"};
    public String thislang;

    public LanguageCommand(KannystraPluggins plugin, String thislang) {
        this.plugin = plugin;
        this.thislang = thislang;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String path = "lang."+player.getName();


            switch (thislang) {
                case "uk_UA":
                    plugin.getConfig().set(path, "uk_UA");
                    player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Тепер ваша мова українська!");
                    break;

                case "en_US":
                    plugin.getConfig().set(path, "en_US");
                    player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Now your language is English!");
                    break;

                case "ru_RU":
                    plugin.getConfig().set(path, "ru_RU");
                    player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Ваш язык теперь русский!");
                    break;
            }
            plugin.saveConfig();
            return true;
        }
        return false;
    }
}