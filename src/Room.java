import Devices.SmartDevice;

import java.util.HashMap;

public class Room {
    private String name;
    private RoomType type;
    private HashMap<String, SmartDevice> smartDevices = new HashMap<>();

    public Room(String name, RoomType type) {
        this.name = name;
        this.type = type;
    }

    public void addDevice(SmartDevice device) {
        smartDevices.put(device.getName(), device);
    }
    public void removeDevice(String device) {
        smartDevices.remove(device);
    }

    public String getName() {
        return name;
    }
    public String getSmartDevicesNames() {
        StringBuilder sb = new StringBuilder();
        for (SmartDevice smartDevice : smartDevices.values()) {
            sb.append(smartDevice.toString()).append(" ");
        }
        return sb.toString();
    }

    public RoomType getType() {
        return type;
    }

    public HashMap<String, SmartDevice> getSmartDevices() {
        return smartDevices;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Type: " + type;
    }
}
