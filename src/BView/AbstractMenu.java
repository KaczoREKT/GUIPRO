package BView;

import java.util.Scanner;

public abstract class AbstractMenu {
    boolean running = true;
    public static Scanner scanner = new Scanner(System.in);
    public String choice;
    abstract void getMenu();
}
