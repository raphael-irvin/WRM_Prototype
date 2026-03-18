package object.vehicle.car;

import utilclass.UtilClass;

public class Chassis {

    private String material;
    private int aerodynamicity;
    private double thermalPerformance;
    private double weight;
    private int reliability;

    private Chassis(String material, int aerodynamicity, double thermalPerformance, double weight, int reliability) {
        this.material = material;
        this.aerodynamicity = aerodynamicity;
        this.thermalPerformance = thermalPerformance;
        this.weight = weight;
        this.reliability = reliability;
    }

    public static Chassis createRandomLowChassis() {
        return new Chassis("Aluminum", UtilClass.generateRandomInt(1,10), 0.7, 150.0, 3);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getAerodynamicity() {
        return aerodynamicity;
    }

    public void setAerodynamicity(int aerodynamicity) {
        this.aerodynamicity = aerodynamicity;
    }

    public double getThermalPerformance() {
        return thermalPerformance;
    }

    public void setThermalPerformance(double thermalPerformance) {
        this.thermalPerformance = thermalPerformance;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }
}
