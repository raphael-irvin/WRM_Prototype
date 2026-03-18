package object.circuit;

import object.circuit.sector.Sector;
import object.circuit.sector.Straight;
import object.circuit.sector.Turn;
import utilclass.UtilClass;

public class Circuit {

    private String circuitID;

    private String roadMaterial;

    private String weather;
    private String windSpeed;
    private double temperature;
    private double wetness;
    private int visibility;

    private Track[] tracks;

    private Circuit() {
        // Prevent Instantiation
    }

    public Track getRandomTrack() {
        return tracks[(UtilClass.generateRandomInt(0, tracks.length-1))];
    }

    public static Circuit createRandomSingleTrackCircuit() {
        Circuit circuit = new Circuit();
        circuit.circuitID = Circuit.generateCircuitID();
        circuit.tracks = new Track[1];
        circuit.tracks[0] = Track.createRandomTrack(circuit);
        System.out.println("\nSINGLE TRACK CIRCUIT CREATED");
        circuit.displayCircuitInformation();
        return circuit;
    }

    public static Circuit createRandomMultiTrackCircuit() {
        Circuit circuit = new Circuit();
        circuit.circuitID = Circuit.generateCircuitID();
        int amountOfTracks = UtilClass.generateRandomInt(2, 4);
        circuit.tracks = new Track[amountOfTracks];
        for (int i = 0; i < amountOfTracks; i++) {
            circuit.tracks[i] = Track.createRandomTrack(circuit);
        }
        System.out.println("\nMULTI TRACK CIRCUIT CREATED");
        circuit.displayCircuitInformation();
        return circuit;
    }

    public void displayCircuitInformation() {

        for (Track track : tracks) {
            System.out.printf("\nCIRCUIT %s\n", circuitID);
            System.out.printf("TRACK %s:\n", track.getTrackID());
            System.out.printf("Laps: %d\n", track.getLaps());

            int sectorNumber = 0;
            for (Sector sector : track.getSectors()) {
                System.out.println("\n\tSECTOR " + ++sectorNumber);
                System.out.println("\tTurns:");

                int turnNumber = 0;
                for (Turn turn : sector.getTurns()) {
                    System.out.println("\tTurn " + ++turnNumber + " | Sharpness: " + turn.getSharpnessString());
                }

                int straightNumber = 0;
                for (Straight straight : sector.getStraights()) {
                    System.out.println("\tStraight " + ++straightNumber + " | Length: " + straight.getLengthString());
                }
            }
        }
    }

    // TEMPORARY ID Generation
    public static String generateCircuitID() {
        return String.valueOf(UtilClass.generateRandomInt(1000, 9999));
    }

    public String getCircuitID() {
        return circuitID;
    }

    public String getRoadMaterial() {
        return roadMaterial;
    }

    public String getWeather() {
        return weather;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWetness() {
        return wetness;
    }

    public int getVisibility() {
        return visibility;
    }

    public Track[] getTracks() {
        return tracks;
    }
}
