package Devices;

import Interfaces.Switchable;

import java.awt.*;
import java.util.UUID;

public class Lightbulb extends SmartDevice implements Switchable {
    private int hue = 30;
    private double saturation = 0.01;
    private double value = 0.98;

    public Lightbulb() {
        super("lightbulb", DeviceStatus.OFF);
    }

    public void setHue(int hue) {
        if (hue < 0 || hue >= 360) throw new IllegalArgumentException("Hue must be in [0, 360)");
        this.hue = hue;
    }

    public void setSaturation(double saturation) {
        if (saturation < 0 || saturation > 1) throw new IllegalArgumentException("Saturation must be in [0, 1]");
        this.saturation = saturation;
    }

    public void setValue(double value) {
        if (value < 0 || value > 1) throw new IllegalArgumentException("Value must be in [0, 1]");
        this.value = value;
    }

    public Color getRGBColor() {
        float H = hue;
        float S = (float) saturation;
        float V = (float) value;

        float C = V * S;
        float X = C * (1 - Math.abs((H / 60f) % 2 - 1));
        float m = V - C;

        float r = 0, g = 0, b = 0;

        if (0 <= H && H < 60) {
            r = C; g = X; b = 0;
        } else if (60 <= H && H < 120) {
            r = X; g = C; b = 0;
        } else if (120 <= H && H < 180) {
            r = 0; g = C; b = X;
        } else if (180 <= H && H < 240) {
            r = 0; g = X; b = C;
        } else if (240 <= H && H < 300) {
            r = X; g = 0; b = C;
        } else if (300 <= H && H < 360) {
            r = C; g = 0; b = X;
        }

        int R = Math.round((r + m) * 255);
        int G = Math.round((g + m) * 255);
        int B = Math.round((b + m) * 255);

        return new Color(R, G, B);
    }

    @Override
    public void simulate() {

    }

    @Override
    public void addCustomStatus() {

    }

    @Override
    public void turnOn() {
        if (isOn()){
            throw new IllegalStateException("Device is already on.");
        } else {
            setStatus(DeviceStatus.ON);
        }
    }

    @Override
    public void turnOff() {
        if (isOn()){
            throw new IllegalStateException("Device is already off.");
        } else {
            setStatus(DeviceStatus.OFF);
        }
    }

    @Override
    public boolean isOn() {
        return getStatus() == DeviceStatus.ON;
    }


}
