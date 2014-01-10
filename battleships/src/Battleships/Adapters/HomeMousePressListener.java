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
		this.currentShip = AircraftCarrier.class.getSimpleName();
	}

	public void mousePressed(MouseEvent event) {
		int gridj= resolveAxisCoOrdinate(event.getX());
		int gridi= resolveAxisCoOrdinate(event.getY());

		boolean valid = gui.placeShip(currentShip, new Position(gridi, gridj, gui.orientation));
		if(valid) 
			currentShip = ResolverFactory.nextShip(currentShip);
		
		if(currentShip == null)
			gui.gameState.setPlayerShipsDeployed();
		System.out.println("Element corresponds to " + gridi + gridj);
		
		if(gui.gameState.arePlayerShipsDeployed()) {
			gui.setOut("All Ships Deployed, Player's Turn! Click on the left grid to fire shots");
			gui.setOut(gui.gameState.turnToString());
		}	
	}
	private int resolveAxisCoOrdinate(int x) {
		return ResolverFactory.AxisCoOrdinate(x);
	}

}
