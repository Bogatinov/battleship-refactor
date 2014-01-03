package Battleships.Factories;

import Battleships.Behaviors.ManyHotspotsBehavior;
import Battleships.Behaviors.OneHotspotBehavior;
import Battleships.Behaviors.ShooterBehavior;
import Battleships.Behaviors.ZeroHotspotsBehavior;

public class ShotFactory {
	public static ShooterBehavior getShooterBehavior(int numberHotspots) {
		if(numberHotspots == 0) {
			return new ZeroHotspotsBehavior();
		}
		if(numberHotspots == 1) {
			return new OneHotspotBehavior();
		}
		if(numberHotspots > 1) {
			return new ManyHotspotsBehavior();
		}
		return null;
	}
}
