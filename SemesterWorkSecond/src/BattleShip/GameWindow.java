package BattleShip;

import chat.Resender;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameWindow extends Application {

    public Stage stage;

   public Socket socket;
   public BufferedReader in;
   public PrintWriter out;
   public Resender resend;
    public Button btn_init;
    private boolean isRun;
   @FXML
    public VBox box_player;
    @FXML
    public VBox box_enemy;
    @FXML
    Board enemyBoard;
    Board playerBoard;
    @FXML
    public TextField tf_roomNum;
    private boolean enemyTurn = false;
    @FXML
    public Button btn_connectToTheRoom;
    @FXML
    public TextArea ta_gameInfoPanel;
    @FXML
    public TextField tf_usernameInput;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    createContent();

    }

    private void createContent() {
        AnchorPane root;
        stage = new Stage();
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/BattleShip/gameBoard.fxml"));
            root = loader.load();
            stage.setTitle("BattleShip");
            stage.setScene(new Scene(root, 327, 792));
            stage.setResizable(false);
            stage.sizeToScene();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        stage.show();

    }

    private void setDisabledConnect() {
            btn_connectToTheRoom.setDisable(true);
            tf_usernameInput.setEditable(false);
            tf_roomNum.setEditable(false);
    }

    public void initBoard() {
        EventHandler<? super MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Cell cell = (Cell) event.getSource();
                enemyTurn = !cell.shoot();
                sendMessage("0"+ cell.x + ";" + cell.y);
            }
        };

        for (int y = 0; y < 10; y++) {
            HBox eBox = new HBox();
            HBox pBox = new HBox();
            for (int x = 0; x < 10; x++) {
                Cell e1 = new Cell(x, y,true);
                Cell p1 = new Cell(x, y,false);
                e1.setOnMouseClicked(handler);
                eBox.getChildren().add(e1);
                pBox.getChildren().add(p1);
            }
            box_player.getChildren().add(pBox);
            box_enemy.getChildren().add(eBox);
        }
    }

    private void checkMessage(String message) {
        // 0 - ход| 1 - выйграл первый | 2 - выйграл второй | 3 - инфа | 4 - начало игры
        int action = Integer.parseInt(message.charAt(0) +"");
        String info;
        switch(message.charAt(0)){
            case '1':
            case '2': {
                gameOver(action);
                break;
            }
            case '0':{
                info = message.substring(1);
                getStep(info);
                break;
            }
            case '3':{
                info = message.substring(1);
                showMessage(info);
                break;
            }
            default:{
                hasError("");
            }
        }
    }

    private void showMessage(String info) {

    }

    public void startConnection() {
        try {
            socket = new Socket("localhost", 65001);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            resend = new Resender(this);
            setDisabledConnect();
            resend.start();
            out.println("3"+tf_usernameInput.getText().trim());
            out.println("3"+tf_roomNum.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
       out.println(message);
       ta_gameInfoPanel.setText(ta_gameInfoPanel.getText() + "\n" + message);
    }

    public void startMove(){

    }

    public void endMove(){

    }

    public void getMessage(String message) {
        checkMessage(message);
    }

    private void hasError(String info) {
    }

    private void getStep(String info) {
        String [] coords = info.split(";");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        Cell cell = getCell(x,y);
        cell.shoot();
    }

    private Cell getCell(int x, int y){
        ObservableList row = box_player.getChildren();
        Cell cell = (Cell)row.get(y);
        return cell;
    }

    private void gameOver(int action) {
    }
}
