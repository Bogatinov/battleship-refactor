package Battleships.Adapters;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Battleships.GameState;
import Battleships.Factories.ResolverFactory;
import Battleships.Graphics.PlayerPanel;

public class AttackMousePressListener extends MouseAdapter
{
	
	private PlayerPanel attackPanel;
	private GameState gameState;
	
	public AttackMousePressListener(PlayerPanel attackPanel, GameState gameState)
	{
		this.attackPanel = attackPanel;
		this.gameState = gameState;
	}
	  
	
			public void mousePressed(MouseEvent event)
			{
				if(gameState.IsAcceptingPlayerInput())
				{
					int x = event.getX();
					int y = event.getY();
				
					int gridj= resolveAxisCoOrdinate(x);
					int gridi= resolveAxisCoOrdinate(y);
				
					Graphics attackPanelGraphics = attackPanel.getGraphics();
					
					String acceptPlayerShotString = 
	                	gameState.acceptPlayerShot(gridi,gridj, attackPanelGraphics);

					System.out.println(acceptPlayerShotString);
					System.out.println("Element corresponds to " + gridi + gridj);
				}
			}

			private int resolveAxisCoOrdinate(int x) {
				return ResolverFactory.AxisCoOrdinate(x);
			}	
}
