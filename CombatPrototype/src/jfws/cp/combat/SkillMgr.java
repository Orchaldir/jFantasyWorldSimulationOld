package jfws.cp.combat;

import java.util.HashMap;
import java.util.Map;

public class SkillMgr
{
	private Map<String,Skill> skills_ = new HashMap<>();
	
	public Skill createSkill(String name, int default_level)
	{
		Skill new_skill = new Skill(name, default_level);
		
		skills_.put(name, new_skill);
		
		return new_skill;
	}
	
	public Skill getSkill(String name)
	{
		return skills_.get(name);
	}
}
