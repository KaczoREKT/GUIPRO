package AModel;

import Devices.SmartDevice;
import House.*;


import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

public class RoomModel {
    public TreeMap<String, Room> rooms = new TreeMap<>();
    public RoomModel() {
    }

    public TreeMap<String, Room> getRoomsMap() {
        return rooms;
    }

    public boolean roomExists(String choice) {
        return rooms.containsKey(choice);
    }

    public Room findRoomWithDevice(String deviceName) {
        for (Room room : rooms.values()) {
            if (room.hasDevice(deviceName)) {
                return room;
            }
        }
        return null;
    }

    public void addRoom(Room room) {
        rooms.put(room.getName(), room);
    }
    public void removeRoom(String room) {
        rooms.remove(room);
    }
    public Room getRoom(String choice) {
        return rooms.get(choice.toLowerCase());
    }

    public void addDevice(SmartDevice device, String roomName){
        for (Room room : rooms.values()){
            if (Objects.equals(room.getName(), roomName)){
                room.addDevice(device);
            }
        }
    }

    public void removeDevice(String deviceName) {
        Room room = findRoomWithDevice(deviceName);
        if (room != null) {
            room.removeDevice(deviceName);
        }
    }
    public String getRoomsInfo() {
        if (rooms.isEmpty()) {
            return "No rooms found.";
        }
        StringBuilder roomsString = new StringBuilder("=== List of Rooms ===\n");
        for (Room room : rooms.values()) {
            roomsString.append(room.toString()).append("\n");
        }

        return roomsString.toString();
    }
    public String getRoomsNames() {
        return String.join(", ", rooms.keySet());
    }

    public ArrayList<RoomType> getRoomsTypes() {
        ArrayList<RoomType> roomTypes = new ArrayList<>();
        for (Room room : rooms.values()){
            roomTypes.add(room.getType());
        }
        return roomTypes;
    }
}
