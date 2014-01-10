package Battleships;
import java.util.Random;

import Battleships.Behaviors.ShooterBehavior;
import Battleships.Factories.ShipFactory;
import Battleships.Factories.ShotFactory;
import Battleships.Ships.AircraftCarrier;
import Battleships.Ships.Battleship;
import Battleships.Ships.Destroyer;
import Battleships.Ships.Minesweeper;
import Battleships.Ships.Submarine;
import Enums.Orientation;
import Enums.Position;
public class Agent
{
	private InfluenceMap m = null;
	private Grid grid = null;
	private int i=-1;
	private int j=-1;
	private ShooterBehavior shooterBehavior;
	
	public Agent()
	{
		m = new InfluenceMap();		
		grid = new Grid(10,10);
	}
	
	public int getI()
	{
		return i;
	}
	
	public int getJ()
	{
		return j;
	}	
	
	public InfluenceMap setSunk(int i, int j)
	{
		m.sunk(i,j);
		return m;
	}

	public void nextShot(InfluenceMap m1, Grid Attackgrid)
	{
		m = m1;
		grid = Attackgrid;
		shooterBehavior = ShotFactory.getShooterBehavior(m.getNumberOfHotspots());
		shooterBehavior.nextShot(m, grid);
		i = shooterBehavior.i;
		j = shooterBehavior.j;
	}
	
	public Grid placeShips()
	{
		while(!grid.areShipsPlaced())
		{
			Random random = new Random();
			int x = random.nextInt(10);
			int y = random.nextInt(10);
			boolean orientation = random.nextBoolean();
			Position position = new Position(x, y, orientation == true ? Orientation.Horizontal : Orientation.Vertical);
			if(!grid.isShipPlaced(AircraftCarrier.class.getSimpleName())) {
				grid.addShip(AircraftCarrier.class.getSimpleName(), position);
			}
			else if(!grid.isShipPlaced(Battleship.class.getSimpleName())) {
				grid.addShip(Battleship.class.getSimpleName(), position);
			} 
			else if(!grid.isShipPlaced(Destroyer.class.getSimpleName())) {
				grid.addShip(Destroyer.class.getSimpleName(), position);
			}
			else if(!grid.isShipPlaced(Submarine.class.getSimpleName())) {
				grid.addShip(Submarine.class.getSimpleName(), position);
			}
			else if(!grid.isShipPlaced(Minesweeper.class.getName())) {
				grid.addShip(Minesweeper.class.getSimpleName(), position);
			}
		}
		
		System.out.println("agent grid");
		System.out.println(grid.toString());
		
		return grid;
	}
}
