package jfws.cp.combat.target;

import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.MockMap;
import jfws.cp.combat.map.Pose;
import org.junit.Test;
import static org.junit.Assert.*;

public class SelfTest
{
	private MockMap map_ = new MockMap(10);
	private Pose pose_ = new Pose(1);
	private Character character0_ = new Character("Character0", 1);
	private Character character1_ = new Character("Character1", 1);
	private Self self_ = new Self();
	
	@Test
	public void testCanTargetUser()
	{
		assertTrue(self_.canTargetUser(character0_, map_));
	}

	@Test
	public void testCanTargetCharacter()
	{
		assertTrue(self_.canTargetCharacter(character0_, character0_, map_));
	}
	
	@Test
	public void testCanTargetInvalidCharacter()
	{
		assertFalse(self_.canTargetCharacter(character0_, character1_, map_));
	}
	
	@Test
	public void testCanTargetPosition()
	{
		assertFalse(self_.canTargetPosition(character0_, pose_, map_));
	}
	
	@Test
	public void testTargetUser()
	{
		List<Character> targets = self_.targetUser(character0_, map_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}

	@Test
	public void testTargetCharacter()
	{
		List<Character> targets = self_.targetCharacter(character0_, character0_, map_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}
	
	@Test
	public void testTargetInvalidCharacter()
	{
		List<Character> targets = self_.targetCharacter(character0_, character1_, map_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
	
	@Test
	public void testTargetPosition()
	{
		List<Character> targets = self_.targetPosition(character0_, pose_, map_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
}