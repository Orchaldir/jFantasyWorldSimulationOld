package jfws.cp.combat;

import jfws.cp.combat.value.Value;

public class Defense
{
	private String name_;

	private Value value_;
	
	public Defense(String name, Value value)
	{
		name_ = name;
		value_ = value;
	}
	
	public String getName()
	{
		return name_;
	}
	
	public int getDefenseValue(Character defender)
	{
		return value_.getValue(defender);
	}
}
