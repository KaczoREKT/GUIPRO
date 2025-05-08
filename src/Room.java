import java.util.ArrayList;

public class Room {
    private String name;
    private String type;
    private ArrayList<SmartDevice> smartDevices;

    public Room(String name, String type, ArrayList<SmartDevice> smartDevices) {
        this.name = name;
        this.type = type;
        this.smartDevices = smartDevices;
    }

    public void addDevice(SmartDevice device) {
        smartDevices.add(device);
    }
    public void removeDevice(SmartDevice device) {
        smartDevices.remove(device);
    }
}
