package Battleships.Graphics;
/* File: AircraftCarrier.java	
Instructions to draw a AircraftCarrier on the screen.
 */

import java.awt.Color;
import java.awt.Graphics;

import Enums.Position;

public class AircraftCarrier extends Ship
{
	public AircraftCarrier(Graphics graphics, Position position) {
		super(graphics,position);
	}
	
	@Override
	protected void paintVertical(Graphics g, int xLeft, int yTop)
	{
		Color navyGrey = new Color( 180, 180, 180);			

		//draw main body of ship
		g.setColor(navyGrey);
		g.fillRect(xLeft, yTop, 20, 100);

		//draw detail
		g.setColor(Color.black);	

		g.drawRect(xLeft, yTop, 20, 100); //outline

		//Mid deck tower
		g.drawRect(xLeft+15,yTop+40,5,20);
		g.drawRect(xLeft+17,yTop+45,3,10);

		//Draw Runway
		g.setColor(Color.white);
		g.fillRect(xLeft+10,yTop+3,1,10);
		g.fillRect(xLeft+10,yTop+15,1,10);
		g.fillRect(xLeft+10,yTop+27,1,10);
		g.fillRect(xLeft+10,yTop+40,1,10);
		g.fillRect(xLeft+10,yTop+53,1,10);
		g.fillRect(xLeft+10,yTop+65,1,10);
		g.fillRect(xLeft+10,yTop+76,1,10);
		g.fillRect(xLeft+10,yTop+90,1,7);
	}

	@Override
	protected void paintHorizontal(Graphics g, int xLeft, int yTop)
	{
		Color navyGrey = new Color( 180, 180, 180);			

		//draw main body of ship
		g.setColor(navyGrey);
		g.fillRect(xLeft, yTop, 100, 20);

		//draw detail
		g.setColor(Color.black);	

		g.drawRect(xLeft, yTop, 100, 20); //outline

		//Mid deck tower
		g.drawRect(xLeft+40,yTop+15,20,5);
		g.drawRect(xLeft+45,yTop+17,10,3);

		//Draw Runway
		g.setColor(Color.white);
		g.fillRect(xLeft+3,yTop+10,10,1);
		g.fillRect(xLeft+15,yTop+10,10,1);
		g.fillRect(xLeft+28,yTop+10,10,1);
		g.fillRect(xLeft+40,yTop+10,10,1);
		g.fillRect(xLeft+53,yTop+10,10,1);
		g.fillRect(xLeft+65,yTop+10,10,1);
		g.fillRect(xLeft+78,yTop+10,10,1);
		g.fillRect(xLeft+90,yTop+10,10,1);
	}		
}