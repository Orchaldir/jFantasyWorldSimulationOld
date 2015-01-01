
package jfws.cp.combat.action;

import jfws.cp.combat.Character;
import jfws.cp.combat.map.Direction;
import jfws.cp.combat.map.GameMap1d;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MoveTest
{
	private int size_ = 10;
	private int pos_ = 4;
	private GameMap1d map_ = new GameMap1d(size_);
	private Move move_left_ = new Move("Left", Direction.WEST);
	private Move move_right_ = new Move("Right", Direction.EAST);
	private Character character0_ = new Character("Character0", 1);
	private Character character1_ = new Character("Character1", 1);
	
	@Before
	public void setUp()
	{
		map_ = new GameMap1d(size_);
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("Left", move_left_.getName());
		assertEquals("Right", move_right_.getName());
	}
	
	@Test
	public void testGetNewIndex()
	{
		map_.setCharacter(character0_, pos_);
		
		assertEquals(pos_-1, move_left_.getNewIndex(character0_, map_));
		assertEquals(pos_+1, move_right_.getNewIndex(character0_, map_));
	}
	
	@Test
	public void testIsFree()
	{
		assertFalse(move_left_.isFree(map_, -1));
		assertFalse(move_left_.isFree(map_, size_));
		
		for(int i = 0; i < size_; i++)
		{
			assertTrue(move_left_.isFree(map_, i));
		}
	}
	
	@Test
	public void testIsFreeWithObstacle()
	{
		map_.setCharacter(character0_, pos_);
		
		assertFalse(move_left_.isFree(map_, pos_));
	}

	@Test
	public void testIsPossible()
	{
		map_.setCharacter(character0_, pos_);
		
		assertTrue(move_left_.isPossible(character0_, map_));
		assertTrue(move_right_.isPossible(character0_, map_));
	}
	
	@Test
	public void testIsPossibleWithObstacle()
	{
		map_.setCharacter(character0_, pos_);
		map_.setCharacter(character1_, pos_+1);
		
		assertTrue(move_left_.isPossible(character0_, map_));
		assertFalse(move_right_.isPossible(character0_, map_));
		
		assertFalse(move_left_.isPossible(character1_, map_));
		assertTrue(move_right_.isPossible(character1_, map_));
	}

	@Test
	public void testHandle()
	{
		map_.setCharacter(character0_, pos_);
		move_right_.handle(character0_, map_);
		
		assertTrue(map_.isFree(pos_));
		assertTrue(map_.isFree(pos_-1));
		assertFalse(map_.isFree(pos_+1));
		assertTrue(map_.isFree(pos_+2));
	}
}