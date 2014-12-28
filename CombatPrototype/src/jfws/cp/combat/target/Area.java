package jfws.cp.combat.target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.Range;
import jfws.cp.combat.map.GameMap;
import jfws.cp.combat.map.Pose;

public class Area implements TargetSelection
{
	private TargetSelection center_selection_;
	private Range radius_;
	private boolean can_target_user_;
	
	public Area(TargetSelection center_selection, Range radius, boolean can_target_user)
	{
		center_selection_ = center_selection;
		radius_ = radius;
		can_target_user_ = can_target_user;
	}
	
	public Area(TargetSelection center_selection, int range, boolean can_target_user)
	{
		this(center_selection, new Range(range), can_target_user);
	}
	
	@Override
	public boolean canTarget(Character user, GameMap map)
	{
		return center_selection_.canTarget(user, map);
	}

	@Override
	public boolean canTarget(Character user, Character target, GameMap map)
	{
		return center_selection_.canTarget(user, target, map);
	}
	
	@Override
	public boolean canTarget(Character user, Pose pose, GameMap map)
	{
		return center_selection_.canTarget(user, pose, map);
	}
	
	@Override
	public List<Character> getTargets(Character user, GameMap map)
	{
		return getTargetsAroundCenters(user, map, center_selection_.getTargets(user, map));
	}

	@Override
	public List<Character> getTargets(Character user, Character target, GameMap map)
	{
		return getTargetsAroundCenters(user, map, center_selection_.getTargets(user, target, map));
	}
	
	@Override
	public List<Character> getTargets(Character user, Pose pose, GameMap map)
	{
		if(!center_selection_.canTarget(user, pose, map))
			return Collections.<Character>emptyList();
		
		return getTargetsAroundCenter(user, map, pose);
	}
	
	private List<Character> getTargetsAroundCenters(Character user, GameMap map, List<Character> centers)
	{
		if(centers.size() == 1)
		{
			Pose center = map.getPose(centers.get(0));
			
			return getTargetsAroundCenter(user, map, center);
		}
		
		return Collections.<Character>emptyList();
	}
	
	private List<Character> getTargetsAroundCenter(Character user, GameMap map, Pose center)
	{
		List<Character> targets = new ArrayList<>(1);
		
		for(Character possible_target : map.getCharacters())
		{
			if(isTarget(user, map, center, possible_target))
				targets.add(possible_target);
		}
		
		return targets;
	}
	
	private boolean isTarget(Character user, GameMap map, Pose center, Character possible_target)
	{
		if(user == possible_target && !can_target_user_)
		{
			return false;
		}
		
		return radius_.isInside(user, center, possible_target, map);
	}
}
