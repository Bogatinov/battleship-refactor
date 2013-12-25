package Battleships.Ships;
/*
 * Author: Michael
 * Created: 07 December 2004 15:52:31
 * Modified: 07 December 2004 15:52:31
 */

import Battleships.Grid;
import Battleships.Behaviors.ShipGridSetterBehavior;
import Battleships.Factories.GridBehaviorSetterFactory;


public abstract class Ship
{
	private int intactSegments;
	private ShipGridSetterBehavior setOnGrid;
	
	public Ship(Grid board, int CoordinateX, int CoordinateY, boolean isHorizontal) {
		setOnGrid = GridBehaviorSetterFactory.CreateBehavior(isHorizontal);
		this.setShipLength();
		this.setBoard(board);
		this.setOnBoard(CoordinateX, CoordinateY);
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
		setOnGrid.setBoard(board);
	}
	private void setOnBoard(int coordinateX, int coordinateY) {
		setOnGrid.placeShipOnGrid(coordinateX, coordinateY);
	}
	
	protected int getIntactSegments() {
		return intactSegments;
	}
	protected void setIntactSegments(int intactSegments) {
		this.intactSegments = intactSegments;
		setOnGrid.setShipLength(intactSegments);
		setOnGrid.setShipGridValue(shipGridValue());
	}
	protected abstract void setShipLength();
	protected int shipGridValue() {
		return 9;
	}
}