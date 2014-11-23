package jfws.cp.combat;

import java.util.HashMap;
import java.util.Map;

public class SkillMgr
{
	private Map<String,Skill> skills_ = new HashMap<>();
	
	public Skill createSkill(String name, int default_level)
	{
		if(name == null)
			throw new IllegalArgumentException("Argument name can not be null!");
		else if(name.isEmpty())
			throw new IllegalArgumentException("Argument name can not be empty!");
		
		Skill new_skill = new Skill(name, default_level);
		
		skills_.put(name, new_skill);
		
		return new_skill;
	}
	
	public Skill getSkill(String name)
	{
		if(name == null)
			throw new IllegalArgumentException("Argument name can not be null!");
		else if(name.isEmpty())
			throw new IllegalArgumentException("Argument name can not be empty!");
		
		return skills_.get(name);
	}
}
