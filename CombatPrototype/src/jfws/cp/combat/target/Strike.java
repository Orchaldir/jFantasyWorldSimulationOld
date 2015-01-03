package jfws.cp.combat.target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.Range;
import jfws.cp.combat.map.GameMap;
import jfws.cp.combat.map.Pose;

public class Strike implements TargetSelection
{
	private Range range_;
	
	public Strike(Range range)
	{
		range_ = range;
	}
	
	public Strike(int range)
	{
		this(new Range(range));
	}
	
	@Override
	public boolean canTargetUser(Character user, GameMap map)
	{
		return true;
	}

	@Override
	public boolean canTargetCharacter(Character user, Character target, GameMap map)
	{
		return range_.isInside(user, target, map);
	}
	
	@Override
	public boolean canTargetPosition(Character user, Pose pose, GameMap map)
	{
		return range_.isInside(user, pose, map);
	}
	
	@Override
	public List<Character> targetUser(Character user, GameMap map)
	{
		List<Character> targets = new ArrayList<>(1);
		
		targets.add(user);
		
		return targets;
	}

	@Override
	public List<Character> targetCharacter(Character user, Character target, GameMap map)
	{
		List<Character> targets = new ArrayList<>(1);
		
		if(canTargetCharacter(user, target, map))
			targets.add(target);
		
		return targets;
	}

	@Override
	public List<Character> targetPosition(Character user, Pose pose, GameMap map)
	{
		Character possible_target = map.getCharacter(pose);
		
		if(possible_target == null)
			return Collections.<Character>emptyList();
		
		List<Character> targets = new ArrayList<>(1);
		
		if(canTargetCharacter(user, possible_target, map))
			targets.add(possible_target);
		
		return targets;
	}
}
