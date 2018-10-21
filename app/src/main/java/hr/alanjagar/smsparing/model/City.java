package hr.alanjagar.smsparing.model;

import java.util.List;

/**
 * Created by ajagar on 20.10.2018..
 */

public class City {
    private  String name;
    private List<Zone> zones;

    public City() {
    }

    public City(String name, List<Zone> zones) {
        this.setName(name);
        this.setZones(zones);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}
