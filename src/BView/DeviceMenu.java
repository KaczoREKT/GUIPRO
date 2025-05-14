package BView;

import Controller.DeviceController;
import Controller.HouseController;
import Controller.RoomController;
import Devices.DeviceStatus;
import Devices.SmartDevice;
import House.RoomType;

import java.util.Set;
import java.util.stream.Collectors;

public class DeviceMenu extends AbstractMenu {
    private DeviceController deviceController;
    private HouseController houseController;
    private RoomController roomController;
    SmartDevice currentSmartDevice;

    public DeviceMenu(DeviceController deviceController, HouseController houseController, RoomController roomController) {
        this.deviceController = deviceController;
        this.houseController = houseController;
        this.roomController = roomController;
    }

    public void getMenu() {
        // Check if house exists
        if (roomController.getRoomsMap().isEmpty()) {
            System.out.println("No Rooms found. Create one.");
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
                /* === Function for adding device to chosen room === */
                case "1":
                    // Check available devices and add one
                    System.out.println("=== Available devices: ===");
                    System.out.println("Choose one to add");
                    System.out.println("Heater, LightBulb, Outlet, TemperatureSensor");
                    String device = scanner.nextLine();
                    // Ask which room to add device in
                    System.out.println("Choose Room to add device: " + choice);
                    System.out.println(deviceController.getAllRoomsNames());
                    choice = scanner.nextLine();
                    // Add to global model and house
                    roomController.addDeviceToRoom(device, choice);
                    deviceController.addToDevicesMap(device);
                    System.out.println("Device added.");
                    break;

                /* === Function for removing device from the room it is currently in. === */
                case "2":
                    // Remove device
                    System.out.println("=== Enter device to remove ===");
                    System.out.println("Devices in current house:");
                    System.out.println(deviceController.getDevicesNames());// Wyswietl wszystkie urzadzenia
                    choice = scanner.nextLine();
                    if (deviceController.deviceExists(choice)) {
                        deviceController.removeDevice(choice);
                    }
                    System.out.println("Device removed.");
                    break;
                case "3":
                    deviceStatusMenu();
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

    public void deviceStatusMenu() {
        running = true;
        if (deviceController.getDevicesMap().isEmpty()) {
            System.out.println("No devices. Add some.");
        }

        if (!deviceController.getAvailableDevices().isEmpty()) {
            System.out.println("=== Choose available device ===");
            System.out.println(deviceController.getDevicesNames());
            String chosenDevice = scanner.nextLine().toLowerCase();

            if (deviceController.getDevicesMap().containsKey(chosenDevice)) {
                currentSmartDevice = deviceController.getDevicesMap().get(chosenDevice);
                while (running) {
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
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid choice, please try again.");
                    }
                }
            } else {
                System.out.println("Device not found.");
            }
        } else {
            System.out.println("No devices in room.");
        }
    }

    public void groupDeviceMenu() {
        System.out.println("=== Group device control: ===");
        System.out.println("Change status in which room type?");
        System.out.println(
                roomController.getRoomsMap().values().stream()
                .map(room -> "Type: " + room.getType())
                .distinct()
                .collect(Collectors.joining("\n")));
        choice = scanner.nextLine().toUpperCase();
        RoomType chosenRoomType = RoomType.valueOf(choice);
        if (roomController.getRoomsTypes().contains(chosenRoomType)) {
            System.out.println("Changed type to " + chosenRoomType);
        }
        // Zbieramy dostępne statusy urządzeń dla wybranego typu pokoju
        Set<DeviceStatus> availableStatuses = deviceController.getAllStatusesForRoomType(RoomType.valueOf(choice));

        // Wyświetlamy dostępne statusy
        System.out.println("Available statuses for devices in " + chosenRoomType + " rooms:");
        availableStatuses.forEach(status -> System.out.println("\t" + status));

        // Pytamy użytkownika o nowy status
        System.out.println("Change status to?");
        String chosenStatusString = scanner.nextLine().toUpperCase();

        // Sprawdzamy, czy wybrany status jest poprawny
        try {
            DeviceStatus chosenStatus = DeviceStatus.valueOf(chosenStatusString);
            System.out.println(chosenStatus);

            // Zmieniamy status urządzeń w wybranych pokojach i globalnie (do zmiany)
            // classes = roomController.setStatusForDevicesInRoomType(chosenRoomType, chosenStatus);
            // deviceController.updateClasses(classes)
            //
            roomController.setStatusForDevicesInRoomType(chosenRoomType, chosenStatus);
            deviceController.setStatusForDevicesInRoomType(chosenRoomType, chosenStatus);
            System.out.println("Device status changed to " + chosenStatusString);
        } catch (IllegalStateException e) {
            System.out.println("Invalid operation for device: " + e.getMessage());
        }
    }
}
