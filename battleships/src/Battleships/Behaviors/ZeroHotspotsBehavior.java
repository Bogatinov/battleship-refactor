package Battleships.Behaviors;

import Battleships.Grid;
import Battleships.InfluenceMap;
import Battleships.NumberGenerator;

public class ZeroHotspotsBehavior extends ShooterBehavior {

	@Override
	public void nextShot(InfluenceMap m1, Grid Attackgrid) {
		NumberGenerator Powergen = new NumberGenerator();
		
		i = Powergen.rand(10);
		j = Powergen.rand(10);
	}

}
