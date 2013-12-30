package Battleships.Adapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Battleships.GUI;
import Battleships.Factories.AxisResolverFactory;

public class HomeMousePressListener extends MouseAdapter {
	private GUI gui;
	
	public HomeMousePressListener(GUI gui)
	{
		this.gui = gui;
	}
	
	public void mousePressed(MouseEvent event) {
		int gridj= resolveAxisCoOrdinate(event.getX());
		int gridi= resolveAxisCoOrdinate(event.getY());
				
		if(!this.gui.gameState.isBothPlayerAndAgentShipsDeployed()) {
				gui.deploy(gridi,gridj);	
		}
			
		System.out.println("Element corresponds to " + gridi + gridj);
	}
	private int resolveAxisCoOrdinate(int x) {
		return AxisResolverFactory.resolveAxisCoOrdinate(x);
	}

}
