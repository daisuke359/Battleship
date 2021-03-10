package model;

import java.util.ArrayList;

/**
 * The class is to have business logics for BattleShip Application
 * @author Group 6
 * 
 */
public class BattleShipModel {
	public int shipSunk; //Number of ships already sunk
	public int numShips; //Number of ships
	public int boardSize; //Size of the board
	private int shipLength; //Length of the ship
	private Ship[] ships; //Array of ship object

	/**
	 * Default constructor
	 */
	public BattleShipModel() {
		boardSize = 7;
		numShips = 3;
		shipLength = 3;
		shipSunk = 0;
		ships = new Ship[]{new Ship(), new Ship(), new Ship()};
	}

	/**
	 * It takes the guess and compares with the location in the ship object.<br>
	 * Check if the guess is wrong. If correct, check if it is already sunk, or the user sank of the battleships. 
	 * @param guess
	 * @return
	 */
	public String fire(String guess) {
		String text = "";
		System.out.println("guess: " + guess);
		for (int i = 0; i < this.numShips; i++) {
			Ship ship = this.ships[i];
			int index = ship.getLocation().indexOf(guess);

			if (index < 0) {
				continue;
			} else if (ship.getHit().get(index) == "hit") {
				text = "Oops, you already hit that location!";
				return text;
			} else {
				ship.setHit("hit", index);
				text = "HIT!";

				if (this.isSunk(ship)) {
					text = "You sank my battleship!";
					this.shipSunk++;
				}
				return text;
			}

		}

		text = "You missed";
		return text;
	}

	/**
	 * A method to check all the battleship is sunk
	 * @param ship
	 * @return boolean
	 */
	public boolean isSunk(Ship ship) {
		for(int i=0; i<this.shipLength; i++) {
			if(ship.getHit().get(i) != "hit") {
				return false;
			}
		}
		return true;
	}

	/**
	 * Initialize the model class
	 */
	public void clearData() {
		//Initialise
		this.shipSunk = 0;
		for(int i=0; i < ships.length; i++) {
			for(int j=0; j< ships[i].getHit().size(); j++) {
				ships[i].setHit("", j);
			}
		}
		
	}

	/**
	 * Create a set of ships and push them into ship classes
	 * that are created if the model class is initiated
	 */
	public void generateShipLocations() {
		ArrayList<String> locations = new ArrayList<String>();

		
		
		for(int i = 0; i < this.numShips; i++) {
			do {
				locations = this.generateShip();
			} while (this.collision(locations));
			this.ships[i].setLocation(locations);
			System.out.println("location: " + ships[i].getLocation().get(0));

		}

	}

	/**
	 * Check if there is any collision when creating battleships.
	 * @param locations
	 * @return boolean
	 */
	private boolean collision(ArrayList<String> locations) {
		for(int i = 0; i< this.numShips; i++) {
			Ship ship = this.ships[i];
			for(int j=0;j<locations.size();j++) {
				if(ship.getLocation().indexOf(locations.get(j))>= 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Randomly generate a ship either in vertical or horizontal.
	 * @return newShipLocations
	 */
	public ArrayList<String> generateShip() {
		double direction = Math.floor(Math.random()*2);
		int row, col;

		if(direction == 1) {
			row = (int)Math.round(Math.floor(Math.random() * this.boardSize));
			col = (int)Math.round(Math.floor(Math.random() * (this.boardSize - this.shipLength + 1)));
		} else {
			row = (int)Math.round(Math.floor(Math.random() * (this.boardSize - this.shipLength + 1)));
			col = (int)Math.round(Math.floor(Math.random() * this.boardSize));
		}

		ArrayList<String> newShipLocations = new ArrayList<String>();

		for(int i=0; i<this.shipLength; i++) {
			if(direction==1) {
				newShipLocations.add(row + "" + (col + i));
			} else {
				newShipLocations.add((row + i) + "" + col);
			}
		}

		return newShipLocations;

	}
}


