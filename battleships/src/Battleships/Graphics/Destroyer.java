package Battleships.Graphics;
/* File: Destroyer.java	
Instructions to draw a Destroyer on the screen.
*/
	
import java.awt.*;

import Enums.Position;

public class Destroyer extends Ship
{
	public Destroyer(Graphics graphics, Position position) {
		super(graphics, position);
	}

	@Override
	protected void paintVertical(Graphics g, int xLeft, int yTop) {
		Color navyGrey = new Color( 180, 180, 180);			
		
		
		//draw main body of ship
		g.setColor(navyGrey);
		g.fillOval(xLeft, yTop, 20, 60);
		//draw detail
		g.setColor(Color.black);	
		
		g.drawOval(xLeft, yTop, 20, 60); //outline
		g.setColor(navyGrey);
		g.fillRect(xLeft,yTop+20,20,40);
		
		g.setColor(Color.black);

		//Mid deck tower
		g.drawRect(xLeft+5,yTop+40,10,15);
		g.drawLine(xLeft+5,yTop+40,xLeft+10,yTop+20);
		g.drawLine(xLeft+10,yTop+20,xLeft+15,yTop+40);
		g.drawRect(xLeft+7,yTop+45,5,5);
		
		//Gun Barrel
		g.drawOval(xLeft+5, yTop +5, 10, 10);
		g.drawLine(xLeft+10,yTop,xLeft+10,yTop+10);
		
		//Outline of the stern of the ship
		g.drawLine(xLeft,yTop+20,xLeft,yTop+60);
		g.drawLine(xLeft,yTop+60,xLeft+20,yTop+60);
		g.drawLine(xLeft+20,yTop+60,xLeft+20,yTop+20);
	}

	@Override
	protected void paintHorizontal(Graphics g, int xLeft, int yTop) {
		Color navyGrey = new Color( 180, 180, 180);			

		//draw main body of ship
		g.setColor(navyGrey);
		g.fillOval(xLeft, yTop, 60, 20);
		//draw detail
		g.setColor(Color.black);	
		
		g.drawOval(xLeft, yTop, 60, 20); //outline
		g.setColor(navyGrey);
		g.fillRect(xLeft+20,yTop,40,20);
		
		g.setColor(Color.black);
		
					
		//Mid deck tower
		g.drawRect(xLeft+40,yTop+5,15,10);			
		g.drawLine(xLeft+40,yTop+5,xLeft+20,yTop+10);
		g.drawLine(xLeft+20,yTop+10,xLeft+40,yTop+15);
		g.drawRect(xLeft+45,yTop+8,5,5);
		
		//Gun Barrel
		g.drawOval(xLeft+5, yTop +5, 10, 10);
		g.drawLine(xLeft,yTop+10,xLeft+10,yTop+10);
		
		//Outline of the stern of the ship
		g.drawLine(xLeft+20,yTop,xLeft+60,yTop);
		g.drawLine(xLeft+60,yTop,xLeft+60,yTop+20);
		g.drawLine(xLeft+60,yTop+20,xLeft+20,yTop+20);
	}		
}