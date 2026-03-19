package object.circuit.sector;

import race.Racer;
import utilclass.UtilClass;

public class Turn implements Segment{

    /* SHARPNESS PROPERTIES
    VERY SHARP = 4
    SHARP = 3
    MODERATE = 2
    GRADUAL = 1
     */

    private int sharpness;

    private Turn(int sharpness) {
        super();
        this.sharpness = sharpness;
    }

    public double calculateScore(Racer racer) {
        // Base Score
        double base = 0.2*racer.getCar().getChassis().getAerodynamicity() +
                0.2*racer.getCar().getGearbox().getAcceleration() +
                0.2*racer.getCar().getBrakes().getDeceleration() +
                (0.3*racer.getDriver().getCornering()) * (1 + 0.1*sharpness) +
                0.1*racer.getDriver().getFocus();

        // Focus Variance
        double baseVariance = (1 - (double) racer.getDriver().getFocus()/30) * sharpness;
        double variance = Math.min(baseVariance, 0.3);
        return base*UtilClass.generateRandomDouble(1-variance, 1+variance);
    }

    public static Turn createRandomTurn() {
        return new Turn(UtilClass.generateRandomInt(1,4));
    }

    public int getSharpness() {
        return sharpness;
    }

    public String getSharpnessString() {
        if (sharpness == 1) {
            return "Gradual";
        } else if (sharpness == 2) {
            return "Moderate";
        } else if (sharpness == 3) {
            return "Sharp";
        } else {
            return "Very Sharp";
        }
    }
}
