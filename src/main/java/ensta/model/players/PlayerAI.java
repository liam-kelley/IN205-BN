package ensta.model.players;
import java.util.List;

import ensta.model.Board;
import ensta.model.ship.AbstractShip;

public class PlayerAI extends Player {
    // /* **
    //  * Attribut
    //  */
    // private BattleShipsAI ai;

    // /* **
    //  * Constructeur
    //  */
    public PlayerAI(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super("Computer", ownBoard, opponentBoard, ships);
        //ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    // // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.
}