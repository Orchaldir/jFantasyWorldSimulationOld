package jfws.cp.combat.health;

import jfws.cp.combat.AttackResult;
import jfws.cp.combat.Attribute;
import jfws.cp.combat.Character;

public class WoundSystem
{
	private Attribute toughness_;
	private int penalty_per_level_;
	
	public WoundSystem(Attribute toughness, int penalty_per_level)
	{
		toughness_ = toughness;
		penalty_per_level_ = penalty_per_level;
	}
	
	public int getProtectionValue(Character defender)
	{
		int value = defender.getAttributeLevel(toughness_);
		
		value += defender.getProtectionValue();
		
		int max_wound_level = defender.getHealthComponent().getWoundLevel().toInteger();
		
		value -= penalty_per_level_ * max_wound_level;
		
		return value;
	}
	
	public int getPenetratingDamage(Character defender, int damage)
	{
		return damage - getProtectionValue(defender);
	}
	
	public WoundLevel getWoundLevel(Character defender, int penetrating_damage)
	{
		int points_per_level = Math.max(defender.getAttributeLevel(toughness_), 1);
		int wound_level = (int)Math.ceil((double)penetrating_damage / points_per_level);
		
		return WoundLevel.fromInteger(wound_level);
	}
	
	public Wound getWound(Character defender, int penetrating_damage)
	{
		WoundLevel wound_level = getWoundLevel(defender, penetrating_damage);
		
		return new Wound(wound_level);
	}
	
	public void handle(AttackResult result)
	{
		Character defender = result.getDefender();
		
		int penetrating_damage = getPenetratingDamage(defender, result.getDamage());
		Wound wound = getWound(defender, penetrating_damage);
		
		result.setPenetratingDamage(penetrating_damage);
		result.setWound(wound);
		
		defender.getHealthComponent().applyDamage(result);
	}
}
