
package jfws.cp.combat;

import jfws.cp.combat.value.ConstantValue;
import jfws.cp.combat.value.Value;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class RangeTest
{
	static private Character character_ = new Character("Character0", 1);
	static private int base_ = 100;
	static private Value value_ = new ConstantValue(2);
	static private int multiplier_ = 10;
	static private int distance = 120;
	static private Range range_;
	
	@Before
	public void setUp()
	{
		range_ = new Range(base_, value_, multiplier_);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithNull()
	{
		new Range(base_, null, multiplier_);
	}
	
	@Test
	public void testGetDistance()
	{
		assertEquals(distance, range_.getDistance(character_));
	}
}