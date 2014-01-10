package Battleships.Behaviors;

import Battleships.Grid;
import Battleships.exception.PositionExceedsBoardException;
import Battleships.exception.PositionOccupiedException;
import Enums.GridValue;

public abstract class ShipGridSetterBehavior {
	protected Grid board;
	protected int shipLength;
	protected GridValue shipGridValue;

	public void setBoard(Grid board) {
		this.board = board;
	}

	public void setShipLength(int shipLength) {
		this.shipLength = shipLength;
	}
	public void setShipGridValue(GridValue shipGridValue) {
		this.shipGridValue = shipGridValue;
	}
	public void placeShipOnGrid(int CoordinateX, int CoordinateY) {			
		this.IsPositionExceedsBoard(CoordinateX, CoordinateY);
		this.IsPositionOccupied(CoordinateX, CoordinateY);
		this.UpdateBoard(CoordinateX, CoordinateY);
	}

	protected abstract void IsPositionExceedsBoard(int CoordinateX, int CoordinateY) throws PositionExceedsBoardException;
	protected abstract void IsPositionOccupied(int CoordinateX, int CoordinateY) throws PositionOccupiedException;
	protected abstract void UpdateBoard(int CoordinateX, int CoordinateY);
}