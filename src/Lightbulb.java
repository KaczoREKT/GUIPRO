import java.awt.*;
import java.util.UUID;

public class Lightbulb extends SmartDevice implements Switchable {
    private int hue = 30
    private double saturation = 0.01
    private double value = 0.98

    public Lightbulb(UUID deviceID, String deviceName, DeviceStatus currentDeviceStatus) {
        super(deviceID, deviceName, currentDeviceStatus);

    }

    public Color getRGBColor(){
        return Color.BLACK;
    }
    @Override
    public void simulate() {

    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public boolean isOn() {
        return false;
    }
}
