package Model;

import House.House;

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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (House house : houses.values()) {
            sb.append(house).append("\n");
        }
        return sb.toString();
    }
}
