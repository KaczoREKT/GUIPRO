package Model;

import Devices.SmartDevice;

import java.util.TreeMap;

public class DeviceModel {
    TreeMap<String, SmartDevice> devices = new TreeMap<>();
    public DeviceModel() {

    }
    public void addDevice(String deviceName, SmartDevice device) {
        devices.put(deviceName, device);
    }
}
