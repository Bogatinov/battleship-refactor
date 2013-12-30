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

public class ShipFactory {
	public static Ship createShip(String shipType, Grid g, int i, int j, int s) {
		boolean isHorizontal = (s == 0);
		Ship ship = null;
		try {
			switch(shipType) {
				case "AircraftCarrier": 
					ship = new AircraftCarrier(g, i, j, isHorizontal);
					break;
				case "Battleship": 
					ship = new Battleship(g,i,j,isHorizontal);
					break;
				case "Destroyer": 
					ship = new Destroyer(g, i, j, isHorizontal);
					break;
				case "Minesweeper":
					ship = new Minesweeper(g, i, j, isHorizontal);
					break;
				case "Submarine":
					ship = new Submarine(g, i, j, isHorizontal);
					break;
			}
		}
		catch (PositionOccupiedException Exception)
		{
			System.out.println(String.format("Cannot place %s %s here, position is occupied \n", 
					(isHorizontal? "horizontal" : "vertical"), shipType));
		}
		
		catch (PositionExceedsBoardException Exception)
		{
			System.out.println(String.format("Cannot place %s %s here, ship will not fit on grid \n", 
					(isHorizontal? "horizontal" : "vertical"), shipType));
		}
		return ship;
	}
	
	
	
}
