package jfws.cp.combat;

public class Attack
{
	private String name_;

	private Attribute attribute_;
	private Skill skill_;
	private int modifier_;
	
	private Damage damage_;
	
	public Attack(String name, Attribute attribute, Skill skill, int modifier, Damage damage)
	{
		name_ = name;
		
		attribute_ = attribute;
		skill_ = skill;
		modifier_ = modifier;
		
		damage_ = damage;
	}
	
	public String getName()
	{
		return name_;
	}
	
	public Damage getDamage()
	{
		return damage_;
	}
	
	public int getAttackValue(Character attacker)
	{
		int attribute_level = attacker.getAttributeLevel(attribute_);
		int skill_level = attacker.getSkillLevel(skill_);
		
		return attribute_level + skill_level + modifier_;
	}
	
	public static int handle(TestMgr test_mgr, Character attacker, Attack attack, Character defender, Defense defense)
	{
		int attack_value = attack.getAttackValue(attacker);
		int defense_value = defense.getDefenseValue(defender);
		
		return test_mgr.handle(attack_value, defense_value);
	}
	
	public static void handle(TestMgr test_mgr, AttackResult result)
	{
		int margin_of_success = handle(test_mgr,
			result.getAttacker(), result.getAttack(),
			result.getDefender(), result.getDefense());
		
		result.setMarginOfSuccess(margin_of_success);
	}
}
