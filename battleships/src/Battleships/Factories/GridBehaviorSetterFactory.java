package Battleships.Factories;

import Battleships.Behaviors.HShipGridSetter;
import Battleships.Behaviors.ShipGridSetterBehavior;
import Battleships.Behaviors.VShipGridSetter;
import Enums.Orientation;

public class GridBehaviorSetterFactory {
	public static ShipGridSetterBehavior CreateBehavior(Orientation orientation) {
		if(orientation == Orientation.Horizontal) {
			return new HShipGridSetter();
		}
		else {
			return new VShipGridSetter();
		}
	}
}
