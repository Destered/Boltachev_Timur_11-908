import client.GameWindow;
import org.junit.jupiter.api.*;
import server.Connection;
import server.GameRoom;
import server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class ProgramTest {
    static private Server server;
    private GameWindow gameWindow;
    private BufferedReader in;
    private PrintWriter out;
    static private Socket socket;
    @BeforeEach
    public void startServer(){
        if(server != null) server.closeAll();
        server = new Server();
        server.start();
    }
    @AfterEach
    public void disconnect() throws Exception{
        if(socket != null){
            socket.close();
        }
        socket = null;
    }

    @Test
    void connectionTest() throws Exception{
        socket = new Socket("localhost",65001);
        Connection con = new Connection(socket,server);
        Connection con1 = server.connections.get(0);
        Assertions.assertEquals(con1, con);
    }

    private void setFlow(Socket socket) throws Exception{
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    }

    private Socket createSocket() throws Exception{
        socket = new Socket("localhost",65001);
        Thread.sleep(1000);
        return socket;
    }




    @Test
    void createRoom(){
        server.createRoom(1);
        Assertions.assertEquals(server.roomList.get(0).number, 1);
    }

   @Test
    void serverDisconnect() throws Exception {
       socket = createSocket();
       server.connections.get(0).close(true);
        Assertions.assertTrue(server.connections.isEmpty());
    }

    @Test
    void setConnectionName() throws Exception {
        socket = createSocket();
        Connection connection = server.connections.get(0);
        setFlow(socket);
        out.println("Oleg");
        out.flush();
        Thread.sleep(1000);
        Assertions.assertEquals("Oleg",connection.getUsername());

    }

    @Test
    void setRoomNum() throws Exception {
        socket = createSocket();
        Connection connection = server.connections.get(0);
        setFlow(socket);
        out.println("Oleg");
        out.flush();
        out.println("2");
        out.flush();
        Thread.sleep(1000);
        Assertions.assertEquals(2,connection.getRoomNum());
    }

    @Test
    void setGameRoom() throws Exception {
        socket = createSocket();
        Connection connection = server.connections.get(0);
        setFlow(socket);
        out.println("Oleg");
        out.flush();
        out.println("2");
        out.flush();
        Thread.sleep(1000);
        GameRoom serverRoom = server.roomList.get(0);
        Assertions.assertEquals(serverRoom,connection.getRoom());
    }
/*


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
