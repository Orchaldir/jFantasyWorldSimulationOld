package jfws.cp.combat.health;

public class Wound
{
	private WoundLevel level_;
	
	public Wound(WoundLevel level)
	{
		level_ = level;
	}
	
	public WoundLevel getLevel()
	{
		return level_;
	}
	
	public boolean isGreater(Wound wound)
	{
		return level_.isGreater(wound.level_);
	}
	
	public boolean isGreater(WoundLevel level)
	{
		return level_.isGreater(level);
	}
	
	public boolean isLess(Wound wound)
	{
		return level_.isLess(wound.level_);
	}
	
	public boolean isLess(WoundLevel level)
	{
		return level_.isLess(level);
	}
}
