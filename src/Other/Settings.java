package Other;

import java.util.TreeMap;

public class Settings {

    public static TreeMap<String, Boolean> settings = new TreeMap<>();

    static {
        settings.put("geoLokacja", true);
    }

    public static void set(String key, boolean value) {
        settings.put(key, value);
    }

    public static boolean isEnabled(String key) {
        return settings.getOrDefault(key, false);
    }



}
