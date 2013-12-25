package Battleships.Adapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Battleships.GUI;
import Battleships.Factories.AxisResolverFactory;

public class HomeMousePressListener extends MouseAdapter {
	private GUI gui;
	
	public HomeMousePressListener(GUI gui2)
	{
		gui=gui2;
	}
	
	
			public void mousePressed(MouseEvent event)
			{
				int gridj= resolveAxisCoOrdinate(event.getX());
				int gridi= resolveAxisCoOrdinate(event.getY());
				
				if(!gameState.isBothPlayerAndAgentShipsDeployed())
				{
					gui.deploy(gridi,gridj);
					
				}
				System.out.println("Element corresponds to " + gridi + gridj);
				//repaint();
			}
			private int resolveAxisCoOrdinate(int x) {
				return AxisResolverFactory.resolveAxisCoOrdinate(x);
			}

}