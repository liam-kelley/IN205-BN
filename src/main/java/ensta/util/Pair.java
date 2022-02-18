package ensta.util;

public class Pair<Hit,Coords> {
    
    public final Hit hit;
    public final Coords coords;

    public Pair(Hit hit, Coords coords) {
        this.hit = hit;
        this.coords = coords;
    }

    public static <Hit, Coords> Pair<Hit, Coords> makePair(Hit hit, Coords coords) {
        return new Pair<Hit, Coords>(hit, coords);
    }
};
