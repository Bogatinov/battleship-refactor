package Battleships.Adapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Battleships.GUI;
import Battleships.Factories.ResolverFactory;
import Battleships.Ships.AircraftCarrier;
import Enums.Position;

public class HomeMousePressListener extends MouseAdapter {
	private GUI gui;
	private String currentShip;

	public HomeMousePressListener(GUI gui)
	{
		this.gui = gui;
	}
	
	private void NewGame() {
		if(currentShip == null) {
			this.currentShip = AircraftCarrier.class.getSimpleName();
		}
	}
	
	private void AddShip(int gridX, int gridY) {
		boolean valid = gui.placeShip(currentShip, new Position(gridX, gridY, gui.orientation));
		if(valid) 
			currentShip = ResolverFactory.nextShip(currentShip);
		if(currentShip == null) {
			gui.gameState.setPlayerShipsDeployed();
		}
	}
	
	private void Print() {
		if(gui.gameState.arePlayerShipsDeployed()) {
			gui.setOut("All Ships Deployed, Player's Turn! Click on the left grid to fire shots");
			gui.setOut(gui.gameState.turnToString());
		}
	}

	public void mousePressed(MouseEvent event) {
		if(!gui.gameState.arePlayerShipsDeployed()) {
			this.NewGame();
			int gridj= resolveAxisCoOrdinate(event.getX());
			int gridi= resolveAxisCoOrdinate(event.getY());
			this.AddShip(gridi, gridj);
			this.Print();
		}
	}
	private int resolveAxisCoOrdinate(int x) {
		return ResolverFactory.AxisCoOrdinate(x);
	}

}
