package jfws.cp.combat;

public class Damage
{
	private Attribute attribute_;
	private int modifier_;
	
	public Damage(Attribute attribute, int modifier)
	{
		attribute_ = attribute;
		modifier_ = modifier;
	}
	
	public int getBaseDamage(Character attacker)
	{
		if(attribute_ != null)
			return attacker.getAttributeLevel(attribute_) + modifier_;
		else
			return modifier_;
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
