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
    public void startServer() throws Exception{
        if(server != null) server.closeAll();
        server = new Server();
        server.start();
        Thread.sleep(100);
    }
    @AfterEach
    public void disconnect() throws Exception{
        if(socket != null){
            socket.close();
        }
        socket = null;
        Thread.sleep(100);
    }



    private void setFlow(Socket socket) throws Exception{
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    }

    private Socket createSocket() throws Exception{
        socket = new Socket("localhost",65001);
        Thread.sleep(200);
        return socket;
    }

    @Test
    void checkDisconnect() throws Exception{
        socket = createSocket();
        Connection connection = server.connections.get(0);
        Socket socket2 = createSocket();
        Connection connection2 = server.connections.get(1);
        setFlow(socket);
        out.println("Oleg");
        out.flush();
        out.println("2");
        out.flush();
        String getLine = in.readLine();
        out.println("1\\q");
        out.flush();
        BufferedReader socketIn2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        String equals = socketIn2.readLine();
        Assertions.assertEquals(equals,"3Oleg больше не с нами");
    }

    @Test
    void connectionTest() throws Exception{
        socket = new Socket("localhost",65001);
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
        Thread.sleep(200);
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
        Thread.sleep(200);
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
        Thread.sleep(200);
        GameRoom serverRoom = server.roomList.get(0);
        Assertions.assertEquals(serverRoom,connection.getRoom());
    }

    @Test
    void checkMessageReceive() throws Exception{
        socket = createSocket();
        Connection connection = server.connections.get(0);
        setFlow(socket);
        out.println("Oleg");
        out.flush();
        out.println("2");
        out.flush();
        Thread.sleep(200);
        connection.out.println("message");
        connection.out.flush();
        String getLine = in.readLine();
        getLine = in.readLine();
        Assertions.assertEquals("message",getLine);
    }

    @Test
    void playerReady() throws Exception {
        socket = createSocket();
        Connection connection = server.connections.get(0);
        setFlow(socket);
        out.println("Oleg");
        out.flush();
        out.println("2");
        out.flush();
        String getLine = in.readLine();
        out.println("canStart");
        out.flush();
        getLine = in.readLine();
        Assertions.assertEquals(getLine,"3Oleg готов");
    }

    @Test
    void closeServer() throws Exception {
        socket = createSocket();
        Thread.sleep(150);
        server.connections.get(0).close(true);

        Assertions.assertTrue(server.server.isClosed());

    }

/*



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
