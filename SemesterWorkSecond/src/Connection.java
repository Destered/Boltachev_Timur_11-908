import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

class Connection extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private Server server;

    private String name = "";
    private int roomNum = 0;
    private GameRoom room;

    public Connection(Socket socket,Server server) {
        this.socket = socket;
        this.server = server;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
            close(true);
        }
    }

    public void run() {
        try {
            name = in.readLine();
            String action = in.readLine();
            roomNum = Integer.parseInt(in.readLine());
            switch (action){
                case"create":{
                   room = server.createRoom(roomNum);
                   break;
                }
                case"connect":{
                    room = server.checkRoom(roomNum);
                    break;
                }
                default:{
                    close(false);
                    start();
                }
            }

            if(room != null){
                if(!room.add(this)){
                    close(true);
                }
            }
            if (action.equals("create")) {
                out.println("Комната создана");
            } else if (action.equals("connect")){
                out.println("Подключенно");
            }
            synchronized(room) {
                Iterator<Connection> iter = room.players.iterator();
                while(iter.hasNext()) {
                    ((Connection) iter.next()).out.println(name + " теперь в комнате");
                }
            }
            String str = "";
            while (true) {
                str = in.readLine();
                if (str.equals("\\q")) break;

                synchronized (room.players) {
                    Iterator<Connection> iter = room.players.iterator();
                    while (iter.hasNext()) {
                        ((Connection) iter.next()).out.println(name + ": " + str);
                    }
                }
            }

            synchronized (room.players) {
                Iterator<Connection> iter = room.players.iterator();
                while (iter.hasNext()) {
                    ((Connection) iter.next()).out.println(name + " больше не с нами");
                }
            }
        }catch(IOException e){
                e.printStackTrace();
            } finally{
                close(true);
            }
        }

    public void close(boolean fullclose) {
        try {
            if(!fullclose) {
                socket.close();
            }
            in.close();
            out.close();
            room.players.remove(this);
            server.connections.remove(this);
            if (server.connections.size() == 0) {
                server.closeAll();
                System.exit(0);
            }
        } catch (Exception e) {
            System.err.println("Траблы с закрытием");
        }
    }
}
