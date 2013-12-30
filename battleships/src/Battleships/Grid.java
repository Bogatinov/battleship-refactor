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

import Battleships.Factories.ShipFactory;
import Battleships.Ships.AircraftCarrier;
import Battleships.Ships.Battleship;
import Battleships.Ships.Destroyer;
import Battleships.Ships.Minesweeper;
import Battleships.Ships.Ship;
import Battleships.Ships.Submarine;
import Battleships.exception.PositionExceedsBoardException;
import Battleships.exception.PositionOccupiedException;
	
	
public class Grid
{	
	private int[][] board;// two dimensional array to hold the board data
	
	private int userRow;
	private int userColumn;
	private Ship minesweeper;
	private Ship submarine;
	private Ship destroyer;
	private Ship battleship;
	private Ship aircraftCarrier;
	
	public Grid(int height, int width)
	{
		this.setHeight(height);
		this.setWidth(width);
		this.InitializeBoard();
	}
	
	public void InitializeBoard() {
		board = new int[userRow][userColumn];
		
		for (int row = 0; row < userRow; row++)
			for (int column = 0; column < userColumn; column++)
				board[row][column] = 0;
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
		int index = this.getGridVal(row,column);
		return index > 1 && index < 8;
	}
	
	public boolean areShipsSunk() {
		return minesweeper.isSunk() && 
				submarine.isSunk() && 
				destroyer.isSunk() && 
				battleship.isSunk() && 
				aircraftCarrier.isSunk();
	}		
	
	public boolean isMineSunk() {
		return minesweeper.isSunk();		
	}
	
	public boolean isSubSunk() {
		return submarine.isSunk();
	}
	
	public boolean isDestSunk() {
		return destroyer.isSunk();
	}
	
	public boolean isBattleSunk() {
		return battleship.isSunk();
	}
	
	public boolean isAirSunk() {		
		return aircraftCarrier.isSunk();
	}
	
	public boolean isMinePlaced() {
		return minesweeper != null;
	}
	
	public boolean addMine(int i, int j, int s) {
		minesweeper = ShipFactory.createShip(
				Minesweeper.class.getName(), 
				this, i, j, s);

		return isMinePlaced();
	}

	public boolean isSubPlaced() {
		return submarine != null;
	}

	public boolean addAir(int i, int j, int s) {
		aircraftCarrier = ShipFactory.createShip(
			 AircraftCarrier.class.getName(), 
			 this, i, j, s);
		return isAirPlaced();	
	}

	public boolean addSub(int i, int j, int s) {
		submarine = ShipFactory.createShip(
				Submarine.class.getName(), 
				this, i, j, s);
		return isSubPlaced();
	}
	
	public boolean isDestPlaced() {
		return destroyer != null;
	}
	
	public boolean addDest(int i, int j, int s) {
		destroyer = ShipFactory.createShip(
				Destroyer.class.getName(), 
				this, i, j, s);
		
		return isDestPlaced();
	}

	public boolean isBattlePlaced() {
		return battleship != null;
	}

	public boolean addBattle(int i, int j, int s) {
		battleship = ShipFactory.createShip(
				Battleship.class.getName(), 
				this, i, j, s);
		return isBattlePlaced();
	}

	public boolean isAirPlaced() {
		return aircraftCarrier != null;
	}
	
	public boolean areShipsPlaced() {
		return isMinePlaced() && 
			   isSubPlaced() && 
			   isDestPlaced() && 
			   isBattlePlaced() && 
			   isAirPlaced();
	}	
	
	/**
		This method is used by the ship classes to add the ships to the grid.
		Sets the value of a grid location to a specified integer. The grid location must be set to (zero) 0.
		@param i the row index
		@param j the column index
		@param value the value of the square 
	*/
	public void set(int i, int j, int value)
	{
		board[i][j] = value;
	}
	
	
	/**
		Returns the value of the given grid index
		@param i the row index
		@param j the column index
	*/
	public int getGridVal(int i, int j)
	{
		return board[i][j];
	}
	
	/**
		Fires a shot on the grid
	*/
	public boolean shot(int i, int j)
	{
		int sqr = this.getGridVal(i,j);
		
		boolean hit = false;
		
		switch (sqr) {
			case 0: hit= false; this.set(i,j,1); break;
			case 1: hit= false; break;
			case 2: minesweeper.scoreHit();
					this.set(i,j, (sqr - 8)); 
					hit= true;
					break;
			case 3: submarine.scoreHit(); 
					this.set(i,j,(sqr - 8)); 
					hit= true;
					break;		
			case 4: battleship.scoreHit(); 
					this.set(i,j,(sqr - 8)); 
					hit= true;
					break;
			case 5: aircraftCarrier.scoreHit(); 
					this.set(i,j,(sqr - 8)); 
					hit= true;
					break;
			case 7: destroyer.scoreHit(); 
					this.set(i,j,(sqr - 8)); 
					hit= true;
					break;
			default: 
					break; 
		}
		return hit;
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

	public String printIsSunk()
	{
		String MINESWEEPER =("Minesweeper is intact");
		String SUBMARINE =("Submarine is intact");
		String DESTROYER =("Destroyer is intact");
		String BATTLESHIP =("Battleship is intact");
		String AIRCRAFTCARRIER =("Aircraft Carrier is intact");
			
		if (minesweeper.isSunk())
			 MINESWEEPER =("Minesweeper is SUNK");
		
		if (submarine.isSunk())
			 SUBMARINE =("Submarine is SUNK");
		
		if (destroyer.isSunk())
			 DESTROYER =("Destroyer is SUNK");
		
		
		if (battleship.isSunk())
			 BATTLESHIP =("Battleship is SUNK");
		
		if (aircraftCarrier.isSunk())
			 AIRCRAFTCARRIER =("Aircraft Carrier is SUNK");

		
		return (MINESWEEPER + "\n" +SUBMARINE + "\n" +  DESTROYER + "\n" + BATTLESHIP+ "\n" +AIRCRAFTCARRIER); 
	}
	
	
	public String printIsPlaced()
	{
		System.out.println("The following ships are now placed ");
		String Minesweeper="Minesweeper NOT Placed";
		String Destroyer="Destroyer NOT Placed";
		String Submarine="Submarine NOT placed";
		String Battleship="Battleship NOT placed";
		String AircraftCarrier="Aircraft Carrier NOT placed";
		
		if (isMinePlaced()) 
			Minesweeper="Minesweeper has been placed";

		if (isDestPlaced()) 
			Destroyer="Destroyer has been placed";
		
		if (isSubPlaced()) 
			Submarine="Submarine has been placed";
		
		if (isBattlePlaced())
			Battleship="Battleship has been placed";
		
		if(isAirPlaced())
			AircraftCarrier="Aircraft Carrier has been placed";
		
		return Minesweeper + "\n" + Destroyer + "\n" + Submarine + "\n" + Battleship + "\n" + AircraftCarrier;
	}
}
