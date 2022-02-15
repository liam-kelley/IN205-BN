package ensta.model.ship;

public class Carrier extends AbstractShip {
    public Carrier(){
        super(5, "Carrier");
        setOrientation(Orientation.EAST);
    }
}
