package Battleships;
import java.util.Random;

import Battleships.Behaviors.ShooterBehavior;
import Battleships.Factories.ResolverFactory;
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
	private InfluenceMap map;
	private Grid grid;
	private int i=-1;
	private int j=-1;
	private ShooterBehavior shooterBehavior;

	public Agent(InfluenceMap map, Grid grid)
	{
		this.grid = grid;
		this.map = map;
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
		map.sunk(i,j);
		return map;
	}

	public void nextShot()
	{
		shooterBehavior = ShotFactory.getShooterBehavior(map.getNumberOfHotspots());
		shooterBehavior.nextShot(map, grid);
		i = shooterBehavior.i;
		j = shooterBehavior.j;
	}

	public Grid placeShips()
	{
		String currentShip = AircraftCarrier.class.getSimpleName();
		while(!grid.areShipsPlaced())
		{
			Random random = new Random();
			int x = random.nextInt(10);
			int y = random.nextInt(10);
			boolean orientation = random.nextBoolean();
			Position position = new Position(x, y, 
					orientation == true ? Orientation.Horizontal : Orientation.Vertical);
			grid.addShip(currentShip, position);
			if(grid.isShipPlaced(currentShip)) {
				currentShip = ResolverFactory.nextShip(currentShip);
			}
		}

		System.out.println("agent grid");
		System.out.println(grid.toString());

		return grid;
	}
}
