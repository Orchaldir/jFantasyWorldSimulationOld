package jfws.cp.combat.target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.GameMap;
import jfws.cp.combat.map.Pose;

public class Self implements TargetSelection
{
	@Override
	public boolean canTargetUser(Character user, GameMap map)
	{
		return true;
	}

	@Override
	public boolean canTargetCharacter(Character user, Character target, GameMap map)
	{
		return user == target;
	}
	
	@Override
	public boolean canTargetPosition(Character user, Pose pose, GameMap map)
	{
		return false;
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
		
		if(user == target)
			targets.add(user);
		
		return targets;
	}
	
	@Override
	public List<Character> targetPosition(Character user, Pose pose, GameMap map)
	{
		return Collections.<Character>emptyList();
	}
}
