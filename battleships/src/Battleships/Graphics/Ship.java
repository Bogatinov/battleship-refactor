package Battleships.Graphics;

import java.awt.Graphics;

import Enums.Orientation;
import Enums.Position;

public abstract class Ship {
	public Ship(Graphics graphics, Position position) {
		if(position.Orientation() == Orientation.Horizontal) {
			this.paintHorizontal(graphics, position.Y()*20, position.X()*20);
		} else {
			this.paintVertical(graphics, position.Y()*20, position.X()*20);
		}
	}
	
	protected abstract void paintVertical(Graphics g, int xLeft, int yTop);
	protected abstract void paintHorizontal(Graphics g, int xLeft, int yTop);
}
