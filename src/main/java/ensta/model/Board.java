package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class Board implements IBoard { //IBoard c'est l'interface

	private static final int DEFAULT_SIZE = 10;
	
	private int size;
	private String name;
	private char[][] navires;
	private boolean[][] frappes;

	private void initBoards(int size){
		navires = new char[size][size];
		frappes = new boolean[size][size];
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				navires[i][j]='.';
				frappes[i][j]=false;
			}
		}
	}

	public Board(String name, int size) {
		this.name = name;
		this.size = size;
		initBoards(size);
	}

	public Board(String name) {
		this.name = name;
		this.size = DEFAULT_SIZE;
		initBoards(DEFAULT_SIZE);
	}

	public Board() {
		this.size = DEFAULT_SIZE;
		initBoards(DEFAULT_SIZE);
	}

	public void print() { //à compléter
		
		System.out.print("Navires :");
		for(int i = 0; i<this.size; i++){
			System.out.print("  ");
		}
		System.out.print("Frappes :");
		for(int i = 0; i<this.size; i++){
			System.out.print("  ");
		}
		System.out.println("");

		char letter = 'A';
		System.out.print("   ");
		for(int i = 0; i<this.size; i++){
			System.out.print(letter++ + " ");
		}
		System.out.print("     ");
		letter = 'A';
		for(int i = 0; i<this.size; i++){
			System.out.print(letter++ + " ");
		}
		System.out.println("");

		for(int i = 0; i<this.size + 0; i++){
			if(i+1<10){System.out.print((i+1) + "  ");}
			else{System.out.print((i+1) + " ");}
			for(int j = 0; j<this.size; j++){
				System.out.print(navires[i][j] + " ");
			}
			if(i+1<10){System.out.print("  ");}
			else{System.out.print(" ");}
			System.out.print((i+1) + "  ");
			for(int j = 0; j<this.size; j++){
				if(frappes[i][j] == false){
					System.out.print(". ");
				}
				else{System.out.print("X ");}
			}
			System.out.println("");
		}
	}

	public int getSize() { //à compléter
		return(1);
	}

	public boolean putShip(AbstractShip ship, Coords coords){ //à compléter
		return(true);
	}

	public boolean hasShip(Coords coords){ //à compléter
		return(true);
	}

	public void setHit(boolean hit, Coords coords){ //à compléter
	}

	public Boolean getHit(Coords coords){ //à compléter
		return(true);
	}

	public Hit sendHit(Coords res){ //à compléter
		Hit hit = Hit.MISS;
		return(hit);
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() >= this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}
}
