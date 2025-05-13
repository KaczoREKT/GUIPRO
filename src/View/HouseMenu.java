package View;

import Controller.HouseController;
import House.*;
import Other.*;

import java.util.Random;

public class HouseMenu extends AbstractMenu {
    private HouseController controller;

    public HouseMenu(HouseController controller) {
        this.controller = controller;
    }

    public static String generateRandomGeoLocation() {
        Random random = new Random();
        double latitude = -90 + 180 * random.nextDouble();    // -90 to 90
        double longitude = -180 + 360 * random.nextDouble();  // -180 to 180

        // Round to 6 decimal places
        latitude = Math.round(latitude * 1_000_000.0) / 1_000_000.0;
        longitude = Math.round(longitude * 1_000_000.0) / 1_000_000.0;

        return latitude + ", " + longitude;
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
                    if (Settings.isEnabled("GeoLocation")) {
                        System.out.println("GeoLocation is enabled. Retrieving data.");
                        String geoLocation = generateRandomGeoLocation();
                        controller.addHouseToSet(choice, geoLocation);
                        System.out.println("House added: \n" + choice);
                    } else {
                        System.out.println("GeoLocation is disabled. You can enable it in the settings.");
                        controller.addHouseToSet(choice, null);
                        System.out.println("House added: \n" + choice);
                    }
                    break;

                case "2":
                    System.out.println("=== Enter the name of the house to remove ===");
                    System.out.println("Saved houses:");
                    System.out.println(controller.getHousesSet());
                    String chosenHouse = scanner.nextLine();
                    controller.removeHouseFromSet(chosenHouse);
                    break;

                case "3":
                    System.out.println("=== Saved houses: ===\n");
                    System.out.println(controller.getHousesNames());
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
