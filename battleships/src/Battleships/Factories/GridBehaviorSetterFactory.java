package Battleships.Factories;

import Battleships.Behaviors.HShipGridSetter;
import Battleships.Behaviors.ShipGridSetterBehavior;
import Battleships.Behaviors.VShipGridSetter;

public class GridBehaviorSetterFactory {
	public static ShipGridSetterBehavior CreateBehavior(boolean isHorizontal) {
		if(isHorizontal) {
			return new HShipGridSetter();
		}
		else {
			return new VShipGridSetter();
		}
	}
}
