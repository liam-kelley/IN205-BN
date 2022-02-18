package ensta.model;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;

public class AutoSetupPlayer extends Player{
    public AutoSetupPlayer(String name, Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
		super(name, ownBoard,opponentBoard,ships);
	}

    public void putShips() {    //écraser le putShips de Player.
		boolean done = false;
		int i = 0;
		AbstractShip ship;
		Coords coords = new Coords();
		Random ran = new Random();
		int boardSize = ownBoard.getSize();
		System.out.println("Cher joueur " + this.name + " , AutoSetup en cours.");
		do {
			ship = ships[i];
            ship.setOrientation(Orientation.EAST);
            coords.setCoords(ran.nextInt(boardSize), ran.nextInt(boardSize));
			if(ownBoard.putShip(ship, coords)/*boolean true if ship placement successful*/){
				++i;
				done = i == 5;
			}
		} while (!done);
		ownBoard.print(); //Only prints once done.
	}
}
