package object.vehicle.car.components;

import utilclass.UtilClass;

public class Fuel {

    private double fuelEfficiency;

    private Fuel(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public static Fuel createRandomLowFuel() {
        return new Fuel(UtilClass.generateRandomDouble(0.01,1));
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }
}
