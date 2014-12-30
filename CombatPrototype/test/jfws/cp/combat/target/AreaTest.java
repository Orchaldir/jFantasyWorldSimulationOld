
package jfws.cp.combat.target;

import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.GameMap1d;
import jfws.cp.combat.map.Pose;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AreaTest
{
	private GameMap1d map_ = new GameMap1d(10);
	private Pose pose_ = new Pose(1);
	private Character character0_ = new Character("Character0", 1);
	private Character character1_ = new Character("Character1", 1);
	private Character character2_ = new Character("Character2", 1);
	private Character character3_ = new Character("Character3", 1);
	private Self self_ = new Self();
	private Area strike_ = new Area(self_, 4, false);
	private Area strike_self_ = new Area(self_, 4, true);
	
	@Before
	public void setUpClass()
	{
		map_.setCharacter(character0_, 6);
		map_.setCharacter(character1_, 5);
		map_.setCharacter(character2_, 7);
		map_.setCharacter(character3_, 0);
	}

	@Test
	public void testCanTargetWithUser()
	{
		assertTrue(strike_.canTarget(character0_, map_));
	}

	@Test
	public void testCanTargetWithUserAsTarget()
	{
		assertTrue(strike_.canTarget(character0_, character0_, map_));
	}
	
	@Test
	public void testCanTargetWithInvalidTarget()
	{
		assertFalse(strike_.canTarget(character0_, character1_, map_));
	}
	
	@Test
	public void testCanTargetWithPose()
	{
		assertFalse(strike_self_.canTarget(character0_, pose_, map_));
	}
	
	@Test
	public void testGetTargetsWithUser()
	{
		List<Character> targets = strike_self_.getTargets(character0_, map_);
		
		assertNotNull(targets);
		assertEquals(3, targets.size());
		
		assertTrue(targets.contains(character0_));
		assertTrue(targets.contains(character1_));
		assertTrue(targets.contains(character2_));
		assertFalse(targets.contains(character3_));
	}
	
	@Test
	public void testGetTargetsWithUserNotAllowed()
	{
		List<Character> targets = strike_.getTargets(character0_, map_);
		
		assertNotNull(targets);
		assertEquals(2, targets.size());
		
		assertFalse(targets.contains(character0_));
		assertTrue(targets.contains(character1_));
		assertTrue(targets.contains(character2_));
		assertFalse(targets.contains(character3_));
	}
	
	@Test
	public void testGetTargetsWithPose()
	{
		List<Character> targets = strike_self_.getTargets(character0_, pose_, map_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
}