package object.vehicle.car.components;

public class Component {

    private double thermalPerformance;
    private String reliability;
    private double degradationRate;

    public double getThermalPerformance() {
        return thermalPerformance;
    }

    public void setThermalPerformance(double thermalPerformance) {
        this.thermalPerformance = thermalPerformance;
    }
}
