package View;

import Controller.DeviceController;
import Devices.*;
import House.*;

import java.util.Set;
import java.util.stream.Collectors;

public class DeviceMenu extends AbstractMenu {
    private DeviceController controller;

    public DeviceMenu(DeviceController controller) {
        this.controller = controller;
    }

    public void getMenu() {
        // Check if house exists
        if (controller.getHousesMap().isEmpty()) { // funkcja ktora sprawdzi czy jest jakikolwiek dom w systemie
            System.out.println("No house found. Create one.");
            return;
        }
        // Check if room exists
        if (controller.getRoomsMap().isEmpty()) { // Sprawdza czy istnieje jakikolwiek pokoj
            System.out.println("No rooms found. Create one.");
            return;
        }
        while (running) {
            System.out.println("=== DEVICE MENU ===");
            System.out.println("1. Add Device");
            System.out.println("2. Remove Device");
            System.out.println("3. Manage devices");
            System.out.println("4. Back");
            System.out.print("Choose an option (1-4): ");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // Add device
                    System.out.println("=== Available devices: ===");
                    System.out.println("Choose one to add");
                    System.out.println("Heater, LightBulb, Outlet, TemperatureSensor");
                    choice = scanner.nextLine();
                    controller.addToDevicesMap(choice);

                    // Ask which room to add device in
                    System.out.println("Choose Room to add device: " + choice);
                    System.out.println(controller.getAllRoomsNames());
                    choice = scanner.nextLine();
                    controller.addToDevicesMap();
                    System.out.println("Device added.");
                    break;
                case "2":
                    // Remove device
                    System.out.println("=== Enter device to remove ===");
                    System.out.println("Devices in current house:");
                    // Wyswietl wszystkie urzadzenia
                    choice = scanner.nextLine();
                    // jeżeli takie urządzenie istnieje usuń je
                    if (controller.deviceExists(choice)) {
                        controller.removeDeviceFromRoom(choice);
                    }
                    System.out.println("Device removed.");
                    break;
                case "3":
                    // Go to device status menu
                    System.out.println("TODO");
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
            System.out.println();
        }
    }

//    public void deviceStatusMenu() {
//        running = true;
//        System.out.println("=== Choose available device ===");
//        if (!currentRoom.getSmartDevices().isEmpty()) {
//            System.out.println(currentRoom.getSmartDevicesNames());
//            String chosenDevice = scanner.nextLine().toLowerCase();
//
//            if (currentRoom.getSmartDevices().containsKey(chosenDevice)) {
//                currentSmartDevice = currentRoom.getSmartDevices().get(chosenDevice);
//                while (inDeviceStatusMenu) {
//                    System.out.println("=== CHOOSE WHAT TO DO WITH DEVICE  ===");
//                    System.out.println("1. Change Status");
//                    System.out.println("2. Check current status");
//                    System.out.println("3. Group device control");
//                    System.out.println("4. Back");
//                    System.out.print("Choose an option (1-4): ");
//
//                    String choice = scanner.nextLine();
//                    switch (choice) {
//                        case "1":
//                            System.out.println("=== Available status: ===");
//                            for (DeviceStatus ds : currentSmartDevice.getDeviceStatusSet()) {
//                                System.out.println(ds.toString() + " ");
//                            }
//                            String chosenStatus = scanner.nextLine().toUpperCase().trim();
//                            try {
//                                currentSmartDevice.setStatus(DeviceStatus.valueOf(chosenStatus));
//                                System.out.println("Changed status to " + currentSmartDevice.getStatus());
//                            } catch (IllegalStateException exc) {
//                                System.out.println(exc.getMessage());
//                            }
//                            break;
//
//                        case "2":
//                            System.out.println("=== Current status: ===");
//                            System.out.println(currentSmartDevice.getStatus());
//                            break;
//
//                        case "3":
//                            groupDeviceMenu();
//                            break;
//
//                        case "4":
//                            inDeviceStatusMenu = false;
//                            break;
//                        default:
//                            System.out.println("Invalid choice, please try again.");
//                    }
//                }
//            } else {
//                System.out.println("Device not found.");
//            }
//        } else {
//            System.out.println("No devices in room.");
//        }
//    }
//
//    public void groupDeviceMenu() {
//        System.out.println("=== Group device control: ===");
//        System.out.println("Change status in which room type?");
//        System.out.println(currentHouse.getRooms().values().stream()
//                .map(room -> "Type: " + room.getType())
//                .distinct()
//                .collect(Collectors.joining("\n")));
//        choice = scanner.nextLine().toUpperCase();
//        if (currentHouse.getRoomTypes().contains(RoomType.valueOf(chosenType))) {
//            currentRoomType = RoomType.valueOf(chosenType);
//            System.out.println("Changed type to " + currentRoomType);
//        }
//        // Zbieramy dostępne statusy urządzeń dla wybranego typu pokoju
//        controller.getAllStatusesForRoomType(RoomType.valueOf(choice));
//
//        // Wyświetlamy dostępne statusy
//        System.out.println("Available statuses for devices in " + currentRoomType + " rooms:");
//        availableStatuses.forEach(status -> System.out.println("\t" + status));
//
//        // Pytamy użytkownika o nowy status
//        System.out.println("Change status to?");
//        String chosenStatusString = scanner.nextLine().toUpperCase();
//
//        // Sprawdzamy, czy wybrany status jest poprawny
//        try {
//            DeviceStatus chosenStatus = DeviceStatus.valueOf(chosenStatusString);
//            System.out.println(chosenStatus);
//
//            // Zmieniamy status urządzeń w wybranych pokojach
//            controller.setStatusForDevicesInRoomType(chosenRoomType, chosenStatus);
//            System.out.println("Device status changed to " + chosenStatusString);
//        } catch (IllegalStateException e) {
//            System.out.println("Invalid operation for device: " + e.getMessage());
//        }
//
//    }
}
