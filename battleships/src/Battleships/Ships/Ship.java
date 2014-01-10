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
	private ShipGridSetterBehavior onGrid;
	
	public Ship(Grid board, Position position) {
		onGrid = GridBehaviorSetterFactory.CreateBehavior(position.Orientation());
		this.setShipLength();
		onGrid.setBoard(board);
		onGrid.placeShipOnGrid(position.X(), position.Y());
	}

	public boolean isSunk() {
		return onGrid.getShipLength() == 0;
	}
	public void scoreHit() {
		this.areIntactSegmentsLessThanZero();
		onGrid.setShipLength(onGrid.getShipLength() - 1);
	}
	
	private void areIntactSegmentsLessThanZero() {
		if (onGrid.getShipLength() < 0)
			throw new IllegalArgumentException("Segments var is less than 0");
	}

	protected void setIntactSegments(int intactSegments) {
		onGrid.setShipLength(intactSegments);
		onGrid.setShipGridValue(shipGridValue());
	}
	protected abstract void setShipLength();
	protected abstract GridValue shipGridValue();
}