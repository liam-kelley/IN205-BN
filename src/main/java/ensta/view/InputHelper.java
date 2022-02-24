package ensta.view;

import java.util.Arrays;
import java.util.Scanner;

public final class InputHelper {

	private static Scanner scanner = new Scanner(System.in);

	/*
	 * ** Constructeur
	 */
	private InputHelper() {
	}

	/*
	 * ** Classe ShipInput, interne à InputHelper
	 */
	public static class ShipInput {
		public String orientation;
		public int x;
		public int y;
	}

	/*
	 * ** Classe CoordInput, interne à InputHelper
	 */
	public static class CoordInput {
		public int x;
		public int y;
	}

	public static class PlayersInput {
		public String P1name;
		public String P2name;
		public String P1type;
		public String P2type;
	}

	/*
	 * ** Méthodes de la classe InputHelper
	 */
	public static PlayersInput readPlayers() {
		PlayersInput ply = new PlayersInput();
		String[] validPlayerTypes = { "ai", "autosetup", "default"};
		int donecnt = 0;
		do {
			donecnt = 0;
			try {
				String[] in = scanner.nextLine().split(" ");
				if (in.length == 4) {
					if (Arrays.asList(validPlayerTypes).contains(in[0])) {
						ply.P1type = in[0];
						donecnt++;
					}
					ply.P1name = in[1];
					if (Arrays.asList(validPlayerTypes).contains(in[2])) {
						ply.P2type = in[2];
						donecnt++;
					}
					ply.P2name = in[3];
				}
			} catch (Exception e) {}
			if (donecnt<2) {
				System.err.println("Format incorrect! Entrez les jouers sous forme 'ai Kevin autosetup Pierre'. validPlayerTypes = { 'ai', 'autosetup', 'default'} ");
			}
		} while (donecnt<2 && scanner.hasNextLine());

		return ply;
	}

	public static Boolean readYN() {
		Boolean yn = null;
		boolean done = false;
		do {
			try {
				String scan = scanner.nextLine().toLowerCase();
				if (scan.equals("y")){yn=true; done = true;}
				else if(scan.equals("n")){yn=false; done = true;}
				else{System.err.println("Format incorrect! Entrez sous forme 'Y'");}
			} catch (Exception e) {
				System.err.println("Format incorrect! Entrez sous forme 'Y'");
			}
		} while (!done && scanner.hasNextLine());

		return yn;
	}

	public static ShipInput readShipInput(int boardSize) {
		ShipInput res = new ShipInput();
		String[] validOrientations = { "north", "south", "east", "west" }; // North, South, East, West
		boolean done = false;
		do {
			try {
				String[] in = scanner.nextLine().toLowerCase().split(" ");
				if (in.length == 2) {
					String coord = in[0];
					if (Arrays.asList(validOrientations).contains(in[1])) {
						res.orientation = in[1];
						res.x = coord.charAt(0) - 'a';
						res.y = Integer.parseInt(coord.substring(1, coord.length()));
						done = true;
					}
				}
			} catch (Exception e) {}
			if (!done) {
				System.err.println("Format incorrect! Entrez la position sous forme 'A1 north'");
			}
			if(res.x<=-1 | res.x>=boardSize | res.y<=-1 | res.y>=boardSize){
				System.err.println("Position incorrecte! Restez dans votre board. Entrez la position sous forme 'A1'");
				done=false;
			}
		} while (!done && scanner.hasNextLine());

		return res;
	}

	public static CoordInput readCoordInput(int boardSize) {
		CoordInput res = new CoordInput();
		boolean done = false;
		do {
			try {
				String coord = scanner.nextLine().toLowerCase();
				res.x = coord.charAt(0) - 'a';
				res.y = Integer.parseInt(coord.substring(1, coord.length())) - 1;
				done = true;
			} catch (Exception e) {
				System.err.println("Format incorrect! Entrez la position sous forme 'A1'");
			}
			if(res.x<=-1 | res.x>=boardSize | res.y<=-1 | res.y>=boardSize){
				System.err.println("Position incorrecte! Restez dans la board de l'adversaire. Entrez la position sous forme 'A1'");
				done=false;
			}
		} while (!done && scanner.hasNextLine());

		return res;
	}
}
