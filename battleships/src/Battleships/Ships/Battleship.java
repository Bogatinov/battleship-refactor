package Battleships.Ships;
/*
 * Author: Michael
 * Created: 07 December 2004 23:01:02
 * Modified: 07 December 2004 23:01:02
 */

import Battleships.Grid;
import Enums.GridValue;
import Enums.Position;

public class Battleship extends Ship
{	
	public Battleship(Grid board ,Position position)
	{
		super(board,position);
	}
	

	@Override
	public void setShipLength() {
		this.setIntactSegments(4);
	}
	
	@Override
	protected GridValue shipGridValue() {
		return GridValue.BattleshipIntact;
	}
}
