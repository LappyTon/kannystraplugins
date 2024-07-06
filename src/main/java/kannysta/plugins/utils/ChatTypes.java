package kannysta.plugins.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatTypes {
    public String issue(String s) {
        StringBuilder string = new StringBuilder();
        string.append(ChatColor.RED+""+ChatColor.BOLD).append(" >>> ").append(s);
        return string.toString();
    }
    public String warning(String s) {
        StringBuilder string = new StringBuilder();
        string.append(ChatColor.YELLOW+""+ChatColor.BOLD).append("<!!!> ").append(s).append(" <!!!>");
        return string.toString();
    }
    public String succes(String s) {
        StringBuilder string = new StringBuilder();
        string.append(ChatColor.GREEN+""+ChatColor.BOLD).append(">>> ").append(s);
        return string.toString();
    }
    public String event(String s) {
        StringBuilder string = new StringBuilder();
        string.append(ChatColor.WHITE+""+ChatColor.BOLD).append("=== ").append(s).append(" ===");
        return string.toString();
    }
    public String info(String s) {
        StringBuilder string = new StringBuilder();
        String questionMark = ChatColor.BOLD+" "+ChatColor.WHITE+"<"+ChatColor.BLUE+"???"+ChatColor.WHITE+"> ";
        string.append(questionMark).append(s).append(questionMark);
        return string.toString();
    }
}
