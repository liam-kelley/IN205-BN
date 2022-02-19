package ensta.model.players;
import java.util.List;

import ensta.model.Board;
import ensta.model.ship.AbstractShip;
import ensta.util.Pair;
import ensta.model.Hit;
import ensta.model.Coords;
import ensta.ai.BattleShipsAI;

public class AIPlayer extends AutoSetupPlayer {
    // /* **
    //  * Attribut
    //  */
    private BattleShipsAI ai;

    // /* **
    //  * Constructeur
    //  */
    public AIPlayer(String name, Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(name, ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super("Computer", ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    /**
    *   DoHit Gets player inputs
    *   Checks it is a valid position
    *   Strikes the opponentboard
    *   Updates your ownBoard
    *
    *   @return A pair of the done hit and its position for game.
    */
    @Override
    public Pair<Hit,Coords> doHit() { 
		Hit hit = null;
		Coords coords;

        System.out.println("\n" + this.name + " is looking where to play...");
        coords = ai.chooseValidStrikeCoords();

        System.out.println(this.name + " strikes at x = " + coords.getX() + " and Y = " + coords.getY() + ".");
        hit = this.opponentBoard.boardHitByOpponent(coords); //Strike the opponent's board! Returns a HIT, which explains if you hit, missed, or if you sank a boat (with its type)
        ownBoard.updateDoneHits(hit, coords); //Update your own board of hits

        ai.updateLasts(hit, coords);

		Pair<Hit,Coords> pair = Pair.makePair(hit,coords);
		return(pair);
    }
    
}
