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

	public int getSize() { return(this.size);}

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

		for(int j = 0; j<this.size + 0; j++){
			if(j+1<10){System.out.print((j+1) + "  ");}
			else{System.out.print((j+1) + " ");}
			for(int i = 0; i<this.size; i++){
				System.out.print(navires[i][j] + " ");
			}
			if(j+1<10){System.out.print("  ");}
			else{System.out.print(" ");}
			System.out.print((j+1) + "  ");
			for(int i = 0; i<this.size; i++){
				if(frappes[i][j] == false){
					System.out.print(". ");
				}
				else{System.out.print("X ");}
			}
			System.out.println("");
		}
	}


	public boolean putShip(AbstractShip ship, Coords coords){
		if(canPutShip(ship, coords)){
			Orientation o = ship.getOrientation();
			int dx = 0, dy = 0;
			if (o == Orientation.EAST) { dx = 1;}
			else if (o == Orientation.SOUTH){ dy = 1; }
			else if (o == Orientation.NORTH) { dy = -1;}
			else if (o == Orientation.WEST) { dx = -1; }

			Coords iCoords = new Coords(coords);
			for (int i = 0; i < ship.getLength(); ++i) {
				navires[iCoords.getX()][iCoords.getY()]=ship.getName().charAt(0);
				iCoords.setX(iCoords.getX() + dx);
				iCoords.setY(iCoords.getY() + dy);
			}
			return(true);
		}
		else{return(false);}
	}

	public boolean hasShip(Coords coords){ return(navires[coords.getX()][coords.getY()] != '.'); }

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
		//Est-ce que le bateau sort du plateau?
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
		//Est-ce qu'il y a un bateau qui gène?
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
