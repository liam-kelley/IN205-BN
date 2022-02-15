package ensta.model.ship;

import ensta.util.Orientation;

public class Submarine extends AbstractShip {
    public Submarine(){
        super(Orientation.EAST, 3, "Submarine");
    }
    public Submarine(Orientation orientation){
        super(orientation, 3, "Submarine");
    }
}
