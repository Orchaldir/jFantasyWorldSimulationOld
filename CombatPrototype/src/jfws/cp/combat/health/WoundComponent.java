package jfws.cp.combat.health;

public class WoundComponent
{
	private Wound wound_;
	
	public void addWound(Wound wound)
	{
		if(wound_ == null || wound.isGreater(wound_))
		{
			wound_ = wound;
		}
	}
	
	public WoundLevel getHighestWoundLevel()
	{
		if(wound_ == null)
			return WoundLevel.NO_WOUND;
		
		return wound_.getLevel();
	}
	
	public boolean isAlive()
	{
		if(wound_ == null)
			return true;
		
		return wound_.isLess(WoundLevel.HEAVY_WOUND);
	}
}
