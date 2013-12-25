package Battleships.Ships;
/*
 * Author: Michael
 * Created: 07 December 2004 16:50:18
 * Modified: 07 December 2004 16:50:18
 */

import Battleships.Grid;

public class Destroyer extends Ship
{
	public Destroyer(Grid board, int i, int j, boolean isHorizontal)
	{
		super(board,i,j,isHorizontal);
	}
	

	@Override
	public void setShipLength() {
		this.setIntactSegments(3);
	}
	
	@Override
	protected int shipGridValue() {
		return 7;
	}
}
