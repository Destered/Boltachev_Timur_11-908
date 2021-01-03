package core;

import messages.ServiceCommand;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import sun.rmi.runtime.Log;
import utility.FileDataPicker;

import java.util.HashSet;
import java.util.logging.Level;

public class BotCore extends TelegramLongPollingBot {
    private  FileDataPicker dataPicker = new FileDataPicker();
    private  int timeoutConnection = 2;
    private String token;
    private AbsSender absSender;
    private HashSet<String> autorisedUser = new HashSet<>();

    public BotCore(){
        token = dataPicker.getToken();
        absSender = new DefaultAbsSender(new DefaultBotOptions()) {
            @Override
            public String getBotToken() {
                return token;
            }
        };
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String messageText = update.getMessage().getText();
        if(messageText.startsWith("/authorization")){
            String authorization = messageText.replaceAll("/authorization","").trim();
            System.out.println(authorization);
            System.out.println(message.getChat().getUserName());
            if(authorization.equals("12345")) {
                autorisedUser.add(message.getChat().getUserName());
                sendMsg(message.getChatId().toString(),"Вот мы и познакомились");
            } else {
                sendMsg(message.getChatId().toString(),"Нет, я вас не знаю");
            }
        }
        if(autorisedUser.contains(message.getChat().getUserName())){

            if(messageText.startsWith("/")){
                ServiceCommand.executeCommand(messageText,absSender,message.getChatId(),getUserName(message));
            }else {
                sendMsg(message.getChatId().toString(), messageText);
            }
        }
        else{
            sendMsg(message.getChatId().toString(), "Я вас не знаю\nИспользуйте /authorization");
        }
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "Тинка";
    }

    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

    public String getBotToken() {
        return token;
    }
}
