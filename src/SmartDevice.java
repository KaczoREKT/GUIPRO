import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class SmartDevice {
    private UUID deviceID;
    private String deviceName;
    private DeviceStatus currentDeviceStatus;
    protected Set<DeviceStatus> deviceStatusSet;

    public SmartDevice(UUID deviceID, String deviceName, DeviceStatus currentDeviceStatus) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.currentDeviceStatus = currentDeviceStatus;
        deviceStatusSet = new HashSet<>();
        deviceStatusSet.add(DeviceStatus.ON);
        deviceStatusSet.add(DeviceStatus.OFF);
        addNewStatus();
    }

    public abstract void simulate();
    public abstract void addNewStatus();

    public DeviceStatus getStatus() {
        return currentDeviceStatus;
    }

    public void setStatus(DeviceStatus status) {
        if (!deviceStatusSet.contains(status)){
            throw new IllegalArgumentException ("Device cannot be in that state.");
        }else{
            currentDeviceStatus = status;
        }
    }

    @Override
    public String toString() {
        return "SmartDevice {" +
                "ID=" + deviceID +
                ", Name='" + deviceName + '\'' +
                ", Status=" + currentDeviceStatus +
                ", AllowedStatuses=" + deviceStatusSet +
                '}';
    }
}
