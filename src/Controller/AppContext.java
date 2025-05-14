package Controller;

import Devices.*;
import House.*;
import Other.Settings;

import java.util.*;
import java.util.stream.Collectors;

public class AppContext {
    private TreeMap<String, House> houseTreeMap = new TreeMap<>();

    // DOMY
    public TreeMap<String, House> getHouseMap() {
        return houseTreeMap;
    }

    public String getHousesNames() {
        return houseTreeMap.keySet().toString();
    }

    public void addToHouseMap(String name) {
        House house = !Settings.isEnabled("geoLocation")
                ? new House(name)
                : new House(generateRandomGeoLocation(), name);
        houseTreeMap.put(house.getName(), house);
    }

    public boolean removeFromHouseMap(String name) {
        if (houseTreeMap.containsKey(name)) {
            houseTreeMap.remove(name);
            return true;
        }
        return false;
    }

    public boolean houseExists(String name) {
        return houseTreeMap.containsKey(name);
    }

    public String getHousesInfo() {
        return houseTreeMap.values().stream()
                .map(House::toString)
                .collect(Collectors.joining("\n"));
    }

    public String generateRandomGeoLocation() {
        Random random = new Random();
        double latitude = -90 + 180 * random.nextDouble();
        double longitude = -180 + 360 * random.nextDouble();
        latitude = Math.round(latitude * 1_000_000.0) / 1_000_000.0;
        longitude = Math.round(longitude * 1_000_000.0) / 1_000_000.0;
        return latitude + ", " + longitude;
    }

    // POKOJE

    public void addRoomToHouse(String houseName, String roomName, String choice) {
        House house = houseTreeMap.get(houseName);
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

    public void removeRoom(String roomName) {
        House house = findHouseContainingRoom(roomName);
        if (house != null) {
            house.getRooms().remove(roomName);
            System.out.println("Room \"" + roomName + "\" removed from house: " + house.getName());
        } else {
            System.out.println("Room \"" + roomName + "\" not found in any house.");
        }
    }

    public boolean roomExists(String choice) {
        return houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().values().stream())
                .anyMatch(room -> room.getName().equalsIgnoreCase(choice));
    }

    public boolean anyRoomExists() {
        return houseTreeMap.values().stream()
                .anyMatch(house -> !house.getRooms().isEmpty());
    }

    public House findHouseContainingRoom(String roomName) {
        for (House house : houseTreeMap.values()) {
            if (house.getRooms().containsKey(roomName)) {
                return house;
            }
        }
        return null;
    }

    public String getRoomsInfo() {
        StringBuilder sb = new StringBuilder();
        for (House house : houseTreeMap.values()) {
            for (Room room : house.getRooms().values()) {
                sb.append(room.toString());
            }
        }
        return sb.toString();
    }

    public String getRoomsNames() {
        return houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().values().stream())
                .map(Room::getName)
                .collect(Collectors.joining(", "));
    }

    public Map<String, Room> getRoomsMap() {
        return houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public ArrayList<RoomType> getRoomsTypes() {
        return getRoomsMap().values().stream()
                .map(Room::getType)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /* ===URZĄDZENIA=== */

    public void addToDeviceMap(String deviceName, String roomName) {
        SmartDevice device = switch (deviceName.toLowerCase()) {
            case "heater" -> new Heater();
            case "lightbulb" -> new Lightbulb();
            case "outlet" -> new Outlet();
            case "temperaturesensor" -> new TemperatureSensor();
            default -> null;
        };

        if (device == null) {
            System.out.println("Nieznany typ urządzenia: " + deviceName);
            return;
        }

        for (House house : houseTreeMap.values()) {
            for (Room room : house.getRooms().values()) {
                if (room.getName().equalsIgnoreCase(roomName)) {
                    room.getDeviceMap().put(deviceName.toLowerCase(), device);
                    System.out.println("Dodano urządzenie " + deviceName + " do pokoju " + roomName);
                    return;
                }
            }
        }

        System.out.println("Nie znaleziono pokoju o nazwie: " + roomName);
    }

    public SmartDevice getDevice(String chosenDevice) {
        String normalizedName = chosenDevice.toLowerCase();

        return houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().values().stream())
                .flatMap(room -> room.getDeviceMap().values().stream())
                .filter(device -> device.getName().equalsIgnoreCase(normalizedName))
                .findFirst()
                .orElse(null);
    }

    public boolean deviceExists(String deviceName) {
        return houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().values().stream())
                .flatMap(room -> room.getDeviceMap().keySet().stream())
                .anyMatch(name -> name.equalsIgnoreCase(deviceName));
    }

    public boolean anyDeviceExists() {
        return houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().values().stream())
                .anyMatch(room -> !room.getDeviceMap().isEmpty());
    }

    public void removeDevice(String deviceName) {
        String normalizedName = deviceName.toLowerCase();

        houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().values().stream())
                .filter(room -> room.getDeviceMap().containsKey(normalizedName))
                .findFirst()
                .ifPresent(room -> room.getDeviceMap().remove(normalizedName));
    }

    public String getDevicesNames() {
        return houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().values().stream())
                .flatMap(room -> room.getDeviceMap().values().stream())
                .map(SmartDevice::getName)
                .collect(Collectors.joining(", "));
    }

    public Set<DeviceStatus> getAllStatusesForRoomType(RoomType roomType) {
        return houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().values().stream())
                .filter(room -> room.getType().equals(roomType))
                .flatMap(room -> room.getDeviceMap().values().stream())
                .map(SmartDevice::getStatus)
                .collect(Collectors.toSet());
    }

    public void setStatusForDevicesInRoomType(RoomType chosenRoomType, DeviceStatus chosenStatus) {
        houseTreeMap.values().stream()
                .flatMap(house -> house.getRooms().values().stream())
                .filter(room -> room.getType().equals(chosenRoomType))
                .flatMap(room -> room.getDeviceMap().values().stream())
                .forEach(device -> device.setStatus(chosenStatus));
    }
}
