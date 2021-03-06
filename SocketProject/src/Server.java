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
    public List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    public List<GameRoom> roomList = new ArrayList<>();
    public ServerSocket server;

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

    public boolean roomIsCreat(int number){
        boolean isCreate = false;
        for(GameRoom room:roomList){
            if(room.number == number)isCreate = true;
        }
        return isCreate;
    }

    public GameRoom connectToRoom(int number){
            for(GameRoom room:roomList){
                if(room.number == number) return room;
            }
            return null;
    }

    public GameRoom createRoom(int number){
        GameRoom room = new GameRoom(number);
        roomList.add(room);
        return room;
    }

    public void closeAll() {
        try {
            server.close();

            synchronized(connections) {
                Iterator<Connection> iter = connections.iterator();
                Iterator<GameRoom> iterRoom = roomList.iterator();
                while(iter.hasNext()) {
                    ((Connection) iter.next()).close(true);
                }
                while(iterRoom.hasNext()) {
                    ((GameRoom) iterRoom.next()).close(true);
                }
            }
        } catch (Exception e) {
            System.err.println("Error close");
        }
    }

}
