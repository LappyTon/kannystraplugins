package kannysta.plugins;

import kannysta.plugins.language.LanguageCommand;
import kannysta.plugins.littlecommands.HubCommand;
import kannysta.plugins.login.LoginJoinListener;
import kannysta.plugins.login.PlayerLeaveListener;
import kannysta.plugins.login.RegisterListeners;
import org.bukkit.Location;
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

        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new RegisterListeners(this), this);
        getServer().getPluginManager().registerEvents(new LoginJoinListener(this), this);
//        getCommand("home").setExecutor(new FastSethomeCommand(this));
        getCommand("hub").setExecutor(new HubCommand(this));
        getCommand("language").setExecutor(new LanguageCommand(this, "en_US"));
        getCommand("мова").setExecutor(new LanguageCommand(this, "uk_UA"));
        getCommand("язык").setExecutor(new LanguageCommand(this, "ru_RU"));

//
        getConfig().set("locations.register", new Location(getServer().getWorld("register"), 0.5, 63.0, 0.5, 0, 0));
        getConfig().set("locations.login", new Location(getServer().getWorld("login"), 0.5, 65.0, 0.5, 0, 0));
        getConfig().set("locations.hub", new Location(getServer().getWorld("hub"), 0.5, 65.0, 0.5, 0, 0));


        getConfig().set("messages.registrationSuccess.en_US", "Registration success!!");
        getConfig().set("messages.registrationSuccess.uk_UA", "Успішна реєстрація");
        getConfig().set("messages.registrationSuccess.ru_RU", "Успешная регистация");


        getConfig().set("messages.registerFirst.en_US", "Register first!");
        getConfig().set("messages.registerFirst.uk_UA", "Спочатку зареєструйтесь!");
        getConfig().set("messages.registerFirst.ru_RU", "Сперва зарегестрируйтесь!");

        getConfig().set("messages.register.en_US", "Now register - just send your password in chat");
        getConfig().set("messages.register.uk_UA", "Тепер зареєструйтесь - просто відправте пароль в чат");
        getConfig().set("messages.register.ru_RU", "Теперь зарегестрируйтесь - просто отправьте пароль в чат");

        getConfig().set("messages.whileRegister.en_US", "You cant execute any commands while registering!");
        getConfig().set("messages.whileRegister.uk_UA", "ти не можеш писати команди поки ти не зареєстрований!");
        getConfig().set("messages.whileRegister.ru_RU", "Тебе нелзя писать команды пока ты не зарегестрирован!");

        getConfig().set("messages.loginPlease.en_US", "Please log into your account");
        getConfig().set("messages.loginPlease.uk_UA", "Будь ласка, увійдіть в ваш аккаунт!");
        getConfig().set("messages.loginPlease.ru_RU", "Пожалуйста, ввойдите в ваш аккаунт!");

        getConfig().set("messages.howToLogin.en_US", "Open chat (default T button) and send message with your password");
        getConfig().set("messages.howToLogin.uk_UA", "Відкрийте чат (англійська Т українска Н клавіша) і відправте свій пароль");
        getConfig().set("messages.howToLogin.ru_RU", "Откройте чат (английская Т русская Н кнопка) и отправьте свой пароль");

        getConfig().set("messages.noNeedLogin.en_US", "You are already logged in");
        getConfig().set("messages.noNeedLogin.uk_UA", "Ви вже ввійшли в аккаунт");
        getConfig().set("messages.noNeedLogin.ru_RU", "Вы уже вошли в аккаунт");

        getConfig().set("messages.passwordCheck.en_US", "Checking your password...");
        getConfig().set("messages.passwordCheck.uk_UA", "Перевіряємо ваш пароль...");
        getConfig().set("messages.passwordCheck.ru_RU", "Проверям ваш пароль...");

        getConfig().set("messages.passwordCorrect.en_US", "Your password is correct!");
        getConfig().set("messages.passwordCorrect.uk_UA", "Ваш пароль правильний!");
        getConfig().set("messages.passwordCorrect.ru_RU", "Ваш пароль правильный!");

        getConfig().set("messages.passwordIncorrect.en_US", "Your password is incorrect!");
        getConfig().set("messages.passwordIncorrect.uk_UA", "Ваш пароль неправильний!");
        getConfig().set("messages.passwordIncorrect.ru_RU", "Ваш пароль неправильный!");

        getConfig().set("messages.hubTp.en_US", "You have been teleported to hub!");
        getConfig().set("messages.hubTp.uk_UA", "Ви телепорутвались в хаб!");
        getConfig().set("messages.hubTp.ru_RU", "Вы были телепортированы в хаб!");


//
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
