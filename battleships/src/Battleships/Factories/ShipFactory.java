package Battleships.Factories;

import Battleships.Grid;
import Battleships.Ships.AircraftCarrier;
import Battleships.Ships.Battleship;
import Battleships.Ships.Destroyer;
import Battleships.Ships.Minesweeper;
import Battleships.Ships.Ship;
import Battleships.Ships.Submarine;
import Battleships.exception.PositionExceedsBoardException;
import Battleships.exception.PositionOccupiedException;
import Enums.Position;

public class ShipFactory {
	private Grid grid;
	public ShipFactory(Grid grid) {
		this.grid = grid;
	}
	public Ship createShip(String type, Position position) throws PositionOccupiedException, PositionExceedsBoardException {
		Ship ship = null;
		
			if(type.equals(AircraftCarrier.class.getName())) {
				ship = new AircraftCarrier(grid, position);
			} 
			
			else if(type.equals(Battleship.class.getName())) {
				ship = new Battleship(grid, position);
			}
			
			else if(type.equals(Destroyer.class.getName())) {
				ship = new Destroyer(grid, position);
			}
			
			else if(type.equals(Minesweeper.class.getName())) {
				ship = new Minesweeper(grid, position);
			}
			
			else if(type.equals(Submarine.class.getName())) {
				ship = new Submarine(grid, position);
			}

		return ship;
	}
}
