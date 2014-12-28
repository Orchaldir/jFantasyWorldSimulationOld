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
	private boolean can_target_user_;
	
	public Strike(Range range, boolean can_target_user)
	{
		range_ = range;
		can_target_user_ = can_target_user;
	}
	
	public Strike(Range range)
	{
		this(range, false);
	}
	
	public Strike(int range, boolean can_target_user)
	{
		this(new Range(range), can_target_user);
	}
	
	public Strike(int range)
	{
		this(new Range(range), false);
	}
	
	@Override
	public boolean canTarget(Character user, GameMap map)
	{
		return can_target_user_;
	}

	@Override
	public boolean canTarget(Character user, Character target, GameMap map)
	{
		if(user == target)
		{
			return can_target_user_;
		}
		
		return range_.isInside(user, target, map);
	}
	
	@Override
	public boolean canTarget(Character user, Pose pose, GameMap map)
	{
		return range_.isInside(user, pose, map);
	}
	
	@Override
	public List<Character> getTargets(Character user, GameMap map)
	{
		List<Character> targets = new ArrayList<>(1);
		
		if(can_target_user_)
			targets.add(user);
		
		return targets;
	}

	@Override
	public List<Character> getTargets(Character user, Character target, GameMap map)
	{
		List<Character> targets = new ArrayList<>(1);
		
		if(canTarget(user, target, map))
			targets.add(user);
		
		return targets;
	}

	@Override
	public List<Character> getTargets(Character user, Pose pose, GameMap map)
	{
		Character possible_target = map.getCharacter(pose);
		
		if(possible_target == null)
			return Collections.<Character>emptyList();
		
		List<Character> targets = new ArrayList<>(1);
		
		if(canTarget(user, possible_target, map))
			targets.add(user);
		
		return targets;
	}
}
