package ensta.model.ship;

import ensta.util.Orientation;

public class Submarine extends AbstractShip {
    public Submarine(){
        super(3, "Submarine");
        setOrientation(Orientation.EAST);
    }
}
