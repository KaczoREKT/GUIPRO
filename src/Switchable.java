//Określa, że urządzenie może być włączane i wyłączane.


public interface Switchable {
    void turnOn();
    void turnOff();
    boolean isOn();
}
