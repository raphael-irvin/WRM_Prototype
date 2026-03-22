package race;

import object.circuit.Circuit;
import object.circuit.Track;
import object.circuit.sector.Segment;
import object.circuit.sector.Sector;
import utilclass.UtilClass;

import java.util.*;

public class Race {

    // RACE SIMULATION CLASS
    private MatchEngine matchEngine;

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

    public static Race instantiateRandomRace(Racer[] racers, Circuit circuit, MatchEngine matchEngine) {
        Race race = new Race(circuit);
        race.instantiateRacers(racers);
        race.track = race.circuit.getRandomTrack();
        race.laps = race.track.getLaps();

        // Establish Handshake with Match Engine
        race.matchEngine = matchEngine;
        race.matchEngine.setRace(race);

        // TEMPORARY ID Generation
        race.raceID = String.valueOf(UtilClass.generateRandomInt(1000, 9999));

        race.displayRaceInformation();
        return race;
    }

    public void simulateRace() {
        matchEngine.simulateRace();
    }

    public void instantiateRacers(Racer[] racers) {
        this.racers = racers;
        for (Racer racer : racers) {
            liveTime.put(racer, 0.0);
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

    public MatchEngine getMatchEngine() {
        return matchEngine;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Map<Racer, Double> getLiveTime() {
        return liveTime;
    }

    public HashMap<Integer, Map<Racer, Double>> getLapRecords() {
        return lapRecords;
    }
}
