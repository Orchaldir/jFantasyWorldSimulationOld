package jfws.cp.combat.health;

import jfws.cp.combat.AttackResult;
import jfws.cp.combat.Attribute;
import jfws.cp.combat.Character;

public class WoundSystem
{
	private Attribute toughness_;
	
	public WoundSystem(Attribute toughness)
	{
		toughness_ = toughness;
	}
	
	public int getProtectionValue(Character defender)
	{
		int value = defender.getAttributeLevel(toughness_);
		
		value += defender.getProtectionValue();
		
		return value;
	}
	
	public WoundLevel getWoundLevel(Character defender, int penetrating_damage)
	{
		int points_per_level = Math.max(defender.getAttributeLevel(toughness_), 1);
		int wound_level = (int)Math.ceil((double)penetrating_damage / points_per_level);
		
		return WoundLevel.fromInteger(wound_level);
	}
	
	public void handle(AttackResult result)
	{
		int protection = getProtectionValue(result.getDefender());
		int penetrating_damage = result.getDamage() - protection;
		
		result.setPenetratingDamage(penetrating_damage);
		
		WoundLevel wound_level = getWoundLevel(result.getDefender(), penetrating_damage);
		Wound wound = new Wound(wound_level);
		
		result.setWound(wound);
	}
}
