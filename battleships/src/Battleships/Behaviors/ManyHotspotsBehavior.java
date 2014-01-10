package Battleships.Behaviors;

import Battleships.Grid;
import Battleships.InfluenceMap;
import Enums.GridValue;

public class ManyHotspotsBehavior extends ShooterBehavior {
	private int []refs;
	@Override
	public void nextShot(InfluenceMap m1, Grid Attackgrid) {
		System.out.println("Target multiple hotspots");
		refs = m1.getIntHotspots();

		if(Attackgrid.getGridVal(refs[0],refs[1]) == GridValue.EmptyCellValue)	
		{
			i=refs[0];
			j=refs[1];
		}
		else 
		{
			int loop = 0;
			while(Attackgrid.isValidPlaceForAShip(i,j))
			{
				if(this.NothingFound(loop)) {
					break;
				}
				this.searchThroughRefs();
				loop++;
			}

		}

		this.Dispose();
	}

	private void Dispose() {
		int length = refs.length-2;

		for (int z= 0; z < length; z++)
		{
			refs[z] = refs[z+2];
		}
	}

	private boolean NothingFound(int loop) {
		return loop == 100;
	}

	private void searchThroughRefs() {
		for (int q= 2; q < refs.length-1; q++)
		{
			i=refs[q];
			j=refs[q+1];

		}
	}

}
