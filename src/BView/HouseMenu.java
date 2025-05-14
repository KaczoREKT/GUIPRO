package BView;

import Controller.HouseController;

public class HouseMenu extends AbstractMenu {
    private HouseController controller;

    public HouseMenu(HouseController controller) {
        this.controller = controller;
    }

    public void getMenu() {
        boolean inHouseMenu = true;

        while (inHouseMenu) {
            System.out.println("=== HOUSE MENU ===");
            System.out.println("1. Add house");
            System.out.println("2. Remove house");
            System.out.println("3. Show saved houses");
            System.out.println("4. Back");
            System.out.print("Choose an option (1-5): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("=== Enter house name ===");
                    choice = scanner.nextLine();
                    controller.addHouseToSet(choice);
                    System.out.println("House added: \n" + choice);
                    break;

                case "2":
                    System.out.println("=== Enter the name of the house to remove ===");
                    System.out.println(controller.getHousesInfo());
                    String chosenHouse = scanner.nextLine();
                    System.out.println(
                            controller.removeHouseFromSet(chosenHouse)
                                    ? "Deleted: " + chosenHouse
                                    : "House doesn't exist."
                    );
                    break;

                case "3":
                    System.out.println("=== Saved houses: ===\n");
                    System.out.println(controller.getHousesInfo());
                    break;

                case "4":
                    inHouseMenu = false; // Return to main menu
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }

            System.out.println();
        }
    }
}
