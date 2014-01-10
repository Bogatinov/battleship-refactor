package Battleships.Graphics;
/* File: Minesweeper.java	
Instructions to draw a Minesweeper on the screen.
*/
	
import java.awt.*;

import Enums.Position;

public class Minesweeper extends Ship
{
	public Minesweeper(Graphics graphics, Position position) {
		super(graphics, position);
	}

	@Override
	protected void paintVertical(Graphics g, int xLeft, int yTop) {
		//Color of the ship
		Color navyGrey = new Color( 180, 180, 180);			
		g.setColor(navyGrey);
		//Draw Main Body of ship
		g.fillOval(xLeft, yTop, 20, 40);
		//Draw detail on ship
		g.setColor(Color.black);	
		g.drawOval(xLeft, yTop, 20, 40);
		g.setColor(navyGrey);
		g.fillRect(xLeft,yTop+20,20,20);
		g.setColor(Color.black);
		g.drawOval(xLeft+5, yTop +5, 10, 10);
					
			
		g.drawRect(xLeft+5,yTop+20,10,15);
		
		g.drawRect(xLeft+7,yTop+25,5,5);
		
		g.drawLine(xLeft+10,yTop,xLeft+10,yTop+10);
		
		g.setColor(Color.black);
		g.drawLine(xLeft,yTop+20,xLeft,yTop+40);
		
		g.drawLine(xLeft,yTop+40,xLeft+20,yTop+40);
		
		g.drawLine(xLeft+20,yTop+40,xLeft+20,yTop+20);


	}

	@Override
	protected void paintHorizontal(Graphics g, int xLeft, int yTop) {
		//Color of the ship
		Color navyGrey = new Color( 180, 180, 180);			
		g.setColor(navyGrey);
		//Draw Main Body of ship
		g.fillOval(xLeft, yTop, 40, 20);
		//Draw detail on ship
		g.setColor(Color.black);	
		g.drawOval(xLeft, yTop, 40, 20);
		g.setColor(navyGrey);
		g.fillRect(xLeft+20,yTop,20,20);
		
		/*Gun turret*/
		g.setColor(Color.black);
		g.drawOval(xLeft+5, yTop +5, 10, 10);
		g.drawRect(xLeft+20,yTop+5,15,10);
		g.drawRect(xLeft+25,yTop+8,5,5);
		g.drawLine(xLeft,yTop+10,xLeft+10,yTop+10);
		
		/*Outline of the stern of the ship*/
		g.drawLine(xLeft+40,yTop,xLeft+20,yTop);
		
		g.drawLine(xLeft+40,yTop,xLeft+40,yTop+20);
		
		g.drawLine(xLeft+40,yTop+20,xLeft+20,yTop+20);

	}
		
}			