package ensta.ai;

import java.io.Serializable;

import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.IBoard;
import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class BattleShipsAI implements Serializable {

	/*
	 * ** Attributs
	 */

	/**
	 * My board. My ships have to be put on this one.
	 */
	private final IBoard board;

	/**
	 * My opponent's board. My hits go on this one to strike his ships.
	 */
	private final IBoard opponent;

	/**
	 * Coordss of last known strike. Would be a good idea to target next hits around
	 * this point.
	 */
	private Coords lastStrike;

	/**
	 * If last known strike lead me to think the underlying ship has vertical
	 * placement.
	 */
	private Boolean lastVertical;

	/*
	 * ** Constructeur
	 */

	/**
	 *
	 * @param ownBoard       board where ships will be put.
	 * @param opponentBoard Opponent's board, where hits will be sent.
	 */
	public BattleShipsAI(IBoard ownBoard, IBoard opponentBoard) {
		this.board = ownBoard;
		this.opponent = opponentBoard;
	}

	/*
	 * ** Méthodes publiques
	 */

	//TODO do autoPutShips here because its an ai....? Importing from AutoSetupPlayer is more direct and less annoying with the ai

	/**
	 * ai will choose proper strike coordinates.
	 * This function won't be updating any boards. That's the AIPlayers job.
	 * This function won't update the ai on its lastVertical or its lastStrike like how sendHit() would've done.
	 * 
	 * @return the valid strike coords
	 */
	public Coords chooseValidStrikeCoords() {
		Coords validStrikeCoords = null;

		// already found strike & orientation?
		if (lastVertical != null) {
			if (lastVertical) {validStrikeCoords = pickVCoords();}
			else {validStrikeCoords = pickHCoords();}

			if (validStrikeCoords == null) {
				// no suitable coords found... forget last strike.
				lastStrike = null;
				lastVertical = null;
			}
		}
		else if (lastStrike != null) {
			// if already found a strike, without orientation
			// try to guess orientation
			validStrikeCoords = pickVCoords();
			if (validStrikeCoords == null) {
				validStrikeCoords = pickHCoords();
			}
			if (validStrikeCoords == null) {
				// no suitable coords found... forget last strike.
				lastStrike = null;
			}
		}
		if (lastStrike == null) {
			validStrikeCoords = pickRandomCoords(); //These will be valid.
		}
		return validStrikeCoords;
	}

	/**
	 * This function updates the ai on its lastVertical or its lastStrike like how sendHit() would've done.
	 * 
	 * @param hit last done hit with its
	 * @param coords coords
	 */
	public void updateLasts(Hit hit, Coords coords){
		if (hit != Hit.MISS) {
			if (lastStrike != null) {
				lastVertical = guessOrientation(lastStrike, coords); //true if last strike was below
			}
			lastStrike = coords;
		}
	}

	/*
	 * *** Méthodes privées
	 */

	/**
	 * @param lastStrikee
	 * @param currentStrike
	 * @preturn true if last hit was vertically aligned
	 */
	private boolean guessOrientation(Coords lastStrikee, Coords currentStrike) { 
		return lastStrikee.getX() == currentStrike.getX(); //last strike has same x position as current X BUGFIX
	}

	private boolean isUndiscovered(Coords coords) {
		return (coords.isInBoard(board.getSize()) && board.getFrappe(coords) == null ) ;
	}

	private Coords pickRandomCoords() {
		Coords coords;
		do {
			coords = Coords.randomCoords(board.getSize());
		} while (!isUndiscovered(coords));

		return coords;
	}

	/**
	 * pick a coords verically around last known strike. Looks above then below, then further above, then further below (is this true?)
	 * Can return null if no suitable candidates are found
	 * 
	 * @return suitable coords, or null if none is suitable
	 */
	private Coords pickVCoords() {
		int x = lastStrike.getX();
		int y = lastStrike.getY();

		for (int iy : new int[] { y - 1, y + 1 }) {
			Coords coords = new Coords(Math.min(Math.max(0,x), board.getSize()-1)
									, Math.min(Math.max(0,iy), board.getSize()-1));
			if (isUndiscovered(coords)) {
				return coords;
			}
		}
		return null;
	}

	/**
	 * pick a coords horizontally around last known strike. Looks left then right, then further left, then further right (is this true?)
	 * 
	 * @return suitable coords, or null if none is suitable
	 */
	private Coords pickHCoords() {
		int x = lastStrike.getX();
		int y = lastStrike.getY();

		for (int ix : new int[] { x - 1, x + 1 }) {
			Coords coords = new Coords( Math.min(Math.max(0,ix), board.getSize()-1)
										, Math.min(Math.max(0,y), board.getSize()-1));
			if (isUndiscovered(coords)) {
				return coords;
			}
		}
		return null;
	}
}
