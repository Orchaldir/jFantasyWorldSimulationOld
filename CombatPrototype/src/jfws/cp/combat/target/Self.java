package jfws.cp.combat.target;

import java.util.ArrayList;
import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.GameMap;

public class Self implements TargetSelection
{
	@Override
	public List<Character> getTargets(Character user)
	{
		List<Character> targets = new ArrayList<>(1);
		
		targets.add(user);
		
		return targets;
	}

	@Override
	public List<Character> getTargets(Character user, Character target, GameMap map)
	{
		List<Character> targets = new ArrayList<>(1);
		
		if(user == target)
			targets.add(user);
		
		return targets;
	}
}
