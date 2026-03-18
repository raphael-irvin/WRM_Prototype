package object.vehicle.car.components;

import utilclass.UtilClass;

public class Gearbox extends Component {

    private int Acceleration;

    private Gearbox(int acceleration) {
        this.Acceleration = acceleration;
    }

    public static Gearbox createRandomLowGearbox() {
        return new Gearbox(UtilClass.generateRandomInt(1, 10));
    }

    public int getAcceleration() {
        return Acceleration;
    }

    public void setAcceleration(int acceleration) {
        Acceleration = acceleration;
    }
}
