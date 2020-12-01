import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientProgram extends Application {
    public Button btn_send;
    public TextArea tf_chatField;
    public TextField tf_chatInput;
    public Button btn_connect;
    public AnchorPane rootWindow;
    public Stage stage;
    public Button btn_createRoom;
    public TextField tf_username;
    public TextField tf_roomNum;
    Scanner scan;
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    Resender resend;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = null;
        stage = new Stage();
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/chatWindow.fxml"));
            root = loader.load();
            stage.setTitle("Чат");
            stage.setScene(new Scene(root, 600, 400));
            stage.sizeToScene();
            stage.show();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public void sendMessage(ActionEvent actionEvent) {
        String text = tf_chatInput.getText().trim();
        if (text.equals("\\q")) {
            close();
        } else {
            out.println(text);
        }
        tf_chatInput.setText("");
    }

    public void getMessage(String message) {
        tf_chatField.setText(tf_chatField.getText() + "\n" + message);
    }


    public void connectRoom(ActionEvent actionEvent) {
        setDisabledConnect(true);
        init("connect");
    }

    public void createRoom(ActionEvent actionEvent) {
        setDisabledConnect(true);
        init("create");
    }

    private void setDisabledConnect(boolean disabled) {
        tf_chatInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    out.println(tf_chatInput.getText());
                    tf_chatInput.setText("");
                }
            }
        });
        if (disabled) {
            btn_connect.setDisable(true);
            btn_createRoom.setDisable(true);
            btn_send.setDisable(false);
            tf_roomNum.setEditable(false);
            tf_username.setEditable(false);
        } else {
            btn_connect.setDisable(false);
            btn_createRoom.setDisable(false);
            btn_send.setDisable(true);
            tf_roomNum.setEditable(true);
            tf_username.setEditable(true);
        }

    }

    public void init(String action) {
        try {
            socket = new Socket("localhost", 65001);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            resend = new Resender();
            resend.start();
            out.println(tf_username.getText().trim());
            out.println(action);
            out.println(tf_roomNum.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            in.close();
            out.close();
            resend.setStop();
            socket.close();
            setDisabledConnect(false);
        } catch (IOException e) {
            e.printStackTrace();
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
                    if(str.equals("\\q")){
                        close();
                    }else getMessage(str);
                }
            } catch (IOException e) {
                System.err.println("Ошибка при получении сообщения.");
                e.printStackTrace();
            }
        }
    }


}
