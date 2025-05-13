package Controller;

import House.House;
import Model.HouseModel;
import Model.RoomModel;

import java.util.TreeMap;

public class RoomController {
    public RoomModel roomModel;
    public HouseModel houseModel;

    public RoomController(RoomModel roomModel, HouseModel houseModel) {
        this.roomModel = roomModel;
        this.houseModel = houseModel;
    }

    public void addRoomToMap(String choice) {
    }

    public void getRoomsNames() {
    }

    public TreeMap<String, House> getHousesMap() {
        return houseModel.getHousesMap();
    }

    public boolean roomExists(String choice) {
        return roomModel.roomExists(choice);
    }
}
