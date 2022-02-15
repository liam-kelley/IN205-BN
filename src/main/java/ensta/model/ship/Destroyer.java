package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {
    public Destroyer(){
        super(Orientation.EAST, 2, "Destroyer");
    }
    public Destroyer(Orientation orientation){
        super(orientation, 2, "Destroyer");
    }
}
