package BView;

import Controller.HouseController;
import Controller.RoomController;
import House.*;


public class RoomMenu extends AbstractMenu {
    public RoomController roomController;
    public HouseController houseController;

    public RoomMenu(RoomController roomController, HouseController houseController) {
        this.roomController = roomController;
        this.houseController = houseController;
    }

    public void getMenu() {
        running = true;

        if (houseController.housesExists()) {
            System.out.println("No house found. Create one.");
            return;
        }
        while (running) {
            System.out.println("=== ROOM MENU ===");
            System.out.println("1. Add Room");
            System.out.println("2. Remove Room");
            System.out.println("3. Show saved rooms");
            System.out.println("4. Back");
            System.out.print("Choose an option (1-4): ");

            String choice = scanner.nextLine();

            switch (choice) {
                /* Function for adding room to house */
                case "1":
                    System.out.println("=== Which House to add room to? ===");
                    System.out.println(houseController.getHousesNames());
                    String houseName = scanner.nextLine().toLowerCase();
                    System.out.println("=== Enter room name ===");
                    String roomName = scanner.nextLine().toLowerCase();
                    System.out.println("=== Choose available room type ===");
                    for (RoomType roomType : RoomType.values()) {
                        System.out.print(roomType + " ");
                        System.out.println();
                    }
                    choice = scanner.nextLine().trim().toUpperCase();

                    // Add to global model and house
                    try {
                        houseController.addRoomToHouse(houseName, roomName, choice);
                        roomController.addRoomToMap(roomName, choice);
                        System.out.println("Room created: " + choice);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid room type entered. Please try again.");
                    }
                    break;
                case "2":
                    /* Function for removing room from house */
                    System.out.println("=== Enter room to remove ===");

                    System.out.println(roomController.getRoomsNames());
                    choice = scanner.nextLine().toLowerCase();
                    if (roomController.roomExists(choice)) {
                        roomController.removeRoom(choice);
                        houseController.removeRoom(choice);
                        System.out.println("Room removed.");
                    } else {
                        System.out.println("Room not found.");
                    }
                    break;
                /* Function to show saved rooms */
                case "3":
                    System.out.println(roomController.getRoomsInfo());
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
}


