package kannysta.plugins;

import kannysta.plugins.customize.CustomGuiListener;
import kannysta.plugins.customize.CustomizeCommand;
import kannysta.plugins.language.LanguageCommand;
import kannysta.plugins.littlecommands.FeedCommand;
import kannysta.plugins.littlecommands.GuiChecker;
import kannysta.plugins.littlecommands.GuiCheckerListener;
import kannysta.plugins.littlecommands.HubCommand;
import kannysta.plugins.littlecommands.RtpCommand;
import kannysta.plugins.login.LoginJoinListener;
import kannysta.plugins.login.PlayerLeaveListener;
import kannysta.plugins.login.RegisterListeners;
import kannysta.plugins.mainMenu.MainMenuCommand;
import kannysta.plugins.worlds.HubListeners;
import kannysta.plugins.worlds.PvpWorlds;
import kannysta.plugins.worlds.WorldsInventoryListener;
import kannysta.plugins.worlds.commands.PvpWorldsCommand;
import kannysta.plugins.worlds.commands.WorldsCommand;
import kannysta.plugins.worlds.commands.netherPvp;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
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
        
        // getConfig().set("messages..en_US", "");
        // getConfig().set("messages..uk_UA", "");
        // getConfig().set("messages..ru_RU", "");


        getConfig().set("messages.currentQuest.pve.en_US", "Current quests: PVE");
        getConfig().set("messages.currentQuest.pve.uk_UA", "Поточні завдання: PVE");
        getConfig().set("messages.currentQuest.pve.ru_RU", "Текущие задания: PVE");

        getConfig().set("messages.currentQuest.minigames.en_US", "Current quests: Minigames");
        getConfig().set("messages.currentQuest.minigames.uk_UA", "Поточні завдання: Мініігри");
        getConfig().set("messages.currentQuest.minigames.ru_RU", "Текущие задания: Мини-игры");

        getConfig().set("messages.currentQuest.pvp.en_US", "Current quests: PVP");
        getConfig().set("messages.currentQuest.pvp.uk_UA", "Поточні завдання: PVP");
        getConfig().set("messages.currentQuest.pvp.ru_RU", "Текущие задания: PVP");

        getConfig().set("messages.currentQuest.clan.en_US", "Current quests: Clan");
        getConfig().set("messages.currentQuest.clan.uk_UA", "Поточні завдання: Клан");
        getConfig().set("messages.currentQuest.clan.ru_RU", "Текущие задания: Клан");

        getConfig().set("messages.currentQuest.none.en_US", "Current quests: None");
        getConfig().set("messages.currentQuest.none.uk_UA", "Поточні завдання: Немає");
        getConfig().set("messages.currentQuest.none.ru_RU", "Текущие задания: Нету");

        getConfig().set("messages.tabConfig.en_US", "Tab configuration");
        getConfig().set("messages.tabConfig.uk_UA", "Конфігурація табу");
        getConfig().set("messages.tabConfig.ru_RU", "Конфигурация таба");

        
        getConfig().set("messages.tabItem.clanPlayersOnline.en_US", "Highlight clan players online");
        getConfig().set("messages.tabItem.clanPlayersOnline.uk_UA", "Виділити онлайн гравців клану");
        getConfig().set("messages.tabItem.clanPlayersOnline.ru_RU", "Выделить онлайн игроков клана");

        getConfig().set("messages.tabItem.playerSorting.en_US", "Change player sorting");
        getConfig().set("messages.tabItem.playerSorting.uk_UA", "Змінити сортування гравців");
        getConfig().set("messages.tabItem.playerSorting.ru_RU", "Изменить сортировку игроков");

        getConfig().set("messages.tabItem.balance.en_US", "Add balance");
        getConfig().set("messages.tabItem.balance.uk_UA", "Додати баланс");
        getConfig().set("messages.tabItem.balance.ru_RU", "Добавить баланс");

        getConfig().set("messages.tabItem.killStreak.en_US", "Add killstreak");
        getConfig().set("messages.tabItem.killStreak.uk_UA", "Додати серію вбивств");
        getConfig().set("messages.tabItem.killStreak.ru_RU", "Добавить серию убийств");

        getConfig().set("messages.tabItem.name.en_US", "Add name");
        getConfig().set("messages.tabItem.name.uk_UA", "Додати ім'я");
        getConfig().set("messages.tabItem.name.ru_RU", "Добавить имя");

        getConfig().set("messages.tabItem.ahItems.en_US", "Add number of auction items (/ah)");
        getConfig().set("messages.tabItem.ahItems.uk_UA", "Додати кількість предметів на аукціоні (/ah)");
        getConfig().set("messages.tabItem.ahItems.ru_RU", "Добавить количество предметов на аукционе (/ah)");

        getConfig().set("messages.tabItem.worldName.en_US", "Add current worlds name");
        getConfig().set("messages.tabItem.worldName.uk_UA", "Додати назву поточного світу");
        getConfig().set("messages.tabItem.worldName.ru_RU", "Добавить название текущего мира");

        getConfig().set("messages.tabItem.nextEvent.en_US", "Add next event (time and name)");
        getConfig().set("messages.tabItem.nextEvent.uk_UA", "Додати наступну подію (час і назву)");
        getConfig().set("messages.tabItem.nextEvent.ru_RU", "Добавить следующее событие (время и название)");

        getConfig().set("messages.tabItem.nextEventInaccessible.en_US", "Add next event: There is no events in this world!");
        getConfig().set("messages.tabItem.nextEventInaccessible.uk_UA", "Додати наступний івент: У цьому світі немає івентів!");
        getConfig().set("messages.tabItem.nextEventInaccessible.ru_RU", "Добавить следующий ивент: В этом мире нет ивентов!");
        
        getConfig().set("messages.tabItem.nextRestart.en_US", "Add next restart time");
        getConfig().set("messages.tabItem.nextRestart.uk_UA", "Додати час наступного перезапуску");
        getConfig().set("messages.tabItem.nextRestart.ru_RU", "Добавить время следующей перезагрузки");

        getConfig().set("messages.tabItem.currentQuest.en_US", "Add current quest status");
        getConfig().set("messages.tabItem.currentQuest.uk_UA", "Додати статус поточного завдання");
        getConfig().set("messages.tabItem.currentQuest.ru_RU", "Добавить статус текущего задания");

        getConfig().set("messages.tabItem.lobby.en_US", "Change tab for lobby");
        getConfig().set("messages.tabItem.lobby.uk_UA", "Змінити таб для лобі");
        getConfig().set("messages.tabItem.lobby.ru_RU", "Изменить таб для лобби");
        
        getConfig().set("messages.tabItem.lobby.en_US", "Change tab for lobby");
        getConfig().set("messages.tabItem.lobby.uk_UA", "Змінити таб для лобі");
        getConfig().set("messages.tabItem.lobby.ru_RU", "Изменить таб для лобби");
        
        getConfig().set("messages.tabItem.pveWorld.en_US", "Change tab for pve worlds");
        getConfig().set("messages.tabItem.pveWorld.uk_UA", "Змінити таб для PvE-світів");
        getConfig().set("messages.tabItem.pveWorld.ru_RU", "Изменить таб для PvE-миров");
        
        getConfig().set("messages.tabItem.pvpWorld.en_US", "Change tab for pvp worlds");
        getConfig().set("messages.tabItem.pvpWorld.uk_UA", "Змінити таб для PvP-світів");
        getConfig().set("messages.tabItem.pvpWorld.ru_RU", "Изменить таб для PvP-миров");
        
        getConfig().set("messages.tabItem.pvp.en_US", "Change tab in pvp mode");
        getConfig().set("messages.tabItem.pvp.uk_UA", "Змінити таб в режимі PvP");
        getConfig().set("messages.tabItem.pvp.ru_RU", "Изменить таб в режиме PvP");
        
        getConfig().set("messages.tabItem.minigamesWorld.en_US", "Change tab for minigame worlds");
        getConfig().set("messages.tabItem.minigamesWorld.uk_UA", "Змінити таб для світів з мінііграми");
        getConfig().set("messages.tabItem.minigamesWorld.ru_RU", "Изменить таб для миров с мини-играми");
        
        getConfig().set("messages.tabItem.minigame.en_US", "Change tab in minigame");
        getConfig().set("messages.tabItem.minigame.uk_UA", "Змінити таб в мінігрі");
        getConfig().set("messages.tabItem.minigame.ru_RU", "Изменить таб в мини-игре");
        
        getConfig().set("messages.tabItem.style.en_US", "Change tab style");
        getConfig().set("messages.tabItem.style.uk_UA", "Змінити стиль табу");
        getConfig().set("messages.tabItem.style.ru_RU", "Изменить стиль таба");
        
        getConfig().set("messages.tabItem.colors.en_US", "Change tab color");
        getConfig().set("messages.tabItem.colors.uk_UA", "Змінити колір табу");
        getConfig().set("messages.tabItem.colors.ru_RU", "Изменить цвет таба");
        
        getConfig().set("messages.prefix_clan.en_US", "Clan Name");
        getConfig().set("messages.prefix_clan.uk_UA", "Назва клану");
        getConfig().set("messages.prefix_clan.ru_RU", "Название клана");
        getConfig().set("messages.prefix_clanLore.en_US", "The prefix shows the name of the clan the player is in");
        getConfig().set("messages.prefix_clanLore.uk_UA", "Префікс показує назву клана в якому перебуває гравецьi");
        getConfig().set("messages.prefix_clanLore.ru_RU", "Префикс показывает название клана, в котором находится игрок");

        getConfig().set("messages.prefix_claimedMoney.en_US", "Claimed Money");
        getConfig().set("messages.prefix_claimedMoney.uk_UA", "Отримані гроші");
        getConfig().set("messages.prefix_claimedMoney.ru_RU", "Полученные деньги");
        getConfig().set("messages.prefix_claimedMoneyLore.en_US", "Money the player has received from the auction over time");
        getConfig().set("messages.prefix_claimedMoneyLore.uk_UA", "Гроші, які гравець отримав з аукціону за весь час");
        getConfig().set("messages.prefix_claimedMoneyLore.ru_RU", "Деньги, которые игрок получил с аукциона за всё время");

        getConfig().set("messages.prefix_money.en_US", "Player Balance");
        getConfig().set("messages.prefix_money.uk_UA", "Баланс гравця");
        getConfig().set("messages.prefix_money.ru_RU", "Баланс игрока");
        getConfig().set("messages.prefix_moneyLore.en_US", "The prefix shows the player's current balance");
        getConfig().set("messages.prefix_moneyLore.uk_UA", "Префікс показує баланс гравця в даний момент");
        getConfig().set("messages.prefix_moneyLore.ru_RU", "Префикс показывает текущий баланс игрока");

        getConfig().set("messages.prefix_playtime.en_US", "Time on Server");
        getConfig().set("messages.prefix_playtime.uk_UA", "Час на сервері");
        getConfig().set("messages.prefix_playtime.ru_RU", "Время на сервере");
        getConfig().set("messages.prefix_playtimeLore.en_US", "Shows time spent on the server across all resets");
        getConfig().set("messages.prefix_playtimeLore.uk_UA", "Показує час проведений на сервері за всі вайпи");
        getConfig().set("messages.prefix_playtimeLore.ru_RU", "Показывает время, проведённое на сервере за все вайпы");

        getConfig().set("messages.prefix_kills.en_US", "Kills in World");
        getConfig().set("messages.prefix_kills.uk_UA", "Вбивства на світі");
        getConfig().set("messages.prefix_kills.ru_RU", "Убийства в мире");
        getConfig().set("messages.prefix_killsLore.en_US", "Kills made in the world where the message was sent");
        getConfig().set("messages.prefix_killsLore.uk_UA", "Вбивства, скоєні в світі де надіслано повідомлення");
        getConfig().set("messages.prefix_killsLore.ru_RU", "Убийства, совершённые в мире, где было отправлено сообщение");

        getConfig().set("messages.prefix_kills2Lore.en_US", "If PvP is disabled in the world, shows kills across all worlds");
        getConfig().set("messages.prefix_kills2Lore.uk_UA", "Якщо в світі виключене пвп, показуються вбивства по всіх світах");
        getConfig().set("messages.prefix_kills2Lore.ru_RU", "Если в мире отключено PvP, показываются убийства по всем мирам");

        getConfig().set("messages.prefix_kd.en_US", "Kills / Deaths");
        getConfig().set("messages.prefix_kd.uk_UA", "Вбивства / Смерті");
        getConfig().set("messages.prefix_kd.ru_RU", "Убийства / Смерти");
        getConfig().set("messages.prefix_kdLore.en_US", "The prefix shows total kills and deaths across all worlds");
        getConfig().set("messages.prefix_kdLore.uk_UA", "Префікс показує загальні вбивства і смерті по всіх світах");
        getConfig().set("messages.prefix_kdLore.ru_RU", "Префикс показывает общее количество убийств и смертей по всем мирам");

        getConfig().set("messages.prefix_none.en_US", "Do not show prefix");
        getConfig().set("messages.prefix_none.uk_UA", "Не показувати префікс");
        getConfig().set("messages.prefix_none.ru_RU", "Не показывать префикс");
        getConfig().set("messages.prefix_noneLore.en_US", "The prefix will be removed. Example:");
        getConfig().set("messages.prefix_noneLore.uk_UA", "Префікс буде видалено. Приклад:");
        getConfig().set("messages.prefix_noneLore.ru_RU", "Префикс будет удалён. Пример:");


        getConfig().set("messages.enabled.en_US", "ENABLED");
        getConfig().set("messages.enabled.uk_UA", "УВІМКНЕНО");
        getConfig().set("messages.enabled.ru_RU", "ВКЛЮЧЕНО");
        
        getConfig().set("messages.disabled.en_US", "DISABLED");
        getConfig().set("messages.disabled.uk_UA", "ВИМКНЕНО");
        getConfig().set("messages.disabled.ru_RU", "ВЫКЛЮЧЕНО");
        
        getConfig().set("messages.worldChat.en_US", "Global chat messages: LOCAL WORLD CHAT");
        getConfig().set("messages.worldChat.uk_UA", "Повідомлення глобального чату: ЛОКАЛЬНИЙ ЧАТ СВІТУ");
        getConfig().set("messages.worldChat.ru_RU", "Сообщения глобального чата: ЛОКАЛЬНЫЙ ЧАТ МИРА");
        
        getConfig().set("messages.noChat.en_US", "Global chat messages: CHAT DISABLED");
        getConfig().set("messages.noChat.uk_UA", "Повідомлення глобального чату: ЧАТ ВИМКНЕНО");
        getConfig().set("messages.noChat.ru_RU", "Сообщения глобального чата: ЧАТ ОТКЛЮЧЕН");
        
        getConfig().set("messages.clanChat.en_US", "Global chat messages: CLAN CHAT");
        getConfig().set("messages.clanChat.uk_UA", "Повідомлення глобального чату: ЧАТ КЛАНУ");
        getConfig().set("messages.clanChat.ru_RU", "Сообщения глобального чата: ЧАТ КЛАНА");
        
        getConfig().set("messages.globalChat.en_US", "Global chat messages: GLOBAL WORLDS CHAT");
        getConfig().set("messages.globalChat.uk_UA", "Повідомлення глобального чату: ГЛОБАЛЬНИЙ ЧАТ");
        getConfig().set("messages.globalChat.ru_RU", "Сообщения глобального чата: ГЛОБАЛЬНЫЙ ЧАТ");
        
        getConfig().set("messages.chatLang_eng.en_US", "Global chat language: English");
        getConfig().set("messages.chatLang_eng.uk_UA", "Мова глобального чату: Англійська");
        getConfig().set("messages.chatLang_eng.ru_RU", "Язык глобального чата: Английский");
        
        getConfig().set("messages.chatLang_ru.en_US", "Global chat language: Russian");
        getConfig().set("messages.chatLang_ru.uk_UA", "Мова глобального чату: Російська");
        getConfig().set("messages.chatLang_ru.ru_RU", "Язык глобального чата: Русский");
        
        getConfig().set("messages.chatLang_ua.en_US", "Global chat language: Ukrainian");
        getConfig().set("messages.chatLang_ua.uk_UA", "Мова глобального чату: Українська");
        getConfig().set("messages.chatLang_ua.ru_RU", "Язык глобального чата: Украинский");

        getConfig().set("messages.chatLangChange_eng.en_US", "&7Change language to: &fUkrainian");
        getConfig().set("messages.chatLangChange_eng.uk_UA", "&7Змінити мову на: &fАнглійську");
        getConfig().set("messages.chatLangChange_eng.ru_RU", "&7Change language to: &fRussian");
        
        getConfig().set("messages.chatLangChange_ru.en_US", "&7Change language to: &fUkrainian");
        getConfig().set("messages.chatLangChange_ru.uk_UA", "&7Змінити мову на: &fУкраїнську");
        getConfig().set("messages.chatLangChange_ru.ru_RU", "&7Change language to: &fEnglish");
        
        getConfig().set("messages.chatLangChange_ua.en_US", "&7Change language to: &fRussian");
        getConfig().set("messages.chatLangChange_ua.uk_UA", "&7Змінити мову на: &fРосійську");
        getConfig().set("messages.chatLangChange_ua.ru_RU", "&7Change language to: &fUkrainian");
        
        getConfig().set("messages.newbieTips.en_US", "Newbie tips:");
        getConfig().set("messages.newbieTips.uk_UA", "Поради для новачків:");
        getConfig().set("messages.newbieTips.ru_RU", "Советы для новичков:");
        
        getConfig().set("messages.playersPrefix.en_US", "Change prefixes for players");
        getConfig().set("messages.playersPrefix.uk_UA", "Змінити префікси для гравців");
        getConfig().set("messages.playersPrefix.ru_RU", "Изменить префиксы для игроков");
        
        getConfig().set("messages.monochrome.en_US", "Monochrome (white-black) chat mode:");
        getConfig().set("messages.monochrome.uk_UA", "Режим монохромного (чорно-білого) чату:");
        getConfig().set("messages.monochrome.ru_RU", "Режим монохромного (чёрно-белого) чата:");
        
        getConfig().set("messages.chatConfig.en_US", "Chat configuration");
        getConfig().set("messages.chatConfig.uk_UA", "Змінити чат");
        getConfig().set("messages.chatConfig.ru_RU", "Поменять чат");

        getConfig().set("messages.wait.en_US", "Wait one more sec...");
        getConfig().set("messages.wait.uk_UA", "Почекай секунду...");
        getConfig().set("messages.wait.ru_RU", "Подожди секунду...");

        getConfig().set("messages.registerTimePassed.en_US", "Time for register passed! Try again");
        getConfig().set("messages.registerTimePassed.uk_UA", "Час реєстрації вийшов! Спробуйте ще раз");
        getConfig().set("messages.registerTimePassed.ru_RU", "Время регистрации вышло! Попробуйте еще раз");
        
        getConfig().set("messages.badVersion.en_US", "Unsupported version! Join from version specified in world's description");
        getConfig().set("messages.badVersion.uk_UA", "Непідтримувана версія! Приєднайтеся з версії, вказаної в описі світу");
        getConfig().set("messages.badVersion.ru_RU", "Неподдерживаемая версия! Присоединитесь с версии, указанной в описании мира");
        
        getConfig().set("messages.soupPvp.en_US", "Soup 1.8.9");
        getConfig().set("messages.soupPvp.uk_UA", "Суп 1.8.9");
        getConfig().set("messages.soupPvp.ru_RU", "Сут 1.8.9");

        getConfig().set("messages.beastPvp.en_US", "Beast 1.12.2");
        getConfig().set("messages.beastPvp.uk_UA", "Beast 1.12.2");
        getConfig().set("messages.beastPvp.ru_RU", "Beast 1.12.2");

        getConfig().set("messages.smpPvp.en_US", "Smp 1.21.1");
        getConfig().set("messages.smpPvp.uk_UA", "Smp 1.21.1");
        getConfig().set("messages.smpPvp.ru_RU", "Smp 1.21.1");

        getConfig().set("messages.crystalPvp.en_US", "CPvp 1.12.2");
        getConfig().set("messages.crystalPvp.uk_UA", "КПвп 1.12.2");
        getConfig().set("messages.crystalPvp.ru_RU", "КПвп 1.12.2");

        getConfig().set("messages.customDuel.en_US", "Custom duel");
        getConfig().set("messages.customDuel.uk_UA", "Кастомна дуель");
        getConfig().set("messages.customDuel.ru_RU", "Кастомная дуэль");

        getConfig().set("messages.noSoupPvp.en_US", "Default 1.8.9");
        getConfig().set("messages.noSoupPvp.uk_UA", "Стандарт 1.8.9");
        getConfig().set("messages.noSoupPvp.ru_RU", "Стандарт 1.8.9");

        getConfig().set("messages.diamondPvp.en_US", "Default 1.12.2");
        getConfig().set("messages.diamondPvp.uk_UA", "Стандарт 1.12.2");
        getConfig().set("messages.diamondPvp.ru_RU", "Стандарт 1.12.2");

        getConfig().set("messages.netherPvp.en_US", "Default 1.16.5");
        getConfig().set("messages.netherPvp.uk_UA", "Стандарт 1.16.5");
        getConfig().set("messages.netherPvp.ru_RU", "Стандарт 1.16.5");

        getConfig().set("messages.anchorPvp.en_US", "Anchor 1.21.1");
        getConfig().set("messages.anchorPvp.uk_UA", "Якір 1.21.1");
        getConfig().set("messages.anchorPvp.ru_RU", "Якорь 1.21.1");

        getConfig().set("messages.classicDuel.en_US", "Classic duel");
        getConfig().set("messages.classicDuel.uk_UA", "Класична дуель");
        getConfig().set("messages.classicDuel.ru_RU", "Классическая дуэль");

        getConfig().set("messages.serverIpAddress.en_US", "ip: mc.Kannystra.com");
        getConfig().set("messages.serverIpAddress.uk_UA", "ip: mc.Kannystra.com");
        getConfig().set("messages.serverIpAddress.ru_RU", "ip: mc.Kannystra.com");

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

        getConfig().set("messages.damagedAlreadyInPvp.en_US", "Damaged player is already fighting!");
        getConfig().set("messages.damagedAlreadyInPvp.uk_UA", "Вдарений гравець вже в ПвП!");
        getConfig().set("messages.damagedAlreadyInPvp.ru_RU", "Ударенный игрок уже в ПВП");
        
        getConfig().set("messages.cantDamageBetter.en_US", "You cant damage players with better armor");
        getConfig().set("messages.cantDamageBetter.uk_UA", "Ти не можеш атакувати людей з кращою бронею");
        getConfig().set("messages.cantDamageBetter.ru_RU", "Ты не можешь атаковать людей с лучшей броней");

        getConfig().set("messages.noRtpLocation.en_US", "There is no valid RTP locations in world! Try again");
        getConfig().set("messages.noRtpLocation.uk_UA", "У світі немає безпечних місць RTP! Спробуйте знову");
        getConfig().set("messages.noRtpLocation.ru_RU", "В мире нет безопасных локаций RTP! Попробуйте еще раз");

        getConfig().set("messages.rtpCooldown.en_US", "Random teleportation is on cooldown! Try again after ");
        getConfig().set("messages.rtpCooldown.uk_UA", "Випадкова телепортація перезаряджається! Спробуйте знову через ");
        getConfig().set("messages.rtpCooldown.ru_RU", "Случайная телепортация находится на перезарядке! Попробуйте еще раз после ");

        getConfig().set("messages.feedCooldown.en_US", "Feed command is on cooldown! Try again after ");
        getConfig().set("messages.feedCooldown.uk_UA", "Команда сытости перезаряжается! Попробуйте снова через ");
        getConfig().set("messages.feedCooldown.ru_RU", " находится на перезарядке! Попробуйте еще раз после ");
        
// you dont have access to /feed

        getConfig().set("translate.name.en_US", "Name: ");
        getConfig().set("translate.name.uk_UA", "Ім'я: ");
        getConfig().set("translate.name.ru_RU", "Имя: ");

        getConfig().set("translate.youNeedToRegister.en_US", "You need to register!");
        getConfig().set("translate.youNeedToRegister.uk_UA", "Тобі потрібно зареєструватись!");
        getConfig().set("translate.youNeedToRegister.ru_RU", "Тебе нужно зарегестрироваться!");

        getConfig().set("translate.youNeedToLogin.en_US", "You need to login!");
        getConfig().set("translate.youNeedToLogin.uk_UA", "Тобі потрібно увійти!");
        getConfig().set("translate.youNeedToLogin.ru_RU", "Тебе нужно войти!");

        getConfig().set("translate.openChatAndEnterPassword.en_US", "Open chat (T Button) and enter password");
        getConfig().set("translate.openChatAndEnterPassword.uk_UA", "Відкрий чат (Т англійська Е українська кнопка) і введи пароль");
        getConfig().set("translate.openChatAndEnterPassword.ru_RU", "Открой чат (Т английская Е русская) и напиши пароль");

        getConfig().set("translate.online.en_US", "Online: ");
        getConfig().set("translate.online.uk_UA", "Онлайн: ");
        getConfig().set("translate.online.ru_RU", "Онлайн: ");

        getConfig().set("translate.timeRemaining.en_US", "Time Remaining: ");
        getConfig().set("translate.timeRemaining.uk_UA", "Часу залишилось: ");
        getConfig().set("translate.timeRemaining.ru_RU", "Времени осталось: ");
        
        getConfig().set("translate.pvpStarted.en_US", "Pvp started!");
        getConfig().set("translate.pvpStarted.uk_UA", "Пвп почалось!");
        getConfig().set("translate.pvpStarted.ru_RU", "Пвп началось!");

        getConfig().set("translate.arena.en_US", "Arena: ");
        getConfig().set("translate.arena.uk_UA", "Арена: ");
        getConfig().set("translate.arena.ru_RU", "Арена: ");




        getConfig().set("cooldowns.feed.c3a7ef25-aa49-32b9-a3a5-fcfa1958b68e", 1);
        
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new WorldsInventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new RegisterListeners(this), this);
        getServer().getPluginManager().registerEvents(new CustomGuiListener(this), this);
        getServer().getPluginManager().registerEvents(new PvpWorlds(this), this);
        getServer().getPluginManager().registerEvents(new HubListeners(this), this);
        getServer().getPluginManager().registerEvents(new GuiCheckerListener(this), this);
        getServer().getPluginManager().registerEvents(new LoginJoinListener(this), this);
        getCommand("netherpvp").setExecutor(new netherPvp(this));
        getCommand("guichecker").setExecutor(new GuiChecker());
        getCommand("customize").setExecutor(new CustomizeCommand(this));
        getCommand("pvp").setExecutor(new PvpWorldsCommand(this));
        getCommand("hub").setExecutor(new HubCommand(this));
        getCommand("feed").setExecutor(new FeedCommand(this));
        getCommand("menu").setExecutor(new MainMenuCommand(this));
        getCommand("worlds").setExecutor(new WorldsCommand(this));
        getCommand("rtp").setExecutor(new RtpCommand(this));

        getCommand("language").setExecutor(new LanguageCommand(this, "en_US"));
        getCommand("мова").setExecutor(new LanguageCommand(this, "uk_UA"));
        getCommand("язык").setExecutor(new LanguageCommand(this, "ru_RU"));

//
        getConfig().set("locations.register", new Location(getServer().getWorld("register"), 0.5, 65.0, 0.5, 0, 0));
        getConfig().set("locations.login", new Location(getServer().getWorld("login"), 0.5, 63.0, 0.5, 0, 0));
        getConfig().set("locations.hub", new Location(getServer().getWorld("hub"), -179, 38, 843, 0, 0));

//
        saveConfig();
        saveDefaultConfig();


    }
//        Get utils


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
        saveDefaultConfig();
    }
}