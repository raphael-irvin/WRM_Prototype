package object.vehicle.car;

import object.vehicle.car.components.Brakes;
import object.vehicle.car.components.Engine;
import object.vehicle.car.components.Fuel;
import object.vehicle.car.components.Gearbox;

public class Car {

    private Chassis chassis;
    private Engine engine;
    private Gearbox gearbox;
    private Brakes brakes;
    private Fuel fuel;

    private Car(Chassis chassis, Engine engine, Gearbox gearbox, Brakes brakes, Fuel fuel) {
        this.chassis = chassis;
        this.engine = engine;
        this.gearbox = gearbox;
        this.brakes = brakes;
        this.fuel = fuel;
    }

    public static Car createRandomLowCar() {
        System.out.println("\nRANDOM LOW CAR CREATED");
        return new Car(Chassis.createRandomLowChassis(), Engine.createRandomLowEngine(), Gearbox.createRandomLowGearbox(), Brakes.createRandomLowBrakes(), Fuel.createRandomLowFuel());
    }

    public Chassis getChassis() {
        return chassis;
    }

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public Brakes getBrakes() {
        return brakes;
    }

    public void setBrakes(Brakes brakes) {
        this.brakes = brakes;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }
}
