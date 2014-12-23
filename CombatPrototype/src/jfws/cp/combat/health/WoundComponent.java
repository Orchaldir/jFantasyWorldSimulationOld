package jfws.cp.combat.health;

import jfws.cp.combat.AttackResult;

public class WoundComponent implements HealthComponent
{
	private Wound wound_ = new Wound(WoundLevel.NO_WOUND);
	
	public void addWound(Wound wound)
	{
		if(wound.isGreater(wound_))
		{
			wound_ = wound;
		}
	}
	
	@Override
	public WoundLevel getWoundLevel()
	{
		return wound_.getLevel();
	}
	
	@Override
	public boolean isAlive()
	{
		return wound_.isLess(WoundLevel.HEAVY_WOUND);
	}
	
	@Override
	public boolean isDead()
	{
		return !isAlive();
	}
	
	@Override
	public void applyDamage(AttackResult result)
	{
		addWound(result.getWound());
	}
}
