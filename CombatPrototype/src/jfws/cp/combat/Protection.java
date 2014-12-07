package jfws.cp.combat;

public class Protection
{
	private String name_;
	private int value_;
	
	public Protection(String name, int value)
	{
		name_ = name;
		value_ = value;
	}

	public String getName()
	{
		return name_;
	}

	public int getProtectionValue()
	{
		return value_;
	}
}
