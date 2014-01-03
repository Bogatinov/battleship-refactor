package Battleships.Factories;

import Battleships.Grid;
import Battleships.NumberGenerator;

public class AgentShipSetterFactory {
	private Grid grid;
	public AgentShipSetterFactory(Grid g) {
		this.grid = g;
	}
	public void setNextShip(String type) {
		NumberGenerator gen = new NumberGenerator();
		int x = gen.rand(10);
		int y = gen.rand(10);
		int o = gen.rand(2);
		
		if(type.equals("AircraftCarrier")) {
			grid.addAir(x, y, o);
		}
		if(type.equals("Battleship")) {
			grid.addBattle(x, y, o);
		}
		if(type.equals("Destroyer")) {
			grid.addDest(x, y, o);
		}
		if(type.equals("Minesweeper")) {
			grid.addMine(x, y, o);
		}
		if(type.equals("Submarine")) {
			grid.addSub(x, y, o);
		}
	}
}
