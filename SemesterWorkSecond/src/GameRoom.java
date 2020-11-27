

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameRoom {
    public int roomNum;
    public List<Connection> players = Collections.synchronizedList(new ArrayList<>());
    public GameRoom(int num){
        roomNum = num;
    }

    public boolean add(Connection connection){
        try {
            players.add(connection);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
