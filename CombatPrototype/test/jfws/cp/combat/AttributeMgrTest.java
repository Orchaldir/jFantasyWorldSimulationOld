package jfws.cp.combat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AttributeMgrTest
{
	static private String name0_ = "Attribute0";
	static private String name1_ = "Attribute1";
	static private AttributeMgr attribute_mgr_;
	
	@Before
	public void setUp()
	{
		attribute_mgr_ = new AttributeMgr();
	}

	@Test
	public void testCreateAttribute()
	{
		Attribute attribute0 = attribute_mgr_.createAttribute(name0_);
		
		assertNotNull(attribute0);
		assertEquals(name0_, attribute0.getName());
		assertEquals(0, attribute0.getId());
		
		Attribute attribute1 = attribute_mgr_.createAttribute(name1_);
		
		assertNotNull(attribute1);
		assertEquals(name1_, attribute1.getName());
		assertEquals(1, attribute1.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateAttributeWithNull()
	{
		attribute_mgr_.createAttribute(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateSkillWithEmptyString()
	{
		attribute_mgr_.createAttribute("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateSkillTwice()
	{
		attribute_mgr_.createAttribute(name0_);
		attribute_mgr_.createAttribute(name0_);
	}

	@Test
	public void testGetAttribute()
	{
		Attribute attribute0 = attribute_mgr_.createAttribute(name0_);
		Attribute attribute1 = attribute_mgr_.createAttribute(name1_);
		
		assertEquals(attribute0, attribute_mgr_.getAttribute(name0_));
		assertEquals(attribute1, attribute_mgr_.getAttribute(name1_));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetAttributeWithNull()
	{
		attribute_mgr_.getAttribute(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetAttributeWithEmptyString()
	{
		attribute_mgr_.getAttribute("");
	}
	
	@Test
	public void testGetNumberOfAttributes()
	{
		assertEquals(0, attribute_mgr_.getNumberOfAttributes());
		
		attribute_mgr_.createAttribute(name0_);
		
		assertEquals(1, attribute_mgr_.getNumberOfAttributes());
		
		attribute_mgr_.createAttribute(name1_);
		
		assertEquals(2, attribute_mgr_.getNumberOfAttributes());
	}
}
