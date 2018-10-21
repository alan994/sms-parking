package hr.alanjagar.smsparing.data;

import java.util.ArrayList;
import java.util.List;

import hr.alanjagar.smsparing.model.City;
import hr.alanjagar.smsparing.model.Zone;

/**
 * Created by ajagar on 20.10.2018..
 */

public class MockCityRepository implements CityRepository {
    @Override
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();

        Zone zgZone1 = new Zone("I. Zona", "700101", "");
        Zone zgZone2 = new Zone("I. Zona*", "700109", "");
        Zone zgZone3 = new Zone("II. 1. Zona", "700102", "");
        Zone zgZone4 = new Zone("II. 1. Zona**", "700106", "");
        Zone zgZone5 = new Zone("II. 3. Zona", "700108", "");
        Zone zgZone6 = new Zone("III. Zona", "700103", "");
        Zone zgZone7 = new Zone("IV. 1. Zona", "700105", "");
        Zone zgZone8 = new Zone("IV. 2. Zona", "700104", "");
        Zone zgZone9 = new Zone("IV. 2. 'Paromlin'", "700107", "");

        List<Zone> zagrebZones = new ArrayList<Zone>();
        zagrebZones.add(zgZone1);
        zagrebZones.add(zgZone2);
        zagrebZones.add(zgZone3);
        zagrebZones.add(zgZone4);
        zagrebZones.add(zgZone5);
        zagrebZones.add(zgZone6);
        zagrebZones.add(zgZone7);
        zagrebZones.add(zgZone8);
        zagrebZones.add(zgZone9);
        City zagreb = new City("Zagreb", zagrebZones);


        Zone daZone1 = new Zone("I. Zona", "708885", "");
        Zone daZone2 = new Zone("II. Zona", "708886", "");
        Zone daZone3 = new Zone("III. Zona", "707030", "");

        List<Zone> daruvarZones = new ArrayList<Zone>();
        daruvarZones.add(daZone1);
        daruvarZones.add(daZone2);
        daruvarZones.add(daZone3);
        City daruvar = new City("Daruvar", daruvarZones);


        Zone paZone1 = new Zone("I. Zona", "708815", "");
        Zone paZone2 = new Zone("II. Zona*", "708816", "");

        List<Zone> pakracZones = new ArrayList<Zone>();
        pakracZones.add(paZone1);
        pakracZones.add(paZone2);
        City pakrac = new City("Pakrac", pakracZones);

        cities.add(zagreb);
        cities.add(daruvar);
        cities.add(pakrac);

        return cities;
    }
}
