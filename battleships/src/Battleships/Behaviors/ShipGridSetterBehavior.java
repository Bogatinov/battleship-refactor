package Battleships.Behaviors;

import Battleships.Grid;

public abstract class ShipGridSetterBehavior {
	protected Grid board;
	protected int shipLength;
	protected int shipGridValue;

	public void setBoard(Grid board) {
		this.board = board;
	}

	public void setShipLength(int shipLength) {
		this.shipLength = shipLength;
	}
	public void setShipGridValue(int shipGridValue) {
		this.shipGridValue = shipGridValue;
	}
	public void placeShipOnGrid(int CoordinateX, int CoordinateY) {			
		this.IsPositionExceedsBoard(CoordinateX, CoordinateY);
		this.IsPositionOccupied(CoordinateX, CoordinateY);
		this.UpdateBoard(CoordinateX, CoordinateY);
	}

	protected abstract void IsPositionExceedsBoard(int CoordinateX, int CoordinateY);
	protected abstract void IsPositionOccupied(int CoordinateX, int CoordinateY);
	protected abstract void UpdateBoard(int CoordinateX, int CoordinateY);
}