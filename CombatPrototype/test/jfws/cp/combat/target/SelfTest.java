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
	public void testCanTargetWithUser()
	{
		assertTrue(self_.canTarget(character0_, map_));
	}

	@Test
	public void testCanTargetWithUserAsTarget()
	{
		assertTrue(self_.canTarget(character0_, character0_, map_));
	}
	
	@Test
	public void testCanTargetWithInvalidTarget()
	{
		assertFalse(self_.canTarget(character0_, character1_, map_));
	}
	
	@Test
	public void testCanTargetWithPose()
	{
		assertFalse(self_.canTarget(character0_, pose_, map_));
	}
	
	@Test
	public void testGetTargetsWithUser()
	{
		List<Character> targets = self_.getTargets(character0_, map_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}

	@Test
	public void testGetTargetsWithUserAsTarget()
	{
		List<Character> targets = self_.getTargets(character0_, character0_, map_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}
	
	@Test
	public void testGetTargetsWithInvalidTarget()
	{
		List<Character> targets = self_.getTargets(character0_, character1_, map_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
	
	@Test
	public void testGetTargetsWithPose()
	{
		List<Character> targets = self_.getTargets(character0_, pose_, map_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
}