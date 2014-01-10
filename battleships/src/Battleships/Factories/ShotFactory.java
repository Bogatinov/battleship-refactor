package Battleships.Factories;

import Battleships.Behaviors.ManyHotspotsBehavior;
import Battleships.Behaviors.OneHotspotBehavior;
import Battleships.Behaviors.ShooterBehavior;
import Battleships.Behaviors.ZeroHotspotsBehavior;
import Battleships.Ships.AircraftCarrier;
import Battleships.Ships.Battleship;
import Battleships.Ships.Destroyer;
import Battleships.Ships.Minesweeper;
import Battleships.Ships.Submarine;
import Enums.GridValue;
import Enums.ShotType;

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
	
	public static ShotType getShotType(GridValue value) {
		if(value == GridValue.EmptyCellValue || value == GridValue.MissedShot) {
			return new ShotType(GridValue.MissedShot, null);
		}
		
		if(value == GridValue.AircraftcarrierIntact) {
			return new ShotType(GridValue.AircraftcarrierShot, AircraftCarrier.class.getSimpleName());
		} 
		
		if(value == GridValue.MinesweeperIntact) {
			return new ShotType(GridValue.MinesweeperShot, Minesweeper.class.getSimpleName());
		} 
		
		if(value == GridValue.DestroyerIntact) {
			return new ShotType(GridValue.DestroyerShot, Destroyer.class.getSimpleName());
		}
		
		if(value == GridValue.BattleshipIntact) {
			return new ShotType(GridValue.BattleshipShot, Battleship.class.getSimpleName());
		}
		
		if(value == GridValue.SubmarineIntact) {
			return new ShotType(GridValue.SubmarineShot, Submarine.class.getSimpleName());
		}
		
		return null;
	}
}
