package Battleships.Ships;
/*
 * Author: Michael
 * Created: 05 December 2004 18:57:44
 * Modified: 05 December 2004 18:57:44
 */
import Battleships.Grid;
import Enums.GridValue;
import Enums.Position;

public class Minesweeper extends Ship
{
	public Minesweeper(Grid board, Position position)
	{
		super(board, position);
	}

	@Override
	protected void setShipLength() {
		this.setIntactSegments(2);
	}	
	
	@Override
	protected GridValue shipGridValue() {
		return GridValue.MinesweeperIntact;
	}
}
