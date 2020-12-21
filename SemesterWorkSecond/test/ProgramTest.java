import client.GameWindow;
import server.Connection;
import server.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.Socket;


class ProgramTest {
    static private Server server;
    private GameWindow gameWindow;
    @BeforeAll
    static void setUp(){
        new Thread(() -> server = new Server()).start();

    }


    @Test
    void connectionTest() throws Exception{
        Thread.sleep(1000);
        Socket socket = new Socket("localhost",65001);
        Connection con = new Connection(socket,server);
        System.out.println(server.connections.toString());
        Assertions.assertEquals(con, server.connections.get(0));
    }

    @Test
    void createRoom(){
        server.createRoom(1);
        Assertions.assertEquals(server.roomList.get(0).number, 1);
    }

    @Test
    void gameWindowStart() {

    }

    @Test
    void gameWindowStartConnection() {
    }

    @Test
    void gameWindowSendMessage() {
    }

    @Test
    void gameWindowStartMove() {
    }

    @Test
    void gameWindowEndMove() {
    }

    @Test
    void gameWindowGetMessage() {
    }

    @Test
    void gameWindowGetCell() {
    }

    @Test
    void gameWindowDisconnect() {
    }

    @Test
    void gameWindowPlaceShip() {
    }

    @Test
    void gameWindowRestartWindows() {
    }

    @Test
    void CellShoot() {
    }

    @Test
    void ServerRoomIsCreat() {
    }

    @Test
    void ServerConnectToRoom() {
    }

    @Test
    void ServerCreateRoom() {
    }

    @Test
    void ServerCloseAll() {
    }

    @Test
    void ConnectionCheckCount(){

    }
}
