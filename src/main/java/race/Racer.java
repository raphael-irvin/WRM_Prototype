package race;

import object.Driver;
import object.vehicle.car.Car;

public class Racer {

    private Driver driver;
    private Car car;
    private int state; // 0: Out of Race, 1: Active

     public Racer(Driver driver, Car car) {
        this.driver = driver;
        this.car = car;
        this.state = 1;
    }

     public Driver getDriver() {
        return driver;
    }

     public Car getCar() {
        return car;
    }

    public int getState() {
         return state;}
}
