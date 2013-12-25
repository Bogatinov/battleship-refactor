package Battleships.Adapters;

import java.awt.Graphics;
import Battleships.GUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Battleships.Factories.AxisResolverFactory;
import Battleships.Graphics.AttackPanel;

public class AttackMousePressListener extends MouseAdapter
{
	
	private AttackPanel a;
	private GUI gui;
	
	public AttackMousePressListener(AttackPanel p, GUI gui2)
	{
		a=p;
		gui=gui2;
	}
	  
	
			public void mousePressed(MouseEvent event)
			{
				if(gameState.IsAcceptingPlayerInput())
				{
					int x = event.getX();
					int y = event.getY();
				
					int gridj= resolveAxisCoOrdinate(x);
					int gridi= resolveAxisCoOrdinate(y);
				
					Graphics attackPanelGraphics = a.getGraphics();
					
	                String acceptPlayerShotString = 
	                	gameState.acceptPlayerShot(gridi,gridj, attackPanelGraphics, outText);
	                
	                //gui.gameState.updatePlayerClick(gridi, gridj, gui);
	                
	                
	                
					System.out.println(acceptPlayerShotString);
					System.out.println("Element corresponds to " + gridi + gridj);
				}
			}

			private int resolveAxisCoOrdinate(int x) {
				return AxisResolverFactory.resolveAxisCoOrdinate(x);
			}	
}
