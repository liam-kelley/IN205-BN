package ensta.model.ship;

import ensta.util.Orientation;
import ensta.model.Coords;

public class AbstractShip {
    private Orientation orientation;
    private Coords coords;
    private int length;
    private String name;
    
    public AbstractShip(Orientation orientation, Coords coords, int length, String name){
        this.orientation = orientation;
        this.coords = coords;
        this.length = length;
        this.name = name;
    }

    public AbstractShip(int length, String name){ //Constructeur sans init de orientation, position
        this.length = length;
        this.name = name;
    }

    public Orientation getOrientation(){
        return(this.orientation);
	}

    public int getLength(){
        return(this.length);
	}

    public String getName(){
        return(this.name);
    }

    public boolean isSunk(){ //à compléter
        return(true);
    }
}
