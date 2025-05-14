import Controller.DeviceController;
import Controller.HouseController;
import Controller.RoomController;
import AModel.DeviceModel;
import AModel.HouseModel;
import AModel.RoomModel;
import BView.DeviceMenu;
import BView.HouseMenu;
import BView.MainMenu;
import BView.RoomMenu;

public class Main {
    public static void main(String[] args) {
        // Model
        HouseModel houseModel = new HouseModel();
        RoomModel roomModel = new RoomModel();
        DeviceModel deviceModel = new DeviceModel();

        // Controller
        DeviceController deviceController = new DeviceController(deviceModel, houseModel, roomModel);
        HouseController houseController = new HouseController(houseModel);
        RoomController roomController = new RoomController(roomModel, houseModel);

        // View
        DeviceMenu deviceMenu = new DeviceMenu(deviceController, houseController, roomController);
        RoomMenu roomMenu = new RoomMenu(roomController, houseController);
        HouseMenu houseMenu = new HouseMenu(houseController);

        // MainView
        MainMenu mainMenu = new MainMenu(houseMenu, roomMenu, deviceMenu);
        mainMenu.getMenu();
    }
}