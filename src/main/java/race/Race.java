package race;

import object.circuit.Circuit;
import object.circuit.Track;
import object.circuit.sector.Segment;
import object.circuit.sector.Sector;
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

    // RACE LAP RECORDS
    private HashMap<Integer, Map<Racer, Double>> lapRecords = new HashMap<>();

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

        // Set Lap Record for Lap 0 (Initial Standings)
        lapRecords.put(0, new HashMap<>(liveTime));

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

                processSegments(sector.getTurns(), "Turn");
                processSegments(sector.getStraights(), "Straight");
            }

            // Save Lap Record
            lapRecords.put(lap, new HashMap<>(liveTime));

            // Display Lap Information

            // Display Racer Standings and Lap Time Difference compared to the previous lap
            System.out.printf("\nLap %d Standings:\n", lap);
            for (int i = 0; i < racers.length; i++) {
                System.out.printf("%d. %s - %.3f ", i+1, racers[i].getDriver().getName(), liveTime.get(racers[i]));
                double previousLapTime = lapRecords.get(lap - 1).get(racers[i]);
                double lapDifference = liveTime.get(racers[i]) - previousLapTime;
                System.out.printf("(%.3f)\n", lapDifference);
            }
        }
    }

    private void processSegments(Segment[] segments, String segmentLabel) {
        int segmentNumber = 0;
        for (Segment segment : segments) {
            segmentNumber++;
            System.out.println();

            // Work on a snapshot, then commit once this segment is fully evaluated.
            Map<Racer, Double> currentLapTimes = new HashMap<>(liveTime);
            Map<Racer, Double> performances = new HashMap<>();
            List<Double> validPerformances = new ArrayList<>();

            for (Racer racer : racers) {
                if (racer.getState() == 1) {
                    double performance = segment.calculateScore(racer);
                    performances.put(racer, performance);
                    validPerformances.add(performance);
                    System.out.printf("Racer: %s - %s %d | Performance: %.3f\n", racer.getDriver().getName(), segmentLabel, segmentNumber, performance);
                } else {
                    performances.put(racer, 0.0);
                }
            }

            double averagePerformance = validPerformances.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            System.out.printf("Average %s Performance: %.3f\n\n", segmentLabel, averagePerformance);

            for (Racer racer : performances.keySet()) {
                double relativePerformance = performances.get(racer) / averagePerformance;
                double addedTime = calculateAddedTime(relativePerformance);
                currentLapTimes.put(racer, currentLapTimes.get(racer) + addedTime);
                System.out.printf("Racer: %s - %s %d | Relative Performance: %.3f, Time Added: %.3f\n", racer.getDriver().getName(), segmentLabel, segmentNumber, relativePerformance, addedTime);
            }

            liveTime = currentLapTimes;
        }
    }

    public double calculateAddedTime(double relativePerformance) {
        double addedTime;
        if (relativePerformance > 1) {
            addedTime = UtilClass.generateRandomDouble(1, 1.2) - (relativePerformance - 1) * UtilClass.generateRandomDouble(0.5, 0.75);
        } else {
            addedTime = UtilClass.generateRandomDouble(1, 1.2) + (1 - relativePerformance) * UtilClass.generateRandomDouble(0.5, 0.75);
        }
        return addedTime;
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
