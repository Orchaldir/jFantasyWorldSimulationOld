package jfws.cp.combat.health;

public enum WoundLevel
{
	NO_WOUND,
	LIGHT_WOUND,
	MEDIUM_WOUND,
	HEAVY_WOUND;
	
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
