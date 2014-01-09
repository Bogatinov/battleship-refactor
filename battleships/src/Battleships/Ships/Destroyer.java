package Battleships.Ships;
/*
 * Author: Michael
 * Created: 07 December 2004 16:50:18
 * Modified: 07 December 2004 16:50:18
 */

import Battleships.Grid;
import Enums.GridValue;
import Enums.Position;

public class Destroyer extends Ship
{
	public Destroyer(Grid board, Position position)
	{
		super(board,position);
	}
	

	@Override
	public void setShipLength() {
		this.setIntactSegments(3);
	}
	
	@Override
	protected GridValue shipGridValue() {
		return GridValue.DestroyerIntact;
	}
}
