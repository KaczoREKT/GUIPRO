package AModel;

import Devices.DeviceStatus;
import Devices.SmartDevice;
import House.RoomType;

import java.util.TreeMap;

public class DeviceModel {
    TreeMap<String, SmartDevice> devices = new TreeMap<>();
    public DeviceModel() {

    }
    public void addDevice(String deviceName, SmartDevice device) {
        devices.put(deviceName, device);
    }
    public void removeDevice(String deviceName) {
        devices.remove(deviceName);
    }

    public String getDevicesNames() {
        return devices.values().toString();
    }


    public TreeMap<String, SmartDevice> getDevicesMap() {
        return devices;
    }

    public void setStatusForDevicesInRoomType(RoomType type, DeviceStatus status) {
        devices.values().stream()
    }
}
