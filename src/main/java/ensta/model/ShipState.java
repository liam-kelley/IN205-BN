package ensta.model;

import java.io.Serializable;

import ensta.model.ship.AbstractShip;
import ensta.util.ColorUtil;

public class ShipState implements Serializable{
    private AbstractShip ship;
    private boolean struck;

    public ShipState(){
        this.ship = null;
        this.struck = false;
    }
    public ShipState(AbstractShip ship){
        this.ship = ship;
        this.struck = false;
    }

    public AbstractShip getShip() { return(this.ship);}
    public String toString(){
        String str;
        if(this.struck){str=ColorUtil.colorize(this.ship.getName(), ColorUtil.Color.RED);}
        else{str=this.ship.getName();}
        return(str);
    }
    public boolean isStruck(){ return(this.struck); }
    public boolean isSunk(){return(this.ship.isSunk());} //pointless... can just use getShip().isSunk()
    public void setStrike(){    //Could be boolean to check for errors.
        if(!this.struck){
            this.struck = true;
            ship.addStrike();
        }
        else{System.out.println("You dummy! This part has been struck already!");} //Should be redundant with proper protection from where the player is allowed to strike.
    }
    
    
    
    
}
