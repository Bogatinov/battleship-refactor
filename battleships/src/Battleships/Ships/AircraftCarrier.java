package Battleships.Ships;
/*
 * Author: Michael
 * Created: 08 December 2004 09:37:10
 * Modified: 08 December 2004 09:37:10
 */

import Battleships.Grid;

public class AircraftCarrier extends Ship
{
	public AircraftCarrier(Grid board, int i, int j, boolean isHorizontal)
	{
		super(board,i,j,isHorizontal);
	}

	@Override
	public void setShipLength() {
		this.setIntactSegments(5);
	}
	
	@Override
	protected int shipGridValue() {
		return 5;
	}
}
