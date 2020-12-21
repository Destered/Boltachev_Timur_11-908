package server;

import client.GameWindow;

import java.io.IOException;

public class Resender extends Thread {
    GameWindow game;
    private boolean stoped;

    public Resender(GameWindow game) {
        this.game = game;
    }

    public Resender() {
    }

    public void setStop() {
        stoped = true;
    }

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
