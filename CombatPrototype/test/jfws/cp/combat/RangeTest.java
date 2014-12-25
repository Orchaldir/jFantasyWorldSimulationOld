
package jfws.cp.combat;

import jfws.cp.combat.map.MockMap;
import jfws.cp.combat.value.ConstantValue;
import jfws.cp.combat.value.Value;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class RangeTest
{
	static private Character character0_ = new Character("Character0", 1);
	static private Character character1_ = new Character("Character1", 1);
	
	static private int base_ = 100;
	static private Value value_ = new ConstantValue(2);
	static private int multiplier_ = 10;
	static private int distance_ = 120;
	static private Range range_;
	
	static private MockMap inside_ = new MockMap(distance_ - 10);
	static private MockMap outside_ = new MockMap(distance_ + 10);
	
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
		assertEquals(distance_, range_.getDistance(character0_));
	}
	
	@Test
	public void testIsInside()
	{
		assertTrue(range_.isInside(character0_, character1_, inside_));
		assertFalse(range_.isInside(character0_, character1_, outside_));
	}
}