package messages;

import core.BotStarter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * Суперкласс для сервисных команд
 */
public class ServiceCommand{

    public static void executeCommand(String command,AbsSender absSender,Long chatId,String username){
        switch(command){
            case"/start":{
                sendAnswer(absSender,chatId,username,"Используй меня...");
                break;
            }
            case"/help":{
                sendAnswer(absSender,chatId,username,"Тут должна быть вроде помощь");
                break;
            }
            case"/time":{
                sendAnswer(absSender,chatId,username,"Часики натикали уже " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
                break;
            }
            case"/work":{
                sendAnswer(absSender,chatId,username,"Я тут уже... ээээ...\nА, точно: "+ ((int)(System.currentTimeMillis() - BotStarter.startTime)/1000) + " секунд");
                break;
            }
            case"/joke":{
                sendAnswer(absSender,chatId,username,"Шуток я пока не знаю =(");
                break;
            }

        }
    }


   public static void sendAnswer(AbsSender absSender, Long chatId, String userName, String text) {
       System.out.println("Message: " + text);
        SendMessage message = new SendMessage();
        //включаем поддержку режима разметки, чтобы управлять отображением текста и добавлять эмодзи
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
