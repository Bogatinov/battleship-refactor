package Battleships.Graphics;
/* File: Submarine.java	
Instructions to draw a Submarine on the screen.
*/
	
import java.awt.*;

import Enums.Position;

public class Submarine extends Ship
{
	public Submarine(Graphics graphics, Position position) {
		super(graphics, position);
	}

	@Override
	protected void paintVertical(Graphics g, int xLeft, int yTop) {
		Color navyGrey = new Color( 180, 180, 180);			
		g.setColor(navyGrey);
		//Shape of Submarine
		g.fillOval(xLeft, yTop, 20, 60);
		
		//Detail of the Sub
		g.setColor(Color.black);
		g.drawOval(xLeft, yTop, 20, 60); //Outline of the submarine
		g.drawOval(xLeft+5, yTop +5, 10, 10);
		g.setColor(navyGrey);
		g.fillRect(xLeft+5,yTop+10,10,6);
		g.setColor(Color.black);
		
		//Torpedo Tubes
		g.fillOval(xLeft+7, yTop +10, 2, 2);
		g.fillOval(xLeft+11, yTop +10, 2, 2);
		
		//Tail Fins
		g.drawLine(xLeft+5,yTop+10,xLeft+5,yTop+50);
		g.drawLine(xLeft+15,yTop+10,xLeft+15,yTop+50);
		g.drawLine(xLeft+10,yTop+15,xLeft+10,yTop+45);
		g.drawLine(xLeft+5,yTop+50,xLeft+10,yTop+55);
		g.drawLine(xLeft+15,yTop+50,xLeft+10,yTop+55);
		
		g.drawLine(xLeft+5,yTop+60,xLeft+15,yTop+60);
	}

	@Override
	protected void paintHorizontal(Graphics g, int xLeft, int yTop) {
		Color navyGrey = new Color( 180, 180, 180);			
		g.setColor(navyGrey);
		//Shape of Submarine
		g.fillOval(xLeft, yTop, 60, 20);
		
		//Detail of the Sub
		g.setColor(Color.black);
		g.drawOval(xLeft, yTop, 60, 20); //Outline of the submarine
		g.drawOval(xLeft+5, yTop +5, 10, 10);
		g.setColor(navyGrey);
		g.fillRect(xLeft+10,yTop+5,6,10);
		
		
		//Torpedo Tubes
		g.setColor(Color.black);
		g.fillOval(xLeft+10, yTop +8, 2, 2);
		g.fillOval(xLeft+10, yTop +12, 2, 2);
		
		//Spine of Ship
		g.drawLine(xLeft+10,yTop+5,xLeft+50,yTop+5);
		g.drawLine(xLeft+10,yTop+15,xLeft+50,yTop+15);
		g.drawLine(xLeft+15,yTop+10,xLeft+45,yTop+10);			
		g.drawLine(xLeft+50,yTop+5,xLeft+55,yTop+10);
		g.drawLine(xLeft+50,yTop+15,xLeft+55,yTop+10);
		
		//Tail Fin
		g.drawLine(xLeft+60,yTop+5,xLeft+60,yTop+15);
	}		
}