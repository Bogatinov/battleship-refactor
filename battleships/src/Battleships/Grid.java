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
	private Minesweeper minesweeper;
	private Submarine submarine;
	private Destroyer destroyer;
	private Battleship battleship;
	private AircraftCarrier aircraftCarrier;
	
	
		/**
		Constructs a two dimensional array which represent the game board. 
		All elements in the two dimensional array on the grid are set to (zero) 0, which represents an empty grid
		The board's dimensions are determined by the parameters i and j
		
		@param height the number of rows of the grid
		@param width the number of columns of the grid
	*/	
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
	/**
		Returns the number of columns in the grid
	*/
	public int getWidth()
	{
		return userColumn;
	}
	
	/**
		Returns the number of rows in the grid
	*/
	
	public int getHeight()
	{
		return userRow;
	}
	
	public boolean isValidPlaceForAShip(int row, int column)
	{
		int index = this.getGridVal(row,column);

		if (index > 1 && index < 8) 
			return true;
		
		return false;
		
	}
	
	public boolean areShipsSunk()
	{
		if((minesweeper.isSunk()&& submarine.isSunk()&& destroyer.isSunk()&& battleship.isSunk()&& aircraftCarrier.isSunk() ))
			return true;		
		return false;
	}		
	
	public boolean isMineSunk()
	{
		return minesweeper.isSunk();		
	}
	
	public boolean isSubSunk()
	{
		return submarine.isSunk();
	}
	
	public boolean isDestSunk()
	{
		return destroyer.isSunk();
	}
	
	public boolean isBattleSunk()
	{
		return battleship.isSunk();
	}
	
	public boolean isAirSunk()
	{		
		return aircraftCarrier.isSunk();
	}
	
	public boolean isMinePlaced()
	{
		return minesweeper != null;
	}
	
	public boolean addMine(int i, int j, int s)
	{
		boolean isHorizontal = (s == 0);
		
		try
		{
			minesweeper = new Minesweeper(this, i, j, isHorizontal);
		}
		
		catch (PositionOccupiedException Exception)
		{
			System.out.println(String.format("Cannot place %s Minesweeper here, position is occupied \n", (isHorizontal? "horizontal" : "vertical")));
		}
		
		catch (PositionExceedsBoardException Exception)
		{
			System.out.println(String.format("Cannot place %s Minesweeper here, ship will not fit on grid \n", (isHorizontal? "horizontal" : "vertical")));
		}

		return isMinePlaced();
	}
	
	
	
	/**
		Checks if the Sub has been placed
	*/
	
	public boolean isSubPlaced()
	{
		return submarine != null;
	}
	
	/**
	Adds an Air object to the grid
*/

public boolean addAir(int i, int j, int s)
{
	boolean isHorizontal = (s == 0);
	
	try
	{
		aircraftCarrier = new AircraftCarrier(this, i, j, isHorizontal);
	}
	
	catch (PositionOccupiedException Exception)
	{
		System.out.println(String.format("Cannot place %s Aircraft Carrier here, position is occupied \n", (isHorizontal? "horizontal" : "vertical")));
	}
	
	catch (PositionExceedsBoardException Exception)
	{
		System.out.println(String.format("Cannot place %s Aircraft Carrier here, ship will not fit on grid \n", (isHorizontal? "horizontal" : "vertical")));
	}
	

	 
	 return isAirPlaced();
		
}

	
	
	/**
		Adds a Submarine object to the grid
	*/
	
	public boolean addSub(int i, int j, int s)
	{
		boolean isHorizontal = (s == 0);
		
		try{
		
			Submarine sub = new Submarine(this, i, j, isHorizontal);
			submarine = sub;
		}
		
		
		catch (PositionOccupiedException Exception)
		{
			System.out.println(String.format("Cannot place %s submarine here, position is occupied \n", (isHorizontal? "horizontal" : "vertical")));
		}
		
		catch (PositionExceedsBoardException Exception)
		{
			System.out.println(String.format("Cannot place %s submarine here, ship will not fit on grid \n", (isHorizontal? "horizontal" : "vertical")));
		}


		 return isSubPlaced();
	}
	

	/**
		Checks if the Destroyer has been placed
	*/
	
	public boolean isDestPlaced()
	{
		return destroyer != null;
	}

	/**
		Adds a Destroyer object to the grid
	*/
	
	public boolean addDest(int i, int j, int s)
	{
		boolean isHorizontal = (s == 0);
		
		try
		{
			 destroyer = new Destroyer(this, i, j, isHorizontal);
		}
		
		catch (PositionOccupiedException Exception)
		{
			System.out.println(String.format("Cannot place %s destroyer here, position is occupied \n", (isHorizontal? "horizontal" : "vertical")));
		}
		
		catch (PositionExceedsBoardException Exception)
		{
			System.out.println(String.format("Cannot place %s destroyer here, ship will not fit on grid \n", (isHorizontal? "horizontal" : "vertical")));
		}
		
		return isDestPlaced();
	}
		
	
	/**
		Checks if the Battleship has been placed
	*/
		public boolean isBattlePlaced()
		{
			return battleship != null;
		}
	
	/**
		Adds a Battle object to the grid
	*/
	
	public boolean addBattle(int i, int j, int s)
	{
		boolean isHorizontal = (s == 0);
		
		try
		{
			 battleship = new Battleship(this, i, j, isHorizontal);
		}
		
		catch (PositionOccupiedException Exception)
		{
			System.out.println(String.format("Cannot place %s battleship here, position is occupied \n", (isHorizontal? "horizontal" : "vertical")));
		}
		
		catch (PositionExceedsBoardException Exception)
		{
			System.out.println(String.format("Cannot place %s battleship here, ship will not fit on grid \n", (isHorizontal? "horizontal" : "vertical")));
		}
			return isBattlePlaced();
	}
	
	
	/**
		Checks if the aircraftCarrier has been placed
	*/
		public boolean isAirPlaced()
	{
		return aircraftCarrier != null;
	}
	
	/**Checks if all ships have been placed*/
	public boolean areShipsPlaced()
	{
		
		if(isMinePlaced()&& isSubPlaced()&& isDestPlaced()&& isBattlePlaced()&& isAirPlaced())
			return true;
		else
			return false;
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
		if(i > userRow || j > userColumn)
			throw new IllegalArgumentException("Number is bigger that the grid size");
		if(i < 0 || j < 0 || value < 0) 
			throw new IllegalArgumentException("Number cannot be negative");
		if(board[i][j] != 0)
			throw new IllegalArgumentException("Initial Position occupied");
		if(value == 0)
			throw new IllegalArgumentException("Number cannot = 0");
		board[i][j] = value;
	}
	
	/**
		This method is used by the shot() method to update the grid.
		Sets the value of a grid location to a specified integer. The grid location must be set to (zero) 0.
		@param i the row index
		@param j the column index
		@param value the value of the square 
	*/
	public void update(int i, int j, int value)
	{
		if(i > userRow || j > userColumn)
			throw new IllegalArgumentException("Number is bigger that the grid size");
		if(i < 0 || j < 0) 
			throw new IllegalArgumentException("Number cannot be negative");
		if(value == 0)
			throw new IllegalArgumentException("Number cannot = 0");
		board[i][j] = value;
	}
	
	
	/**
		Returns the value of the given grid index
		@param i the row index
		@param j the column index
	*/
	public int getGridVal(int i, int j)
	{
		if(i < 0 || j < 0)
			throw new IllegalArgumentException("Number cannot be negative");
		if(i > userRow || j > userColumn)
			throw new IllegalArgumentException("Number is bigger that the grid size");
		return board[i][j];
	}
	
	/**
		Fires a shot on the grid
	*/
	public boolean shot(int i, int j)
	{
		int sqr = this.getGridVal(i,j);
		
		
		boolean hit = false;
		
		
		
		switch (sqr)
		{
		case 0: hit= false; this.update(i,j,1); break;
		case 1: hit= false; break;
			
		case 2: minesweeper.scoreHit();
					this.update(i,j, (sqr - 8)); 
					hit= true;
		break;
		
			
		case 3: submarine.scoreHit(); 
					this.update(i,j,(sqr - 8)); 
					hit= true;
		break;		
		
		case 4: battleship.scoreHit(); 
					this.update(i,j,(sqr - 8)); 
					hit= true;
			break;
		
		
		case 5: aircraftCarrier.scoreHit(); 
					this.update(i,j,(sqr - 8)); 
					hit= true;
		break;
		
		
		
		case 7: destroyer.scoreHit(); 
					this.update(i,j,(sqr - 8)); 
					hit= true;
		break;
		
		default: break; 
		}
			
		return hit;
	
	
	}
	
	/**
		Creates a string representation of the game board like so:
		|000|
		|050|
		|000|
		
		@return the string representation
	*/
	
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
	
	/**
		Returns a string output of the status of the ships on the grid, whever they are
		sunk or not.
	*/
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
	
	
	/**
		Returns the a string returning the value of each ship's IsPlaced flag. If a ship is placed this flag will 
		change to true and the this method will return a string confirming this. 
	*/
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
