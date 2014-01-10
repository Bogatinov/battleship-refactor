package Battleships.Ships;
/*
 * Author: Michael
 * Created: 07 December 2004 15:52:31
 * Modified: 07 December 2004 15:52:31
 */

import Battleships.Grid;
import Battleships.Behaviors.ShipGridSetterBehavior;
import Battleships.Factories.GridBehaviorSetterFactory;
import Enums.GridValue;
import Enums.Position;


public abstract class Ship
{
	private int intactSegments;
	private ShipGridSetterBehavior onGrid;
	
	public Ship(Grid board, Position position) {
		onGrid = GridBehaviorSetterFactory.CreateBehavior(position.Orientation());
		this.setShipLength();
		this.setBoard(board);
		this.setOnBoard(position.X(), position.Y());
	}

	public boolean isSunk() {
		return getIntactSegments() == 0;
	}
	public void scoreHit() {
		this.areIntactSegmentsLessThanZero();
		this.setIntactSegments(this.getIntactSegments()-1);
	}
	
	private void areIntactSegmentsLessThanZero() {
		if (getIntactSegments() < 0)
			throw new IllegalArgumentException("Segments var is less than 0");
	}
	private void setBoard(Grid board) {
		onGrid.setBoard(board);
	}
	private void setOnBoard(int coordinateX, int coordinateY) {
		onGrid.placeShipOnGrid(coordinateX, coordinateY);
	}
	
	protected int getIntactSegments() {
		return intactSegments;
	}
	protected void setIntactSegments(int intactSegments) {
		this.intactSegments = intactSegments;
		onGrid.setShipLength(intactSegments);
		onGrid.setShipGridValue(shipGridValue());
	}
	protected abstract void setShipLength();
	protected abstract GridValue shipGridValue();
}