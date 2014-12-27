package jfws.cp.combat.map;

import java.util.ArrayList;
import java.util.Collection;
import jfws.cp.combat.Character;

public class MockMap implements GameMap
{
	private int distance_;
	
	public MockMap(int distance)
	{
		distance_ = distance;
	}
	
	@Override
	public Collection<Character> getCharacters()
	{
		return new ArrayList<>(1);
	}
	
	@Override
	public int getDistance(Character a, Character b)
	{
		return distance_;
	}
	
	@Override
	public int getDistance(Character a, Pose b)
	{
		return distance_;
	}
	
	@Override
	public int getDistance(Pose a, Pose b)
	{
		return distance_;
	}
	
	@Override
	public Pose getPose(Character character)
	{
		return null;
	}
}
