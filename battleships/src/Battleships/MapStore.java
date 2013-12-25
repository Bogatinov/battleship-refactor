package Battleships;
/*
 * Author: Michael
 * Created: 06 March 2005 13:52:20
 * Modified: 06 March 2005 13:52:20
 */

import java.util.ArrayList;


public class MapStore
{
	private ArrayList<InfluenceMap> store;
	
	/**
		Constructs an empty InfluenceMap store
	*/
	public MapStore()
	{
		store = new ArrayList<InfluenceMap>();
	}
	/**
		Adds a InfluenceMap into the InfluenceMap store
	*/
	public void add(InfluenceMap IM)
	{
		store.add(IM);
	}
	
	/**Returns the map in the arraylist at the specified element if it exists
	@throws IllegalArgumentException if the parameter is outside the arrayList boundary*/
	public InfluenceMap getMap(int i)
	{
		int num = store.size();
		
		if(i<0 || i > num)
			throw new IllegalArgumentException();
		else 
			return store.get(i);
	}
	
	public InfluenceMap getBestMap()
	{
					
			int turns = 9999;
			InfluenceMap current = null;
			InfluenceMap best = null;
			for(int i= 0; i<store.size(); i++)
			{
				current = store.get(i);
				if(current.getTurns() < turns)
				{
					turns = current.getTurns();
					best = current; 
				}
			
				current = null;
			}
		
		return best;
		
		
	}
	
	/**
		Returns the number of InfluenceMap objects in the store
	*/
	public int size()
	{
		return store.size();
	}

}