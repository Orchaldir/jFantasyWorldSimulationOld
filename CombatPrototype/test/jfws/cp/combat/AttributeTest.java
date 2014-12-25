
package jfws.cp.combat;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AttributeTest
{
	static private String name_ = "Skill";
	static private int id_ = 42;
	static private Attribute attribute_;
	
	@Before
	public void setUp()
	{
		attribute_ = new Attribute(name_, id_);
	}

	@Test
	public void testGetName()
	{
		assertEquals(name_, attribute_.getName());
	}

	@Test
	public void testGetId()
	{
		assertEquals(id_, attribute_.getId());
	}
}