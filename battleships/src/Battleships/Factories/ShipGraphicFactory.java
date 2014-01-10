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
		if(type.equals(Submarine.class.getSimpleName())) {
			new Submarine(graphics, position);
		}
		
		else if(type.equals(Minesweeper.class.getSimpleName())) {
			new Minesweeper(graphics, position);
		}
		
		else if(type.equals(Destroyer.class.getSimpleName())) {
			new Destroyer(graphics, position);
		}
		
		else if(type.equals(Battleship.class.getSimpleName())) {
			new Battleship(graphics, position);
		}
		
		else if(type.equals(AircraftCarrier.class.getSimpleName())) {
			new AircraftCarrier(graphics, position);
		}
	}
}
