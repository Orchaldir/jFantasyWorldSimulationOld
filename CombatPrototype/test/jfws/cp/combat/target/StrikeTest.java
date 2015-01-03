package jfws.cp.combat.target;

import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.MockMap;
import jfws.cp.combat.map.Pose;
import org.junit.Test;
import static org.junit.Assert.*;

public class StrikeTest
{
	private Character character0_ = new Character("Character0", 1);
	private Character character1_ = new Character("Character1", 1);
	private MockMap inside_ = new MockMap(1, character0_);
	private MockMap outside_ = new MockMap(10);
	private Pose pose_ = new Pose(1);
	private Strike strike_ = new Strike(4);
	
	@Test
	public void testCanTargetUser()
	{
		assertTrue(strike_.canTargetUser(character0_, inside_));
	}
	
	@Test
	public void testCanTargetCharacterWithUser()
	{
		assertTrue(strike_.canTargetCharacter(character0_, character0_, inside_));
	}
	
	@Test
	public void testCanTargetCharacter()
	{
		assertTrue(strike_.canTargetCharacter(character0_, character1_, inside_));
	}
	
	@Test
	public void testCanTargetCharacterTooFar()
	{
		assertFalse(strike_.canTargetCharacter(character0_, character1_, outside_));
	}
	
	@Test
	public void testCanTargetPosition()
	{
		assertTrue(strike_.canTargetPosition(character0_, pose_, inside_));
	}
	
	@Test
	public void testCanTargePositionTooFar()
	{
		assertFalse(strike_.canTargetPosition(character0_, pose_, outside_));
	}
	
	@Test
	public void testTargetUser()
	{
		List<Character> targets = strike_.targetUser(character0_, inside_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}
	
	@Test
	public void testTargetCharacterWithUser()
	{
		List<Character> targets = strike_.targetCharacter(character0_, character0_, inside_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}
	
	@Test
	public void testTargetCharacter()
	{
		List<Character> targets = strike_.targetCharacter(character0_, character1_, inside_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character1_, targets.get(0));
	}
	
	@Test
	public void testTargetCharacterTooFar()
	{
		List<Character> targets = strike_.targetCharacter(character0_, character1_, outside_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
	
	@Test
	public void testTargetPosition()
	{
		inside_.setCharacter(character1_, 0);
		List<Character> targets = strike_.targetPosition(character0_, pose_, inside_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character1_, targets.get(0));
	}
	
	@Test
	public void testTargetPositionWithUser()
	{
		inside_.setCharacter(character0_, 0);
		List<Character> targets = strike_.targetPosition(character0_, pose_, inside_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}
	
	@Test
	public void testTargetPositionTooFar()
	{
		List<Character> targets = strike_.targetPosition(character0_, pose_, outside_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
}