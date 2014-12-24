package jfws.cp.combat.value;

import jfws.cp.combat.Character;

public class ConstantValue implements Value
{
	private int value_;
	
	public ConstantValue(int value)
	{
		value_ = value;
	}
	
	@Override
	public int getValue(Character character)
	{
		return value_;
	}
}
