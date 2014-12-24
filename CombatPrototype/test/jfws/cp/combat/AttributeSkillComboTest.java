package jfws.cp.combat;

import jfws.cp.combat.value.AttributeSkillCombo;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AttributeSkillComboTest
{
	static private Attribute attribute_ = new Attribute("Attribute0", 0);
	static private Skill skill_ = new Skill("Skill0", 0);
	static private int modifier_ = 4;
	static private Character character_ = new Character("Character0", 1);
	
	@BeforeClass
	public static void setUpClass()
	{
		character_.setAttributeLevel(attribute_, 1);
		character_.setSkillLevel(skill_, 2);
	}

	@Test
	public void testGetValue()
	{
		AttributeSkillCombo combo = new AttributeSkillCombo(attribute_, skill_, modifier_);
		
		assertEquals(7, combo.getValue(character_));
	}
	
}
