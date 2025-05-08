import java.util.UUID;

public class Outlet extends SmartDevice implements Switchable{
    private boolean inUse = false;

    public Outlet(UUID deviceID, String deviceName, DeviceStatus currentDeviceStatus, boolean inUse) {
        super(deviceID, deviceName, currentDeviceStatus);
        this.inUse = inUse;
    }

    @Override
    public void simulate() {

    }

    @Override
    public void turnOn() {
        if (inUse) {
            setStatus(DeviceStatus.ON);
        } else {
            throw new IllegalStateException("Device is not in use.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn()){
            setStatus(DeviceStatus.OFF);
        } else {
            throw new IllegalStateException("Device is not powered on.");
        }
    }

    @Override
    public boolean isOn() {
        return false;
    }
}
