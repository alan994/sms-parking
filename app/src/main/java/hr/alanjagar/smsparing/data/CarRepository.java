package hr.alanjagar.smsparing.data;

import java.util.List;

import hr.alanjagar.smsparing.model.Car;

/**
 * Created by ajagar on 20.10.2018..
 */

public interface CarRepository {
    List<Car> getAllCars();
    Car getCarById(int id);
    void removeCar(int id);
    int addCar(Car car);
    void editCar(Car car);
}
