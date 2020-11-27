import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Server {
         List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    List<GameRoom> roomList =
            Collections.synchronizedList(new ArrayList<GameRoom>());
    private ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(65001);

            while (true) {
                Socket socket = server.accept();
                Connection con = new Connection(socket,this);
                connections.add(con);
                con.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    void closeAll() {
        try {
            server.close();

            synchronized(connections) {
                Iterator<Connection> iter = connections.iterator();
                while(iter.hasNext()) {
                    ((Connection) iter.next()).close(true);
                }
            }
        } catch (Exception e) {
            System.err.println("Error close");
        }
    }

    public GameRoom checkRoom(int num){
        for(GameRoom room : roomList){
            if (room.roomNum == num){
                return room;
            }
        }
        return null;
    }

    public GameRoom createRoom(int roomNum) {
        boolean isAvailable = true;
        for(GameRoom room : roomList){
            if (room.roomNum == roomNum){
                isAvailable = false;
            }
        }
        if(isAvailable){
            GameRoom room = new GameRoom(roomNum);
            roomList.add(room);
            return room;
        } else{
            return null;
        }
    }
}
