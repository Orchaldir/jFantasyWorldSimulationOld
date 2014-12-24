package jfws.cp.combat;

import org.junit.Test;
import static org.junit.Assert.*;

public class SkillTest
{
	static private Skill skill_ = new Skill("Name", 42);

	@Test
	public void testGetName()
	{
		assertEquals("Name", skill_.getName());
	}

	@Test
	public void testGetDefaultLevel()
	{
		assertEquals(42, skill_.getDefaultLevel());
	}
}
