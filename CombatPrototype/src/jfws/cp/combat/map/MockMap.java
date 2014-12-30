package jfws.cp.combat.map;

import java.util.ArrayList;
import java.util.Collection;
import jfws.cp.combat.Character;

public class MockMap implements GameMap
{
	private int distance_;
	private Character character_;
	
	public MockMap(int distance, Character character)
	{
		distance_ = distance;
		character_ = character;
	}
	
	public MockMap(int distance)
	{
		distance_ = distance;
	}
	
	@Override
	public Collection<Character> getCharacters()
	{
		return new ArrayList<>(1);
	}
	
	// characters
	
	@Override
	public Character getCharacter(Pose pose)
	{
		return character_;
	}
	
	@Override
	public boolean setCharacter(Character character, int index)
	{
		return false;
	}
	
	@Override
	public void removeCharacter(Character character)
	{
		
	}
	
	// checks
	
	@Override
	public boolean isValid(int index)
	{
		return false;
	}

	@Override
	public boolean isFree(int index)
	{
		return false;
	}
	
	// distance
	
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
	
	// index
	
	@Override
	public int getNeighborIndex(int index, Direction dir)
	{
		return 0;
	}
	
	// pose
	
	@Override
	public Pose getPose(Character character)
	{
		return null;
	}
}
