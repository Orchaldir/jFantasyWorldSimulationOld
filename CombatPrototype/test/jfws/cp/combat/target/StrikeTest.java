
package jfws.cp.combat.target;

import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.Range;
import jfws.cp.combat.map.MockMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class StrikeTest
{
	private MockMap inside_ = new MockMap(1);
	private MockMap outside_ = new MockMap(10);
	private Character character0_ = new Character("Character0", 1);
	private Character character1_ = new Character("Character1", 1);
	private Strike strike_ = new Strike(new Range(4), false);
	private Strike strike_self_ = new Strike(new Range(4), true);
	
	@Test
	public void testGetTargetsWithUser()
	{
		List<Character> targets = strike_self_.getTargets(character0_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}
	
	@Test
	public void testGetTargetsWithUserNotAllowed()
	{
		List<Character> targets = strike_.getTargets(character0_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
	
	@Test
	public void testGetTargetsWithUserAsTarget()
	{
		List<Character> targets = strike_self_.getTargets(character0_, character0_, inside_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}
	
	@Test
	public void testGetTargetsWithUserAsTargetNotAllowed()
	{
		List<Character> targets = strike_.getTargets(character0_, character0_, inside_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
	
	@Test
	public void testGetTargetsWithTarget()
	{
		List<Character> targets = strike_self_.getTargets(character0_, character1_, inside_);
		
		assertNotNull(targets);
		assertEquals(1, targets.size());
		assertEquals(character0_, targets.get(0));
	}
	
	@Test
	public void testGetTargetsWithTargetTooFar()
	{
		List<Character> targets = strike_self_.getTargets(character0_, character1_, outside_);
		
		assertNotNull(targets);
		assertEquals(0, targets.size());
	}
}