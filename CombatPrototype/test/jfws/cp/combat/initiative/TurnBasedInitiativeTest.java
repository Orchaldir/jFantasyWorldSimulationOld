
package jfws.cp.combat.initiative;

import jfws.cp.combat.Character;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TurnBasedInitiativeTest
{
	private Character character0_ = new Character("Character0", 1);
	private Character character1_ = new Character("Character1", 1);
	private Character character2_ = new Character("Character2", 1);
	
	private TurnBasedInitiative initiative_;
	
	@Before
	public void setUp()
	{
		initiative_ = new TurnBasedInitiative();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddNull()
	{
		initiative_.add(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddTwice()
	{
		initiative_.add(character0_);
		initiative_.add(character0_);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveNull()
	{
		initiative_.remove(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveNotAdded()
	{
		initiative_.remove(character0_);
	}
	
	@Test
	public void testGetCurrentBeforeAdding()
	{
		assertEquals(null, initiative_.getCurrent());
	}
	
	@Test
	public void testOrder()
	{
		initiative_.add(character0_);
		assertEquals(character0_, initiative_.getCurrent());
		
		initiative_.add(character1_);
		assertEquals(character0_, initiative_.getCurrent());
		
		initiative_.add(character2_);
		assertEquals(character0_, initiative_.getCurrent());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUpdateNull()
	{
		initiative_.add(character0_);
		initiative_.update(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUpdatWrongCharacter()
	{
		initiative_.add(character0_);
		initiative_.add(character1_);
		initiative_.update(character1_);
	}
	
	@Test
	public void testUpdate()
	{
		initiative_.add(character0_);
		initiative_.add(character1_);
		initiative_.add(character2_);
		
		initiative_.update(character0_);
		assertEquals(character1_, initiative_.getCurrent());
		
		initiative_.update(character1_);
		assertEquals(character2_, initiative_.getCurrent());
		
		initiative_.update(character2_);
		assertEquals(character0_, initiative_.getCurrent());
	}
}