package jfws.cp.combat.target;

import java.util.ArrayList;
import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.Range;
import jfws.cp.combat.map.GameMap;

public class Projectile implements TargetSelection
{
	private Range range_;
	private boolean can_target_user_;
	
	public Projectile(Range range, boolean can_target_user)
	{
		range_ = range;
		can_target_user_ = can_target_user;
	}
	
	public Projectile(Range range)
	{
		this(range, false);
	}
	
	public Projectile(int range, boolean can_target_user)
	{
		this(new Range(range), can_target_user);
	}
	
	public Projectile(int range)
	{
		this(new Range(range), false);
	}
	
	@Override
	public List<Character> getTargets(jfws.cp.combat.Character user)
	{
		List<Character> targets = new ArrayList<>(1);
		
		if(can_target_user_)
			targets.add(user);
		
		return targets;
	}

	@Override
	public List<Character> getTargets(jfws.cp.combat.Character user, jfws.cp.combat.Character target, GameMap map)
	{
		List<Character> targets = new ArrayList<>(1);
		
		if(user == target)
		{
			if(can_target_user_)
				targets.add(user);
		}
		else if(range_.isInside(user, target, map))
		{
			targets.add(user);
		}
		
		return targets;
	}
}
