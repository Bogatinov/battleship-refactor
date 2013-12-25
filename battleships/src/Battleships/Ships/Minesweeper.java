package Battleships.Ships;
/*
 * Author: Michael
 * Created: 05 December 2004 18:57:44
 * Modified: 05 December 2004 18:57:44
 */
import Battleships.Grid;

public class Minesweeper extends Ship
{
	public Minesweeper(Grid board, int i, int j, boolean isHorizontal)
	{
		super(board,i,j,isHorizontal);
	}

	@Override
	protected void setShipLength() {
		this.setIntactSegments(2);
	}	
	
	@Override
	protected int shipGridValue() {
		return 2;
	}
}
