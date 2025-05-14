package BView;

import Controller.AppContext;
import Devices.DeviceStatus;
import Devices.SmartDevice;
import House.RoomType;

import java.util.Set;
import java.util.stream.Collectors;

public class DeviceMenu extends AbstractMenu {
    private AppContext appContext;

    public DeviceMenu(AppContext appContext) {
        this.appContext = appContext;
    }

    public void getMenu() {
        // Check if rooms exist
        if (!appContext.anyRoomExists()) {
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
                    System.out.println(appContext.getRoomsNames());
                    choice = scanner.nextLine();
                    // Add device to room
                    appContext.addToDeviceMap(device, choice);
                    System.out.println("Device added.");
                    break;

                /* === Function for removing device from the room it is currently in. === */
                case "2":
                    // Remove device
                    System.out.println("=== Enter device to remove ===");
                    System.out.println("Devices in current house:");
                    System.out.println(appContext.getDevicesNames());// Wyswietl wszystkie urzadzenia
                    choice = scanner.nextLine();
                    if (appContext.deviceExists(choice)) {
                        appContext.removeDevice(choice);
                        System.out.println("Device removed.");
                    } else {
                        System.out.println("Device not found.");
                    }
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
        if (!appContext.anyDeviceExists()) {
            System.out.println("No devices. Add some.");
            return;
        }
        System.out.println("=== Choose available device ===");
        System.out.println(appContext.getDevicesNames());
        String chosenDevice = scanner.nextLine().toLowerCase();

        if (appContext.deviceExists(chosenDevice)) {
            SmartDevice currentSmartDevice = appContext.getDevice(chosenDevice);
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
    }

    public void groupDeviceMenu() {
        System.out.println("=== Group device control: ===");
        System.out.println("Change status in which room type?");
        System.out.println(
                appContext.getRoomsTypes().stream()
                        .map(RoomType::toString)
                        .collect(Collectors.joining(", ", "Typy pokoi: ", ".")));
        choice = scanner.nextLine().toUpperCase();
        RoomType chosenRoomType = RoomType.valueOf(choice);
        if (appContext.getRoomsTypes().contains(chosenRoomType)) {
            System.out.println("Changed type to " + chosenRoomType);
        }
        // Zbieramy dostępne statusy urządzeń dla wybranego typu pokoju
        Set<DeviceStatus> availableStatuses = appContext.getAllStatusesForRoomType(RoomType.valueOf(choice));

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

            appContext.setStatusForDevicesInRoomType(chosenRoomType, chosenStatus);
            System.out.println("Device status changed to " + chosenStatusString);
        } catch (IllegalStateException e) {
            System.out.println("Invalid operation for device: " + e.getMessage());
        }
    }
}

