package jfws.cp.combat;

public class AttackResult
{
	private Character attacker_;
	private Attack attack_;
	
	private Character defender_;
	private Defense defense_;
	
	private int margin_of_success_;

	public AttackResult(Character attacker, Attack attack, Character defender, Defense defense, int margin_of_success)
	{
		attacker_ = attacker;
		attack_ = attack;
		
		defender_ = defender;
		defense_ = defense;
		
		margin_of_success_ = margin_of_success;
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
	
	public boolean hasHit()
	{
		return margin_of_success_ >= 0;
	}
}
