package jfws.cp.combat.health;

public class Wound
{
	WoundLevel level_;
	
	public Wound(WoundLevel level)
	{
		level_ = level;
	}
	
	public WoundLevel getLevel()
	{
		return level_;
	}
}
