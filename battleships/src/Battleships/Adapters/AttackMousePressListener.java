package Battleships.Adapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Battleships.GUI;
import Battleships.Factories.ResolverFactory;

public class AttackMousePressListener extends MouseAdapter
{
	private GUI gui;
	
	public AttackMousePressListener(GUI gui)
	{
		this.gui = gui;
	}
	  
	
			public void mousePressed(MouseEvent event)
			{
				if(gui.IsAcceptingPlayerInput())
				{
					int x = event.getX();
					int y = event.getY();
				
					int gridj= resolveAxisCoOrdinate(x);
					int gridi= resolveAxisCoOrdinate(y);
					
					String acceptPlayerShotString = 
	                	gui.acceptPlayerShot(gridi,gridj);

					System.out.println(acceptPlayerShotString);
					System.out.println("Element corresponds to " + gridi + gridj);
				}
			}

			private int resolveAxisCoOrdinate(int x) {
				return ResolverFactory.AxisCoOrdinate(x);
			}	
}
