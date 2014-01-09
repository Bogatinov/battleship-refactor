package Battleships.Graphics;
/* File: Battleship.java	
Instructions to draw a Battleship on the screen.
*/
	
import java.awt.Color;
import java.awt.Graphics;

import Enums.Position;


public class Battleship extends Ship
{
	public Battleship(Graphics graphics, Position position) {
		super(graphics, position);
	}
	
	@Override
	protected void paintVertical(Graphics g, int xLeft, int yTop) {
		Color navyGrey = new Color( 180, 180, 180);			
		
		
		//draw main body of ship
		g.setColor(navyGrey);
		g.fillOval(xLeft, yTop, 20, 80);
		
		//draw detail
		g.setColor(Color.black);	
		
		g.drawOval(xLeft, yTop, 20, 80); //outline
		g.setColor(navyGrey);
			
			
					
		//Mid deck tower
		g.setColor(Color.black);
		g.drawRect(xLeft+5,yTop+40,10,15);
		g.drawLine(xLeft+5,yTop+40,xLeft+10,yTop+20);
		g.drawLine(xLeft+10,yTop+20,xLeft+15,yTop+40);
		g.drawRect(xLeft+7,yTop+45,5,5);
		g.drawLine(xLeft+5,yTop+55,xLeft+10,yTop+63);
		g.drawLine(xLeft+10,yTop+63,xLeft+15,yTop+55);
		
		//Draw Top Gun Barrel
		g.drawOval(xLeft+5, yTop +5,10, 10);
		g.drawLine(xLeft+13,yTop,xLeft+13,yTop+10);
		g.drawLine(xLeft+8,yTop,xLeft+8,yTop+10);
		
		//Draw Bottom Gun Barrel
		g.drawOval(xLeft+5, yTop +65, 10, 10);
		g.drawLine(xLeft+7,yTop+70,xLeft+7,yTop+80);
		g.drawLine(xLeft+13,yTop+70,xLeft+13,yTop+80);

	}

	@Override
	protected void paintHorizontal(Graphics g, int xLeft, int yTop) {
		Color navyGrey = new Color( 180, 180, 180);			
		
		//draw main body of ship
		g.setColor(navyGrey);
		g.fillOval(xLeft, yTop, 80, 20);

		//draw detail
		g.setColor(Color.black);	

		g.drawOval(xLeft, yTop, 80, 20); //outline
		g.setColor(navyGrey);

		//Mid deck tower
		g.setColor(Color.black);
		g.drawRect(xLeft+40,yTop+5,15,10);
		g.drawLine(xLeft+40,yTop+5,xLeft+20,yTop+10);
		g.drawLine(xLeft+20,yTop+10,xLeft+40,yTop+15);
		g.drawRect(xLeft+45,yTop+7,5,5);
		g.drawLine(xLeft+55,yTop+5,xLeft+63,yTop+10);
		g.drawLine(xLeft+63,yTop+10,xLeft+55,yTop+15);
		
		//Draw Top Gun Barrel
		g.drawOval(xLeft+5, yTop +5, 10, 10);
		g.drawLine(xLeft,yTop+13,xLeft+10,yTop+13);
		g.drawLine(xLeft,yTop+8,xLeft+10,yTop+8);
		
		//Draw Bottom Gun Barrel
		g.drawOval(xLeft+65, yTop +5, 10, 10);
		g.drawLine(xLeft+70,yTop+8,xLeft+80,yTop+8);
		g.drawLine(xLeft+70,yTop+13,xLeft+80,yTop+13);

	}
		
}			