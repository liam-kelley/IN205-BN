package ensta.model;

import java.io.Serializable;
import java.util.Random;

public class Coords implements Serializable{

    private int X;
    private int Y;

    public Coords(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    public Coords(Coords otherCoords){
        this.X = otherCoords.X;
        this.Y = otherCoords.Y;
    }

    public Coords(){
        this.X = 0;
        this.Y = 0;
    }

    public void setX(int X){
        this.X = X;
    }
    public void setY(int Y){
        this.Y = Y;
    }
    public void setCoords(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
    public void setCoords(Object truc){ //utilisé pour régler un problem avec un truc dans BattleShipsAI.java
        if(truc == null){
            this.X = 0;
            this.Y = 0;
        }
    }
    public static Coords randomCoords(int boardSize){
        Random ran =  new Random();
        return(new Coords(ran.nextInt(boardSize),ran.nextInt(boardSize)));
    }
    public int getX(){
        return(this.X);
    }
    public int getY(){
        return(this.Y);
    }

    public boolean isInBoard(int boardSize){
        return(this.X < boardSize && this.Y < boardSize);
    }
}
