package jfws.cp.combat.value;

import jfws.cp.combat.Attribute;
import jfws.cp.combat.Character;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class AttributeValueTest
{
	static private Attribute attribute_ = new Attribute("Attribute0", 0);
	static private int modifier_ = 4;
	static private Character character_ = new Character("Character0", 1);
	
	@BeforeClass
	public static void setUpClass()
	{
		character_.setAttributeLevel(attribute_, 1);
	}

	@Test
	public void testGetValue()
	{
		AttributeValue value = new AttributeValue(attribute_, modifier_);
		
		assertEquals(5, value.getValue(character_));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAttributeIsNull()
	{
		AttributeValue value = new AttributeValue(null, modifier_);
	}
}
