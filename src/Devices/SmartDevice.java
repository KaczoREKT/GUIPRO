package Devices;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class SmartDevice {
    private UUID deviceID;
    private String deviceName;
    private DeviceStatus currentDeviceStatus;
    protected Set<DeviceStatus> deviceStatusSet;

    public SmartDevice(String deviceName, DeviceStatus currentDeviceStatus) {
        this.deviceID = UUID.randomUUID();
        this.deviceName = deviceName;
        this.currentDeviceStatus = currentDeviceStatus;
        deviceStatusSet = new HashSet<>();
        deviceStatusSet.add(DeviceStatus.ON);
        deviceStatusSet.add(DeviceStatus.OFF);
        addCustomStatus();
    }

    public abstract void simulate();
    public abstract void addCustomStatus();

    public DeviceStatus getStatus() {
        return currentDeviceStatus;
    }

    public void setStatus(DeviceStatus status) {
        if (!deviceStatusSet.contains(status)){
            throw new IllegalStateException ("Device cannot be in that state.");
        } else if (!(currentDeviceStatus == status)) {
            currentDeviceStatus = status;
        } else{
            throw new IllegalStateException ("Status cannot be the same as current.");
        }
    }

    public Set<DeviceStatus> getDeviceStatusSet() {
        return deviceStatusSet;
    }

    public String getName() {
        return deviceName;
    }

    @Override
    public String toString() {
        return "ID: " + deviceID.toString() + "\nName:" + deviceName + "\nStatus:" + currentDeviceStatus;
    }
}
