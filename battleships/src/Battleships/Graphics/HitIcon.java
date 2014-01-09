package Battleships.Graphics;
/* File: Hit.java	
Instructions to draw a splash, signifing a missed shot on the screen.
*/
	
import java.awt.*;

public class HitIcon extends Icon
{

	public HitIcon(Graphics graphics, int x, int y) {
		super(graphics, x, y);
	}

	@Override
	protected Color getFirstColor() {
		return new Color( 250, 185, 050);
	}

	@Override
	protected Color getSecondColor() {
		return new Color( 250, 137, 0);
	}
	
		
}