package BView;

import Devices.*;
import House.*;

import java.util.Scanner;

public abstract class AbstractMenu {
    House currentHouse;
    Room currentRoom;
    SmartDevice currentSmartDevice;
    boolean running = true;
    public static Scanner scanner = new Scanner(System.in);
    public String choice;
    abstract void getMenu();
}
