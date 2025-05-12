import Devices.*;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuCLI {
    HouseManager houseManager = new HouseManager();
    House currentHouse = null;
    Room currentRoom = null;
    SmartDevice currentSmartDevice = null;
    RoomType currentRoomType = null;
    public static Scanner scanner = new Scanner(System.in);

    public static String generateRandomGeoLocation() {
        Random random = new Random();
        double latitude = -90 + 180 * random.nextDouble();    // -90 to 90
        double longitude = -180 + 360 * random.nextDouble();  // -180 to 180

        // Round to 6 decimal places
        latitude = Math.round(latitude * 1_000_000.0) / 1_000_000.0;
        longitude = Math.round(longitude * 1_000_000.0) / 1_000_000.0;

        return latitude + ", " + longitude;
    }

    public void houseMenu() {
        boolean inHouseMenu = true;

        while (inHouseMenu) {
            System.out.println("=== HOUSE MENU ===");
            System.out.println("1. Add house");
            System.out.println("2. Remove house");
            System.out.println("3. Manage Rooms");
            System.out.println("4. Show saved houses");
            System.out.println("5. Back");
            System.out.print("Choose an option (1-5): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("=== Enter house name ===");
                    String name = scanner.nextLine();
                    if (Settings.isEnabled("GeoLocation")) {
                        System.out.println("GeoLocation is enabled. Retrieving data.");
                        String geoLocation = generateRandomGeoLocation();
                        House house = new House(name, geoLocation);
                        houseManager.addHouse(house);
                        System.out.println("House added: \n" + house);
                    } else {
                        System.out.println("GeoLocation is disabled. You can enable it in the settings.");
                        House house = new House(name);
                        houseManager.addHouse(house);
                        System.out.println("House added: \n" + house);
                    }
                    break;

                case "2":
                    System.out.println("=== Enter the name of the house to remove ===");
                    System.out.println("Saved houses:");
                    System.out.println(houseManager);
                    String chosenHouse = scanner.nextLine();
                    if (houseManager.getHouses().containsKey(chosenHouse)) {
                        houseManager.removeHouse(chosenHouse);
                        System.out.println("House removed.");
                    } else {
                        System.out.println("House not found.");
                    }
                    break;

                case "3":
                    roomMenu();
                    break;
                case "4":
                    System.out.println("=== Saved houses: ===\n");
                    System.out.println(houseManager);
                    break;

                case "5":
                    inHouseMenu = false; // Return to main menu
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }

            System.out.println(); // blank line for readability
        }
    }

    public void roomMenu() {
        boolean inRoomMenu = true;

        if (houseManager.getHouses().isEmpty()) {
            System.out.println("No house found. Create one.");
        } else {
            System.out.println("Which house would you like to use?");
            System.out.println(houseManager.getHousesNames());
            String chosenHouse = scanner.nextLine();
            if (houseManager.getHouses().containsKey(chosenHouse)) {
                currentHouse = houseManager.getHouses().get(chosenHouse);
                while (inRoomMenu) {
                    System.out.println("=== ROOM MENU ===");
                    System.out.println("1. Add Room");
                    System.out.println("2. Remove Room");
                    System.out.println("3. Manage room devices");
                    System.out.println("4. Back");
                    System.out.print("Choose an option (1-4): ");

                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            System.out.println("=== Enter room name ===");
                            String roomName = scanner.nextLine().toLowerCase();

                            System.out.println("=== Choose available room type ===");
                            for (RoomType roomType : RoomType.values()) {
                                System.out.print(roomType + " ");
                                System.out.println();
                            }

                            String roomType = scanner.nextLine().trim().toUpperCase();
                            try {
                                RoomType selectedType = RoomType.valueOf(roomType);
                                Room room = new Room(roomName, selectedType);
                                currentHouse.addRoom(room);
                                System.out.println("Room created: " + room);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid room type entered. Please try again.");
                            }
                            break;
                        case "2":
                            System.out.println("=== Enter room to remove ===");
                            System.out.println("Rooms in current house:");
                            System.out.println(currentHouse.getRoomNames());
                            String chosenRoom = scanner.nextLine().toLowerCase();
                            if (currentHouse.getRooms().get(chosenRoom) != null) {
                                houseManager.removeHouse(chosenHouse);
                                System.out.println("Room removed.");
                            } else {
                                System.out.println("Room not found.");
                            }
                            break;
                        case "3":
                            deviceMenu();
                            break;
                        case "4":
                            inRoomMenu = false; // Return to main menu
                            break;

                        default:
                            System.out.println("Invalid choice, please try again.");
                    }

                    System.out.println(); // blank line for readability
                }
            } else {
                System.out.println("House not found.");
            }
        }
    }

    public void deviceMenu() {
        boolean inDeviceMenu = true;
        // Check if room exists
        if (currentHouse.getRooms().isEmpty()) {
            System.out.println("No rooms found. Create one.");
        } else {
            // Ask which room to configure devices for
            System.out.println("Which room would you like to use?");
            System.out.println(currentHouse.getRoomNames());
            String chosenRoom = scanner.nextLine();
            if (currentHouse.getRooms().containsKey(chosenRoom)) {
                currentRoom = currentHouse.getRooms().get(chosenRoom);

                while (inDeviceMenu) {
                    System.out.println("=== DEVICE MENU ===");
                    System.out.println("1. Add Device");
                    System.out.println("2. Remove Device");
                    System.out.println("3. Manage devices");
                    System.out.println("4. Back");
                    System.out.print("Choose an option (1-4): ");

                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("=== Available devices: ===");
                            System.out.println("Choose one to add");
                            System.out.println("Heater, LightBulb, Outlet, TemperatureSensor");
                            String deviceName = scanner.nextLine();
                            SmartDevice newDevice = switch (deviceName.toLowerCase()) {
                                case "heater" -> new Heater();
                                case "lightbulb" -> new Lightbulb();
                                case "outlet" -> new Outlet();
                                case "temperaturesensor" -> new TemperatureSensor();
                                default -> null;
                            };
                            currentRoom.addDevice(newDevice);
                            System.out.println("Device added.");
                            break;
                        case "2":
                            System.out.println("=== Enter device to remove ===");
                            System.out.println("Devices in current house:");
                            System.out.println(currentRoom.getSmartDevicesNames());
                            String chosenDevice = scanner.nextLine();
                            if (currentRoom.getSmartDevicesNames().contains(chosenDevice)) {
                                currentRoom.removeDevice(chosenDevice);
                            }
                            System.out.println("Device removed.");
                            break;
                        case "3":
                            deviceStatusMenu();
                        case "4":
                            inDeviceMenu = false;
                            break;

                        default:
                            System.out.println("Invalid choice, please try again.");
                    }

                    System.out.println();
                }

            }
        }
    }

    public void deviceStatusMenu() {
        boolean inDeviceStatusMenu = true;
        System.out.println("=== Choose available device ===");

            if (!currentRoom.getSmartDevices().isEmpty()) {
                System.out.println(currentRoom.getSmartDevicesNames());
                String chosenDevice = scanner.nextLine().toLowerCase();
                if (currentRoom.getSmartDevices().containsKey(chosenDevice)) {
                    currentSmartDevice = currentRoom.getSmartDevices().get(chosenDevice);
                    while (inDeviceStatusMenu) {
                    System.out.println("=== CHOOSE WHAT TO DO WITH DEVICE  ===");
                    System.out.println("1. Change Status");
                    System.out.println("2. Check current status");
                    System.out.println("3. Group device control");
                    System.out.println("4. Back");
                    System.out.print("Choose an option (1-4): ");

                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("=== Available status: ===");
                            for (DeviceStatus ds : currentSmartDevice.getDeviceStatusSet()) {
                                System.out.println(ds.toString() + " ");
                            }
                            String chosenStatus = scanner.nextLine().toUpperCase().trim();
                            try {
                                currentSmartDevice.setStatus(DeviceStatus.valueOf(chosenStatus));
                                System.out.println("Changed status to " + currentSmartDevice.getStatus());
                            } catch (IllegalStateException exc) {
                                System.out.println(exc.getMessage());
                            }
                            break;

                        case "2":
                            System.out.println("=== Current status: ===");
                            System.out.println(currentSmartDevice.getStatus());
                            break;

                        case "3":
                            groupDeviceMenu();
                            break;

                        case "4":
                            inDeviceStatusMenu = false;
                            break;
                    }
                }
            }else {
                    System.out.println("Device not found.");
                }
            }else {
                System.out.println("No devices in room.");
        }
    }

    public void groupDeviceMenu() {
        System.out.println("=== Group device control: ===");
        System.out.println("Change status in which room type?");
        System.out.println(currentHouse.getRooms().values().stream()
                .map(room -> "Type: " + room.getType())
                .distinct()
                .collect(Collectors.joining("\n")));
        String chosenType = scanner.nextLine().toUpperCase();
        if (currentHouse.getRoomTypes().contains(RoomType.valueOf(chosenType))) {
            currentRoomType = RoomType.valueOf(chosenType);
            System.out.println("Changed type to " + currentRoomType);
        }
        // Zbieramy dostępne statusy urządzeń dla wybranego typu pokoju
        Set<DeviceStatus> availableStatuses = currentHouse.getRooms().values().stream()
                .filter(room -> room.getType() == currentRoomType)
                .flatMap(room -> room.getSmartDevices().values().stream())
                .flatMap(device -> device.getDeviceStatusSet().stream())
                .collect(Collectors.toSet());

        // Wyświetlamy dostępne statusy
        System.out.println("Available statuses for devices in " + currentRoomType + " rooms:");
        availableStatuses.forEach(status -> System.out.println("\t" + status));

        // Pytamy użytkownika o nowy status
        System.out.println("Change status to?");
        String chosenStatusString = scanner.nextLine().toUpperCase();

        // Sprawdzamy, czy wybrany status jest poprawny
        try {
            DeviceStatus chosenStatus = DeviceStatus.valueOf(chosenStatusString);
            System.out.println(chosenStatus);

            // Zmieniamy status urządzeń w wybranych pokojach
            currentHouse.getRooms().values().stream()
                    .filter(room -> room.getType() == currentRoomType)
                    .forEach(room -> room.getSmartDevices().values()
                            .forEach(device -> {
                                device.setStatus(chosenStatus);
                                System.out.println("Changed status of device " + device.getName() + " to " + chosenStatus);
                            }));
            System.out.println("Device status changed to " + chosenStatusString);
        } catch (IllegalStateException e) {
            System.out.println("Invalid operation for device: " + e.getMessage());
        }

    }

    public void ruleMenu() {
        System.out.println("Sike");
    }

    public void showSystemStatus() {
        System.out.println("Sike");
    }

    private void settingsMenu() {
        System.out.println("Sike");
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== MAIN MENU ===");
            System.out.println("1. House management");
            System.out.println("2. Device management");
            System.out.println("3. Rule automation");
            System.out.println("4. System status");
            System.out.println("5. Settings");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    houseMenu();
                    break;
                case "2":
                    deviceMenu();
                    break;
                case "3":
                    deviceMenu();
                    break;
                case "4":
                    ruleMenu();
                    break;
                case "5":
                    showSystemStatus();
                    break;
                case "6":
                    settingsMenu();
                    break;
                case "7":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

            System.out.println(); // blank line for readability
        }

        scanner.close();
    }
}
