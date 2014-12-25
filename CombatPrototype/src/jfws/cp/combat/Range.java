package jfws.cp.combat;

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
	
	public int getDistance(Character character)
	{
		int range = base_ + value_.getValue(character) * multiplier_;
		
		return Math.max(range, 1);
	}
}
