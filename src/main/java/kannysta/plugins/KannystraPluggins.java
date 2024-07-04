package kannysta.plugins;

import kannysta.plugins.language.LanguageCommand;
import kannysta.plugins.login.LoginJoinListener;
import kannysta.plugins.login.RegisterJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class KannystraPluggins extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("[Kannystra plugins] ACTIVATED!!!");
        System.out.println(" ");
        System.out.println(" ");

        getConfig().options().copyDefaults();

        getServer().getPluginManager().registerEvents(new RegisterJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new LoginJoinListener(this), this);
        getCommand("home").setExecutor(new FastSethomeCommand(this));
        getCommand("language").setExecutor(new LanguageCommand(this, "en_US"));
        getCommand("мова").setExecutor(new LanguageCommand(this, "uk_UA"));
        getCommand("язык").setExecutor(new LanguageCommand(this, "ru_RU"));

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
