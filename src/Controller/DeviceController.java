package Controller;

import Devices.*;
import House.*;
import AModel.DeviceModel;
import AModel.HouseModel;
import AModel.RoomModel;

import java.util.*;
import java.util.stream.Collectors;

public class DeviceController extends AbstractController{
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

    public void removeDevice(String deviceName) {
        roomModel.removeDevice(deviceName);
        deviceModel.removeDevice(deviceName);
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


    public Set<DeviceStatus> getAllStatusesForRoomType(RoomType type) {
        return roomModel.getRoomsMap().values().stream()
                .filter(room -> room.getType() == type)
                .flatMap(room -> room.getSmartDevices().values().stream())
                .flatMap(device -> device.getDeviceStatusSet().stream())
                .collect(Collectors.toSet());
    }

    public void setStatusForDevicesInRoomType(RoomType type, DeviceStatus status) {
        deviceModel.setStatusForDevicesInRoomType(type, status);
    }

    public TreeMap<String, House> getHousesMap() {
        return houseModel.getHousesMap();
    }

    public TreeMap<String, Room> getRoomsMap() {
        return roomModel.getRoomsMap();
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

    public String getAllRoomsNames() {
        return roomModel.getRoomsNames();
    }

    public boolean deviceExists(String choice) {
        return getDevicesNames().contains(choice);
    }

    public String getDevicesNames() {
        return deviceModel.getDevicesNames();
    }

    public TreeMap<String, SmartDevice> getDevicesMap() {
        return deviceModel.getDevicesMap();
    }
}
