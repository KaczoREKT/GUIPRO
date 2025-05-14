package AModel;

import House.*;

import java.util.ArrayList;
import java.util.TreeMap;

public class HouseModel {
    private TreeMap<String, House> houses = new TreeMap<>();

    public TreeMap<String, House> getHousesMap() {
        return houses;
    }

    public String getHousesNames() {
        return houses.keySet().toString();
    }

    public void addHouse(House house) {
        houses.put(house.getName(), house);
    }

    public void removeHouse(String houseName) {
        houses.remove(houseName);
    }

    public boolean exists(String name) {
        return houses.containsKey(name);
    }



    public House getHouse(String name) {
        return houses.get(name);
    }

    public String getHousesInfo() {
        if (houses.isEmpty()) {
            return "No houses found.";
        }
        StringBuilder housesString = new StringBuilder("=== List of Houses ===\n");
        for (House house : houses.values()) {
            housesString.append(house).append("\n");
        }

        return housesString.toString();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (House house : houses.values()) {
            sb.append(house.toString()).append("\n");
        }
        return sb.toString();
    }


    public boolean isHousesEmpty() {
        return houses.isEmpty();
    }

    public ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (House house : houses.values()){
            rooms.addAll(house.getRooms().values());
        }
        return rooms;
    }
}
