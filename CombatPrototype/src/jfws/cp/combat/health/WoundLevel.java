package jfws.cp.combat.health;

public enum WoundLevel
{
	NO_WOUND(0),
	LIGHT_WOUND(1),
	MEDIUM_WOUND(2),
	HEAVY_WOUND(3);
	
	private final int level_;

	private WoundLevel(int level)
	{
		level_ = level;
	}
	
	public boolean isGreater(WoundLevel wound_level)
	{
		return level_ > wound_level.level_;
	}
	
	public boolean isLess(WoundLevel wound_level)
	{
		return level_ < wound_level.level_;
	}
	
	public int toInteger()
	{
		return level_;
	}
	
	public static WoundLevel fromInteger(int value)
	{
		if(value <= 0)
		{
			return NO_WOUND;
		}
		else if(value == 1)
		{
			return LIGHT_WOUND;
		}
		else if(value == 2)
		{
			return MEDIUM_WOUND;
		}
		else
		{
			return HEAVY_WOUND;
		}
	}
}
