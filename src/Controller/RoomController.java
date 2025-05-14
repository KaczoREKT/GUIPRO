package Controller;

import Devices.*;
import House.*;
import AModel.HouseModel;
import AModel.RoomModel;

import java.util.ArrayList;
import java.util.TreeMap;

public class RoomController extends AbstractController {
    public RoomModel roomModel;
    public HouseModel houseModel;

    public RoomController(RoomModel roomModel, HouseModel houseModel) {
        this.roomModel = roomModel;
        this.houseModel = houseModel;
    }

    public void addRoomToMap(String roomName, String type) {
        roomModel.addRoom(new Room(roomName, RoomType.valueOf(type)));
    }

    public String getRoomsNames() {
        return roomModel.getRoomsNames();
    }

    public TreeMap<String, Room> getRoomsMap(){
        return roomModel.getRoomsMap();
    }

    public boolean roomExists(String choice) {
        return roomModel.roomExists(choice);
    }

    public void removeRoom(String choice) {
        roomModel.removeRoom(choice);
    }

    public String getRoomsInfo() {
        return roomModel.getRoomsInfo();
    }


    public void addDeviceToRoom(String deviceName, String roomName) {
        SmartDevice newDevice = switch (deviceName.toLowerCase()) {
            case "heater" -> new Heater();
            case "lightbulb" -> new Lightbulb();
            case "outlet" -> new Outlet();
            case "temperaturesensor" -> new TemperatureSensor();
            default -> null;
        };
        roomModel.addDevice(newDevice, roomName);
    }

    public ArrayList<RoomType> getRoomsTypes() {
        return roomModel.getRoomsTypes();
    }
    public ArrayList<Room> setStatusForDevicesInRoomType(RoomType type, DeviceStatus status) {
        houseModel.getRooms().stream()
                .filter(room -> room.getType() == type)
                .forEach(room -> room.getSmartDevices().values()
                        .forEach(device -> device.setStatus(status)));
        return houseModel.getRooms();
    }


}
