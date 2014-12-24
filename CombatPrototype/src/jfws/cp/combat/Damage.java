package jfws.cp.combat;

import jfws.cp.combat.value.Value;

public class Damage
{
	private Value value_;
	
	public Damage(Value value)
	{
		value_ = value;
	}
	
	public int getBaseDamage(Character attacker)
	{
		return value_.getValue(attacker);
	}
	
	public int getDamage(Character attacker, int margin_of_success)
	{
		return getBaseDamage(attacker) + margin_of_success;
	}
	
	public static void handle(AttackResult result)
	{
		Damage damage = result.getAttack().getDamage();
		int damage_value = damage.getDamage(result.getAttacker(), result.getMarginOfSuccess());
		
		result.setDamage(damage_value);
	}
}
