package ensta.model;

import java.io.Serializable;
import java.util.List;

import ensta.controller.Game;
import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;
import ensta.util.Pair;

public class Player {
	/*
	 * ** Attributs
	 */
	protected String name;
	protected Board ownBoard; //Now protected, for other player classes like AutoSetupPlayer
	protected Board opponentBoard;
	private int destroyedCount;
	protected AbstractShip[] ships;
	private boolean lose;

	/*
	 * ** Constructeur
	 */
	public Player(String name, Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
		this.name = name;
		this.setBoard(ownBoard);
		this.ships = ships.toArray(new AbstractShip[0]);
		this.opponentBoard = opponentBoard;
	}

	/*
	 * ** Méthodes
	 */

	/**
	 * Read keyboard input to get ships coordinates. Place ships on given
	 * coodrinates.
	 */
	public void putShips() {
		boolean done = false;
		int i = 0;
		AbstractShip ship;
		String msg;
		Coords coords = new Coords();
		System.out.println("Cher joueur " + this.name + " , c'est à ton tour de placer tes navires."); //Comment avoir le nom du joueur? Cela devrait être un paramètre.
		ownBoard.print();
		do {
			ship = ships[i];
			msg = String.format("placer navire %d : %s(%d)", i + 1, ship.getName(), ship.getLength());
			System.out.println(msg);

			InputHelper.ShipInput res = InputHelper.readShipInput(); //Get input

			ship.setOrientation(res.orientation); //res a des variables public...
			coords.setCoords(res.x, res.y-1);

			if(ownBoard.putShip(ship, coords)/*boolean true if ship placement successful*/){
				++i;
				done = i == 5;
			}
			else{System.out.println("Invalid entry, sorry! Ship can't be placed there.");}
			ownBoard.print();
		} while (!done);
	}

	public Pair<Hit,Coords> doHit() {
		boolean done = false;
		Hit hit = null;
		Coords coords;
		//ownBoard.print();
		do {
			System.out.println("\n" + this.name + ", where do you want to hit? (Format: 'A0')");
			InputHelper.CoordInput hitInput = InputHelper.readCoordInput(); //will catch wrong inputs. But wont check you've already hit somewhere.
			coords = new Coords(hitInput.x,hitInput.y);
			if(!ownBoard.hasStruckThere(coords)){ //If you havent struck there already... 
				hit = this.opponentBoard.boardHitByOpponent(coords); //Strike there! Returns a HIT, which explains if you hit, missed, or if you sank a boat (with its type)
				ownBoard.updateDoneHits(hit, coords); //Update your own board of hits
				done = true;
			}
			else{System.out.println("Sorry! You've already sent a hit there. Try somewhere else.");}
		} while (!done);
		Pair<Hit,Coords> pair = Pair.makePair(hit,coords);
		return(pair);
	}

	public AbstractShip[] getShips() {
		return ships;
	}

	public void setShips(AbstractShip[] ships) {
		this.ships = ships;
	}

	public Board getBoard() {
		return ownBoard;
	}

	public void setBoard(Board ownBoard) {
		this.ownBoard = ownBoard;
	}

	public int getDestroyedCount() {
		return destroyedCount;
	}

	public void setDestroyedCount(int destroyedCount) {
		this.destroyedCount = destroyedCount;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
}
