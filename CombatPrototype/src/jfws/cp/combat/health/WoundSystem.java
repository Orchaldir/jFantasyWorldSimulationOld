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
	
	public void handle(AttackResult result)
	{
		int protection = getProtectionValue(result.getDefender());
		int penetrating_damage = result.getDamage() - protection;
		
		result.setPenetratingDamage(penetrating_damage);
	}
}
