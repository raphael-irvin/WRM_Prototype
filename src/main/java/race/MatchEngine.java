package race;

import object.circuit.sector.Sector;
import object.circuit.sector.Segment;
import utilclass.UtilClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchEngine {

    private Race race;

    protected void simulateRace() {
        // Validate Race States
        if (race.getRacers().length == 0 || race.getCircuit() == null || race.getTrack() == null) {
            throw new IllegalStateException("Race is not properly instantiated");
        }

        if (race.getState() != 0) {
            throw new IllegalStateException("Race has already started or finished");
        }

        // Set Lap Record for Lap 0 (Initial Standings)
        race.getLapRecords().put(0, new HashMap<>(race.getLiveTime()));

        // Change Race State
        race.setState(1);

        // FOR EACH LAP
        for (int lap = 1; lap <= race.getLaps(); lap++) {
            System.out.printf("\n--- LAP %d ---\n", lap);

            // FOR EACH SECTOR (GAP TIME UPDATE HAPPENS IN TURNS AND STRAIGHTS)
            int sectorNumber = 0;
            for (Sector sector : race.getTrack().getSectors()) {
                sectorNumber++;
                System.out.printf("\nSector %d:\n", sectorNumber);

                processSegments(sector.getTurns(), "Turn");
                processSegments(sector.getStraights(), "Straight");
            }

            // Save Lap Record
            race.getLapRecords().put(lap, new HashMap<>(race.getLiveTime()));

            // Display Lap Information

            // Display Racer Standings, Lap Time Differences, and Pace compared to the previous lap
            System.out.printf("\nLap %d Standings:\n", lap);
            for (int i = 0; i < race.getRacers().length; i++) {
                double previousLapTime = race.getLapRecords().get(lap - 1).get(race.getRacers()[i]);
                double lapDifference = race.getLiveTime().get(race.getRacers()[i]) - previousLapTime;
                double previousLapDifference;
                double pace;
                if (lap == 1) {
                    previousLapDifference = 0;
                    pace = lapDifference;
                } else {
                    previousLapDifference = previousLapTime - race.getLapRecords().get(lap - 2).get(race.getRacers()[i]);
                    pace = lapDifference - previousLapDifference;
                }

                System.out.printf("%d. %s - %.3f ", i+1, race.getRacers()[i].getDriver().getName(), race.getLiveTime().get(race.getRacers()[i]));
                System.out.printf("(%.3f) | Pacing: %.3f \n", lapDifference, pace);
            }
        }
    }

    private void processSegments(Segment[] segments, String segmentLabel) {
        int segmentNumber = 0;
        for (Segment segment : segments) {
            segmentNumber++;
            System.out.println();

            // Work on a snapshot, then commit once this segment is fully evaluated.
            Map<Racer, Double> currentLapTimes = new HashMap<>(race.getLiveTime());
            Map<Racer, Double> performances = new HashMap<>();
            List<Double> validPerformances = new ArrayList<>();

            for (Racer racer : race.getRacers()) {
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

            race.getLiveTime().clear();
            race.getLiveTime().putAll(currentLapTimes);
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

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
