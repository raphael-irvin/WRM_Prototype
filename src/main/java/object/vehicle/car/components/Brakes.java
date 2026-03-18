package object.vehicle.car.components;

import utilclass.UtilClass;

public class Brakes extends Component {

    private int Deceleration;

    private Brakes(int deceleration) {
        Deceleration = deceleration;
    }

    public static Brakes createRandomLowBrakes() {
        return new Brakes(UtilClass.generateRandomInt(1, 10));
    }

    public int getDeceleration() {
        return Deceleration;
    }

    public void setDeceleration(int deceleration) {
        Deceleration = deceleration;
    }
}
