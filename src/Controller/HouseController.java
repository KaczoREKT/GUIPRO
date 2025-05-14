package Controller;

import House.*;
import AModel.HouseModel;
import Other.Settings;
import java.util.Random;
import java.util.TreeMap;

public class HouseController extends AbstractController {
    private HouseModel houseModel;

    public HouseController(HouseModel model) {
        this.houseModel = model;
    }

    public TreeMap<String, House> getHousesMap() {
        return houseModel.getHousesMap();
    }
    public String getHousesNames() {
        return houseModel.getHousesNames();
    }

    public String generateRandomGeoLocation() {
        Random random = new Random();
        double latitude = -90 + 180 * random.nextDouble();    // -90 to 90
        double longitude = -180 + 360 * random.nextDouble();  // -180 to 180

        // Round to 6 decimal places
        latitude = Math.round(latitude * 1_000_000.0) / 1_000_000.0;
        longitude = Math.round(longitude * 1_000_000.0) / 1_000_000.0;

        return latitude + ", " + longitude;
    }
    public void addHouseToSet(String name) {
        House house = !Settings.isEnabled("geoLocation")
                ? new House(name)
                : new House(generateRandomGeoLocation(), name);
        houseModel.addHouse(house);
    }

    public boolean removeHouseFromSet(String name) {
        if (houseModel.exists(name)) {
            houseModel.removeHouse(name);
            return true;
        } else {
            return false;
        }
    }
    public boolean housesExists(){
        return houseModel.isHousesEmpty();
    }
    public boolean houseExists(String name) {
        return houseModel.exists(name);
    }
    
    public String getHousesInfo() {
        return houseModel.getHousesInfo();
    }

    public void addRoomToHouse(String houseName, String roomName, String choice) {
        House house = houseModel.getHouse(houseName);
        if (house == null) {
            System.out.println("House not found: " + houseName);
            return;
        }

        RoomType type;
        try {
            type = RoomType.valueOf(choice.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid room type: " + choice);
            return;
        }

        Room room = new Room(roomName, type);
        house.addRoom(room);
    }

    // Znajduje dom zawierający pokój o podanej nazwie
    public House findHouseContainingRoom(String roomName) {
        for (House house : houseModel.getHousesMap().values()) {
            if (house.getRooms().containsKey(roomName)) {
                return house;
            }
        }
        return null; // Nie znaleziono
    }

    // Usuwa pokój z domu, jeśli istnieje
    public void removeRoom(String roomName) {
        House house = findHouseContainingRoom(roomName);
        if (house != null) {
            house.getRooms().remove(roomName);
            System.out.println("Room \"" + roomName + "\" removed from house: " + house.getName());
        } else {
            System.out.println("Room \"" + roomName + "\" not found in any house.");
        }
    }
}
