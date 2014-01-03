package Battleships.Behaviors;

import Battleships.Grid;
import Battleships.InfluenceMap;

public abstract class ShooterBehavior {
	public int i = -1;
	public int j = -1;
	public abstract void nextShot(InfluenceMap m1, Grid Attackgrid);
}
