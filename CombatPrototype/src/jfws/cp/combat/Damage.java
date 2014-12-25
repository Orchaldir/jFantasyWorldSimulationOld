package jfws.cp.combat;

import jfws.cp.combat.value.Value;

public class Damage
{
	private Value value_;
	
	public Damage(Value value)
	{
		if(value == null)
			throw new IllegalArgumentException("Value can not be null!");
		
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
	
	public void handle(AttackResult result)
	{
		int damage_value = getDamage(result.getAttacker(), result.getMarginOfSuccess());
		
		result.setDamage(damage_value);
	}
}
