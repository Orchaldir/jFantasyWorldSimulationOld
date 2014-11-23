package jfws.cp.combat;

import java.util.HashMap;
import java.util.Map;

public class Character
{
	private Map<Skill,Integer> skills_ = new HashMap<>();
	
	public int getSkillLevel(Skill skill)
	{
		if(skills_.containsKey(skill))
			return skills_.get(skill);
		
		return skill.getDefaultLevel();
	}
	
	public void setSkillLevel(Skill skill, int level)
	{
		skills_.put(skill, level);
	}
}
