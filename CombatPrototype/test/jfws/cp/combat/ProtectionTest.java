package jfws.cp.combat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProtectionTest
{
	private String name_ = "ProtectionTest";
	private int value_ = 3;
	private Protection protection_;
	
	@Before
	public void setUp()
	{
		protection_ = new Protection(name_, value_);
	}

	@Test
	public void testGetName()
	{
		assertEquals(name_, protection_.getName());
	}

	@Test
	public void testGetProtectionValue()
	{
		assertEquals(value_, protection_.getProtectionValue());
	}
}
