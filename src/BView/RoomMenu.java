package BView;

import Controller.AppContext;
import House.*;


public class RoomMenu extends AbstractMenu {
    public AppContext appContext;

    public RoomMenu(AppContext appContext) {

        this.appContext = appContext;
    }

    public void getMenu() {
        running = true;

        if (appContext.getHouseMap().isEmpty()) {
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
                    System.out.println(appContext.getHousesNames());
                    String houseName = scanner.nextLine().toLowerCase();
                    System.out.println("=== Enter room name ===");
                    String roomName = scanner.nextLine().toLowerCase();
                    System.out.println("=== Choose available room type ===");
                    for (RoomType roomType : RoomType.values()) {
                        System.out.print(roomType + " ");
                        System.out.println();
                    }
                    choice = scanner.nextLine().trim().toUpperCase();

                    try {
                        appContext.addRoomToHouse(houseName, roomName, choice);
                        System.out.println("Room created: " + choice);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid room type entered. Please try again.");
                    }
                    break;
                case "2":
                    /* Function for removing room from house */
                    System.out.println("=== Enter room to remove ===");

                    System.out.println(appContext.getRoomsNames());
                    choice = scanner.nextLine().toLowerCase();
                    if (appContext.roomExists(choice)) {
                        appContext.removeRoom(choice);
                        System.out.println("Room removed.");
                    } else {
                        System.out.println("Room not found.");
                    }
                    break;
                /* Function to show saved rooms */
                case "3":
                    System.out.println(appContext.getRoomsInfo());
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


