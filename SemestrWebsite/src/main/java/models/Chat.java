package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Chat {
    static int lastFreeIndex = 0;
    boolean firstIter = true;
    private static List<Message> chatStorage = new ArrayList<>();

    public int addMessage(String text, String username) {
        if (lastFreeIndex == 100) {
            lastFreeIndex = 0;
            List<Message> newStorage = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                newStorage.add(chatStorage.get(80+i));
            }
            chatStorage = newStorage;
        }
        Message message = new Message(lastFreeIndex, username, text);
        if (firstIter) {
            chatStorage.add(message);
        } else {
            chatStorage.set(lastFreeIndex, message);
        }
        lastFreeIndex++;
        return lastFreeIndex;
    }

    public List getNewMessage() {
        return chatStorage;
    }
}
