package jfws.cp.combat;

public class Skill
{
	private String name_;
	private int default_level_;

	public Skill(String name, int default_level)
	{
		name_ = name;
		default_level_ = default_level;
	}

	public String getName()
	{
		return name_;
	}

	public int getDefaultLevel()
	{
		return default_level_;
	}
}
