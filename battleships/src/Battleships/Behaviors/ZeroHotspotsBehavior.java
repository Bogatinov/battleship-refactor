package Battleships.Behaviors;

import java.util.Random;

import Battleships.Grid;
import Battleships.InfluenceMap;

public class ZeroHotspotsBehavior extends ShooterBehavior {

	@Override
	public void nextShot(InfluenceMap m1, Grid Attackgrid) {
		Random Powergen = new Random();
		
		i = Powergen.nextInt(10);
		j = Powergen.nextInt(10);
	}

}
