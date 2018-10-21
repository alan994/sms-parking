package hr.alanjagar.smsparing.model;

public class Car {
    private  int id;
    private String name;
    private String plate;

    public Car(int id, String name, String plate) {
        this.id = id;
        this.name = name;
        this.plate = plate;
    }

    public Car(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.name, this.plate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
