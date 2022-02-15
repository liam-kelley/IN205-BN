package ensta.model.ship;

import ensta.util.Orientation;

public class Carrier extends AbstractShip {
    public Carrier(){
        super(Orientation.EAST, 5, "Carrier");
    }
    public Carrier(Orientation orientation){
        super(orientation,5, "Carrier");
    }
}
