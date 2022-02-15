package ensta.model.ship;

import ensta.util.Orientation;
import ensta.model.Coords;

public abstract class AbstractShip {
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

    public AbstractShip(Orientation orientation, int length, String name){
        this.orientation = orientation;
        this.length = length;
        this.name = name;
    }

    public AbstractShip(int length, String name){
        this.length = length;
        this.name = name;
    }

    public Orientation getOrientation(){return(this.orientation);}
    public Coords getCoords(){return(this.coords);}
    public int getLength(){return(this.length);}
    public String getName(){return(this.name);}
    
    public void setOrientation(Orientation orientation){this.orientation = orientation;}
    public void setOrientation(String orientation){
        if(orientation == "north"){
            this.orientation = Orientation.NORTH;
        }
        else if(orientation == "south"){
            this.orientation = Orientation.SOUTH;
        }
        else if(orientation == "east"){
            this.orientation = Orientation.EAST;
        }
        else if(orientation == "west"){
            this.orientation = Orientation.WEST;
        }
    }
    //public void setCoords(Coords coords){this.coords = coords;}
    //public void setLength(int length){this.length = length;}
    //public void setName(String name){this.name = name;}

    public boolean isSunk(){ //à compléter
        return(true);
    }
}
