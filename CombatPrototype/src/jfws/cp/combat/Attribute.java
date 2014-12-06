package jfws.cp.combat;

public class Attribute
{
	private String name_;
	private int id_;
	
	public Attribute(String name, int id)
	{
		name_ = name;
		id_ = id;
	}

	public String getName()
	{
		return name_;
	}
	
	public int getId()
	{
		return id_;
	}
}
