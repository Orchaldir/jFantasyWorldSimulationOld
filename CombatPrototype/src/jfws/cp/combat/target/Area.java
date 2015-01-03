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
	
	public Area(TargetSelection center_selection, Range radius)
	{
		center_selection_ = center_selection;
		radius_ = radius;
	}
	
	public Area(TargetSelection center_selection, int range)
	{
		this(center_selection, new Range(range));
	}
	
	@Override
	public boolean canTargetUser(Character user, GameMap map)
	{
		return center_selection_.canTargetUser(user, map);
	}

	@Override
	public boolean canTargetCharacter(Character user, Character target, GameMap map)
	{
		return center_selection_.canTargetCharacter(user, target, map);
	}
	
	@Override
	public boolean canTargetPosition(Character user, Pose pose, GameMap map)
	{
		return center_selection_.canTargetPosition(user, pose, map);
	}
	
	@Override
	public List<Character> targetUser(Character user, GameMap map)
	{
		return getTargetsAroundCenters(user, map, center_selection_.targetUser(user, map));
	}

	@Override
	public List<Character> targetCharacter(Character user, Character target, GameMap map)
	{
		return getTargetsAroundCenters(user, map, center_selection_.targetCharacter(user, target, map));
	}
	
	@Override
	public List<Character> targetPosition(Character user, Pose pose, GameMap map)
	{
		if(!center_selection_.canTargetPosition(user, pose, map))
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
		return radius_.isInside(user, center, possible_target, map);
	}
}
