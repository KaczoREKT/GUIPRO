package Controller;

import Devices.*;
import House.*;
import Model.DeviceModel;
import Model.HouseModel;
import Model.RoomModel;

import java.util.*;
import java.util.stream.Collectors;

public class DeviceController {
    private DeviceModel deviceModel;
    private HouseModel houseModel;
    private RoomModel roomModel;

    public DeviceController(DeviceModel deviceModel, HouseModel houseModel, RoomModel roomModel) {
        this.deviceModel = deviceModel;
        this.houseModel = houseModel;
        this.roomModel = roomModel;
    }

    public Set<String> getAvailableDevices() {
        return Set.of("Heater", "LightBulb", "Outlet", "TemperatureSensor");
    }

    public SmartDevice createDevice(String deviceName) {
        return switch (deviceName.toLowerCase()) {
            case "heater" -> new Heater();
            case "lightbulb" -> new Lightbulb();
            case "outlet" -> new Outlet();
            case "temperaturesensor" -> new TemperatureSensor();
            default -> null;
        };
    }

    public void removeDeviceFromRoom(Room room, String deviceName) {
        room.removeDevice(deviceName);
    }

    public SmartDevice getDevice(Room room, String deviceName) {
        return room.getSmartDevices().get(deviceName.toLowerCase());
    }

    public Set<DeviceStatus> getAvailableStatuses(SmartDevice device) {
        return device.getDeviceStatusSet();
    }

    public void setDeviceStatus(SmartDevice device, DeviceStatus status) {
        device.setStatus(status);
    }

    public DeviceStatus getDeviceStatus(SmartDevice device) {
        return device.getStatus();
    }

    public Set<RoomType> getRoomTypes() {
        return houseModel.getRoomTypes();
    }

    public Set<DeviceStatus> getAllStatusesForRoomType(RoomType type) {
        return roomModel.getRooms().values().stream()
                .filter(room -> room.getType() == type)
                .flatMap(room -> room.getSmartDevices().values().stream())
                .flatMap(device -> device.getDeviceStatusSet().stream())
                .collect(Collectors.toSet());
    }

    public void setStatusForDevicesInRoomType(RoomType type, DeviceStatus status) {
        houseModel.getRooms().values().stream()
                .filter(room -> room.getType() == type)
                .forEach(room -> room.getSmartDevices().values()
                        .forEach(device -> device.setStatus(status)));
    }

    public TreeMap<String, House> getHousesMap() {
    }

    public TreeMap<String, Room> getRoomsMap() {
    }

    public void addToDevicesMap(String deviceName) {
        SmartDevice newDevice = switch (deviceName.toLowerCase()) {
            case "heater" -> new Heater();
            case "lightbulb" -> new Lightbulb();
            case "outlet" -> new Outlet();
            case "temperaturesensor" -> new TemperatureSensor();
            default -> null;
        };
        deviceModel.addDevice(deviceName, newDevice);
    }

    public boolean getAllRoomsNames() {
    }

    public boolean deviceExists(String choice) {
    }
}
