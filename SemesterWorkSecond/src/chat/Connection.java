package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

public class Connection extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private GameRoom curRoom;
    private Server server;
    private boolean isWorking = true;
    private String name = "";
    private int room = 0;

    public Connection(Socket socket, Server server) {
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
            try {
                room = Integer.parseInt(in.readLine().substring(1));
            } catch (Exception e) {
                out.println("Неправильный номер");
                this.close(true);
            }
            if(server.roomIsCreat(room))curRoom=server.connectToRoom(room);
            else curRoom=server.createRoom(room);
            if (isWorking) {
                if (curRoom != null) {
                    curRoom.user.add(this);
                    synchronized (curRoom) {
                        Iterator<Connection> iter = curRoom.user.iterator();
                        while (iter.hasNext()) {
                            ((Connection) iter.next()).out.println(name + " теперь в комнате");
                        }
                    }

                }else{
                    out.println("Комната заполнена");
                }
            }

                String str = "";
                while (true) {
                    str = in.readLine();
                    if (str.equals("\\q")) break;

                    synchronized (curRoom) {
                        Iterator<Connection> iter = curRoom.user.iterator();
                        while (iter.hasNext()) {
                            ((Connection) iter.next()).out.println(str);
                        }
                    }
                }
            synchronized (curRoom) {
                Iterator<Connection> iter = curRoom.user.iterator();
                while (iter.hasNext()) {
                    ((Connection) iter.next()).out.println(name + " больше не с нами");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(true);
        }
    }

    public void close(boolean fullclose) {
        try {
            curRoom.user.remove(this);
            server.connections.remove(this);
            in.close();
            if (fullclose) {
                isWorking = false;
                out.println("\\q");
                socket.close();
            }
            out.close();
            if (server.connections.size() == 0) {
                server.closeAll();
                System.exit(0);
            }
        } catch (Exception e) {
            System.err.println("Траблы с закрытием");
        }
    }
}