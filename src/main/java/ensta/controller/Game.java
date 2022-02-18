package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.model.AutoSetupPlayer;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;
import ensta.util.Pair;
import ensta.view.InputHelper;

public class Game {

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.dat");

	/*
	 * *** Attributs
	 */
	private Player player1;
	private Player player2;
	private Scanner sin;

	/*
	 * *** Constructeurs
	 */
	public Game() {
	}

	public Game init() {
		if (!loadSave()) {
			Board boardP1 = new Board("BoardP1");
			Board boardP2 = new Board("BoardP2");

			this.player1 = new AutoSetupPlayer("Corentin", boardP1, boardP2, createDefaultShips());
			this.player2 = new AutoSetupPlayer("Thomas", boardP2, boardP1, createDefaultShips());

			introMessage();
			
			this.player1.putShips();
			System.out.println("");
			this.player2.putShips();
		}
		return this;
	}

	/*
	 * *** Méthodes
	 */
	private void introMessage(){
		System.out.println("\n" + "/////////////////////////////////////////////////\n////////  BATAILLE NAVALE - made by Liam ////////\n/////////////////////////////////////////////////\n");
	}

	
	public void run() {
		Board b1 = player1.getBoard();
		Board b2 = player2.getBoard();
		Pair<Hit,Coords> pair; 
		boolean gameOver;
		boolean playAgain = false;

		// main loop
		System.out.println("\n----------------PLAYER 1 DO HITS------------------\n");
		b1.print();
		do {
			pair = player1.doHit(); //Won't leave its loop until its hit is done.
			gameOver = updateScore(); //returns true if one of the players has lost.

			if(!playAgain){System.out.println("");}
			b1.print();
			System.out.println(makeHitMessage(false /*false = outgoing hit */, pair.coords, pair.hit));

			//if (!gameOver) {save();}
			playAgain = checkForPlayAgain(pair.hit);

			if (!gameOver && !playAgain) {
				System.out.println("----------------PLAYER 2 DO HITS------------------\n");
				b2.print();
				playAgain = false;
				do {
					pair = player2.doHit(); //Won't leave its loop until its hit is done.
					gameOver = updateScore();

					if(!playAgain){System.out.println("");}
					b2.print();

					System.out.println(makeHitMessage(false /* incoming hit */, pair.coords, pair.hit));

					//if (!gameOver) {save();}
					playAgain = checkForPlayAgain(pair.hit);
				} while (!gameOver && playAgain);
				if (!gameOver) {
					System.out.println("----------------PLAYER 1 DO HITS------------------\n");
					b1.print();
					playAgain = false;
				}
			}
		} while (!gameOver);

		SAVE_FILE.delete();
		System.out.println(String.format("Le joueur %d a gagné!", player1.isLose() ? 2 : 1));
		sin.close();
	}

	private boolean checkForPlayAgain(Hit hit){ return(hit != Hit.MISS); }

	private void save() {
//		try {
//			// TODO bonus 2 : uncomment
//			// if (!SAVE_FILE.exists()) {
//			// SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
//			// }
//
//			// TODO bonus 2 : serialize players
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private boolean loadSave() {
//		if (SAVE_FILE.exists()) {
//			try {
//				// TODO bonus 2 : deserialize players
//
//				return true;
//			} catch (IOException | ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
		return false;
	}

	private boolean updateScore() { //returns true if a player has lost.
		for (Player player : new Player[] { player1, player2 }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) { //Counts destroyed ships for each player
				if (ship.isSunk()) { destroyed++; }
			}
			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {return true;}
		}
		return false;
	}

	private String makeHitMessage(boolean incoming, Coords coords, Hit hit) {
		String msg;
		ColorUtil.Color color = ColorUtil.Color.RESET;
		switch (hit) {
		case MISS:
			msg = hit.toString();
			break;
		case STRIKE:
			msg = hit.toString();
			color = ColorUtil.Color.RED;
			break;
		default:
			msg = hit.toString() + " coulé";
			color = ColorUtil.Color.RED;
		}
		msg = String.format("%s Frappe en %c%d : %s%s", incoming ? "<=" : "=>", ((char) ('A' + coords.getX())), (coords.getY() + 1), msg, "\n");
		return ColorUtil.colorize(msg, color);
	}

	private static List<AbstractShip> createDefaultShips() {
		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(),
				new Carrier() });
	}
}
