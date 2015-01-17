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
		else if(order_.contains(character))
			throw new IllegalArgumentException("Character already added!");
		
		order_.add(character);
	}
	
	public void remove(Character character)
	{
		if(character == null)
			throw new IllegalArgumentException("Character can not be null!");
		else if(!order_.contains(character))
			throw new IllegalArgumentException("Character not contained!");
		
		order_.remove(character);
	}
	
	public Character getCurrent()
	{
		return order_.peek();
	}
	
	public void update(Character character)
	{
		if(character == null)
			throw new IllegalArgumentException("Character can not be null!");
		else if(character != getCurrent())
			throw new IllegalArgumentException("Only the current Character can be updated!");
		
		if(order_.remove(character))
			order_.add(character);
		else
			throw new IllegalArgumentException("Character not found!");
	}
}
