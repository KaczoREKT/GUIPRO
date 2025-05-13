package Controller;

import House.House;
import Model.HouseModel;
import Other.Settings;

import java.util.TreeMap;

public class HouseController {
    private HouseModel houseModel;

    public HouseController(HouseModel model) {
        this.houseModel = model;
    }

    public TreeMap<String, House> getHousesSet() {
        return houseModel.getHousesMap();
    }
    public String getHousesNames() {
        return houseModel.getHousesNames();
    }
    public void addHouseToSet(String name, String geoLocation) {
        House house = !Settings.isEnabled("geoLokacja")
                ? new House(name)
                : new House(name, geoLocation);
        houseModel.addHouse(house);
    }

    public void removeHouseFromSet(String name) {
        if (houseModel.exists(name)) {
            houseModel.removeHouse(name);
        }
    }

    public boolean houseExists(String name) {
        return houseModel.exists(name);
    }
}
