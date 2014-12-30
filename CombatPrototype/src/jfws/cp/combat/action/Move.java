package jfws.cp.combat.action;

import jfws.cp.combat.Character;
import jfws.cp.combat.map.Direction;
import jfws.cp.combat.map.GameMap;
import jfws.cp.combat.map.Pose;

public class Move extends Action
{
	private Direction direction_;
	
	public Move(String name, Direction direction)
	{
		super(name);
		
		direction_ = direction;
	}
	
	public boolean isFree(GameMap map, int index)
	{
		return map.isValid(index) && map .isFree(index);
	}

	public boolean isPossible(Character user, GameMap map)
	{
		int new_index = getNewIndex(user, map);
		
		return isFree(map, new_index);
	}
	
	public void handle(Character user, GameMap map)
	{
		int new_index = getNewIndex(user, map);
		
		if(!map.setCharacter(user, new_index))
		{
			throw new IllegalArgumentException("Character can not move!");
		}
	}
	
	private int getNewIndex(Character user, GameMap map)
	{
		Pose pose = map.getPose(user);
		
		if(pose == null)
		{
			throw new IllegalArgumentException("Character has no Pose!");
		}
		
		return map.getNeighborIndex(pose.index_, direction_);
	}
}
