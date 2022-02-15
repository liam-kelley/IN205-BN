package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {
    public Destroyer(){
        super(2, "Destroyer");
        setOrientation(Orientation.EAST);
    }
}
