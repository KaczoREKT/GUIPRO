import Devices.SmartDevice;

import java.util.ArrayList;
import java.util.TreeMap;

public class HouseManager {
    private TreeMap<String, House> houses = new TreeMap<>();

    public TreeMap<String, House> getHouses() {
        return houses;
    }
    public String getHousesNames(){
        return houses.keySet().toString();
    }

    public void addHouse(House house) {
        houses.put(house.getName(), house);
    }

    public void removeHouse(String houseName) {
        houses.remove(houseName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (houses.isEmpty()) {
            sb.append("Brak zapisanych domów.");
        } else {
            for (House house : houses.values()) {
                sb.append(house).append("\n");
            }
        }
        return sb.toString();
    }

}
