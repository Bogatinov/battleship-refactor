package Battleships.Ships;
/*
 * Author: Michael
 * Created: 08 December 2004 09:37:10
 * Modified: 08 December 2004 09:37:10
 */

import Battleships.Grid;
import Enums.GridValue;
import Enums.Position;

public class AircraftCarrier extends Ship
{
	public AircraftCarrier(Grid board, Position position)
	{
		super(board,position);
	}

	@Override
	public void setShipLength() {
		this.setIntactSegments(5);
	}
	
	@Override
	protected GridValue shipGridValue() {
		return GridValue.AircraftcarrierIntact;
	}
}
