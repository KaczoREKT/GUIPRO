package Devices;

import Interfaces.SensorDevice;

import java.util.Random;
import java.util.UUID;

public class TemperatureSensor extends SmartDevice implements SensorDevice {

    Random random = new Random();
    private int temperature = random.nextInt(81) - 40;

    public TemperatureSensor() {
        super("temperaturesensor", DeviceStatus.OFF);
    }

    @Override
    public void addCustomStatus(){
        deviceStatusSet.add(DeviceStatus.ACTIVE);
        deviceStatusSet.add(DeviceStatus.FAULT);
        deviceStatusSet.add(DeviceStatus.LOW_BATTERY);
        deviceStatusSet.add(DeviceStatus.TAMPERED);
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
