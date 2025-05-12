package Devices;

import Interfaces.DeviceObserver;
import Interfaces.Switchable;

import java.util.UUID;

public class Heater extends SmartDevice implements DeviceObserver, Switchable {

    public Heater() {
        super("heater", DeviceStatus.OFF);
    }

    @Override
    public void simulate() {

    }

    @Override
    public void addCustomStatus() {

    }

    @Override
    public void turnOn() {
        setStatus(DeviceStatus.ON);
    }

    @Override
    public void turnOff() {
        setStatus(DeviceStatus.OFF);
    }

    @Override
    public boolean isOn() {
        return getStatus() == DeviceStatus.ON;
    }
}
