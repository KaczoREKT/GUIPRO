package House;

import Devices.SmartDevice;

import java.util.HashMap;

public class Room {
    private String name;
    private RoomType type;
    private HashMap<String, SmartDevice> deviceMap = new HashMap<>();

    public Room(String name, RoomType type) {
        this.name = name;
        this.type = type;
    }

    public void addDevice(SmartDevice device) {
        deviceMap.put(device.getName(), device);
    }
    public void removeDevice(String device) {
        deviceMap.remove(device);
    }

    public String getName() {
        return name;
    }
    public String getSmartDevicesNames() {
        StringBuilder sb = new StringBuilder();
        for (SmartDevice smartDevice : deviceMap.values()) {
            sb.append(smartDevice.toString()).append(" ");
        }
        return sb.toString();
    }

    public RoomType getType() {
        return type;
    }

    public HashMap<String, SmartDevice> getDeviceMap() {
        return deviceMap;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Type: " + type;
    }

    public boolean hasDevice(String deviceName) {
        return deviceMap.containsKey(deviceName);
    }
}
