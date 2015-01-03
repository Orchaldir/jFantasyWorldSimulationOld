package jfws.cp.combat.target;

import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.GameMap;
import jfws.cp.combat.map.Pose;

public class CanNotEffectUser implements TargetSelection
{
	private TargetSelection target_selection_;
	
	public CanNotEffectUser(TargetSelection target_selection)
	{
		target_selection_ = target_selection;
	}
	
	@Override
	public boolean canTargetUser(Character user, GameMap map)
	{
		return target_selection_.canTargetUser(user, map);
	}

	@Override
	public boolean canTargetCharacter(Character user, Character target, GameMap map)
	{
		return target_selection_.canTargetCharacter(user, target, map);
	}
	
	@Override
	public boolean canTargetPosition(Character user, Pose pose, GameMap map)
	{
		return target_selection_.canTargetPosition(user, pose, map);
	}
	
	@Override
	public List<Character> targetUser(Character user, GameMap map)
	{
		List<Character> targets = target_selection_.targetUser(user, map);
		
		targets.remove(user);
		
		return targets;
	}

	@Override
	public List<Character> targetCharacter(Character user, Character target, GameMap map)
	{
		List<Character> targets = target_selection_.targetCharacter(user, target, map);
		
		targets.remove(user);
		
		return targets;
	}
	
	@Override
	public List<Character> targetPosition(Character user, Pose pose, GameMap map)
	{
		List<Character> targets = target_selection_.targetPosition(user, pose, map);
		
		targets.remove(user);
		
		return targets;
	}
}
