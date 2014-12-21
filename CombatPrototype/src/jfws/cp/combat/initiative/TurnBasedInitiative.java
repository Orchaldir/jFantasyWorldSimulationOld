package jfws.cp.combat.initiative;

import java.util.LinkedList;
import java.util.Queue;
import jfws.cp.combat.Character;

public class TurnBasedInitiative
{
	private Queue<Character> order_ = new LinkedList<>();
	
	public void add(Character character)
	{
		if(character == null)
			throw new IllegalArgumentException("Character can not be null!");
		
		order_.add(character);
	}
	
	public void remove(Character character)
	{
		order_.remove(character);
	}
	
	public Character getCurrent()
	{
		return order_.peek();
	}
	
	public void update(Character character)
	{
		if(order_.remove(character))
			order_.add(character);
	}
}
