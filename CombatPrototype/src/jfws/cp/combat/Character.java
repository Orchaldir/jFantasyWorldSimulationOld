package jfws.cp.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character
{
	private List<Integer> attributes_;
	private Map<Skill,Integer> skills_ = new HashMap<>();
	
	public Character(AttributeMgr attribute_mgr)
	{
		attributes_ = new ArrayList<>(attribute_mgr.getNumberOfAttributes());
		
		for(int i = 0; i < attribute_mgr.getNumberOfAttributes(); i++)
		{
			attributes_.add(0);
		}
	}
	
	public int getAttributeLevel(Attribute attribute)
	{
		return attributes_.get(attribute.getId());
	}
	
	public void setAttributeLevel(Attribute attribute, int level)
	{
		attributes_.set(attribute.getId(), level);
	}
	
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
