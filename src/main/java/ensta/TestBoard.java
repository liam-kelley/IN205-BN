package ensta;

import ensta.model.Coords;
import ensta.model.Board;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.Orientation;

public class TestBoard {
    public static void main(String args[]) {
        Board board = new Board("TestBoard", 10);
        board.print();

        BattleShip bs = new BattleShip();
        Carrier ca = new Carrier(Orientation.WEST);
        Destroyer de = new Destroyer(Orientation.SOUTH);
        Submarine su = new Submarine();
        board.putShip(bs, new Coords(1,1)); //Coordinates are +1 ... Could be fixed!
        board.putShip(ca, new Coords(4,3));
        board.putShip(de, new Coords(4,4));
        board.putShip(su, new Coords(2,7));
        board.print();
    }
}
