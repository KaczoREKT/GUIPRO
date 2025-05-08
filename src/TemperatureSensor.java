import java.util.Random;
import java.util.UUID;

public class TemperatureSensor extends SmartDevice implements SensorDevice{

    Random random = new Random();
    private int temperature = random.nextInt(81) - 40;
    public TemperatureSensor(UUID deviceID, String deviceName, DeviceStatus currentDeviceStatus) {
        super(deviceID, deviceName, currentDeviceStatus);
    }

    @Override
    public void addNewStatus(){
        deviceStatusSet.add(DeviceStatus.ACTIVE)
    }
    @Override
    public void simulate() {

    }

    @Override
    public Object readValue() {
        return null;
    }

    @Override
    public String getUnit() {
        return "";
    }
}
