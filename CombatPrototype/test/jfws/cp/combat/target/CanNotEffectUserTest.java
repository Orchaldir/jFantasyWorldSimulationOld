
package jfws.cp.combat.target;

import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.MockMap;
import jfws.cp.combat.map.Pose;
import org.junit.Test;
import static org.junit.Assert.*;

public class CanNotEffectUserTest
{
	private Character character0_ = new Character("Character0", 1);
	private Character character1_ = new Character("Character1", 1);
	private MockMap map_ = new MockMap(1, character0_);
	private Pose pose_ = new Pose(1);
	private Projectile projectile_ = new Projectile(4, true);
	private CanNotEffectUser filter_ = new CanNotEffectUser(projectile_);
	
	@Test
	public void testCanTargetUser()
	{
		assertTrue(filter_.canTargetUser(character0_, map_));
	}

	@Test
	public void testCanTargetCharacter()
	{
		assertTrue(filter_.canTargetCharacter(character0_, character0_, map_));
	}

	@Test
	public void testCanTargetPosition()
	{
		assertTrue(filter_.canTargetPosition(character0_, pose_, map_));
	}

	@Test
	public void testTargetUser()
	{
		List<Character> targets = filter_.targetUser(character0_, map_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}

	@Test
	public void testTargetCharacter()
	{
		List<Character> targets = filter_.targetCharacter(character0_, character1_, map_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character1_, targets.get(0));
	}
	
	@Test
	public void testTargetCharacterWithUser()
	{
		List<Character> targets = filter_.targetCharacter(character0_, character0_, map_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}

	@Test
	public void testTargetPosition()
	{
		map_.setCharacter(character1_, 0);
		List<Character> targets = filter_.targetPosition(character0_, pose_, map_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character1_, targets.get(0));
	}
	
	@Test
	public void testTargetPositionWithUser()
	{
		map_.setCharacter(character0_, 0);
		List<Character> targets = filter_.targetPosition(character0_, pose_, map_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
}