package jfws.cp.combat.value;

import jfws.cp.combat.Attribute;
import jfws.cp.combat.Character;

public class AttributeValue implements Value
{
	private Attribute attribute_;
	private int modifier_;
	
	public AttributeValue(Attribute attribute, int modifier)
	{
		if(attribute == null)
			throw new IllegalArgumentException("Attribute can not be null!");
		
		attribute_ = attribute;
		modifier_ = modifier;
	}
	
	public AttributeValue(Attribute attribute)
	{
		this(attribute, 0);
	}
	
	@Override
	public int getValue(Character character)
	{
		int attribute_level = character.getAttributeLevel(attribute_);
		
		return attribute_level + modifier_;
	}
}
