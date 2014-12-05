package jfws.cp.combat;

public class Defense
{
	private String name_;

	private Skill skill_;
	private int modifier_;
	
	public Defense(String name, Skill skill, int modifier)
	{
		name_ = name;
		skill_ = skill;
		modifier_ = modifier;
	}
	
	public String getName()
	{
		return name_;
	}
	
	public int getDefenseValue(Character defender)
	{
		return defender.getSkillLevel(skill_) + modifier_;
	}
}
