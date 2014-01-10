package Battleships.Factories;

import Battleships.Ships.AircraftCarrier;
import Battleships.Ships.Battleship;
import Battleships.Ships.Destroyer;
import Battleships.Ships.Minesweeper;
import Battleships.Ships.Submarine;

public class ResolverFactory {
	public static int AxisCoOrdinate(int Coordinate) {
		if (Coordinate < 20)
			return 0;
		else if (Coordinate < 40)
			return 1;
		else if (Coordinate < 60)
			return 2;
		else if (Coordinate < 80)
			return 3;
		else if (Coordinate < 100)
			return 4;
		else if (Coordinate < 120)
			return 5;
		else if (Coordinate < 140)
			return 6;
		else if (Coordinate < 160)
			return 7;
		else if (Coordinate < 180)
			return 8;
		else if (Coordinate < 200)
			return 9;
		return -1;
	}
	public static String nextShip(String current) {
		if(current.equalsIgnoreCase(AircraftCarrier.class.getSimpleName()))
			return Battleship.class.getSimpleName();
		else if(current.equalsIgnoreCase(Battleship.class.getSimpleName())) 
			return Destroyer.class.getSimpleName();
		else if(current.equalsIgnoreCase(Destroyer.class.getSimpleName())) 
			return Submarine.class.getSimpleName();
		else if(current.equalsIgnoreCase(Submarine.class.getSimpleName()))
			return Minesweeper.class.getSimpleName();
		return null;
	}
}
