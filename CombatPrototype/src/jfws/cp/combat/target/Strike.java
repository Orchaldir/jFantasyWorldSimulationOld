package jfws.cp.combat.target;

import java.util.ArrayList;
import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.Range;
import jfws.cp.combat.map.GameMap;

public class Strike implements TargetSelection
{
	private Range range_;
	private boolean is_user_allowed_;
	
	public Strike(Range range, boolean is_user_allowed)
	{
		range_ = range;
		is_user_allowed_ = is_user_allowed;
	}
	
	@Override
	public List<Character> getTargets(Character user)
	{
		List<Character> targets = new ArrayList<>(1);
		
		if(is_user_allowed_)
			targets.add(user);
		
		return targets;
	}

	@Override
	public List<Character> getTargets(Character user, Character target, GameMap map)
	{
		List<Character> targets = new ArrayList<>(1);
		
		if(user == target)
		{
			if(is_user_allowed_)
				targets.add(user);
		}
		else if(range_.isInside(user, target, map))
		{
			targets.add(user);
		}
		
		return targets;
	}

}
