package Battleships;
/*
 * Author: Michael Okarimia
 * Created: 10 November 2004 15:43:46
 * Modified: 10 November 2004 15:43:46
 * This program creates a grid that a game of 
 * Battleships can be played on
 * Improvements from code1 are:
 * 1: Destroyer ship now added
 * 2: Horizontal and vertical positioning is determined by int not char
 * 3: Grid is now Serializable
 */

import java.util.HashMap;
import java.util.Map;

import Battleships.Factories.ShipFactory;
import Battleships.Ships.AircraftCarrier;
import Battleships.Ships.Destroyer;
import Battleships.Ships.Minesweeper;
import Battleships.Ships.Ship;
import Battleships.exception.PositionExceedsBoardException;
import Battleships.exception.PositionOccupiedException;
import Enums.GridValue;
import Enums.Position;
	
	
public class Grid
{	
	private GridValue[][] board;// two dimensional array to hold the board data
	private int userRow;
	private int userColumn;
	private Map<String,Ship> ships;
//	private Ship minesweeper;
//	private Ship submarine;
//	private Ship destroyer;
//	private Ship battleship;
//	private Ship aircraftCarrier;
	private ShipFactory shipFactory;
	
	public Grid(int height, int width)
	{
		this.setHeight(height);
		this.setWidth(width);
		this.InitializeBoard();
		this.ships = new HashMap<String, Ship>();
		this.setShipFactory(new ShipFactory(this));
	}
	
	public void setShipFactory(ShipFactory shipFactory) {
		this.shipFactory = shipFactory;
	}	
	public void InitializeBoard() {
		board = new GridValue[userRow][userColumn];
		
		for (int row = 0; row < userRow; row++)
			for (int column = 0; column < userColumn; column++)
				board[row][column] = GridValue.EmptyCellValue;
	}
	public void setWidth(int width) {
		this.userColumn = width;
	}
	
	public void setHeight(int height) {
		this.userRow = height;
	}

	public int getWidth() {
		return userColumn;
	}

	public int getHeight() {
		return userRow;
	}
	
	public boolean isValidPlaceForAShip(int row, int column) {
		int index = this.getGridVal(row,column).getValue();
		return index > 1 && index < 8;
	}
	
	public boolean areShipsSunk() {
		for(Ship s : ships.values()) {
			if(!s.isSunk()) {
				return false;
			}
		}
		return true;
	}	
	
	public boolean isShipSunk(String type) {
		return ships.containsKey(type) && ships.get(type).isSunk();
	}
	
	public boolean addShip(String type, Position position) {
		try {
			Ship ship = shipFactory.createShip(type, position);
			ships.put(type, ship);
			return true;
		}
		catch (PositionOccupiedException Exception)
		{
			System.out.println(String.format("Cannot place %s %s here, position is occupied \n", 
					position.toString(), type));
		}
		
		catch (PositionExceedsBoardException Exception)
		{
			System.out.println(String.format("Cannot place %s %s here, ship will not fit on grid \n", 
					position.toString(), type));
		}
		return false;
	}
	
	public boolean areShipsPlaced() {
		return ships.size() == 5;
	}	
	
	/**
		This method is used by the ship classes to add the ships to the grid.
		Sets the value of a grid location to a specified integer. The grid location must be set to (zero) 0.
		@param i the row index
		@param j the column index
		@param value the value of the square 
	*/
	public void set(int i, int j, GridValue value)
	{
		board[i][j] = value;
	}
	
	
	/**
		Returns the value of the given grid index
		@param i the row index
		@param j the column index
	*/
	public GridValue getGridVal(int i, int j)
	{
		return board[i][j];
	}
	
	/**
		Fires a shot on the grid
	*/
	public boolean shot(int i, int j)
	{
		GridValue sqr = this.getGridVal(i,j);
		
		if(sqr == GridValue.EmptyCellValue || sqr == GridValue.MissedShot) {
			this.set(i, j, GridValue.MissedShot);
			return false;
		}
		
		if(sqr == GridValue.AircraftcarrierIntact) {
			ships.get(AircraftCarrier.class.getName()).scoreHit();
			this.set(i, j, GridValue.AircraftcarrierShot);
		} else if(sqr == GridValue.MinesweeperIntact) {
			ships.get(Minesweeper.class.getName()).scoreHit();
			this.set(i, j, GridValue.MinesweeperShot);
		} else if(sqr == GridValue.DestroyerIntact) {
			ships.get(Destroyer.class.getName()).scoreHit();
			
		}
		return true;
		
//			case 2: minesweeper.scoreHit();
//					this.set(i,j, (sqr - 8)); 
//					hit= true;
//					break;
//			case 3: destroyer.scoreHit(); 
//					this.set(i,j,(sqr - 8)); 
//					hit= true;
//					break;		
//			case 4: battleship.scoreHit(); 
//					this.set(i,j,(sqr - 8)); 
//					hit= true;
//					break;
//			case 5: aircraftCarrier.scoreHit(); 
//					this.set(i,j,(sqr - 8)); 
//					hit= true;
//					break;
//			case 7: submarine.scoreHit(); 
//					this.set(i,j,(sqr - 8)); 
//					hit= true;
//					break;
//			default: 
//					break; 
//		}
//		return hit;
	}

	public String toString()
	{
		String r = "";
		for (int i = 0; i < userRow; i++) //change these to ROWS to use the default
		{
			
			r = r + "|";
			for (int j = 0; j < userColumn; j++)//change this to CoLumns for default
				r = r + board[i][j];
			r= r + "|\n";
		}
		return r;
	}
}