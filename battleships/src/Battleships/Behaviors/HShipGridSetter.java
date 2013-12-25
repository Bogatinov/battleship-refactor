package Battleships.Behaviors;

import Battleships.exception.PositionExceedsBoardException;
import Battleships.exception.PositionOccupiedException;

public class HShipGridSetter extends ShipGridSetterBehavior {

	@Override
	protected void UpdateBoard(int CoordinateX, int CoordinateY) {
		for (int c = CoordinateY; c < CoordinateY + shipLength; c++) {
				board.update(CoordinateX, c, shipGridValue);
			}
		}

	@Override
	protected void IsPositionOccupied(int CoordinateX, int CoordinateY) {
		for (int c = CoordinateY; c < CoordinateY + shipLength; c++) {
			while (board.getGridVal(CoordinateX, c) != 0) {
				throw new PositionOccupiedException();
			}
	}
	}

	@Override
	protected void IsPositionExceedsBoard(int CoordinateX, int CoordinateY) {
		if (CoordinateY + shipLength > board.getWidth()) {
			throw new PositionExceedsBoardException();
		}
	}
}
