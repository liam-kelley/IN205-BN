package ensta.model.ship;

import ensta.util.Orientation;
import ensta.model.Coords;

public abstract class AbstractShip {
    private Orientation orientation;
    private Coords coords;
    private int length;
    private String name;
    private int strikeCount;
    
    public AbstractShip(Orientation orientation, Coords coords, int length, String name){
        this.orientation = orientation;
        this.coords = coords;
        this.length = length;
        this.name = name;
        this.strikeCount = 0;
    }

    public AbstractShip(Orientation orientation, int length, String name){
        this.orientation = orientation;
        this.length = length;
        this.name = name;
        this.strikeCount = 0;
    }

    public AbstractShip(int length, String name){
        this.length = length;
        this.name = name;
        this.strikeCount = 0;
    }

    public Orientation getOrientation(){return(this.orientation);}
    public Coords getCoords(){return(this.coords);}
    public int getLength(){return(this.length);}
    public String getName(){return(this.name);}
    
    public void setOrientation(Orientation orientation){this.orientation = orientation;}
    public void setOrientation(String orientation){
        if(orientation.equals(new String("north"))){
            this.orientation = Orientation.NORTH;
        }
        else if(orientation.equals(new String("south"))){
            this.orientation = Orientation.SOUTH;
        }
        else if(orientation.equals(new String("east"))){
            this.orientation = Orientation.EAST;
        }
        else if(orientation.equals(new String("west"))){
            this.orientation = Orientation.WEST;
        }
    }
    
    public boolean isSunk(){return(strikeCount >= length);}

    public void addStrike(){    //Could return boolean to check for errors
        if(this.strikeCount<this.length){strikeCount++;}
        else{System.out.println("Error! strikeCount too high.");}
    }
}
