package jfws.cp.combat;

import jfws.cp.combat.value.ConstantValue;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SkillTest
{
	static private String name_ = "Skill";
	static private int level_ = 9;
	static private Skill skill_;
	
	@Before
	public void setUp()
	{
		skill_ = new Skill(name_, level_);
	}

	@Test
	public void testGetName()
	{
		assertEquals(name_, skill_.getName());
	}

	@Test
	public void testGetDefaultLevel()
	{
		assertEquals(level_, skill_.getDefaultLevel());
	}
}
