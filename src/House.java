import Devices.SmartDevice;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class House {
    private String name;
    private String geoLocation;
    private TreeMap<String, Room> rooms = new TreeMap<>();

    public House(String geoLocation, String name) {
        this.geoLocation = geoLocation;
        this.name = name;
    }

    public House(String name){
        this.name = name;
    }

    public void addRoom(Room room){
        rooms.put(room.getName(), room);
    }
    public TreeMap<String, Room> getRooms() {
        return rooms;
    }

    public String getRoomNames(){
        StringBuilder roomNames = new StringBuilder();
        for (Room room : rooms.values()) {
            roomNames.append(room.getName());
        }
        return roomNames.toString();
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public String getName() {
        return name;
    }
    public Set<RoomType> getRoomTypes(){
        return rooms.values().stream()
                .map(Room::getType)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        if (geoLocation != null) {
            return "Nazwa: " + name + "\nGeoLokacja: " + geoLocation;
        } else{
            return "Nazwa: " + name + "\nGeolokacja: Zmień ustawienia prywatności w ustawieniach";
        }

    }
}
