package core;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class BotStarter {
    public static long startTime = System.currentTimeMillis();

        public static void main(String[] args) {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            BotCore bot = new BotCore();
            try {
                telegramBotsApi.registerBot(bot);
            } catch (TelegramApiRequestException e) {
                e.printStackTrace();
            }
        }
    }
