package jfws.cp.combat;

import jfws.cp.combat.health.Wound;

public class AttackResult
{
	private Character attacker_;
	private Attack attack_;
	
	private Character defender_;
	private Defense defense_;
	
	private int margin_of_success_ = -1;
	
	private int damage_;
	private int penetrating_damage_;
	
	private Wound wound_;

	public AttackResult(Character attacker, Attack attack, Character defender, Defense defense)
	{
		attacker_ = attacker;
		attack_ = attack;
		
		defender_ = defender;
		defense_ = defense;
	}

	public Character getAttacker()
	{
		return attacker_;
	}

	public Attack getAttack()
	{
		return attack_;
	}

	public Character getDefender()
	{
		return defender_;
	}

	public Defense getDefense()
	{
		return defense_;
	}

	public int getMarginOfSuccess()
	{
		return margin_of_success_;
	}
	
	public void setMarginOfSuccess(int margin_of_success)
	{
		margin_of_success_ = margin_of_success;
	}
	
	public boolean hasHit()
	{
		return margin_of_success_ >= 0;
	}
	
	public int getDamage()
	{
		return damage_;
	}
	
	public void setDamage(int damage)
	{
		damage_ = damage;
	}
	
	public int getPenetratingDamage()
	{
		return penetrating_damage_;
	}
	
	public void setPenetratingDamage(int penetrating_damage)
	{
		penetrating_damage_ = penetrating_damage;
	}
	
	public Wound getWound()
	{
		return wound_;
	}
	
	public void setWound(Wound wound)
	{
		wound_ = wound;
	}
	
	public void print()
	{
		System.out.println(attacker_.getName()+ "'s " +
				attack_.getName() + " VS " +
				defender_.getName()+ "'s " +
				defense_.getName());

		if(hasHit())
		{
			System.out.println("Attack: " + margin_of_success_ + " -> Hit");
		}
		else
		{
			System.out.println("Attack: " + margin_of_success_ + " -> Miss");
			return;
		}

		System.out.print("Damage: " + damage_);
		System.out.print(" -> " + penetrating_damage_);
		System.out.println(" -> " + wound_.getLevel());

		System.out.println("Status: " + defender_.getHealthComponent().getWoundLevel());
	}
}
