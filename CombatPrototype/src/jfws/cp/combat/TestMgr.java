package jfws.cp.combat;

import java.util.Random;

public class TestMgr
{
	private Random random_;
	private int die_size_;
	
	public TestMgr(int die_size)
	{
		random_ = new Random();
		die_size_ = die_size;
	}
	
	public int rollDie()
	{
		return random_.nextInt(die_size_) + 1;
	}
	
	public int handle(int value, int opposed_value)
	{
		return value + rollDie() - (opposed_value + rollDie());
	}
}
