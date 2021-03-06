package Battleships.Graphics;
/* File: Miss.java	
Instructions to draw a splash, signifing a missed shot on the screen.
*/
	
import java.awt.*;

public class MissIcon
{
	public static void paint(Graphics g, int xLeft, int yTop) {
		Color firstColor = new Color( 150, 237, 255);
		Color secondColor = new Color( 50, 137, 205);
		xLeft*=20;
		yTop*=20;
		g.setColor(firstColor);
		g.fillOval(xLeft, yTop, 20, 20);
		
		
		g.setColor(secondColor);
		g.fillOval(xLeft+4, yTop +4, 13, 13);
		
		
		g.setColor(firstColor);
		g.fillOval(xLeft+8, yTop +8, 5, 5);
	}		
}			