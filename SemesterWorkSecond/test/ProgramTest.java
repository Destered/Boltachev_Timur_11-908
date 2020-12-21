import client.GameWindow;
import org.junit.jupiter.api.BeforeEach;
import server.Connection;
import server.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;


class ProgramTest {
    static private Server server;
    private GameWindow gameWindow;
    @BeforeEach
    public void startServer(){
        if(server != null) server.closeAll();
        server = new Server();
        server.start();
    }




    @Test
    void connectionTest() throws Exception{
        Socket socket = new Socket("localhost",65001);
        Connection con = new Connection(socket,server);
        Connection con1 = server.connections.get(0);
        Assertions.assertEquals(con1, con);
    }

    @Test
    void createRoom(){
        server.createRoom(1);
        Assertions.assertEquals(server.roomList.get(0).number, 1);
    }

   @Test
    void serverDisconnect() throws Exception {
           Socket socket = new Socket("localhost",65001);
           Thread.sleep(1000);
       server.connections.get(0).close(true);
        Assertions.assertTrue(server.connections.isEmpty());
    }

    @Test
    void gameWindowStartConnection() {

    }

/*
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

    }*/
}
