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
    private List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    private List<Connection> connections1 =
            Collections.synchronizedList(new ArrayList<Connection>());
    private List<Connection> connections2 =
            Collections.synchronizedList(new ArrayList<Connection>());
    private List<Connection> connections3 =
            Collections.synchronizedList(new ArrayList<Connection>());
    private ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(65001);

            while (true) {
                Socket socket = server.accept();
                Connection con = new Connection(socket);
                connections.add(con);
                con.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            server.close();

            synchronized(connections) {
                Iterator<Connection> iter = connections.iterator();
                Iterator<Connection> iter1 = connections1.iterator();
                Iterator<Connection> iter2 = connections2.iterator();
                Iterator<Connection> iter3 = connections3.iterator();
                while(iter.hasNext()) {
                    ((Connection) iter.next()).close(true);
                }
                while(iter1.hasNext()) {
                    ((Connection) iter1.next()).close(true);
                }
                while(iter2.hasNext()) {
                    ((Connection) iter2.next()).close(true);
                }
                while(iter3.hasNext()) {
                    ((Connection) iter3.next()).close(true);
                }
            }
        } catch (Exception e) {
            System.err.println("Error close");
        }
    }

    private class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;
        private List<Connection> curRoom = connections;

        private String name = "";
        private int room = 0;

        public Connection(Socket socket) {
            this.socket = socket;

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
                    room = Integer.parseInt(in.readLine());
                }catch(Exception e){
                    out.println("Неправильный номер");
                    this.close(false);
                    Connection con = new Connection(socket);
                    connections.add(con);
                    con.start();
                }
                switch(room){
                    case 1:{
                        curRoom = connections1;
                        break;
                    }
                    case 2:{
                        curRoom = connections2;
                        break;
                    }
                    case 3:{
                        curRoom = connections3;
                        break;
                    }
                }
                curRoom.add(this);
                synchronized(curRoom) {
                    Iterator<Connection> iter = curRoom.iterator();
                    while(iter.hasNext()) {
                        ((Connection) iter.next()).out.println(name + " теперь в комнате " + room);
                    }
                }

                String str = "";
                while (true) {
                    str = in.readLine();
                    if(str.equals("\\q")) break;

                    synchronized(curRoom) {
                        Iterator<Connection> iter = curRoom.iterator();
                        while(iter.hasNext()) {
                            ((Connection) iter.next()).out.println(name + ": " + str);
                        }
                    }
                }

                synchronized(curRoom) {
                    Iterator<Connection> iter = curRoom.iterator();
                    while(iter.hasNext()) {
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
                if(!fullclose) {
                    socket.close();
                }
                in.close();
                out.close();
                curRoom.remove(this);
                connections.remove(this);
                if (connections.size() == 0) {
                    Server.this.closeAll();
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Траблы с закрытием");
            }
        }
    }

}
