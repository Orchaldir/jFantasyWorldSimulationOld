package jfws.cp.combat.value;

import jfws.cp.combat.Attribute;
import jfws.cp.combat.Character;
import jfws.cp.combat.Skill;

public class AttributeSkillCombo implements Value
{
	private Attribute attribute_;
	private Skill skill_;
	private int modifier_;
	
	public AttributeSkillCombo(Attribute attribute, Skill skill, int modifier)
	{
		if(attribute == null)
			throw new IllegalArgumentException("Attribute can not be null!");
		else if(skill == null)
			throw new IllegalArgumentException("Skill can not be null!");
		
		attribute_ = attribute;
		skill_ = skill;
		modifier_ = modifier;
	}
	
	@Override
	public int getValue(Character character)
	{
		int attribute_level = character.getAttributeLevel(attribute_);
		int skill_level = character.getSkillLevel(skill_);
		
		return attribute_level + skill_level + modifier_;
	}
}
