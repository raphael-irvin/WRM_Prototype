package object.vehicle.car.components;

import utilclass.UtilClass;

public class Engine extends Component {

    private int TopSpeed;

    private Engine(int TopSpeed) {
        this.TopSpeed = TopSpeed;
    }

    public static Engine createRandomLowEngine() {
        return new Engine(UtilClass.generateRandomInt(1, 10));
    }

    public int getTopSpeed() {
        return TopSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        TopSpeed = topSpeed;
    }
}
