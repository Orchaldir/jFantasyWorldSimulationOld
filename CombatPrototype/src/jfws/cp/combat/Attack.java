package jfws.cp.combat;

import jfws.cp.combat.map.GameMap;
import jfws.cp.combat.value.Value;

public class Attack
{
	private String name_;

	private Value value_;
	
	private Damage damage_;
	
	private Range range_;
	
	public Attack(String name, Value value, Damage damage, Range range)
	{
		name_ = name;
		value_ = value;
		damage_ = damage;
		range_ = range;
	}
	
	public String getName()
	{
		return name_;
	}
	
	public Damage getDamage()
	{
		return damage_;
	}
	
	public Range getRange()
	{
		return range_;
	}
	
	public int getAttackValue(Character character)
	{
		return value_.getValue(character);
	}
	
	public boolean isPossible(AttackResult result, GameMap map)
	{
		if(!range_.isInside(result.getAttacker(), result.getDefender(), map))
		{
			System.err.println(result.getDefender().getName()+ " is out of range!");
			return false;
		}
		
		return true;
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
