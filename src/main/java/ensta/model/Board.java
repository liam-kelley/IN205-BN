package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.util.ColorUtil;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	
	private int size;
	private String name;
	private ShipState[][] navires;
	private Boolean[][] frappes; //Boolean has 3 values...

	private void initBoards(){
		int sze = this.size;
		navires = new ShipState[sze][sze];
		frappes = new Boolean[sze][sze];
		for (int i = 0; i<sze; i++){
			for (int j = 0; j<sze; j++){
				navires[i][j]= new ShipState();
				frappes[i][j]=null;
			}
		}
	}

	public Board(String name, int size) {
		this.name = name;
		this.size = size;
		initBoards();
	}

	public Board(String name) {
		this.name = name;
		this.size = DEFAULT_SIZE;
		initBoards();
	}

	public Board() {
		this.size = DEFAULT_SIZE;
		initBoards();
	}

	public int getSize() { return(this.size);}
	public boolean hasShip(Coords coords){ return(navires[coords.getX()][coords.getY()].getShip() != null); }
	public boolean hasStruckThere(Coords coords){ return(frappes[coords.getX()][coords.getY()] != null); }

	public void print() {
		
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
				if(navires[i][j].getShip()!=null){System.out.print(navires[i][j].toString().charAt(0) + " ");}
				else{System.out.print(". ");}
			}
			if(j+1<10){System.out.print("  ");}
			else{System.out.print(" ");}
			System.out.print((j+1) + "  ");
			for(int i = 0; i<this.size; i++){
				if(frappes[i][j] == null){System.out.print(". ");}
				else if (frappes[i][j] == false) {System.out.print("X ");}
				else if (frappes[i][j] == true) {System.out.print(ColorUtil.colorize("X ", ColorUtil.Color.RED));}
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
				navires[iCoords.getX()][iCoords.getY()]= new ShipState(ship);   //.getName().charAt(0);
				iCoords.setX(iCoords.getX() + dx);
				iCoords.setY(iCoords.getY() + dy);
			}
			return(true);
		}
		else{return(false);}
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

	public Hit boardHitByOpponent(Coords coords){
		Hit hit;
		int x = coords.getX();
		int y = coords.getY();
		AbstractShip ship = navires[x][y].getShip(); //can be null

		if(ship ==null){hit = Hit.MISS;} //If they miss, you don't update your board.
		else{ 							//Note: NOT CHECKING to see if this part of the board has been hit already. Good thing this is only called when player hasn't already struck there.
			hit = Hit.STRIKE;			//If they hit, you update your board.
			navires[x][y].setStrike();
			if(ship.isSunk()){			//And if after they hit, that boat sank
				hit = Hit.fromInt(ship.getLength()); //Then you can tell them what the sunken boat was.
			}
		}
		return(hit);
	}

	public void updateDoneHits(Hit hit, Coords coords){
		int x = coords.getX();
		int y = coords.getY();
		if(hit.getValue()==-1){frappes[x][y]=false;}	//I guess i could've used .toString() here... but getValue() is more convenient.
		else{frappes[x][y]=true;}
	}

}
