package jfws.cp.combat;

import jfws.cp.combat.map.GameMap;
import jfws.cp.combat.map.Pose;
import jfws.cp.combat.value.ConstantValue;
import jfws.cp.combat.value.Value;

public class Range
{
	private int base_;
	private Value value_;
	private int multiplier_;
	
	public Range(int base, Value value, int multiplier)
	{
		if(value == null)
			throw new IllegalArgumentException("Value can not be null!");
		
		base_ = base;
		value_ = value;
		multiplier_ = multiplier;
	}
	
	public Range(int base, Value value)
	{
		this(base, value, 0);
	}
	
	public Range(int base)
	{
		this(base, new ConstantValue(0), 0);
	}
	
	public int getMaxRange(Character character)
	{
		int range = base_ + value_.getValue(character) * multiplier_;
		
		return Math.max(range, 1);
	}
	
	public boolean isInside(Character user, Character target, GameMap map)
	{
		int range = map.getDistance(user, target);
		
		return isInside(user, range);
	}
	
	public boolean isInside(Character user, Pose center, Character target, GameMap map)
	{
		int range = map.getDistance(target, center);
		
		return isInside(user, range);
	}
	
	public boolean isInside(Character user, int range)
	{
		int max_range = getMaxRange(user);
		
		return range <= max_range;
	}
}
