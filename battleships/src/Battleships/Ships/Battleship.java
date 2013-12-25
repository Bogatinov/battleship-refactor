package Battleships.Ships;
/*
 * Author: Michael
 * Created: 07 December 2004 23:01:02
 * Modified: 07 December 2004 23:01:02
 */

import Battleships.Grid;

public class Battleship extends Ship
{	
	public Battleship(Grid board ,int i, int j, boolean isHorizontal)
	{
		super(board,i,j,isHorizontal);
	}
	

	@Override
	public void setShipLength() {
		this.setIntactSegments(4);
	}
	
	@Override
	protected int shipGridValue() {
		return 4;
	}
}
