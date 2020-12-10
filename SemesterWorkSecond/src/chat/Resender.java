package chat;

import BattleShip.GameWindow;

import java.io.IOException;

public class Resender extends Thread {
    private boolean stoped;
    GameWindow game;

    public void setStop() {
        stoped = true;
    }

    public Resender(GameWindow game){
        this.game = game;
    }
    public Resender(){ }

    @Override
    public void run() {
        try {
            while (!stoped) {
                String str = game.in.readLine();
                game.getMessage(str);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при получении сообщения.");
            e.printStackTrace();
        }
    }
}
