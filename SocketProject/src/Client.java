import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner scan;
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    Resender resend;

    public Client() {
        scan = new Scanner(System.in);
        try {
            socket = new Socket("localhost", 65001);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            resend = new Resender();
            resend.start();

            System.out.println("Введите свой ник:");
            out.println(scan.nextLine());
            String str = "";
            while (!str.equals("\\q")) {
                str = scan.nextLine();
                out.println(str);
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        } finally {
            close();
        }
    }

        private void close(){
            try {
                resend.setStop();
                in.close();
                out.close();
                socket.close();
            } catch (Exception e) {
                System.err.println("Close error!");
            }
        }

    private class Resender extends Thread {
        private boolean stoped;

        public void setStop() {
            stoped = true;
        }

        @Override
        public void run() {
            try {
                while (!stoped) {
                    String str = in.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                System.err.println("Ошибка при получении сообщения.");
                e.printStackTrace();
            }
        }
    }

}
