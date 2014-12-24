package jfws.cp.combat.value;

import jfws.cp.combat.Character;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConstantValueTest
{
	static private Character character_ = new Character("Character0", 1);
	
	@Test
	public void testGetValue()
	{
		int value = 9;
		ConstantValue constant = new ConstantValue(value);
		
		assertEquals(value, constant.getValue(character_));
	}
}
