package hr.alanjagar.smsparing.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import hr.alanjagar.smsparing.model.Car;

/**
 * Created by ajagar on 20.10.2018..
 */

public class SharedPreferencesCarRepository implements CarRepository {

    public static final String CAR_SETTINGS_KEY  = "cars";
    private Context context;

    public SharedPreferencesCarRepository(Context context){
        this.context = context;
    }

    @Override
    public List<Car> getAllCars() {
        String data = this.getCarDataFromSettings();
        return this.parseCarsDataFromSettings(data);
    }

    @Override
    public Car getCarById(int id) {
        String data = this.getCarDataFromSettings();
        List<Car> cars = this.parseCarsDataFromSettings(data);

        for(Car car : cars){
            if(car.getId() == id){
                return car;
            }
        }

        return  null;
    }

    @Override
    public void removeCar(int id) {
        String originalData = this.getCarDataFromSettings();
        List<Car> cars = this.parseCarsDataFromSettings(originalData);
        int indextoRemove = -1;
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).getId() == id){
                indextoRemove = i;
                break;
            }
        }
        if(indextoRemove != -1){
            cars.remove(indextoRemove);
        }

        String dataToSave = this.convertCarsInToJson(cars);
        this.setCarDatainSettings(dataToSave);
    }

    @Override
    public int addCar(Car car) {
        String data = this.getCarDataFromSettings();
        List<Car> cars = this.parseCarsDataFromSettings(data);

        int newCarId = 1;
        if(cars.size() != 0){
            int lastId = cars.get(cars.size() - 1).getId();
            newCarId = lastId++;
        }

        car.setId(newCarId);
        cars.add(car);

        String dataToSave = this.convertCarsInToJson(cars);
        this.setCarDatainSettings(dataToSave);

        return  newCarId;
    }

    @Override
    public void editCar(Car car) {
        String originalData = this.getCarDataFromSettings();
        List<Car> cars = this.parseCarsDataFromSettings(originalData);

        for(Car c : cars){
            if(c.getId() == car.getId()){
                c.setName(car.getName());
                c.setPlate(car.getPlate());
            }
        }

        String dataToSave = this.convertCarsInToJson(cars);
        this.setCarDatainSettings(dataToSave);
    }

    private String getCarDataFromSettings(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        String data = prefs.getString(CAR_SETTINGS_KEY, "[]");
        return data;
    }

    private void setCarDatainSettings(String data){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.context);
        settings.edit().putString(CAR_SETTINGS_KEY, data).commit();
    }

    private List<Car> parseCarsDataFromSettings(String data){
        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<Car>>(){}.getType();
        List<Car> cars = gson.fromJson(data, collectionType);

        return cars;
    }

    private String convertCarsInToJson(List<Car> cars){
        Gson gson = new Gson();
        String data = gson.toJson(cars);
        return data;
    }

}
