package Battleships.Graphics;
/* File: Miss.java	
Instructions to draw a splash, signifing a missed shot on the screen.
*/
	
import java.awt.*;

public class MissIcon extends Icon
{
	public MissIcon(Graphics graphics, int x, int y) {
		super(graphics, x, y);
	}

	@Override
	protected Color getFirstColor() {
		return new Color( 150, 237, 255);
	}

	@Override
	protected Color getSecondColor() {
		return new Color( 50, 137, 205);
	}
	
		
}			