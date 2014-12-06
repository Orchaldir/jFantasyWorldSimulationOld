package jfws.cp.combat;

public class Defense
{
	private String name_;

	private Attribute attribute_;
	private Skill skill_;
	private int modifier_;
	
	public Defense(String name, Attribute attribute, Skill skill, int modifier)
	{
		name_ = name;
		attribute_ = attribute;
		skill_ = skill;
		modifier_ = modifier;
	}
	
	public String getName()
	{
		return name_;
	}
	
	public int getDefenseValue(Character defender)
	{
		int attribute_level = defender.getAttributeLevel(attribute_);
		int skill_level = defender.getSkillLevel(skill_);
		
		return attribute_level + skill_level + modifier_;
	}
}
