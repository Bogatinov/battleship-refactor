package Battleships.Graphics;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Icon {
	protected Color firstColor;
	protected Color secondColor;
	
	public Icon(Graphics graphics, int x, int y) {
		this.paint(graphics, x*20, y*20);
	}
	
	private void paint(Graphics g, int xLeft, int yTop) {
		firstColor = getFirstColor();
		secondColor = getSecondColor();
		
		g.setColor(firstColor);
		g.fillOval(xLeft, yTop, 20, 20);
		
		
		g.setColor(secondColor);
		g.fillOval(xLeft+4, yTop +4, 13, 13);
		
		
		g.setColor(firstColor);
		g.fillOval(xLeft+8, yTop +8, 5, 5);
	}
	
	protected abstract Color getFirstColor();
	protected abstract Color getSecondColor();
}
