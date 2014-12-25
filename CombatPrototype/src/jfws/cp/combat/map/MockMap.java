package jfws.cp.combat.map;

import jfws.cp.combat.Character;

public class MockMap implements GameMap
{
	private int distance_;
	
	public MockMap(int distance)
	{
		distance_ = distance;
	}
	
	@Override
	public int getDistance(Character a, Character b)
	{
		return distance_;
	}
}
