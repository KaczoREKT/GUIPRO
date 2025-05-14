package BView;

public class MainMenu extends AbstractMenu {
    HouseMenu houseMenu;
    RoomMenu roomMenu;
    DeviceMenu deviceMenu;

    public MainMenu(HouseMenu houseMenu, RoomMenu roomMenu, DeviceMenu deviceMenu) {
        this.houseMenu = houseMenu;
        this.roomMenu = roomMenu;
        this.deviceMenu = deviceMenu;
        System.out.println("Utworzono Main");
    }

    public void getMenu() {
        running = true;

        while (running) {
            System.out.println("=== MAIN MENU ===");
            System.out.println("1. House management");
            System.out.println("2. Room management");
            System.out.println("3. Device management");
            System.out.println("4. Rule automation");
            System.out.println("5. System status");
            System.out.println("6. Settings");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    houseMenu.getMenu();
                    break;
                case "2":
                    roomMenu.getMenu();
                    break;
                case "3":
                    deviceMenu.getMenu();
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

            System.out.println();
        }

        scanner.close();
    }
}
