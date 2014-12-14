package jfws.cp.combat;

public class Range
{
	private int base_;
	private Attribute attribute_;
	private int multiplier_;
	
	public Range(int base, Attribute attribute, int multiplier)
	{
		base_ = base;
		attribute_ = attribute;
		multiplier_ = multiplier;
	}
	
	public int getRange(Character character)
	{
		int range = base_;
		
		if(attribute_ != null)
			range += character.getAttributeLevel(attribute_) * multiplier_;
		
		return Math.max(range, 1);
	}
}
