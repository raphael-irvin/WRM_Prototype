package object.circuit.sector;

import utilclass.UtilClass;

public class Sector {

    private Turn[] turns;
    private Straight[] straights;

    private Sector(){
        // Prevent Instantiation
    }

    public static Sector createRandomSector(){
        int turnAmount = UtilClass.generateRandomInt(1, 3);
        int straightAmount = turnAmount+1;

        Sector sector = new Sector();
        sector.turns = new Turn[turnAmount];
        sector.straights = new Straight[straightAmount];

        for (int i = 0; i < turnAmount; i++) {
            sector.turns[i] = Turn.createRandomTurn();
        }

        for (int i = 0; i < straightAmount; i++) {
            sector.straights[i] = Straight.createRandomStraight();
        }

        return sector;
    }

    public Turn[] getTurns() {
        return turns;
    }

    public Straight[] getStraights() {
        return straights;
    }
}
