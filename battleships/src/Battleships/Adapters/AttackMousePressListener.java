package Battleships.Adapters;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import Battleships.GUI;
import Battleships.GameState;
import Battleships.Factories.AxisResolverFactory;
import Battleships.Graphics.AttackPanel;

public class AttackMousePressListener extends MouseAdapter
{
	
	private AttackPanel attackPanel;
	private GameState gameState;
	
	public AttackMousePressListener(AttackPanel attackPanel, GameState gameState)
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
					
	                JTextField outText = null;
					String acceptPlayerShotString = 
	                	gameState.acceptPlayerShot(gridi,gridj, attackPanelGraphics, outText);

					System.out.println(acceptPlayerShotString);
					System.out.println("Element corresponds to " + gridi + gridj);
				}
			}

			private int resolveAxisCoOrdinate(int x) {
				return AxisResolverFactory.resolveAxisCoOrdinate(x);
			}	
}
