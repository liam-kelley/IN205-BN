package ensta.model.ship;

import ensta.util.Orientation;

public class Carrier extends AbstractShip {
    public Carrier(){
        super(5, "Carrier");
        setOrientation(Orientation.EAST);
    }
}
