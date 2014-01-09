package Battleships.Factories;

import java.awt.Graphics;

import Battleships.Graphics.AircraftCarrier;
import Battleships.Graphics.Battleship;
import Battleships.Graphics.Destroyer;
import Battleships.Graphics.Minesweeper;
import Battleships.Graphics.Submarine;
import Enums.Position;

public class ShipGraphicFactory {
	public static void paint(String type, Graphics graphics, Position position) {
		if(type.equals(Submarine.class.getName())) {
			new Submarine(graphics, position);
		}
		
		else if(type.equals(Minesweeper.class.getName())) {
			new Minesweeper(graphics, position);
		}
		
		else if(type.equals(Destroyer.class.getName())) {
			new Destroyer(graphics, position);
		}
		
		else if(type.equals(Battleship.class.getName())) {
			new Battleship(graphics, position);
		}
		
		else if(type.equals(AircraftCarrier.class.getName())) {
			new AircraftCarrier(graphics, position);
		}
	}
}
