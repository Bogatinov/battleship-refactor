package Battleships.Behaviors;

import Battleships.exception.PositionExceedsBoardException;
import Battleships.exception.PositionOccupiedException;
import Enums.GridValue;

public class VShipGridSetter extends ShipGridSetterBehavior {

	@Override
	protected void UpdateBoard(int CoordinateX, int CoordinateY) {
		for (int c = CoordinateX; c < CoordinateX + shipLength; c++) {
				board.set(c, CoordinateY, shipGridValue); 
			}
		}

	@Override
	protected void IsPositionOccupied(int CoordinateX, int CoordinateY) {
		for (int c = CoordinateX; c < CoordinateX + shipLength; c++) {
			while (board.getGridVal(c, CoordinateY) != GridValue.EmptyCellValue) {
				throw new PositionOccupiedException();
			}
		}
	}

	@Override
	protected void IsPositionExceedsBoard(int CoordinateX, int CoordinateY) {
		if (CoordinateX + shipLength > board.getHeight()) {
			throw new PositionExceedsBoardException();
		}
	}
}
