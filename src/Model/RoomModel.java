package Model;

import House.Room;
import House.RoomType;

import java.util.TreeMap;

public class RoomModel {
    public TreeMap<String, Room> rooms = new TreeMap<>();
    public RoomModel() {
    }

    public boolean roomExists(String choice) {
        return rooms.containsKey(choice);
    }

    public void addRoom(Room room) {
        rooms.put(room.getName(), room);
    }
    public void removeRoom(Room room) {
        rooms.remove(room.getName());
    }
    public Room getRoom(String choice) {
        return rooms.get(choice.toLowerCase());
    }
}
