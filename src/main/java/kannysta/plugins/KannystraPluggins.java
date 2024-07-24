package kannysta.plugins;

import kannysta.plugins.language.LanguageCommand;
import kannysta.plugins.littlecommands.HubCommand;
import kannysta.plugins.login.LoginJoinListener;
import kannysta.plugins.login.PlayerLeaveListener;
import kannysta.plugins.login.RegisterListeners;
import kannysta.plugins.mainMenu.MainMenuCommand;
import kannysta.plugins.mainMenu.WorldsCommand;
import kannysta.plugins.mainMenu.WorldsInventoryListener;

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

        // getConfig().set("messages.", "");

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


        getConfig().set("messages.pvpWorlds.en_US", "PvP Worlds");
        getConfig().set("messages.pvpWorlds.uk_UA", "ПвП Світи");
        getConfig().set("messages.pvpWorlds.ru_RU", "ПвП Миры");

        getConfig().set("messages.pveWorlds.en_US", "PvE Worlds");
        getConfig().set("messages.pveWorlds.uk_UA", "ПвE Світи");
        getConfig().set("messages.pveWorlds.ru_RU", "ПвE Миры");

        getConfig().set("messages.minigamesWorlds.en_US", "PvE Worlds");
        getConfig().set("messages.minigamesWorlds.uk_UA", "ПвE Світи");
        getConfig().set("messages.minigamesWorlds.ru_RU", "ПвE Миры");

        getConfig().set("messages.pvpWorldsLore.en_US", "/pvpworlds");
        getConfig().set("messages.pvpWorldsLore.uk_UA", "/пвпсвіти");
        getConfig().set("messages.pvpWorldsLore.ru_RU", "/пвпмиры");

        getConfig().set("messages.pveWorldsLore.en_US", "/pveworlds");
        getConfig().set("messages.pveWorldsLore.uk_UA", "/пвесвіти");
        getConfig().set("messages.pveWorldsLore.ru_RU", "/пвемиры");

        getConfig().set("messages.minigamesWorldsLore.en_US", "/minigamesmenu");
        getConfig().set("messages.minigamesWorldsLore.uk_UA", "/МенюМініІгор");
        getConfig().set("messages.minigamesWorldsLore.ru_RU", "/МенюМиниИгор");

        getConfig().set("messages.pvpFacts1.en_US", "Choose your way to PvP!");
        getConfig().set("messages.pvpFacts1.uk_UA", "Вибери свій шлях ПвП!");
        getConfig().set("messages.pvpFacts1.ru_RU", "Выбери свой путь ПвП!");

        getConfig().set("messages.pvpFacts2.en_US", "Easiest way to gain and lose resources");
        getConfig().set("messages.pvpFacts2.uk_UA", "Найлегший шлях щоб забрати і віддати ресурси");
        getConfig().set("messages.pvpFacts2.ru_RU", "Самый легкий путь забрать и отдать ресурсы");

        getConfig().set("messages.pvpFacts3.en_US", "Solo? Duo? Clan? Welcome!");
        getConfig().set("messages.pvpFacts3.uk_UA", "Соло? Дуо? Клан? Вітаємо!");
        getConfig().set("messages.pvpFacts3.ru_RU", "Соло? Дуо? Клан? Добро пожаловать!");

        getConfig().set("messages.pvpFacts4.en_US", "You're good at PvP - you're good at money");
        getConfig().set("messages.pvpFacts4.uk_UA", "Є скіл в пвп - є гроші");
        getConfig().set("messages.pvpFacts4.ru_RU", "Есть скилл в пвп - есть деньги");

        getConfig().set("messages.pvpFacts5.en_US", "Cheaters!!! :< (skill issue)");
        getConfig().set("messages.pvpFacts5.uk_UA", "Вони чітери!!! :< (skill issue)");
        getConfig().set("messages.pvpFacts5.ru_RU", "Они читеры!!! :< (skill issue)");
        
        getConfig().set("messages.pveFacts1.en_US", "No PvP in region");
        getConfig().set("messages.pveFacts1.uk_UA", "Нема ПвП в приваті");
        getConfig().set("messages.pveFacts1.ru_RU", "Нет ПвП в привате");

        getConfig().set("messages.pveFacts2.en_US", "Different worlds have different perks");
        getConfig().set("messages.pveFacts2.uk_UA", "Різні світи мають різні переваги");
        getConfig().set("messages.pveFacts2.ru_RU", "Разные миры имеют разные плюсы");

        getConfig().set("messages.pveFacts3.en_US", "TNT is workin btw");
        getConfig().set("messages.pveFacts3.uk_UA", "ТНТ працює до речі");
        getConfig().set("messages.pveFacts3.ru_RU", "ТНТ работает кстати");

        getConfig().set("messages.pveFacts4.en_US", "4 fact");
        getConfig().set("messages.pveFacts4.uk_UA", "4 Факт");
        getConfig().set("messages.pveFacts4.ru_RU", "4 Факт");


        getConfig().set("messages.minigamesFacts1.en_US", "Just play and gain free resources");
        getConfig().set("messages.minigamesFacts1.uk_UA", "Просто грай і отримуй безкоштовні ресурси");
        getConfig().set("messages.minigamesFacts1.ru_RU", "Просто играй и получи бесплатные ресурсы");

        getConfig().set("messages.minigamesFacts2.en_US", "Cybersport in Minecraft?!?! (yes)");
        getConfig().set("messages.minigamesFacts2.uk_UA", "Кіберспорт в Майнкрафті?!?! (так)");
        getConfig().set("messages.minigamesFacts2.ru_RU", "Киберспорт в Майнкрафт?!?! (да)");

        getConfig().set("messages.minigamesFacts3.en_US", "3");
        getConfig().set("messages.minigamesFacts3.uk_UA", "3");
        getConfig().set("messages.minigamesFacts3.ru_RU", "3");

        getConfig().set("messages.minigamesFacts4.en_US", "4");
        getConfig().set("messages.minigamesFacts4.uk_UA", "4");
        getConfig().set("messages.minigamesFacts4.ru_RU", "4");

        getConfig().set("messages.minigamesFacts5.en_US", "5");
        getConfig().set("messages.minigamesFacts5.uk_UA", "5");
        getConfig().set("messages.minigamesFacts5.ru_RU", "5");

        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new WorldsInventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new RegisterListeners(this), this);
        getServer().getPluginManager().registerEvents(new LoginJoinListener(this), this);
        getCommand("hub").setExecutor(new HubCommand(this));
        getCommand("language").setExecutor(new LanguageCommand(this, "en_US"));
        getCommand("мова").setExecutor(new LanguageCommand(this, "uk_UA"));
        getCommand("язык").setExecutor(new LanguageCommand(this, "ru_RU"));
        getCommand("menu").setExecutor(new MainMenuCommand(this));
        getCommand("worlds").setExecutor(new WorldsCommand(this));

//
        getConfig().set("locations.register", new Location(getServer().getWorld("register"), 0.5, 63.0, 0.5, 0, 0));
        getConfig().set("locations.login", new Location(getServer().getWorld("login"), 0.5, 65.0, 0.5, 0, 0));
        getConfig().set("locations.hub", new Location(getServer().getWorld("testhub"), 0.5, 4.0, 0.5, 0, 0));

//
        saveConfig();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
