package jfws.cp.combat.health;

import jfws.cp.combat.AttackResult;

public class WoundComponent
{
	private Wound wound_ = new Wound(WoundLevel.NO_WOUND);
	
	public void addWound(Wound wound)
	{
		if(wound.isGreater(wound_))
		{
			wound_ = wound;
		}
	}
	
	public WoundLevel getWoundLevel()
	{
		return wound_.getLevel();
	}
	
	public boolean isAlive()
	{
		return wound_.isLess(WoundLevel.HEAVY_WOUND);
	}
	
	public boolean isDead()
	{
		return !isAlive();
	}
	
	public void applyDamage(AttackResult result)
	{
		addWound(result.getWound());
	}
}
