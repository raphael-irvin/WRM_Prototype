package object.circuit;

import object.circuit.sector.Sector;
import utilclass.UtilClass;

public class Track {

    private String trackID;

    private Sector[] sectors = new Sector[3];
    private int laps;

    private Track() {
        // Prevent Instantiation
    }

    public static Track createRandomTrack(Circuit circuit) {
        Track track = new Track();

        // TEMPORARY ID Generation
        track.trackID = circuit.getTracks().length > 1 ? String.valueOf((circuit.getTracks().length)) : "1";

        for (int i = 0; i < 3; i++) {
            track.sectors[i] = Sector.createRandomSector();
            track.laps = UtilClass.generateRandomInt(20, 40);
        }
        return track;
    }

    public Sector[] getSectors() {
        return sectors;
    }

    public int getLaps() {
        return laps;
    }

    public String getTrackID() {
        return trackID;
    }
}


