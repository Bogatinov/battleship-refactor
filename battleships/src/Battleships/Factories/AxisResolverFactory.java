package Battleships.Factories;

public class AxisResolverFactory {
	public static int resolveAxisCoOrdinate(int Coordinate) {
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
}
