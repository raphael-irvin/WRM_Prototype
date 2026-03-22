import object.Driver;
import object.circuit.Circuit;
import object.vehicle.car.Car;
import race.MatchEngine;
import race.Race;
import race.Racer;

public class Main {

    public static void main(String[] args) {
        Driver driver1 = Driver.createRandomLowDriver();
        driver1.showDriverInfo();
        Car car1 = Car.createRandomLowCar();
        Racer racer1 = new Racer(driver1, car1);

        Driver driver2 = Driver.createRandomLowDriver();
        driver2.showDriverInfo();
        Car car2 = Car.createRandomLowCar();
        Racer racer2 = new Racer(driver2, car2);

        Driver driver3 = Driver.createRandomLowDriver();
        driver3.showDriverInfo();
        Car car3 = Car.createRandomLowCar();
        Racer racer3 = new Racer(driver3, car3);

        Driver driver4 = Driver.createRandomLowDriver();
        driver4.showDriverInfo();
        Car car4 = Car.createRandomLowCar();
        Racer racer4 = new Racer(driver4, car4);

        Driver driver5 = Driver.createRandomLowDriver();
        driver5.showDriverInfo();
        Car car5 = Car.createRandomLowCar();
        Racer racer5 = new Racer(driver5, car5);

        Racer[] racers = new Racer[]{racer1, racer2, racer3, racer4, racer5};

        Circuit circuit = Circuit.createRandomSingleTrackCircuit();

        MatchEngine matchEngine = new MatchEngine();
        Race race = Race.instantiateRandomRace(racers, circuit, matchEngine);
        race.getMatchEngine().simulateRace();
    }
}
