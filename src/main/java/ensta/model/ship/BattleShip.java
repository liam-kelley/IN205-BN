package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip {
    public BattleShip(){
        super(Orientation.EAST, 4, "BattleShip");
    }
    public BattleShip(Orientation orientation){
        super(orientation,4, "BattleShip");
    }
}
