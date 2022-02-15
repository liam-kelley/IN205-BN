package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip {
    public BattleShip(){
        super(4, "BattleShip");
        setOrientation(Orientation.EAST);
    }
}
