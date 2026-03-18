package race;

import object.circuit.Circuit;
import object.circuit.Track;
import object.circuit.sector.Sector;
import object.circuit.sector.Straight;
import object.circuit.sector.Turn;
import utilclass.UtilClass;

import java.util.*;

public class Race {

    // RACE STATIC INFORMATION
    private Racer[] racers;
    private Circuit circuit;
    private Track track;
    private String raceID;
    private int laps = 0;

    private int state = 0; // 0 = Not Started, 1 = Ongoing, 2 = Finished

    // RACE DYNAMIC INFORMATION
    private Map<Racer, Double> liveTime = new HashMap<>();

    private Race(Circuit circuit) {
        this.circuit = circuit;
    }

    public static Race instantiateRandomRace(Racer[] racers, Circuit circuit) {
        Race race = new Race(circuit);
        race.instantiateRacers(racers);
        race.track = race.circuit.getRandomTrack();
        race.laps = race.track.getLaps();

        // TEMPORARY ID Generation
        race.raceID = String.valueOf(UtilClass.generateRandomInt(1000, 9999));

        race.displayRaceInformation();
        return race;
    }

    public void instantiateRacers(Racer[] racers) {
        this.racers = racers;
        for (Racer racer : racers) {
            liveTime.put(racer, 0.0);
        }
    }

    public void simulateRace() {
        // Validate Race States
        if (racers.length == 0 || circuit == null || track == null) {
            throw new IllegalStateException("Race is not properly instantiated");
        }

        if (state != 0) {
            throw new IllegalStateException("Race has already started or finished");
        }

        // Change Race State
        state = 1;

        // FOR EACH LAP
        for (int lap = 1; lap <= laps; lap++) {
            System.out.printf("\n--- LAP %d ---\n", lap);

            // FOR EACH SECTOR (GAP TIME UPDATE HAPPENS IN TURNS AND STRAIGHTS)
            int sectorNumber = 0;
            for (Sector sector : track.getSectors()) {
                sectorNumber++;
                System.out.printf("\nSector %d:\n", sectorNumber);

                // FOR EACH TURN
                int turnNumber = 0;
                for (Turn turn : sector.getTurns()) {
                    turnNumber++;
                    // Get a copy of the current standing (to ensure data integrity rather than customizing on real data)
                    Map<Racer, Double> currentLapTimes = liveTime;

                    // FOR EACH RACER
                    // Calculate their turn performance and store in a map
                    Map<Racer, Double> turnPerformances = new HashMap<>();
                    for (Racer racer : racers) {
                        // Ensure only active drivers are calculated
                        if (racer.getState() == 1) {
                            double driverTurnPerformance = turn.calculateScore(racer);
                            turnPerformances.put(racer, driverTurnPerformance);
                            System.out.printf("Racer: %s - Turn %d | Performance: %.2f\n", racer.getDriver().getName(), turnNumber, driverTurnPerformance);
                        }
                    }

                    // GET AVERAGE TURN PERFORMANCE
                    double averageTurnPerformance = turnPerformances.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                    System.out.printf("Average Turn Performance: %.2f\n", averageTurnPerformance);

                    // Compare with the average turnPerformance of all active drivers and add to liveTime;
                    for (Racer racer : turnPerformances.keySet()) {
                        double relativePerformance = turnPerformances.get(racer)/averageTurnPerformance;
                        System.out.printf("Racer: %s - Turn %d | Relative Performance: %.2f\n", racer.getDriver().getName(), turnNumber, relativePerformance);
                        double addedTime;
                        if (relativePerformance > 1) {
                            addedTime = UtilClass.generateRandomDouble(1, 1.2) - (relativePerformance - 1) * UtilClass.generateRandomDouble(0.5, 0.75);
                            currentLapTimes.put(racer, currentLapTimes.get(racer) + addedTime);
                        } else {
                            addedTime = UtilClass.generateRandomDouble(1,1.2) + (1 - relativePerformance) * UtilClass.generateRandomDouble(0.5, 0.75);
                            currentLapTimes.put(racer, currentLapTimes.get(racer) + addedTime);
                            System.out.printf("Racer: %s - Turn %d | Time Added: %.2f\n", racer.getDriver().getName(), turnNumber, addedTime);
                        }
                    }
                }

                // FOR EACH STRAIGHT
                int straightNumber = 0;
                for (Straight straight : sector.getStraights()) {
                    straightNumber++;
                    // Get a copy of the current standing (to ensure data integrity rather than customizing on real data)
                    Map<Racer, Double> currentLapTimes = liveTime;

                    // FOR EACH RACER
                    // Calculate their Straight performance and store in a map
                    Map<Racer, Double> straightPerformances = new HashMap<>();
                    for (Racer racer : racers) {
                        // Ensure only active drivers are calculated
                        if (racer.getState() == 1) {
                            double driverStraightPerformance = straight.calculateScore(racer);
                            straightPerformances.put(racer, driverStraightPerformance);
                            System.out.printf("Racer: %s - Straight %d | Performance: %.2f\n", racer.getDriver().getName(), straightNumber, driverStraightPerformance);
                        }
                    }

                    // GET AVERAGE STRAIGHT PERFORMANCE
                    double averageStraightPerformance = straightPerformances.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                    System.out.printf("Average Straight Performance: %.2f\n", averageStraightPerformance);

                    // Compare with the average straightPerformance of all active drivers and add to liveTime;
                    for (Racer racer : straightPerformances.keySet()) {
                        double relativePerformance = straightPerformances.get(racer) / averageStraightPerformance;
                        System.out.printf("Racer: %s - Straight %d | Relative Performance: %.2f\n", racer.getDriver().getName(), straightNumber, relativePerformance);
                        double addedTime;
                        if (relativePerformance > 1) {
                            addedTime = UtilClass.generateRandomDouble(1, 1.2) - (relativePerformance - 1) * UtilClass.generateRandomDouble(0.5, 0.75);
                        } else {
                            addedTime = UtilClass.generateRandomDouble(1, 1.2) + (1 - relativePerformance) * UtilClass.generateRandomDouble(0.5, 0.75);
                        }
                        currentLapTimes.put(racer, currentLapTimes.get(racer) + addedTime);
                        System.out.printf("Racer: %s - Straight %d | Time Added: %.2f\n", racer.getDriver().getName(), straightNumber, addedTime);
                    }
                }
            }

            // Display Lap Information

            // Display Racer Standings
            System.out.printf("\nLap %d Standings:\n", lap);
            for (int i = 0; i < racers.length; i++) {
                System.out.printf("%d. %s - %.3f\n", i+1, racers[i].getDriver().getName(), liveTime.get(racers[i]));
            }
            }
        }

    public void displayRaceInformation() {
        System.out.printf("\nRACE ID: %s\n", raceID);
        System.out.printf("Circuit: %s\n", circuit.getCircuitID());
        System.out.printf("Track: %s\n", track.getTrackID());
        System.out.printf("Laps: %d\n", laps);
        System.out.print("Drivers:\n");
        for (Racer racer : racers) {
            System.out.printf("\t%s\n", racer.getClass().getSimpleName());
        }
    }

    public Racer[] getRacers() {
        return racers;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public Track getTrack() {
        return track;
    }

    public String getRaceID() {
        return raceID;
    }

    public int getLaps() {
        return laps;
    }
}
