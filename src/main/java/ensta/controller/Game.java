package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.players.AIPlayer;
import ensta.model.players.AutoSetupPlayer;
import ensta.model.players.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;
import ensta.util.Pair;

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

	/*
	 * *** Constructeurs
	 */
	public Game() {
	}

	public Game init() {
		if (!loadSave()) {
			Board boardP1 = new Board("BoardP1");
			Board boardP2 = new Board("BoardP2");

			this.player1 = new AIPlayer("Liam", boardP1, boardP2, createDefaultShips());
			this.player2 = new AIPlayer("Sylvain", boardP2, boardP1, createDefaultShips());

			introMessage();
			
			this.player1.putShips();
			pause();
			System.out.println("");
			this.player2.putShips();
			pause();
		}
		return this;
	}

	/*
	 * *** Méthodes
	 */
	private void introMessage(){
		System.out.println("\n" + "/////////////////////////////////////////////////\n////////  BATAILLE NAVALE - made by Liam ////////\n/////////////////////////////////////////////////\n");
	}

	protected void pause(){
		try{Thread.sleep(1500);}
		catch(InterruptedException ex){Thread.currentThread().interrupt();}
	}
	
	public void run() {
		Board b1 = player1.getBoard();
		Board b2 = player2.getBoard();
		Pair<Hit,Coords> pair; 
		boolean gameOver;
		boolean playAgain = false;
		
		// main loop
		System.out.println("\n----------------PLAYER 1 DO HITS------------------\n");
		pause();
		b1.print();
		do {
			pair = player1.doHit(); //Won't leave its loop until its hit is done.
			pause();
			gameOver = updateScore(); //returns true if one of the players has lost.

			if(!playAgain){System.out.println("");}
			b1.print();
			System.out.println(makeHitMessage(false /*false = outgoing hit */, pair.coords, pair.hit));
			pause();

			if (!gameOver) {save();}
			playAgain = checkForPlayAgain(pair.hit);

			if (!gameOver && !playAgain) {
				System.out.println("----------------PLAYER 2 DO HITS------------------\n");
				pause();
				b2.print();
				playAgain = false;
				do {
					pair = player2.doHit(); //Won't leave its loop until its hit is done.
					pause();
					gameOver = updateScore();

					if(!playAgain){System.out.println("");}
					b2.print();
					System.out.println(makeHitMessage(false /* incoming hit */, pair.coords, pair.hit));
					pause();

					if (!gameOver) {save();}
					playAgain = checkForPlayAgain(pair.hit);
					
				} while (!gameOver && playAgain);
				if (!gameOver) {
					System.out.println("----------------PLAYER 1 DO HITS------------------\n");
					pause();
					b1.print();
					playAgain = false;
				}
			}
		} while (!gameOver);

		SAVE_FILE.delete();
		System.out.println(String.format("Le joueur %s a gagné!\n\nBravo a lui!!!\n", player1.isLose() ? player2.getName() : player1.getName()));
	}

	private boolean checkForPlayAgain(Hit hit){ return(hit != Hit.MISS); }

	private boolean updateScore() { //returns true if a player has lost.
		for (Player player : new Player[] { player1, player2 }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) { //Counts destroyed ships for each player
				if (ship.isSunk()) { destroyed++; }
			}
			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {return true;} //here.
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

	private void save() {
		java.io.FileOutputStream fout = null;
		java.io.ObjectOutputStream oos = null;
		try {
			if (!SAVE_FILE.exists()) {
			SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
			}
			
			Player[] players = new Player[2];
			players[0]=player1;
			players[1]=player2;

			fout = new java.io.FileOutputStream(SAVE_FILE);
			oos = new java.io.ObjectOutputStream(fout);
			oos.writeObject(players);
			if(oos != null){
				oos.close();
			} 
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	
	private boolean loadSave() {
		if (SAVE_FILE.exists()) {
			java.io.ObjectInputStream objectinputstream = null;
			Player[] players = new Player[2];
			try {
				java.io.FileInputStream streamIn = new java.io.FileInputStream("G:\\address.ser");
				objectinputstream = new java.io.ObjectInputStream(streamIn);
				Player[] readCase = (Player[]) objectinputstream.readObject(); //Should I be doing java lists instead of []?
				if(objectinputstream != null){
					objectinputstream.close();
				} 
				
				players = readCase;
				this.player1 = players[0];
				this.player2 = players[1];
				return true;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	}