import Controller.DeviceController;
import Controller.HouseController;
import Controller.RoomController;
import Model.DeviceModel;
import Model.HouseModel;
import Model.RoomModel;
import View.DeviceMenu;
import View.HouseMenu;
import View.MainMenu;
import View.RoomMenu;

public class Main {
    public static void main(String[] args) {
        // Model
        HouseModel houseModel = new HouseModel();
        RoomModel roomModel = new RoomModel();
        DeviceModel deviceModel = new DeviceModel();

        // Controller
        DeviceController deviceController = new DeviceController(deviceModel, houseModel, roomModel);
        HouseController houseController = new HouseController(houseModel);
        RoomController roomController = new RoomController(roomModel);

        // View
        DeviceMenu deviceMenu = new DeviceMenu(deviceController);
        RoomMenu roomMenu = new RoomMenu(roomController);
        HouseMenu houseMenu = new HouseMenu(houseController);

        // MainView
        MainMenu mainMenu = new MainMenu(houseMenu, roomMenu, deviceMenu);
        mainMenu.getMenu();
    }
}