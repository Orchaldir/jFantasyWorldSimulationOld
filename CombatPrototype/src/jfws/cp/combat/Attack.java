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
	
	public static AttackResult handle(TestMgr test_mgr, Character attacker, Attack attack, Character defender, Defense defense)
	{
		int attack_value = attack.getAttackValue(attacker);
		int defense_value = defense.getDefenseValue(defender);
		
		int margin_of_success = test_mgr.handle(attack_value, defense_value);
		
		return new AttackResult(attacker, attack, defender, defense, margin_of_success);
	}
}
