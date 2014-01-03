package Battleships;
/*
 * Author: Michael
 * Created: 16 April 2005 12:39:10
 * Modified: 16 April 2005 12:39:10
 */
import java.util.Random;

import Battleships.Behaviors.ShooterBehavior;
import Battleships.Factories.AgentShipSetterFactory;
import Battleships.Factories.ShotFactory;
import Battleships.Ships.AircraftCarrier;
import Battleships.Ships.Battleship;
import Battleships.Ships.Destroyer;
import Battleships.Ships.Minesweeper;
import Battleships.Ships.Submarine;
public class Agent
{
	private InfluenceMap m = null;
	private Grid g = null;
	private int i=-1;
	private int j=-1;
	private ShooterBehavior shooterBehavior;
//	private Random generator;
	
	public Agent()
	{
		m = new InfluenceMap();		
		g= new Grid(10,10);
	}
	
	
	public int getI()
	{
		return i;
	}
	
	public int getJ()
	{
		return j;
	}	
	
	
	public InfluenceMap setSunk()
	{
		for (int i = 0; i < 10; i++) //change these to ROWS to use the default
		{
			for (int j = 0; j < 10; j++)//change this to CoLumns for default
			{
				if(m.getVal(i,j) == 9)
				{
					m.sunk(i,j);
				}
			}
		}
		return m;
	}
	
	public InfluenceMap setSunk(int i, int j)
	{
		m.sunk(i,j);
		return m;
	}

	public void nextShot(InfluenceMap m1, Grid Attackgrid)
	{
		m = m1;
		g = Attackgrid;
		shooterBehavior = ShotFactory.getShooterBehavior(m.getNumberOfHotspots());
		shooterBehavior.nextShot(m, g);
		i = shooterBehavior.i;
		j = shooterBehavior.j;
	}
	
	public Grid placeShips()
	{
		while(!g.areShipsPlaced())
		{
			AgentShipSetterFactory factory = new AgentShipSetterFactory(g);
			factory.setNextShip(AircraftCarrier.class.getName());
			factory.setNextShip(Battleship.class.getName());
			factory.setNextShip(Destroyer.class.getName());
			factory.setNextShip(Minesweeper.class.getName());
			factory.setNextShip(Submarine.class.getName());
		}
		
		System.out.println("agent grid");
		System.out.println(g.toString());
		
	return g;
	}
	
	public InfluenceMap getMap()
	{
		return m;
	}

}
