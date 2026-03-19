package object.circuit.sector;

import race.Racer;
import utilclass.UtilClass;

public class Straight implements Segment {

    /* LENGTH PROPERTIES
    VERY LONG = 4
    LONG = 3
    MODERATE = 2
    SHORT = 1
     */

    private int length;

    private Straight(int length) {
        super();
        this.length = length;
    }

    public double calculateScore(Racer racer) {
        // Base Score
        double base = 0.3*racer.getCar().getChassis().getAerodynamicity() +
                (0.3*racer.getCar().getEngine().getTopSpeed()) * (1 + 0.1*length) +
                0.3*racer.getCar().getGearbox().getAcceleration() +
                0.1*racer.getDriver().getFocus();

        // Focus Variance
        double baseVariance = (1 - (double) racer.getDriver().getFocus()/30) * length;
        double variance = Math.min(baseVariance, 0.3);

        return base*UtilClass.generateRandomDouble(1-variance, 1+variance);
    }

    public static Straight createRandomStraight() {
        return new Straight(UtilClass.generateRandomInt(1,4));
    }

    public int getLength() {
        return length;
    }

    public String getLengthString() {
        if (length == 1) {
            return "Short";
        }  else if (length == 2) {
            return "Moderate";
        }   else if (length == 3) {
            return "Long";
        }   else {
            return "Very Long";
        }
    }
}
