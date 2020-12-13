package BattleShip;

import chat.Resender;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends Application {

    public Stage stage;

    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;
    public Resender resend;
    public Button btn_setTurn;
    @FXML
    public GridPane box_player;
    @FXML
    public GridPane box_enemy;
    @FXML
    public TextField tf_roomNum;
    @FXML
    public Button btn_connectToTheRoom;
    @FXML
    public TextArea ta_gameInfoPanel;
    @FXML
    public TextField tf_usernameInput;
    int ship4 = 1;
    int ship3 = 1;
    int ship2 = 1;
    int ship1 = 1;
    boolean canStart = false;
    Cell lastCell;
    boolean isFirstPlayer = false;
    private boolean isRun;
    private boolean enemyTurn = true;

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


    private void checkMessage(String message) {
        // 0 - ход| 1 - выйграл первый | 2 - выйграл второй | 3 - инфа | 4 - начало игры
        int action = Integer.parseInt(message.charAt(0) + "");
        String info;
        switch (action) {
            case 1:
            case 2: {
                gameOver(action);
                break;
            }
            case 0: {
                info = message.substring(1);
                getStep(info);
                break;
            }
            case 3: {
                info = message.substring(1);
                showMessage(info);
                break;
            }
            case 4: {
                info = message.substring(1);
                startGame(info);
                break;
            }
            default: {
                hasError("");
            }
        }
    }

    private void startGame(String info) {
        ta_gameInfoPanel.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                                Object newValue) {
                ta_gameInfoPanel.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                //use Double.MIN_VALUE to scroll to the top
            }
        });

        isRun = true;
        if (info.equals("1")) {
            showMessage("Вы начинаете первым");
            isFirstPlayer = true;
            startMove();
        } else {
            endMove();
            showMessage("Вы начинаете вторым");
        }

    }

    private void showMessage(String info) {
        ta_gameInfoPanel.appendText("\n" + info);
    }

    public void startConnection() {
        try {
            socket = new Socket("localhost", 65001);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            resend = new Resender(this);
            setDisabledConnect();
            out.println(tf_usernameInput.getText().trim());
            out.println(tf_roomNum.getText());
            resend.start();
            createBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createBoard() {
        Platform.runLater(() -> {
            EventHandler<? super MouseEvent> enemyHandler = (EventHandler<MouseEvent>) event -> {
                if (isRun) {
                    Cell cell = (Cell) event.getSource();
                    cell.shoot();
                    sendMessage("0shoot;" + cell.x + ";" + cell.y);
                    lastCell = cell;
                }
            };

            EventHandler<? super MouseEvent> playerHandler = (EventHandler<MouseEvent>) event -> {
                if (!isRun) {
                    boolean verticalShip = false;
                    if (event.getButton() == MouseButton.PRIMARY || event.getButton() == MouseButton.SECONDARY) {

                        if (event.getButton() == MouseButton.SECONDARY) verticalShip = true;

                        if (ship4 != 0) {
                            Cell cell = (Cell) event.getSource();
                            if (placeShip(new Ship(4, verticalShip), cell.x, cell.y)) ship4--;
                        } else if (ship3 != 0) {
                            Cell cell = (Cell) event.getSource();
                            if (placeShip(new Ship(3, verticalShip), cell.x, cell.y)) ship3--;
                        } else if (ship2 != 0) {
                            Cell cell = (Cell) event.getSource();
                            if (placeShip(new Ship(2, verticalShip), cell.x, cell.y)) ship2--;
                        } else if (ship1 != 0) {
                            Cell cell = (Cell) event.getSource();
                            if (placeShip(new Ship(1, verticalShip), cell.x, cell.y)) ship1--;
                            if(ship1 == 0) {
                                box_player.setDisable(true);
                                sendCanStart();
                            }
                        }
                    }
                }
            };

            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    Cell e1 = new Cell(x, y, true);
                    Cell p1 = new Cell(x, y, false);
                    e1.setOnMouseClicked(enemyHandler);
                    p1.setOnMouseClicked(playerHandler);
                    box_player.add(p1, y, x);
                    box_enemy.add(e1, y, x);
                }
            }
        });

    }

    private void sendCanStart() {
        out.println("canStart");
    }

    public void sendMessage(String message) {
        if(isFirstPlayer) message ="f"+message;
        else message ="s"+message;
        out.println(message);
    }

    public void startMove() {
        enemyTurn = false;
        box_enemy.setDisable(false);
    }

    public void endMove() {
        enemyTurn = true;
        box_enemy.setDisable(true);
    }

    public void getMessage(String message) {
        checkMessage(message);
    }

    private void hasError(String info) {
    }

    private void getStep(String info) {
        String[] coords = info.split(";");
        if(coords[0].equals("shoot")) {
            if (enemyTurn) {
                int x = Integer.parseInt(coords[1]);
                int y = Integer.parseInt(coords[2]);
                Cell cell = getCell(box_player, x, y);
                if (!cell.shoot()) {
                    startMove();
                    sendMessage("0endMove");
                } else {
                    sendMessage("0continue");
                }
            } else {
                int x = Integer.parseInt(coords[1]);
                int y = Integer.parseInt(coords[2]);
                Cell cell = getCell(box_enemy, x, y);
                cell.shoot();

            }
        } else if(coords[0].equals("endMove")){
            endMove();
        } else if(coords[0].equals("continue")){
            lastCell.setFill(Color.RED);
        }
    }

    public Cell getCell(GridPane from, int x, int y) {
        return (Cell) from.getChildren().get(y * 10 + x);
    }


    private void gameOver(int action) {
    }

    public void setTurn() {
        endMove();
    }

    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[]{
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1),
                new Point2D(x + 1, y + 1),
                new Point2D(x - 1, y + 1),
                new Point2D(x + 1, y - 1),
                new Point2D(x - 1, y - 1)
        };
        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point2D p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell(box_player, (int) p.getX(), (int) p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }

    private boolean isValidPoint(double x, double y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.type;

        if (ship.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(box_player, x, i);
                if (cell.ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {

                    if (neighbor.ship != null)
                        return false;
                }
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(box_player, i, y);
                if (cell.ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {

                    if (neighbor.ship != null)
                        return false;
                }
            }
        }

        return true;
    }

    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.type;

            if (ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(box_player, x, i);
                    cell.ship = ship;
                    cell.setFill(Color.DARKGRAY);
                    cell.setStroke(Color.BLACK);

                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(box_player, i, y);
                    cell.ship = ship;
                    cell.setFill(Color.DARKGRAY);
                    cell.setStroke(Color.BLACK);

                }
            }

            return true;
        }

        return false;
    }
}
