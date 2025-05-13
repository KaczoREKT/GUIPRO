package View;

import Controller.RoomController;
import House.*;


public class RoomMenu extends AbstractMenu {
    public RoomController controller;

    public RoomMenu(RoomController controller) {
        this.controller = controller;
    }

    public void getMenu() {
        running = true;

        if (controller.getHousesMap().isEmpty()) {
            System.out.println("No house found. Create one.");
            return;
        }
        while (running) {
            System.out.println("=== ROOM MENU ===");
            System.out.println("1. Add House.Room");
            System.out.println("2. Remove House.Room");
            System.out.println("3. Back");
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

                    choice = scanner.nextLine().trim().toUpperCase();
                    try {
                        controller.addRoomToMap(choice);
                        System.out.println("House.Room created: " + choice);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid room type entered. Please try again.");
                    }
                    break;
                case "2":
                    // Remove room
                    System.out.println("=== Enter room to remove ===");
                    System.out.println("Rooms in current house:");
                    controller.getRoomsNames();
                    choice = scanner.nextLine().toLowerCase();
                    if (controller.roomExists(choice)) { // If this room is in Map
                        System.out.println("Room removed.");
                    } else {
                        System.out.println("Room not found.");
                    }
                    break;
                case "3":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
            System.out.println();
        }
    }
}


