package jfws.cp.combat;

public class Attack
{
	private String name_;

	private Skill skill_;
	private int modifier_;
	
	public Attack(String name, Skill skill, int modifier)
	{
		name_ = name;
		skill_ = skill;
		modifier_ = modifier;
	}
	
	public String getName()
	{
		return name_;
	}
	
	public int getAttackValue(Character attacker)
	{
		return attacker.getSkillLevel(skill_) + modifier_;
	}
}
