import Controller.AppContext;
import BView.*;

public class Main {
    public static void main(String[] args) {
        // Controller
        AppContext appContext = new AppContext();

        // View
        DeviceMenu deviceMenu = new DeviceMenu(appContext);
        RoomMenu roomMenu = new RoomMenu(appContext);
        HouseMenu houseMenu = new HouseMenu(appContext);

        // MainView
        MainMenu mainMenu = new MainMenu(houseMenu, roomMenu, deviceMenu);
        mainMenu.getMenu();
    }
}