package Battleships.Behaviors;

import Battleships.Grid;
import Battleships.InfluenceMap;
import Battleships.NumberGenerator;

public class OneHotspotBehavior extends ShooterBehavior {

	@Override
	public void nextShot(InfluenceMap m1, Grid Attackgrid) {
		int checki = m1.getHotspotI();
		int checkj = m1.getHotspotJ();
		
//		if(Attackgrid.getGridVal(checki, checkj) !=0)
//		{
//			m1.set(checki, checkj, 0);
//			i= checki;
//			j= checkj;
//		}
		
		// if the element on the attack grid has not been hit then set i,j to it.
		if(Attackgrid.getGridVal(checki, checkj) != 0) {
			m1.set(checki, checkj, 0);
			NumberGenerator Powergen = new NumberGenerator();
			
			boolean empty =false;
			//create random numbers
			while(!empty)
			{
				checki = Powergen.rand(10);
				checkj = Powergen.rand(10);
				//if co-ord is empty then set i,j to them
				if(Attackgrid.getGridVal(checki, checkj) ==0)
				{
					empty = true;
				}
					
			}
		}
		
		i= checki;
		j= checkj;
	}
}
